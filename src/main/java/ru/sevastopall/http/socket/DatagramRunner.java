package ru.sevastopall.http.socket;

import java.io.IOException;
import java.net.*;

public class DatagramRunner {
    public static void main(String[] args) throws IOException {
        var inetAddress = Inet4Address.getByName("localhost");
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            byte[] bytes = "Hello from UDP client".getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(packet);
        }
    }
}
