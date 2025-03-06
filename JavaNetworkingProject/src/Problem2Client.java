import java.io.*;
import java.net.*;

public class Problem2Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to the server.");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String message = reader.readLine();
            System.out.println("Message received: " + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

