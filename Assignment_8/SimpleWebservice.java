import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class SimpleWebservice {

    public static void main(String[] args) throws Exception {
        // Create HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Create endpoint /add
        server.createContext("/add", (exchange) -> {
            // Get query string: ?a=5&b=3
            String query = exchange.getRequestURI().getQuery();
            
            // Parse parameters
            String[] params = query.split("&");
            int a = Integer.parseInt(params[0].split("=")[1]);
            int b = Integer.parseInt(params[1].split("=")[1]);
            
            // Calculate
            int result = a + b;
            String response = "Result = " + result;
            
            // Send response
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });
        
        // Start server
        server.start();
        System.out.println("Server running at http://localhost:8000");
        System.out.println("Try: http://localhost:8000/add?a=10&b=20");
    }
}