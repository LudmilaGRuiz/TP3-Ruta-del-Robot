package utilidades;

import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Tablero;

public class JsonManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Tablero cargarTablero(String archivo) throws Exception {
        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, Tablero.class);
        }
    }
}
