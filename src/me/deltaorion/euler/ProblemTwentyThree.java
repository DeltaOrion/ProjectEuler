package me.deltaorion.euler;

import java.util.*;

public class ProblemTwentyThree {

    private final static int LARGEST_NON_ABUNDANT_SUMS = 20161;

    public static void main(String[] args) {
        Set<Integer> abundantSet = new LinkedHashSet<>();
        //n*sqrt(n)
        long before = System.nanoTime();
        for(int i=0;i<LARGEST_NON_ABUNDANT_SUMS;i++) {
            if(factorSum(i) > i) {
                abundantSet.add(i);
            }
        }

        int sum = 0;

        for(int i=0;i<=LARGEST_NON_ABUNDANT_SUMS;i++) {
            boolean sumFound = false;
            for (Integer abundantNumber : abundantSet) {
                if(i<abundantNumber)
                    break;

                int b = i - abundantNumber;
                if(abundantSet.contains(b)) {
                    sumFound = true;
                    break;
                }
            }

            if(!sumFound) {
                sum += i;
            }
        }

        long after = System.nanoTime();
        System.out.println("Elapsed Time: "+(after-before));
        //206599700
        //49381700    3x increase in time
        System.out.println(sum);
    }

    private static int binarySearch(List<Integer> abundantNumbers, int b) {
        int l = 0;
        int h = abundantNumbers.size();
        while (h>=l) {
            int middle = (h+l)/2;
            int m = abundantNumbers.get(middle);
            if(b > m) {
                l = middle+1;
            } else if(b<m) {
                h = middle-1;
            } else {
                return m;
            }
        }

        return -1;
    }


    private static int factorSum(int n) {
        int sum = 0;
        for(int i=1;i*i<=n;i++) {
            if(n%i==0) {
                int multiple = n / i;
                if (i != 1 && multiple != i) {
                    sum += multiple;
                }
                sum += i;
            }
        }
        return sum;
    }

}
