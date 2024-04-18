package graphs;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private static final String IN = "input.txt";
    private static final String OUT = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(IN));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUT))
        ) {
            int n = Integer.parseInt(bufferedReader.readLine());
            Graph graph = new Graph(n);

            for (int i = 0; i < n; ++i) {
                String[] s = bufferedReader.readLine().split(" ");

                for (int j = 0; j < n; ++j) {
                    if (s[j].equals("1")) {
                        graph.add(i, j);
                    }
                }
            }

            Arrays.stream(graph.bfs()).forEach(x -> {
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

class Graph {
    private final int n;
    private final int[][] adjacencyMatrix;

    public Graph(int n) {
        this.n = n;
        this.adjacencyMatrix = new int[n][n];
    }

    public void add(int u, int v) {
        adjacencyMatrix[u][v] = 1;
    }

    public int[] bfs() {
        int[] labels = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int label = 0;

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                queue.add(i);
                visited[i] = true;
                labels[i] = ++label;
                while (!queue.isEmpty()) {
                    int u = queue.poll();

                    for (int j = 0; j < n; ++j) {
                        if (adjacencyMatrix[u][j] == 1 && !visited[j]) {
                            queue.add(j);
                            visited[j] = true;
                            labels[j] = ++label;
                        }
                    }
                }
            }
        }

        return labels;
    }
}