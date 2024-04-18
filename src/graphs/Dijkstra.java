package graphs;

import java.io.*;
import java.util.*;

public class Dijkstra {
    private static final String IN = "input.txt";
    private static final String OUT = "output.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(IN));
        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int start = 0;
        int end = n - 1;

        List<Edge>[] adjacency = new ArrayList[n];

        for (int i = 0; i < n; ++i) {
            adjacency[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] road = bufferedReader.readLine().split(" ");
            int u = Integer.parseInt(road[0]) - 1;
            int v = Integer.parseInt(road[1]) - 1;
            int weight = Integer.parseInt(road[2]);

            adjacency[u].add(new Edge(v, weight));
            adjacency[v].add(new Edge(u, weight));
        }
        bufferedReader.close();

        dijkstra(start, end, n, adjacency);
    }

    private static void dijkstra(int start, int end, int n, List<Edge>[] adjacency) throws IOException {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingLong(Edge::getWeight));
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int cur = edge.getTo();

            if (edge.getWeight() > dist[cur]) {
                continue;
            }

            for (Edge e : adjacency[cur]) {
                int to = e.getTo();
                long newDist = dist[cur] + e.getWeight();
                if (newDist < dist[to]) {
                    dist[to] = newDist;
                    queue.add(new Edge(to, newDist));
                }
            }
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUT));
        bufferedWriter.write(String.valueOf(dist[end]));
        bufferedWriter.close();
    }
}

class Edge {
    private final int to;
    private final long weight;

    Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return this.to;
    }

    public long getWeight() {
        return this.weight;
    }
}