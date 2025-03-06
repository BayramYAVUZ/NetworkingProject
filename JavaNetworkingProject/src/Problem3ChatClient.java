import java.io.*;
import java.net.*;

public class Problem3ChatClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;
            System.out.println(reader.readLine()); // Initial greeting

            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
                System.out.print("You: ");
                writer.println(consoleReader.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

