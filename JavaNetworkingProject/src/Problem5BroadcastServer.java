import java.io.*;
import java.net.*;
import java.util.*;

public class Problem5BroadcastServer {
    private static List<Socket> clientSockets = new ArrayList<>();

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);
                System.out.println("New client connected!");
                new BroadcastHandler(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static class BroadcastHandler extends Thread {
        private Socket socket;

        public BroadcastHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Broadcasting message: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private void broadcastMessage(String message) {
            for (Socket client : clientSockets) {
                try {
                    PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                    writer.println("Server: " + message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
