package graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {
    public static void main(String[] args) {
        FileWorkerUtil.readFromFile();

        int n = FileWorkerUtil.n;
        int m = FileWorkerUtil.m;
        List<Integer> input = FileWorkerUtil.in;

        int[][] adjacencyMatrix = new int[n][n];

        int i = 0;
        while (i < m * 2) {
            int u = input.get(i) - 1;
            int v = input.get(i + 1) - 1;

            adjacencyMatrix[u][v] = 1;
            adjacencyMatrix[v][u] = 1;

            i += 2;
        }

        FileWorkerUtil.writeToFile(adjacencyMatrix);
    }
}


class FileWorkerUtil {
    static int n;
    static int m;
    static List<Integer> in;

    public static void readFromFile() {
        in = new ArrayList<>();
        File input = new File("input.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (i == 0) {
                    n = Integer.parseInt(tokens[0]);
                    m = Integer.parseInt(tokens[1]);
                    ++i;
                } else {
                    in.add(Integer.parseInt(tokens[0]));
                    in.add(Integer.parseInt(tokens[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(int[][] adjacencyMatrix) {
        File output = new File("output.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            for (int[] value : adjacencyMatrix) {
                for (int val : value) {
                    bufferedWriter.write(val + " ");
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}