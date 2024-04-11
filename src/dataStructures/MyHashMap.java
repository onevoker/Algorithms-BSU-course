package dataStructures;

import java.io.*;
import java.util.Arrays;

public class MyHashMap {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
        String[] mcn = bufferedReader.readLine().split(" ");
        int m = Integer.parseInt(mcn[0]);
        int c = Integer.parseInt(mcn[1]);
        int n = Integer.parseInt(mcn[2]);

        int[] table = new int[m];
        Arrays.fill(table, -1);

        for (int i = 0; i < n; ++i) {
            int x = Integer.parseInt(bufferedReader.readLine());

            for (int j = 0; j < m; ++j) {
                int idx = (x % m + c * j) % m;
                if (table[idx] == -1) {
                    table[idx] = x;
                    break;
                }
                if (table[idx] == x) {
                    break;
                }
            }
        }
        writeToFile(table);
    }

    private static void writeToFile(int[] arr) {
        File output = new File("output.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            StringBuilder sb = new StringBuilder();
            for (int x : arr) {
                sb.append(x).append(" ");
            }
            bufferedWriter.write(String.valueOf(sb));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
