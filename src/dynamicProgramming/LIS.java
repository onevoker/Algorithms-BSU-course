package dynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {
    public static void main(String[] args) {
        List<Integer> nums = readFromFile();
        int n = nums.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        List<Integer> inIndexes = new ArrayList<>();

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, n - 1, num);

            if (i < 0) {
                i = -(i + 1);
            }

            inIndexes.add(i);
            dp[i] = num;
        }

        int max = inIndexes.stream().max(Integer::compare).orElse(0) + 1;
        writeToFile(max);
    }

    private static List<Integer> readFromFile() {
        List<Integer> res = new ArrayList<>();
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            bufferedReader.readLine();
            String[] nums = bufferedReader.readLine().split(" ");

            for (String num : nums) {
                res.add(Integer.parseInt(num));
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