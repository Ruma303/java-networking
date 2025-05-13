package lessons.udp;

import java.net.*;

public class SocketServerUDP {

    public static void main(String[] args) {
        try {
            UdpEchoServer.createUdpServer();
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}

class UdpEchoServer {
    public static void createUdpServer() throws Exception {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] buffer = new byte[1024];
        System.out.println("UDP Echo Server Started\r\n");

        // Timeout di 10 secondi. Poi la connessione si chiude se non ci sono dati
        socket.setSoTimeout(10000);

        while (true) {
            try {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String received = new String(request.getData(), 0, request.getLength());
                System.out.println("Ricevuto: " + received);

                // Echo back: invia il pacchetto ricevuto al mittente
                DatagramPacket response = new DatagramPacket(
                        request.getData(), request.getLength(),
                        request.getAddress(), request.getPort()
                );
                socket.send(response);
            } catch (SocketTimeoutException e) {
                System.out.println("Timeout scaduto. Chiudo il server.");
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
            break;
        }
    }
}