package exercises;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetDataFromJSON {

    public static void main(String[] args) {
        try {
            // URL di esempio
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
            getInputStreamFromURL(url);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura dell'URL: " + e.getMessage());
        }
    }

    public static void getInputStreamFromURL(URL url) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            // Verifica e stampa il contenuto come JSON
            System.out.println("\n\nContenuto dell'URL:");
            String jsonString = content.toString();
            if (jsonString.startsWith("{")) { // Controllo semplice per JSON
                JSONObject jsonObject = new JSONObject(jsonString);
                System.out.println(jsonObject.toString(4)); // Stampa formattata
            } else {
                System.out.println(jsonString); // Stampa come testo normale
            }
        }
    }
}
