import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server started...");

        Socket socket = server.accept(); // wait for client
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String msg = in.readLine(); // receive
        System.out.println("Client: " + msg);

        out.println("Received"); // send reply

        socket.close();
        server.close();
    }
}