package graphs;

import java.io.*;
import java.util.Arrays;

public class DFS {
    private static final String IN = "input.txt";
    private static final String OUT = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(IN));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUT))
        ) {
            int n = Integer.parseInt(bufferedReader.readLine());
            Graf graph = new Graf(n);

            for (int i = 0; i < n; ++i) {
                String[] s = bufferedReader.readLine().split(" ");

                for (int j = 0; j < n; ++j) {
                    if (s[j].equals("1")) {
                        graph.add(i, j);
                    }
                }
            }

            Arrays.stream(graph.dfs()).forEach(x -> {
                        try {
                            bufferedWriter.write(x + " ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Graf {
    private final int n;
    private final int[][] adjacencyMatrix;

    public Graf(int n) {
        this.n = n;
        this.adjacencyMatrix = new int[n][n];
    }

    public void add(int u, int v) {
        adjacencyMatrix[u][v] = 1;
    }

    public int[] dfs() {
        int[] labels = new int[n];
        boolean[] visited = new boolean[n];
        int label = 1;

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                label = dfsRecursive(i, visited, labels, label) + 1;
            }
        }

        return labels;
    }

    private int dfsRecursive(int u, boolean[] visited, int[] labels, int label) {
        visited[u] = true;
        labels[u] = label;

        for (int i = 0; i < n; ++i) {
            if (adjacencyMatrix[u][i] == 1 && !visited[i]) {
                label = dfsRecursive(i, visited, labels, ++label);
            }
        }

        return label;
    }
}