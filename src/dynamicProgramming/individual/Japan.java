package dynamicProgramming.individual;

import java.io.*;
import java.math.BigInteger;

public class Japan {
    public static void main(String[] args) throws IOException {
        File in = new File("in.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(in));

        int n = Integer.parseInt(bufferedReader.readLine());
        int k = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.readLine();
        int sum = 0;
        for (int i = 1; i <= k; ++i) {
            sum += Integer.parseInt(bufferedReader.readLine());
        }
        bufferedReader.close();

        if (sum > n - k + 1) {
            writeToFile(BigInteger.valueOf(0));
        } else {
            int m = n - sum + 1;
            writeToFile(C(m, k));
        }
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

    private static void writeToFile(BigInteger num) {
        File out = new File("out.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out))) {
            bufferedWriter.write(String.valueOf(num));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
