package lessons;
import java.util.ArrayList;
import java.net.*;

public class Addesses {
    public static void main(String[] args) throws UnknownHostException {

        // Informazioni su un host locale
        InetAddress locale = InetAddress.getLocalHost();
        System.out.println("Hostname locale: " + locale.getHostName());
        System.out.println("IP locale: " + locale.getHostAddress());
        System.out.println("Nome Canonico: " + locale.getCanonicalHostName());

        // Informazioni su un host remoto
        InetAddress remoto = InetAddress.getByName("www.google.com");
        System.out.println("Hostname: " + remoto.getHostName());
        System.out.println("IP: " + remoto.getHostAddress());

        // Ottenere tutti gli IP da un host
        InetAddress[] indirizzi = InetAddress.getAllByName("www.nba.com");
        for (InetAddress addr : indirizzi) {
            System.out.println("IP NBA: " + addr.getHostAddress());
        }

        // Creare un IP da array di byte
        byte[] indirizzoIp = { (byte)192, (byte)168, 1, 100 };
        InetAddress ip = InetAddress.getByAddress(indirizzoIp);
        System.out.println("Indirizzo IP: " + ip.getHostAddress());

        // IPv4
        InetAddress ipv4 = InetAddress.getByName("www.google.com");
        System.out.println("IPv4: " + ipv4.getHostAddress());

        // IPv6
        InetAddress ipv6 = InetAddress.getByName("ipv6.google.com");
        System.out.println("IPv6: " + ipv6.getHostAddress());

        // Verifica se l'indirizzo è un loopback
        if (locale.isLoopbackAddress()) {
            System.out.println("L'indirizzo è un loopback");
        } else {
            System.out.println("L'indirizzo non è un loopback");
        }

        // Verifica se l'indirizzo è un multicast
        InetAddress multicast = InetAddress.getByName("230.0.0.1");
        if (multicast.isMulticastAddress()) {
            System.out.println("L'indirizzo è un multicast");
        } else {
            System.out.println("L'indirizzo non è un multicast");
        }

        // Verifica se l'indirizzo è un indirizzo di rete
        InetAddress google = InetAddress.getByName("8.8.8.8");
        System.out.println("Nome Canonico: " + google.getCanonicalHostName());
    }
}