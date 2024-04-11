package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LCS {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = SCANNER.nextInt();
        }

        for (int i = 0; i < n; ++i) {
            b[i] = SCANNER.nextInt();
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = n, j = n;
        List<Integer> seqA = new ArrayList<>(), seqB = new ArrayList<>();

        while (i > 0 && j > 0) {
            if (a[i - 1] == b[j - 1]) {
                seqA.add(i - 1);
                seqB.add(j - 1);
                --i;
                --j;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                --i;
            } else {
                --j;
            }
        }


        System.out.println(dp[n][n]);

        for (int k = seqA.size() - 1; k >= 0; --k) {
            System.out.print(seqA.get(k) + " ");
        }

        System.out.println();

        for (int k = seqB.size() - 1; k >= 0; --k) {
            System.out.print(seqB.get(k) + " ");
        }
    }
}