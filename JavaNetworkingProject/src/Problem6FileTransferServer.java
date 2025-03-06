import java.io.*;
import java.net.*;

public class Problem6FileTransferServer {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            InputStream input = socket.getInputStream();
            FileOutputStream fileOutput = new FileOutputStream("received.txt");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }
            fileOutput.close();
            System.out.println("File received and saved as 'received.txt'.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
