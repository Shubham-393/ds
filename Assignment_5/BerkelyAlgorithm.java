import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BerkeleyAlgorithm {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] clocks = new String[n + 1];

        // Input
        for (int i = 0; i <= n; i++) {
            if (i == 0)
                System.out.print("Enter Server Time (HH:mm): ");
            else
                System.out.print("Enter Client " + i + " Time (HH:mm): ");

            clocks[i] = sc.nextLine();
        }

        System.out.println("\nBefore Synchronization");
        display(clocks);

        synchronize(clocks);

        System.out.println("\nAfter Synchronization");
        display(clocks);

        sc.close();
    }

    static void synchronize(String[] clocks) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        long[] time = new long[clocks.length];

        // Convert time into milliseconds
        for (int i = 0; i < clocks.length; i++) {
            time[i] = sdf.parse(clocks[i]).getTime();
        }

        long serverTime = time[0];
        long totalDiff = 0;

        // Find difference with server
        for (int i = 0; i < time.length; i++) {
            totalDiff += (time[i] - serverTime);
        }

        long avg = totalDiff / time.length;

        // Adjust clocks
        for (int i = 0; i < time.length; i++) {
            // time[i] = time[i] + (avg - (time[i] - serverTime));

            long diff = time[i] - serverTime; //clnt1 - server
            long correction = avg - diff;
            time[i] += correction;

            clocks[i] = sdf.format(new Date(time[i]));
        }
    }

    static void display(String[] clocks) {

        System.out.println("Server Clock : " + clocks[0]);

        for (int i = 1; i < clocks.length; i++) {
            System.out.println("Client " + i + " Clock : " + clocks[i]);
        }
    }
}