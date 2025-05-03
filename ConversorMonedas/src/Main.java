import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversion conv = new Conversion();
        ConversionMonedas monedas = new ConversionMonedas();
        GeneradorDeArchivo historial = new GeneradorDeArchivo();
        System.out.println("""
                Elaborado por: Joel Narváez Martinez
                
                Instrucciones de uso:
                1. Primero, el programa pedirá la **cantidad** que deseas convertir a otro tipo de moneda.
                2. Luego, solicitará la moneda base. Si no estás seguro del nombre exacto, puedes escribir solo las **iniciales** o una **parte del nombre**.
                3. A continuación, pedirá la **moneda de destino**. Aplica el mismo criterio: escribe una parte del nombre o las iniciales si no lo sabes completo.
                4. Después de cada conversión, se te preguntará si deseas **ver el historial de conversiones**:
                   - Escribe `'s'` para sí.
                   - Escribe `'n'` para no.
                   - El historial se almacena en un archivo `.json` que es leído e interpretado para mostrar las consultas realizadas a la API.
                5. Finalmente, podrás indicar si deseas realizar otra conversión. En caso afirmativo, se reiniciará el proceso.
                """);

        System.out.println("\n== Bienvenidos al conversor de monedas ==");

        while (true) {
            // Leer cantidad a convertir
            while (true) {
                try {
                    System.out.println("Dame la cantidad que quieres convertir:");
                    conv.setAmount(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor ingresa un número válido.");
                    scanner.nextLine();
                }
            }

            RespuestaMonedas respuesta = obtenerSimbolos();
            if (!respuesta.isSuccess()) {
                System.out.println("No se pudo cargar la lista de monedas.");
                return;
            }

            // Moneda base
            conv.setFrom(null);
            while (conv.getFrom() == null) {
                System.out.print("Ingrese el nombre de la moneda base: ");
                String nombreBase = scanner.nextLine();
                boolean encontrada = false;

                for (Map.Entry<String, String> entry : respuesta.getCurrencies().entrySet()) {
                    if (entry.getValue().equalsIgnoreCase(nombreBase)) {
                        conv.setFrom(entry.getKey());
                        encontrada = true;
                        break;
                    }
                }
                if (!encontrada) {
                    sugerencias(respuesta, null, nombreBase);
                }
            }

            // Moneda a convertir
            conv.setTo(null);
            while (conv.getTo() == null) {
                System.out.print("Ingrese el nombre de la moneda a la que quieres convertir: ");
                String nombreAConvertir = scanner.nextLine();
                boolean encontrada = false;

                for (Map.Entry<String, String> entry : respuesta.getCurrencies().entrySet()) {
                    if (entry.getValue().equalsIgnoreCase(nombreAConvertir)) {
                        conv.setTo(entry.getKey());
                        encontrada = true;
                        break;
                    }
                }

                if (!encontrada) {
                    sugerencias(respuesta, null, nombreAConvertir);
                }
            }

            // Hacer la conversión
            HazConversion resultado = monedas.consultaConversion(conv.getFrom(), conv.getTo(), conv.getAmount());
            conv.setResult(resultado.result());
            System.out.println(resultado);

            // Guardar la conversion en el historial
            try {
                historial.guardarJson(conv);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

            // Preguntar si quiere ver el historial
            String opcHist;
            do {
                System.out.print("¿Deseas ver el historial? (s/n): ");
                opcHist = scanner.nextLine().trim().toLowerCase();
                if (!opcHist.equals("s") && !opcHist.equals("n")) {
                    System.out.println("Opcion no valida. Por favor ingresa 's' o 'n'.");
                }
            } while (!opcHist.equals("s") && !opcHist.equals("n"));

            if (opcHist.equals("s")) {
                try {
                    historial.verHistorial();
                } catch (IOException e) {
                    System.out.println("No se pudo cargar el historial.");
                }
            }

            // Preguntar si desea otra conversión
            String opcion;
            do {
                System.out.print("¿Deseas hacer otra conversion? (s/n): ");
                opcion = scanner.nextLine().trim().toLowerCase();
                if (!opcion.equals("s") && !opcion.equals("n")) {
                    System.out.println("Opcion no valida. Por favor ingresa 's' o 'n'.");
                }
            } while (!opcion.equals("s") && !opcion.equals("n"));

            if (!opcion.equals("s")) {
                System.out.println("¡Gracias por usar el conversor de monedas!");
                break;
            }
        }
        scanner.close();
    }

    public static RespuestaMonedas obtenerSimbolos() {
        URI direccion = URI.create("https://api.exchangerate.host/list?access_key=566673dd73089c9d756ce855fc955fd2");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), RespuestaMonedas.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudieron obtener los símbolos.");
        }
    }

    public static List<String> buscarPorNombre(RespuestaMonedas respuesta, String entrada) {
        List<String> coincidencias = new ArrayList<>();
        for (Map.Entry<String, String> entry : respuesta.getCurrencies().entrySet()) {
            String nombre = entry.getValue().toLowerCase();
            if (nombre.contains(entrada.toLowerCase())) {
                coincidencias.add(entry.getValue() + " (" + entry.getKey() + ")");
            }
        }
        return coincidencias;
    }

    public static void sugerencias(RespuestaMonedas respuesta, String codigoEncontrado, String conv) {
        if (codigoEncontrado == null || codigoEncontrado.isEmpty()) {
            System.out.println("No se encontró ninguna moneda con ese nombre exacto.");

            List<String> sugerencias = buscarPorNombre(respuesta, conv);

            if (sugerencias.isEmpty()) {
                System.out.println("No se encontraron coincidencias.");
            } else {
                System.out.println("¿Querías decir alguna de estas?");
                int i = 1;
                for (String s : sugerencias) {
                    System.out.println(i++ + ". " + s);
                }
            }
        }
    }
}
