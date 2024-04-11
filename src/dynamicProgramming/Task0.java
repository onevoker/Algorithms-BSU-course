package dynamicProgramming;

import java.io.*;
import java.util.*;

public class Task0 {

    public static void main(String[] args) {
        File input = new File("in.txt");
        File output = new File("out.txt");
        Set<Integer> set = readFromFile(input);
        long sum = 0;

        for (Integer num : set) {
            sum += num;
        }

        writeToFile(sum, output);
    }

    private static Set<Integer> readFromFile(File file) {
        Set<Integer> set = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                set.add(Integer.parseInt(line));
            }

            return set;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(long num, File file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(String.valueOf(num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}