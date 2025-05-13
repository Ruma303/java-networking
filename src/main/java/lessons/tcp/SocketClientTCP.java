package lessons.tcp;

import java.io.*;
import java.net.Socket;

public class SocketClientTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("SocketClientTCP attivo.");

        String host = "localhost"; // Indirizzo del server
        // createSocket1(host, 8081);

        ClientHandler clientHandler = new ClientHandler(new Socket("localhost", 8081));
        clientHandler.start(); // Avvia il thread per gestire il client
    }

    public static void createSocket1(String host, int port) {
        try (
                Socket clientSocket = new Socket(host, port);
                OutputStream outputStream = clientSocket.getOutputStream();
                InputStream inputStream = clientSocket.getInputStream();
        ) {
            outputStream.write("GET / HTTP/1.1\r\n".getBytes());
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);
            System.out.println("Risposta dal server: " + response);
        } catch (Exception e) {
            System.out.println("Errore durante la creazione del socket: " + e.getMessage());
        }
    }
}

// Gestire più client in parallelo
class ClientHandler extends Thread {
    private Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Diagnosi
            System.out.println("Locale: " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            System.out.println("Remoto: " + socket.getInetAddress() + ":" + socket.getPort());
            System.out.println("Connesso: " + socket.isConnected());
            System.out.println("Chiuso: " + socket.isClosed());
            System.out.println("Input shutdown: " + socket.isInputShutdown());
            System.out.println("Output shutdown: " + socket.isOutputShutdown());

            // Configurazioni
            socket.setSoTimeout(5000); // timeout di 5 secondi per operazioni di lettura
            socket.setTcpNoDelay(true); // disabilita il Nagle's algorithm

            String input;
            while ((input = in.readLine()) != null) {
                out.println("Echo: " + input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
