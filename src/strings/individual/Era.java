package strings.individual;

import java.util.Scanner;

public class Era {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BC = "BC";
    private static final String AD = "AD";

    public static void main(String[] args) {
        String[] input = SCANNER.nextLine().split(" ");
        char[] s1 = input[0].toCharArray();
        char[] s2 = input[1].toCharArray();

        StringBuilder num1 = new StringBuilder();
        StringBuilder y1 = new StringBuilder();

        StringBuilder num2 = new StringBuilder();
        StringBuilder y2 = new StringBuilder();

        for (char ch : s1) {
            if (Character.isDigit(ch)) {
                num1.append(ch);
                continue;
            }
            y1.append(ch);
        }

        for (char ch : s2) {
            if (Character.isDigit(ch)) {
                num2.append(ch);
                continue;
            }
            y2.append(ch);
        }

        int realNum1 = Integer.parseInt(num1.toString());
        int realNum2 = Integer.parseInt(num2.toString());

        if (y1.toString().equals(BC)) {
            realNum1 = -realNum1;
        }
        if (y2.toString().equals(BC)) {
            realNum2 = -realNum2;
        }
        int res = realNum2 - realNum1;

        if ((y1.toString().equals(BC) && ((y2.isEmpty()) || y2.toString().equals(AD)))
                || (y2.toString().equals(BC) && ((y1.isEmpty()) || y1.toString().equals(AD))
        )) {
            if (res > 0) {
                res -= 1;
            } else {
                res += 1;
            }
            System.out.println(res);
        } else {
            System.out.println(res);
        }
    }
}
