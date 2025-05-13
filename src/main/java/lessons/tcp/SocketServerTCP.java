package lessons.tcp;

import java.io.*;
import  java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTCP {
    public static void main(String[] args) {
        System.out.println("SocketServerTCP attivo.");

        // createSocket1(port);
        createSocket2(8081);
    }

    public static void createSocket1(int port) {
        try (
                ServerSocket serverSocket = new java.net.ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String request = new String(buffer, 0, bytesRead);
            System.out.println("Richiesta ricevuta: " + request);
            outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        } catch (Exception e) {
            System.out.println("Errore durante la creazione del socket: " + e.getMessage());
        }
    }

    public static void createSocket2(int port) {
       ServerSocket server = null;

       try {
        System.out.println("Server in ascolto sulla porta " + port);
        server = new ServerSocket(port);
        // accetta le connessioni in un ciclo infinito
       while (true) {
            Socket client = server.accept(); // blocca finché arriva una connessione
            new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                    String line = in.readLine();
                    out.println("Ricevuto: " + line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
       } catch (IOException e) {
              e.printStackTrace();
         } finally {
              try {
                server.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
       }
    }
}
