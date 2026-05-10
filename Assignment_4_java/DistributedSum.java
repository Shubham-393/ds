import mpi.*;

public class DistributedSum {

    public static void main(String[] args) throws MPIException, InterruptedException {

        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] array = {1,2,3,4,5,6,7,8,9,10};

        int n = array.length;

        // Equal elements for each process
        int local_n = n / size;

        int start = rank * local_n;
        int end = start + local_n;

        int local_sum = 0;

        // Each process calculates local sum
        for (int i = start; i < end; i++) {
            local_sum += array[i];
        }

        // Last process adds remaining elements
        if (rank == size - 1) {

            for (int i = end; i < n; i++) {
                local_sum += array[i];
            }
        }

        System.out.println("Process " + rank +
                " Local Sum = " + local_sum);

        // Gather all local sums
        int[] global_sums = new int[size];
        
        MPI.COMM_WORLD.Gather(
                new int[]{local_sum}, 0, 1, MPI.INT,
                global_sums, 0, 1, MPI.INT,
                0);

        // Root process calculates final sum
        if (rank == 0) {

            int total = 0;

            System.out.println("\nIntermediate Sums:");
            
            for (int i = 0; i < size; i++) {

                System.out.println("Process " + i +
                        " = " + global_sums[i]);

                total += global_sums[i];
            }

            System.out.println("\nTotal Sum = " + total);
        }

        MPI.Finalize();
    }
}