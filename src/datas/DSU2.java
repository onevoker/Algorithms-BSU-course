package datas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DSU2 {
    static int[] parent;
    static int[] size;
    static int components;
    static int[] shakes;
    static boolean[] destroyed;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String[] nmq = br.readLine().split(" ");

        int n = Integer.parseInt(nmq[0]);
        int m = Integer.parseInt(nmq[1]);
        int q = Integer.parseInt(nmq[2]);

        parent = new int[n + 1];
        size = new int[n + 1];
        components = n;

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        List<Integer[]> roads = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            roads.add(new Integer[]{u, v});
        }

        shakes = new int[q + 1];
        destroyed = new boolean[m + 1];

        for (int i = 1; i <= q; i++) {
            int shake = Integer.parseInt(br.readLine());
            shakes[i] = shake;
            destroyed[shake] = true;
        }
        br.close();

        for (int i = 1; i <= m; i++) {
            if (!destroyed[i]) {
                Integer[] road = roads.get(i - 1);
                union(road[0], road[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = q; i >= 1; i--) {
            if (components == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            Integer[] road = roads.get(shakes[i] - 1);
            if (destroyed[shakes[i]] && !(find(road[0]) == find(road[1]))) {
                union(road[0], road[1]);
            }
        }

        PrintWriter printWriter = new PrintWriter("output.txt");
        printWriter.print(sb.reverse());
        printWriter.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            --components;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        parent[x] = find(parent[x]);
        return parent[x];
    }
}
