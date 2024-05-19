package discreteOptimization;

import java.io.*;
import java.util.*;

public class RoutesOfTheQueen {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    private static final String NO_SOLUTIONS = "no_solutions";
    private static final int[][] DIRECTIONS = {
            {1, 0}, {1, -1}, {0, -1}, {-1, -1},
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1}
    };

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());

            boolean[][] matrix = new boolean[m][n];
            Vertex[][] vertices = new Vertex[m][n];

            readObstacles(br, k, matrix);
            createVertices(matrix, vertices, m, n);

            Vertex start = getVertex(br, vertices);
            Vertex end = getVertex(br, vertices);

            end.step = 0;
            end.blocked = true;

            PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.step));
            queue.add(end);
            bfs(queue, vertices);

            sortAllPaths(vertices);

            Deque<Vertex> path = new ArrayDeque<>();
            if (start.neighbours.isEmpty()) {
                bw.write(NO_SOLUTIONS);
            } else {
                writeOutput(start, path, bw);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readObstacles(BufferedReader br, int k, boolean[][] matrix) throws IOException {
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            matrix[y][x] = true;
        }
    }

    private static void createVertices(boolean[][] matrix, Vertex[][] vertices, int rows, int cols) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!matrix[y][x]) {
                    vertices[y][x] = new Vertex(x, y);
                }
            }
        }
    }

    private static Vertex getVertex(BufferedReader br, Vertex[][] vertices) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        return vertices[y][x];
    }

    private static void bfs(PriorityQueue<Vertex> queue, Vertex[][] vertices) {
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (int[] dir : DIRECTIONS) {
                traverseInDirection(current, dir, queue, vertices);
            }
        }
    }

    private static void traverseInDirection(Vertex vertex, int[] direction, PriorityQueue<Vertex> queue, Vertex[][] vertices) {
        int x = vertex.x + direction[0];
        int y = vertex.y + direction[1];

        while (x >= 0 && x < vertices[0].length && y >= 0 && y < vertices.length && vertices[y][x] != null) {
            Vertex current = vertices[y][x];
            int newStep = vertex.step + 1;
            if (newStep < current.step) {
                current.step = newStep;
                current.neighbours.clear();
                current.neighbours.add(vertex);
                if (!current.blocked) {
                    queue.add(current);
                    current.blocked = true;
                }
            } else if (newStep == current.step) {
                current.neighbours.add(vertex);
            }
            x += direction[0];
            y += direction[1];
        }
    }

    private static void sortAllPaths(Vertex[][] vertices) {
        for (Vertex[] row : vertices) {
            for (Vertex vertex : row) {
                if (vertex != null) {
                    sortVertexPaths(vertex);
                }
            }
        }
    }

    private static void sortVertexPaths(Vertex vertex) {
        vertex.neighbours.sort((v1, v2) -> {
                    int dir1 = getDirection(vertex, v1);
                    int dir2 = getDirection(vertex, v2);
                    if (dir1 != dir2) {
                        return Integer.compare(dir1, dir2);
                    } else {
                        double distance1 = calculateDistance(vertex, v1);
                        double distance2 = calculateDistance(vertex, v2);
                        return Double.compare(distance1, distance2);
                    }
                }
        );
    }

    private static int getDirection(Vertex a, Vertex b) {
        int dx = b.x - a.x;
        int dy = b.y - a.y;

        if (dx > 0 && dy == 0)
            return 1;
        if (dx > 0 && dy > 0)
            return 2;
        if (dx == 0 && dy > 0)
            return 3;
        if (dx < 0 && dy > 0)
            return 4;
        if (dx < 0 && dy == 0)
            return 5;
        if (dx < 0 && dy < 0)
            return 6;
        if (dx == 0 && dy < 0)
            return 7;
        if (dx > 0 && dy < 0)
            return 8;
        return 0;
    }

    private static double calculateDistance(Vertex a, Vertex b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    private static void writeOutput(Vertex current, Deque<Vertex> path, BufferedWriter bw) throws IOException {
        path.add(current);

        if (!current.neighbours.isEmpty()) {
            for (Vertex neighbour : current.neighbours) {
                writeOutput(neighbour, path, bw);
                path.pollLast();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(path.size() - 1).append("\n");
            for (Vertex vertex : path) {
                sb.append(vertex).append("\n");
            }
            bw.write(sb.toString());
        }
    }

    private static class Vertex {
        protected int x;
        protected int y;
        protected int step;
        protected boolean blocked;
        protected List<Vertex> neighbours;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            this.step = Integer.MAX_VALUE;
            this.blocked = false;
            this.neighbours = new ArrayList<>();
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }
}