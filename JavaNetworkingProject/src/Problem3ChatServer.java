import java.io.*;
import java.net.*;

public class Problem3ChatServer {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage;
            writer.println("Hello!");
            while ((clientMessage = reader.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                writer.println("Server: " + clientMessage); // Echo back
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

