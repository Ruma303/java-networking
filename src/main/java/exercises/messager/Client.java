package exercises.messager;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        System.out.println("Client avviato...");

        try (
                Socket socket = new Socket("localhost", 12345);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();

                writer.write(message);
                writer.newLine();
                writer.flush();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnessione dal client...");
                    break;
                }

                String response = reader.readLine();
                if (response == null) {
                    System.out.println("Server disconnesso.");
                    break;
                }

                System.out.println("Il server risponde: " + response);
            }

        } catch (IOException e) {
            System.out.println("Errore client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}