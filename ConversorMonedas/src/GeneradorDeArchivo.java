import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDeArchivo {

    private static final String RUTA_ARCHIVO = "Historialito.json";

    public void guardarJson(Conversion conversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Conversion> historial = new ArrayList<>();

        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Conversion>>() {}.getType();
            historial = gson.fromJson(reader, tipoLista);

            if (historial == null) {
                historial = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        historial.add(conversion);

        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(historial, writer);
        }
    }

    public void verHistorial() throws IOException {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Conversion>>() {}.getType();
            List<Conversion> historial = new Gson().fromJson(reader, tipoLista);

            if (historial == null || historial.isEmpty()) {
                System.out.println("No hay conversiones registradas.");
            } else {
                for (Conversion c : historial) {
                    System.out.println(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
