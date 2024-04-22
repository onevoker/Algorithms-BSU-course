package graphs;

import java.util.*;
import java.util.stream.IntStream;

public class MaximalFlow {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int m = SCANNER.nextInt();

        Graff graff = new Graff(n);

        IntStream.range(0, m).forEach(x -> {
                    int u = SCANNER.nextInt() - 1;
                    int v = SCANNER.nextInt() - 1;
                    int w = SCANNER.nextInt();
                    graff.addEdge(u, v, w);
                }
        );

        int start = 0;
        int end = n - 1;
        System.out.println(graff.maxFlow(start, end));
    }
}

class Graff {
    private final Map<Integer, List<Edge>> adjacency;
    private final int n;

    public Graff(int n) {
        this.n = n;
        this.adjacency = new HashMap<>();

        IntStream.range(0, n).forEach(x ->
                this.adjacency.put(x, new ArrayList<>())
        );
    }

    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(to, capacity);
        Edge e2 = new Edge(from, 0);

        e1.reverse = e2;
        e2.reverse = e1;

        adjacency.get(from).add(e1);
        adjacency.get(to).add(e2);
    }

    public int maxFlow(int start, int end) {
        int flow = 0;

        while (true) {
            int newFlow = dfs(start, end, Integer.MAX_VALUE, new boolean[n]);
            if (newFlow == 0) {
                break;
            }
            flow += newFlow;
        }
        return flow;
    }

    private int dfs(int start, int end, int flow, boolean[] visited) {
        if (start == end) {
            return flow;
        }
        visited[start] = true;

        for (Edge edge : adjacency.get(start)) {
            if (!visited[edge.to] && edge.capacity > 0) {
                int minFlow = dfs(edge.to, end, Math.min(flow, edge.capacity), visited);
                if (minFlow > 0) {
                    edge.capacity -= minFlow;
                    edge.reverse.capacity += minFlow;
                    return minFlow;
                }
            }
        }

        return 0;
    }

    static class Edge {
        private final int to;
        private int capacity;
        private Edge reverse;

        public Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }
    }
}
