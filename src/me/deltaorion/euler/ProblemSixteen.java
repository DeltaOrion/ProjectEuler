package me.deltaorion.euler;

import java.math.BigInteger;

public class ProblemSixteen {

    public static void main(String[] args) {
        System.out.println(getSum(2,50));
    }

    public static int getSum(int base, int exponent) {
        BigInteger integer = BigInteger.valueOf(base);
        integer = integer.pow(exponent);
        String str = String.valueOf(integer);
        int sum = 0;
        for(int i=0;i<str.length();i++) {
            sum += Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        return sum;
    }
}
