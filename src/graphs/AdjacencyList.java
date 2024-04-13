package graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyList {
    private static final String in = "input.txt";
    private static final String out = "output.txt";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out))) {

            String[] nm = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            Map<Integer, List<Integer>> adjacency = new HashMap<>(n);

            for (int i = 0; i < m; ++i) {
                String[] uv = bufferedReader.readLine().split(" ");
                int u = Integer.parseInt(uv[0]);
                int v = Integer.parseInt(uv[1]);

                adjacency.putIfAbsent(u, new ArrayList<>());
                adjacency.putIfAbsent(v, new ArrayList<>());

                adjacency.get(u).add(v);
                adjacency.get(v).add(u);
            }

            for (int i = 1; i <= n; ++i) {
                List<Integer> list = adjacency.getOrDefault(i, new ArrayList<>());
                bufferedWriter.write(list.size() + " ");
                for (int v : list) {
                    bufferedWriter.write(v + " ");
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
