import java.io.*;
import java.net.*;

public class Problem6FileTransferClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        File file = new File("file_to_send.txt"); // The file to send

        try (Socket socket = new Socket(host, port);
             FileInputStream fileInput = new FileInputStream(file);
             OutputStream output = socket.getOutputStream()) {

            System.out.println("Sending file...");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            System.out.println("File sent successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
