package trees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionTrees3 {
    public static void main(String[] args) throws IOException {
        File input = new File("bst.in");
        int len;
        int[] tree;
        int[] lines;
        char[] polar;
        List<Board> board;


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            len = Integer.parseInt(bufferedReader.readLine());
            tree = new int[len + 1];
            lines = new int[len + 1];
            polar = new char[len + 1];
            board = new ArrayList<>(len + 1);

            tree[1] = Integer.parseInt(bufferedReader.readLine());

            for (int i = 2; i <= len; ++i) {
                String[] line = bufferedReader.readLine().split(" ");
                tree[i] = Integer.parseInt(line[0]);
                lines[i] = Integer.parseInt(line[1]);
                polar[i] = line[2].charAt(0);
            }
        }

        board.add(0, new Board(Integer.MIN_VALUE, Integer.MAX_VALUE));
        board.add(1, new Board(Integer.MIN_VALUE, Integer.MAX_VALUE));

        int i = 2;

        while (i <= len) {
            int val = tree[i];
            int line = lines[i];
            int p = polar[i];
            int inVal = tree[line];
            Board b = board.get(line);

            if (p == 'L') {
                if (val >= inVal) {
                    writeToFile("NO");
                    return;
                }

                board.add(i, new Board(b.left, inVal - 1));
            }

            if (p == 'R') {
                if (val < inVal) {
                    writeToFile("NO");
                    return;
                }
                board.add(i, new Board(inVal, b.right));
            }

            if (val < b.left || val > b.right) {
                writeToFile("NO");
                return;
            }
            ++i;
        }
        writeToFile("YES");
    }

    private static void writeToFile(String answer) {
        File output = new File("bst.out");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))) {
            bufferedWriter.write(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Board {
        public int left;
        public int right;

        public Board(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
