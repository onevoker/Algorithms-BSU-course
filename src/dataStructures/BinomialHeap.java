package dataStructures;

import java.io.*;

public class BinomialHeap {
    public static void main(String[] args) {
        long n = readFromFile();
        long k = 0;
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            if ((n % 2) == 1) {
                res.append(k).append(" ");
            }
            n /= 2;
            ++k;
        }

        writeToFile(res);
    }

    private static long readFromFile() {
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            return Long.parseLong(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(StringBuilder res) {
        File output = new File("out.txt");
        String[] resArr = res.toString().split(" ");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            for (String s : resArr) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
