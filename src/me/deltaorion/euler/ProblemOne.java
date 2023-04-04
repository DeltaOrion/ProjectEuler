package me.deltaorion.euler;

import me.deltaorion.euler.test.Assert;

/**
 * https://projecteuler.net/problem=1
 *
 *  If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 */
public class ProblemOne {

    public static void main(String[] args) {
        Assert.assertEquals(23,getSumLoop(10));
        Assert.assertEquals(getSumLoop(1000),getSumOpti(1000));
        System.out.println(getSumLoop(1000));
        System.out.println(getSumOpti(100));
    }

    /**
     *
     * Naive Solution, simply add numbers and check if they are multiples
     *
     * Time Complexity: O(n)  Space Complexity: O(1)
     *
     * @param n the number to add to not including
     * @return the sum
     */
    public static int getSumLoop(int n) {
        int sum = 0;
        for(int i=0;i<n;i++) {
            if(i%3==0) {
                sum+=i;
                continue;
            }

            if(i%5==0)
                sum+=i;
        }

        return sum;
    }

    /**
     * Optimised Solution
     *
     * Time Complexity: O(1) Space Complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int getSumOpti(int n) {
        return getSum(0,n,3) + getSum(0,n,5) - getSum(0,n,15);
    }

    public static int getSum(int a, int n, int d) {
        if(n==0)
            return 0;

        int end = (int) ((double)n/d);
        if(n%d!=0)
            end = end+1;

        return (int) ((end/2.0) * (2*a + (end-1)*d));
    }
}
