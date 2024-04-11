package dynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public static void main(String[] args) {
        List<Integer> dim = readFromFile();
        int n = dim.size();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = 0;
            }
        }

        for (int l = 2; l < n; ++l) {
            for (int i = 1; i < n - l + 1; ++i) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    int cost = dp[i][k] + dp[k + 1][j] + dim.get(i - 1) * dim.get(k) * dim.get(j);
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        writeToFile(dp[1][n - 1]);
    }

    private static List<Integer> readFromFile() {
        List<Integer> res = new ArrayList<>();
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line = bufferedReader.readLine();
            int i = Integer.parseInt(line);
            while (i != 0) {
                String[] firstLine = bufferedReader.readLine().split(" ");
                if (i == Integer.parseInt(line)) {
                    res.add(Integer.parseInt(firstLine[0]));
                }
                res.add(Integer.parseInt(firstLine[1]));
                --i;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private static void writeToFile(long num) {
        File output = new File("out.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            bufferedWriter.write(String.valueOf(num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
