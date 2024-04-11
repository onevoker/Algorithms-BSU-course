package dataStructures;

import java.util.Scanner;

public class Sums {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int len;
    private static long[] a, b;

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        len = (int) Math.sqrt(n) + 1;
        a = new long[n];
        b = new long[len];

        for (int i = 0; i < n; ++i) {
            a[i] = SCANNER.nextLong();
            b[i / len] += a[i];
        }

        int q = SCANNER.nextInt();

        while (q > 0) {
            String type = SCANNER.next();
            if (type.equals("FindSum")) {
                int l = SCANNER.nextInt();
                int r = SCANNER.nextInt();
                System.out.println(findSum(l, r));
            } else {
                int idx = SCANNER.nextInt();
                int x = SCANNER.nextInt();
                add(idx, x);
            }
            --q;
        }
    }

    private static long findSum(int l, int r) {
        long sum = 0;
        int jl = l / len, jr = r / len;
        if (jl == jr)
            for (int i = l; i < r; ++i)
                sum += a[i];
        else {
            for (int i = l; i < (jl + 1) * len; ++i) {
                sum += a[i];
            }
            for (int i = jl + 1; i < jr; ++i) {
                sum += b[i];
            }
            for (int i = jr * len; i < r; ++i) {
                sum += a[i];
            }
        }
        return sum;
    }

    private static void add(int idx, int x) {
        a[idx] += x;
        b[idx / len] += x;
    }
}