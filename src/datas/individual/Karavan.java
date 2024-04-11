package datas.individual;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Karavan {
    private static final String YES = "Yes\n";
    private static final String NO = "No";
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; ++i) {
            String[] s = reader.readLine().split(" ");
            for (int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        int k = Integer.parseInt(reader.readLine());

        String[] points = reader.readLine().split(" ");
        reader.close();
        int x1 = Integer.parseInt(points[0]) - 1;
        int y1 = Integer.parseInt(points[1]) - 1;
        int x2 = Integer.parseInt(points[2]) - 1;
        int y2 = Integer.parseInt(points[3]) - 1;

        Coordinate start = new Coordinate(x1, y1);
        Coordinate end = new Coordinate(x2, y2);

        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        String res = bfs(start, end, k, arr);
        writer.write(res);
        writer.close();
    }

    private static String bfs(Coordinate start, Coordinate end, int k, int[][] arr) {
        Queue<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        Queue<Integer> steps = new LinkedList<>();

        queue.add(start);
        steps.add(0);
        visited[start.getX()][start.getY()] = true;

        while (!queue.isEmpty()) {
            Coordinate cur = queue.remove();
            int curX = cur.getX();
            int curY = cur.getY();
            int step = steps.remove();

            if (curX == end.getX() && curY == end.getY()) {
                return YES.concat(String.valueOf(step));
            }

            for (int[] dir : DIRECTIONS) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];

                if (isInRange(newX, newY, arr) && !visited[newX][newY]) {
                    int cost = Math.abs(arr[curX][curY] - arr[newX][newY]);
                    if (canMove(k, cost)) {
                        visited[newX][newY] = true;
                        steps.add(step + 1);
                        queue.add(new Coordinate(newX, newY));
                    }
                }
            }
        }
        return NO;
    }

    public static boolean isInRange(int x, int y, int[][] arr) {
        return x >= 0 && x < arr.length && y >= 0 && y < arr[0].length;
    }

    public static boolean canMove(int k, int val) {
        return val <= k;
    }
}


class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

