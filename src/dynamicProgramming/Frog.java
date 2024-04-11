package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frog {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            arr[i] = SCANNER.nextInt();
        }

        int[] dp = new int[n + 1];
        dp[1] = arr[1];

        if (n == 1) {
            System.out.println(dp[1]);
            System.out.print(n);
            return;
        }

        dp[2] = Integer.MIN_VALUE;

        for (int i = 3; i <= n; ++i) {
            dp[i] = arr[i] + Math.max(dp[i - 2], dp[i - 3]);
        }

        List<String> path = new ArrayList<>();
        path.add(String.valueOf(n));
        int i = n;

        while (i != 3 && i != 4) {
            if (i == 2) {
                System.out.print(-1);
                return;
            }
            if (dp[i - 2] > dp[i - 3]) {
                path.add(String.valueOf(i - 2));
                i -= 2;
            } else {
                path.add(String.valueOf(i - 3));
                i -= 3;
            }
        }

        System.out.println(dp[n]);

        System.out.print(1 + " ");
        for (int j = path.size() - 1; j >= 0; --j) {
            System.out.print(path.get(j) + " ");
        }
    }
}