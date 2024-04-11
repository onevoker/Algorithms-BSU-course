package dataStructures;

import java.io.*;

public class IsHeap {
    public static void main(String[] args) {
        int[] arr = readFromFile();
        int n = arr.length - 1;

        for (int i = 1; i <= n / 2; ++i) {
            if (2 * i <= n && arr[i] > arr[2 * i]) {
                writeToFile("No");
                return;
            }
            if (2 * i + 1 <= n && arr[i] > arr[2 * i + 1]) {
                writeToFile("No");
                return;
            }
        }
        writeToFile("Yes");
    }

    private static int[] readFromFile() {
        int[] res;
        File input = new File("in.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            int n = Integer.parseInt(bufferedReader.readLine());
            res = new int[n + 1];
            String[] arrStr = bufferedReader.readLine().split(" ");
            for (int i = 1; i <= n; ++i) {
                res[i] = Integer.parseInt(arrStr[i - 1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private static void writeToFile(String res) {
        File output = new File("out.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            bufferedWriter.write(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
