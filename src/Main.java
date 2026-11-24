import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Random rng = new Random();
        double avgHeight = 0;
        double avgNodesPerFind = 0;
        int N;
        int K;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of random numbers N, then range of numbers 1-K");

        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intArray = Arrays.stream(inputs)
                .mapToInt(Integer::parseInt)
                .toArray();

        K = intArray[1];
        N = intArray[0];

        for (int t = 0; t < 10; t++) {
            BinarySearchTree tree = new BinarySearchTree();
            int[] values = new int[N];

            for (int i = 0; i < N; i++) {
                values[i] = rng.nextInt(K) + 1; // 1..k inclusive
                tree.insert(values[i]);
            }

            long totalNodes = 0;
            for (int v : values) {
                totalNodes += tree.findCount(v);
            }

            avgHeight += tree.height();
            avgNodesPerFind += totalNodes / (double) N;
        }

        System.out.printf("N = %d, range 1..%d, %d trials%n", N, K, 10);
        System.out.printf("  Average height:         %.2f%n", avgHeight / 10);
        System.out.printf("  Average nodes per find: %.2f%n", avgNodesPerFind / 10);
    }
}
