package dynamicProgramming;

import java.util.Scanner;

public class Ones_2 {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final long MOD = 1000000007;

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int k = SCANNER.nextInt();
        System.out.println(C(n, k) % MOD);
    }

    public static long C(int n, int k) {
        long c = 1L;
        long f = 1L;

        for (int i = 1; i <= k; ++i) {
            long coef = n - i + 1;
            c = ((c % MOD) * (coef % MOD)) % MOD;
            f = ((f % MOD) * (i % MOD)) % MOD;
        }

        long f1 = pow(f, MOD - 2);
        return ((c % MOD) * (f1 % MOD)) % MOD;
    }

    private static long pow(long f, long n) {
        long res = 1;

        while (n > 0) {
            if (n % 2 == 1) {
                res = ((res % MOD) * (f % MOD)) % MOD;
                --n;
            } else {
                f = ((f % MOD) * (f % MOD)) % MOD;
                n /= 2;
            }
        }

        return res;
    }
}