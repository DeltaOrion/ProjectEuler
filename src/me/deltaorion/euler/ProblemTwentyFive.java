package me.deltaorion.euler;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProblemTwentyFive {

    public static void main(String[] args) {
        long before = System.nanoTime();
        int result = getFibDigitOpti(1000);
        long after = System.nanoTime();
        System.out.println("Total Elapsed Time: " + (after-before));
    }

    private static int getFibDigitOpti(int digits) {
        double sqrt5 = Math.sqrt(5);
        double phi = (sqrt5 + 1) / 2;
        return (int) Math.ceil((((digits-1) + Math.log10(sqrt5)) / Math.log10(phi)));
    }

    private static int getFibDigit(int digits) {
        BigInteger lowerBound = BigInteger.TEN.pow(digits-1);
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = BigInteger.ZERO;
        int count = 1;
        while (true) {
            BigInteger nextTerm = f1.add(f2);
            f2 = f1;
            f1= nextTerm;
            count++;
            if(nextTerm.compareTo(lowerBound)>=0)
                return count;
        }
    }
}
