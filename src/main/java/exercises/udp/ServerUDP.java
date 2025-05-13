package exercises.udp;

import java.io.IOException;
import java.net.*;

public class ServerUDP {
    public static void main(String[] args) {
        System.out.println("ServerUDP attivo.");
        udpServer();
    }

    public static void udpServer() {
        DatagramSocket socket = null;
        try {
            // Socket effimero (porta assegnata automaticamente)
            socket = new DatagramSocket();

            // Messaggio da inviare al client
            byte[] data = "Messaggio da server UDP".getBytes();

            InetAddress address = InetAddress.getByName("localhost");
            int port = 9876;

            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            System.err.println("Errore durante l'invio del pacchetto UDP: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
