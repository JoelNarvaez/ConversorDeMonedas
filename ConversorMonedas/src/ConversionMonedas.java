import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversionMonedas {
    public HazConversion consultaConversion(String base, String aConversion, double cantidad) {
        URI direccion = URI.create("https://api.exchangerate.host/convert?access_key=566673dd73089c9d756ce855fc955fd2&from=" + base + "&to=" + aConversion + "&amount=" + cantidad);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HazConversion respuesta = new Gson().fromJson(response.body(), HazConversion.class);

            if (!respuesta.success()) {
                throw new RuntimeException("La API no respondió con éxito.");
            }

            return respuesta;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener la conversión.");
        }
    }
}
