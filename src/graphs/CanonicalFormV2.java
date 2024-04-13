package graphs;

import java.io.*;
import java.util.Arrays;

public class CanonicalFormV2 {
    private static final String in = "input.txt";
    private static final String out = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[] p = new int[n];
            int[][] adjacencyMatrix = new int[n][n];

            for (int i = 0; i < n; ++i) {
                String[] s = bufferedReader.readLine().split(" ");
                for (int j = 0; j < n; ++j) {
                    adjacencyMatrix[i][j] = Integer.parseInt(s[j]);
                }
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (adjacencyMatrix[i][j] == 1) {
                        p[j] = i + 1;
                    }
                }
            }
            String[] pStr = Arrays.stream(p).mapToObj(String::valueOf).toArray(String[]::new);
            bufferedWriter.write(String.join(" ", pStr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
