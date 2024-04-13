package graphs;

import java.io.*;

public class CanonicalForm {
    private static final String in = "input.txt";
    private static final String out = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            int[] p = new int[n];

            for (int i = 0; i < n - 1; ++i) {
                String[] in = bufferedReader.readLine().split(" ");
                int u = Integer.parseInt(in[0]);
                int v = Integer.parseInt(in[1]);
                p[v - 1] = u;
            }

            for (int val : p) {
                bufferedWriter.write(val + " ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
