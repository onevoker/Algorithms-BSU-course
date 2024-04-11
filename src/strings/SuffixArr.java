package strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SuffixArr {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String str = SCANNER.nextLine();
        System.out.println(str.length());

        Integer[] suffixArray = suffixArray(str);

        for (Integer x : suffixArray) {
            System.out.print((x + 1));
            System.out.print(" ");
        }
    }

    private static Integer[] suffixArray(String str) {
        int n = str.length();
        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, Comparator.comparing(str::substring));

        return suffixes;
    }
}
