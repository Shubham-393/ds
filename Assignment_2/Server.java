import java.rmi.*;

public class Server {

    public static void main(String[] args) {

        // In modern Java, stub classes are generated dynamically by the JVM, so the rmic tool is not required.

        try {
            // Create an instance of ServerImpl class
            ServerImpl serverimpl = new ServerImpl();
            // Bind the instance to the RMI registry with the name "Server"
            Naming.rebind("Server", serverimpl);
            System.out.println(" server started !");

        } catch (Exception e) {
            // Handle any exceptions occurred during the execution
            System.out.println("Eception Occurred at server!" + e.getMessage());
        }
    }

}
