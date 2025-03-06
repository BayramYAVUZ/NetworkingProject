import java.io.*;
import java.net.*;

public class Problem4MultiThreadedServer {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("Message from client: " + text);
                writer.println("Echo: " + text);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
