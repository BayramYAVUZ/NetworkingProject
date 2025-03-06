import java.io.*;
import java.net.*;

public class Problem7TimeoutServer {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000); // 10 seconds timeout
            System.out.println("Server is listening on port " + port + "...");

            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
            } catch (SocketTimeoutException ex) {
                System.out.println("Connection timed out after 10 seconds.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
