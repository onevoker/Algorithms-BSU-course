package dynamicProgramming;

import java.io.*;

public class LD {

    public static void main(String[] args) {
        String[] input = readFromFile();
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        int z = Integer.parseInt(input[2]);
        String a = input[3];
        String b = input[4];
        int aSize = a.length();
        int bSize = b.length();
        int[][] dp = new int[aSize + 1][bSize + 1];

        for (int i = 0; i <= aSize; ++i) {
            dp[i][0] = i * x;
        }

        for (int i = 0; i <= bSize; ++i) {
            dp[0][i] = i * y;
        }

        for (int i = 1; i <= aSize; ++i) {
            for (int j = 1; j <= bSize; ++j) {
                int min1 = Math.min(dp[i - 1][j] + x, dp[i][j - 1] + y);
                int f = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                int temp = dp[i - 1][j - 1] + f * z;
                dp[i][j] = Math.min(min1, temp);
            }
        }

        writeToFile(dp[aSize][bSize]);
    }

    private static String[] readFromFile() {
        File input = new File("in.txt");
        String[] in = new String[5];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            for (int i = 0; i < 5; ++i) {
                in[i] = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return in;
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
