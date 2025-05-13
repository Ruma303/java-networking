package lessons.udp;

import java.net.*;

public class SocketClientUDP {
    public static void main(String[] args) {
        try {
            UdpEchoClient.createUdpClient();
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
    }
}

class UdpEchoClient {
    public static void createUdpClient() throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String message = "Hello UDP dal client!";
        byte[] data = message.getBytes();

        // Invia il messaggio al server
        socket.connect(InetAddress.getByName("localhost"), 8888);
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.send(packet);

        // Ricezione della risposta
        byte[] buffer = new byte[1024];
        DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(inPacket);

        String echo = new String(inPacket.getData(), 0, inPacket.getLength());
        System.out.println("Echo dal server: " + echo);

        socket.close();
    }
}