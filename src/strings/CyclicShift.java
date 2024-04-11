package strings;

import java.io.*;

public class CyclicShift {
    private static int n;
    private static String s1;
    private static String s2;

    public static void main(String[] args) {
        readFromFile();
        writeToFile(KMP(n, s1, s2));
    }


    public static int KMP(int n, String s1, String s2) {
        String concat = s1 + s1;
        int[] p = prefixFunc(s2);
        int i = 0;
        int j = 0;

        while (i < 2 * n) {
            if (j < n && s2.charAt(j) == concat.charAt(i)) {
                ++j;
                ++i;
            }
            if (j == n) {
                return i - n;
            }
            if (i < 2 * n && s2.charAt(j) != concat.charAt(i)) {
                if (j != 0) {
                    j = p[j - 1];
                } else {
                    ++i;
                }
            }
        }

        return -1;
    }

    private static int[] prefixFunc(String s) {
        int[] p = new int[s.length()];
        int j = 0;

        for (int i = 1; i < s.length(); ++i) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = p[j - 1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            p[i] = j;
        }

        return p;
    }

    private static void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            n = Integer.parseInt(br.readLine());
            s1 = br.readLine();
            s2 = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(int num) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
