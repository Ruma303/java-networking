package exercises.messager;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server avviato...");

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("In ascolto sulla porta 12345...");

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        Scanner consoleInput = new Scanner(System.in)
                ) {
                    System.out.println("Connessione accettata da: " + socket.getInetAddress());

                    while (true) {
                        String messageFromClient = reader.readLine();
                        if (messageFromClient == null) {
                            System.out.println("Client disconnesso.");
                            break;
                        }

                        System.out.println("Client: " + messageFromClient);
                        if (messageFromClient.equalsIgnoreCase("exit")) {
                            System.out.println("Chiusura comunicazione...");
                            break;
                        }

                        System.out.print("Server: ");
                        String serverResponse = consoleInput.nextLine();

                        writer.write(serverResponse);
                        writer.newLine();
                        writer.flush();
                    }

                } catch (IOException e) {
                    System.out.println("Errore durante la comunicazione con il client: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Errore nell'apertura del server socket: " + e.getMessage());
            e.printStackTrace();
        }
    }
}