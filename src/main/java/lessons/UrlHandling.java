package lessons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlHandling {
    public static void main(String[] args) throws Exception {

        // Creazione di un oggetto URL
        URL url = new URL("http://example.com:8080/docs/index.html?query=test#section1");

        // Ottenere informazioni sull'URL
        System.out.println("URL completo: " + url.toString());
        System.out.println("Protocollo: " + url.getProtocol());
        System.out.println("Host + porta: " + url.getAuthority());
        System.out.println("Host: " + url.getHost());
        System.out.println("Porta: " + url.getPort());
        System.out.println("Percorso: " + url.getPath());
        System.out.println("Query: " + url.getQuery());
        System.out.println("Nome file: " + url.getFile());
        System.out.println("Frammento: " + url.getRef());

        // Stabilire una connessione all'URL
        URLConnection conn = url.openConnection();
        conn.connect();

        // Ottenere informazioni sulla connessione
        System.out.println("Tipo di contenuto: " + conn.getContentType());
        System.out.println("Lunghezza del contenuto: " + conn.getContentLength());
    }
}
