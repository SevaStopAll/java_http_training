package ru.sevastopall.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             InputStream inputStream = new DataInputStream(socket.getInputStream());
             OutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            //step 1 handle request
            System.out.println("Request" + new String(inputStream.readNBytes(400)));

            //step2 handle response
            byte[] body = Files.readAllBytes(Path.of("resources", "example.html"));
            byte[] headers = """
                    HTTP 1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException e) {
            //TODO: 7/12/23 log error message
            e.printStackTrace();
        }
    }
}
