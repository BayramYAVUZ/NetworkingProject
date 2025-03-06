import java.io.*;
import java.net.*;

public class Problem1Server {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Welcome to the Server!");
            System.out.println("Message sent: Welcome to the Server!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
