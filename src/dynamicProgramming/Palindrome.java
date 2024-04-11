package dynamicProgramming;

import java.io.*;

public class Palindrome {

    public static void main(String[] args) {
        File input = new File("in.txt");
        File output = new File("out.txt");
        String str = readFromFile(input);
        String reversedStr = new StringBuilder(str).reverse().toString();
        int length = str.length();
        int[][] dp = new int[length + 1][length + 1];

        for (int i = 1; i <= length; ++i) {
            for (int j = 1; j <= length; ++j) {
                if (str.charAt(i - 1) == reversedStr.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = length, j = length;
        StringBuilder sb = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str.charAt(i - 1) == reversedStr.charAt(j - 1)) {
                sb.append(str.charAt(i - 1));
                --i;
                --j;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                --i;
            } else {
                --j;
            }
        }

        int palindromeLength = sb.length();
        String palindrome = sb.toString();

        writeToFile(palindromeLength, palindrome, output);
    }

    private static String readFromFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(long num, String str, File file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(String.valueOf(num));
            bufferedWriter.newLine();
            bufferedWriter.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
