package dynamicProgramming;

import java.math.BigInteger;
import java.util.Scanner;

public class Ones_1 {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final BigInteger MOD = BigInteger.valueOf(1000000007);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int k = SCANNER.nextInt();
        System.out.println(C(n, k).mod(MOD));
    }

    private static BigInteger C(int n, int k) {
        BigInteger c = BigInteger.ONE;

        for (int i = 1; i <= k; ++i) {
            BigInteger coef = BigInteger.valueOf(n - i + 1);
            c = c.multiply(coef);
        }

        return c.divide(factorial(k));
    }

    private static BigInteger factorial(int num) {
        BigInteger result = BigInteger.ONE;

        for (int i = 2; i <= num; ++i) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}