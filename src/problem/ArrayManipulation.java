package problem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Array Manipulation
 *
 * Created by denny on 2018. 8. 7..
 */
public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        int [][] closedArray = new int[queries.length][];
        for(int i = 0; i < queries.length; i++)
            closedArray[i] = queries[i].clone();

        Arrays.sort(queries, Comparator.comparing(query -> query[0]));
        Arrays.sort(closedArray, Comparator.comparing(query -> query[1]));

        long max = 0L;
        int closedIndex = 0;
        long currentSum = 0L;
        for (int[] query : queries) {
            currentSum += query[2];
            while(closedArray[closedIndex][1] < query[0]) {
                currentSum -= closedArray[closedIndex][2];
                closedIndex++;
            }
            max = currentSum > max ? currentSum : max;
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
