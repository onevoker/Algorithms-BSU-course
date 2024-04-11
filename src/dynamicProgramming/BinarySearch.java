package dynamicProgramming;

import java.util.Scanner;

public class BinarySearch {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = SCANNER.nextInt();
        }

        int k = SCANNER.nextInt();
        int[] requests = new int[k];

        for (int i = 0; i < k; ++i) {
            requests[i] = SCANNER.nextInt();
        }

        for (int x : requests) {
            int b;
            int l = modifiedBinarySearch(x, arr);
            int r;
            if (l <= n - 1 && arr[l] == x) {
                b = 1;
            } else {
                b = 0;
            }
            if (x == Integer.MAX_VALUE) {
                r = n;
            } else {
                r = modifiedBinarySearch(x + 1, arr);
            }
            System.out.println(b + " " + l + " " + r);
        }
    }

    private static int modifiedBinarySearch(int x, int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}