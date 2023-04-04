package me.deltaorion.euler;

import java.math.BigInteger;

public class ProblemTwenty {

    public static void main(String[] args) {
        System.out.println(getFactorialDigit(100));
    }

    public static int getFactorialDigit(int n) {
        BigInteger factorial = BigInteger.valueOf(1);
        for(int i=n;i>1;i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        String value = factorial.toString();
        int sum = 0;
        for(int i=0;i<value.length();i++) {
            sum += Integer.parseInt(String.valueOf(value.charAt(i)));
        }

        return sum;
    }
}
