package graphs.individual;

import java.io.*;
import java.util.*;

public class IntersectingRoads {
    private static final String IN = "input.in";
    private static final String OUT = "output.out";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(IN));
        String[] nk = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String[] se = bufferedReader.readLine().split(" ");
        int start = Integer.parseInt(se[0]) - 1;
        int end = Integer.parseInt(se[1]) - 1;

        Map<Integer, List<Edge>> adjacency = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            adjacency.put(i, new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            String[] road = bufferedReader.readLine().split(" ");
            int u = Integer.parseInt(road[0]) - 1;
            int v = Integer.parseInt(road[1]) - 1;
            int time = Integer.parseInt(road[2]);

            adjacency.get(u).add(new Edge(v, time));
            adjacency.get(v).add(new Edge(u, time));
        }
        bufferedReader.close();

        dijkstra(start, end, n, adjacency);
    }

    private static void dijkstra(int start, int end, int n, Map<Integer, List<Edge>> adjacency) throws IOException {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> dist[a]));
        queue.add(start);

        boolean[] visited = new boolean[n];
        visited[start] = true;

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = true;
            int waitingTime = 0;

            if (!(cur == start || cur == end)) {
                waitingTime = adjacency.get(cur).size();
            }

            for (Edge edge : adjacency.get(cur)) {
                int newDist = dist[cur] + edge.getTime() + waitingTime;
                int to = edge.getTo();
                if (newDist < dist[to] && !visited[to]) {
                    dist[to] = newDist;
                    prev[to] = cur;
                    queue.add(to);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int current = end;

        while (current != -1) {
            path.add(current + 1);
            current = prev[current];
        }
        Collections.reverse(path);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUT));

        bufferedWriter.write(dist[end] + "\n");
        path.forEach(x -> {
                    try {
                        bufferedWriter.write(x + " ");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        bufferedWriter.close();
    }
}

class Edge {
    private final int to;
    private final int time;

    Edge(int to, int time) {
        this.to = to;
        this.time = time;
    }

    public int getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return to == edge.to && time == edge.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, time);
    }
}
