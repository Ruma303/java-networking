package exercises.udp;

import java.io.IOException;
import java.net.*;

public class ClientUDP {
    public static void main(String[] args) {
        System.out.println("ClientUDP attivo.");

        udpClient();
    }

    public static void udpClient() {
        DatagramSocket socket = null;

        try {
            // Inizializzazione del socket sulla porta 9876
            // altrimenti lancerà errore NullPointerException
            socket = new DatagramSocket(9876);
            System.out.println("Socket UDP in ascolto sulla porta 9876...");

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // Imposta un timeout di 5 secondi per la ricezione del pacchetto
            // per evitare blocchi infiniti
            // Se non viene ricevuto alcun pacchetto entro il timeout
            // verrà sollevata un'eccezione SocketTimeoutException
            socket.setSoTimeout(5000);

            // Se la risposta non arriva in 5 secondi, il client si blocca
            socket.receive(packet); // ora socket è valido

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("\nMessaggio ricevuto: " + message);

            // Diagnostica
            System.out.println("\n\tIndirizzo del mittente: " + packet.getAddress());
            System.out.println("\tPorta del mittente: " + packet.getPort());
            System.out.println("\tLunghezza del messaggio: " + packet.getLength());
            System.out.println("\tLunghezza del buffer: " + packet.getData().length);

        } catch (SocketException e) {
            System.err.println("Errore relativo al socket: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Errore di I/O durante la ricezione del pacchetto UDP: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore generico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }

    }
}
