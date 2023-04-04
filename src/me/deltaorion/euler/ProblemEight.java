package me.deltaorion.euler;

import java.io.StringReader;
import java.util.Scanner;

/**
 *


 The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.

 73167176531330624919225119674426574742355349194934
 96983520312774506326239578318016984801869478851843
 85861560789112949495459501737958331952853208805511
 12540698747158523863050715693290963295227443043557
 66896648950445244523161731856403098711121722383113
 62229893423380308135336276614282806444486645238749
 30358907296290491560440772390713810515859307960866
 70172427121883998797908792274921901699720888093776
 65727333001053367881220235421809751254540594752243
 52584907711670556013604839586446706324415722155397
 53697817977846174064955149290862569321978468622482
 83972241375657056057490261407972968652414535100474
 82166370484403199890008895243450658541227588666881
 16427171479924442928230863465674813919123162824586
 17866458359124566529476545682848912883142607690042
 24219022671055626321111109370544217506941658960408
 07198403850962455444362981230987879927244284909188
 84580156166097919133875499200524063689912560717606
 05886116467109405077541002256983155200055935729725
 71636269561882670428252483600823257530420752963450

 Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?


 */
public class ProblemEight {
    static String problem = "73167176531330624919225119674426574742355349194934\n" +
            "96983520312774506326239578318016984801869478851843\n" +
            "85861560789112949495459501737958331952853208805511\n" +
            "12540698747158523863050715693290963295227443043557\n" +
            "66896648950445244523161731856403098711121722383113\n" +
            "62229893423380308135336276614282806444486645238749\n" +
            "30358907296290491560440772390713810515859307960866\n" +
            "70172427121883998797908792274921901699720888093776\n" +
            "65727333001053367881220235421809751254540594752243\n" +
            "52584907711670556013604839586446706324415722155397\n" +
            "53697817977846174064955149290862569321978468622482\n" +
            "83972241375657056057490261407972968652414535100474\n" +
            "82166370484403199890008895243450658541227588666881\n" +
            "16427171479924442928230863465674813919123162824586\n" +
            "17866458359124566529476545682848912883142607690042\n" +
            "24219022671055626321111109370544217506941658960408\n" +
            "07198403850962455444362981230987879927244284909188\n" +
            "84580156166097919133875499200524063689912560717606\n" +
            "05886116467109405077541002256983155200055935729725\n" +
            "71636269561882670428252483600823257530420752963450";

    public static void main(String[] args) {
        int[] number = convertToNumber(problem);
        long before = System.nanoTime();
        long largestSum = getLargestSum(number,13);
        long after = System.nanoTime();
        System.out.println("No Optimization: "+(after-before));
        before = System.nanoTime();
        long largestOpti = getLargestOpti(number,13);
        after = System.nanoTime();
        System.out.println("Optimization: "+(after-before));
        System.out.println(largestSum);
        System.out.println(largestOpti);
    }

    /**
     * Simply loop through the number
     *
     * Time Complexity:  O(n*j) where n is the amount of digits and j is the consecutive digits
     * Space Complexity: O(1)
     *
     *
     * @param number
     * @param nums
     * @return
     */
    private static long getLargestSum(int[] number, int nums) {
        long largestSum = 1;

        for(int i=0;i<number.length-nums+1;i++) {
            long sum = 1;
            for(int j=i;j<i+nums;j++) {
                sum*=number[j];
            }

            if(sum > largestSum)
                largestSum = sum;
        }

        return largestSum;
    }

    /**
     * Loop moving a buffer of sums over to the right
     *
     * Time Complexity: O(n)
     *
     * @param number
     * @param nums
     * @return
     */
    private static long getLargestOpti(int[] number, int nums) {
        long largestSum = 0;
        long sum = 0;
        for(int i=0;i<number.length;i++) {
            if(sum==0) {
                sum = 1;
                int cap = nums+i;
                if(cap>=number.length)
                    return largestSum;

                for(int j=i;j<cap;j++) {
                    sum *= number[j];
                }
                i+=nums-1;
            } else if(number[i] == 0){
                sum = 0;
            } else {
                sum *= number[i];
                int back = number[i-nums];
                if(back!=0)
                    sum/=back;
            }

            if(sum > largestSum)
                largestSum = sum;
        }

        return largestSum;
    }

    private static int[] convertToNumber(String problem) {
        Scanner scanner = new Scanner(new StringReader(problem));
        int[] number = new int[1000];
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(int i=0;i<line.length();i++) {
                char token = line.charAt(i);
                if(token==' ')
                    continue;

                number[count] = Integer.parseInt(String.valueOf(token));
                count++;
            }
        }
        return number;
    }
}
