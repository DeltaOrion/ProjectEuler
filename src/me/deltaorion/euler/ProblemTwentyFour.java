package me.deltaorion.euler;

import java.util.Arrays;

public class ProblemTwentyFour {

    public static void main(String[] args) {
        //permute(9);
        //System.out.println(nPermutation(9,1000000));
        long before = System.nanoTime();
        String result = nPermutation(9,700);
        long after = System.nanoTime();
        System.out.println("Elapsed Time: "+(after-before));
    }

    //n is the digits
    //O(n^2)
    private static String nPermutation(int digits, int number) {
        boolean[] visited = new boolean[digits+1];
        int[] factorial = factorialDP(digits);
        StringBuilder result = new StringBuilder();
        for(int i=digits;i>0;i--) {
            int digitCount = number/factorial[i];
            int digit = 0;
            int count = 0;
            if(digitCount==0) {
                while (visited[digit])
                    digit++;
            } else {
                while (count < digitCount) {
                    digit++;
                    while (visited[digit])
                        digit++;
                    count++;
                }
            }
            visited[digit] = true;
            result.append(digit);
            number = number - factorial(i)*digitCount;
        }
        return result.toString();
    }

    private static int factorial(int n) {
        int res = 1;
        for(int i=1;i<=n;i++) {
            res  *= i;
        }
        return res;
    }

    private static int[] factorialDP(int n) {
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for(int i=1;i<=n;i++) {
            factorial[i] = factorial[i-1] * i;
        }
        return factorial;
    }

    //O(t) where t is the target
    private static void permute(int digits) {
        permute(digits,new boolean[digits+1],0,new int[digits+1]);
    }

    private static int permutationCount = 0;

    private static void permute(int digits, boolean[] visited, int current, int[] solution) {
        if(current==digits+1) {
            permutationCount++;
            if(permutationCount==1000000) {
                System.out.println(Arrays.toString(solution));
            }
            return;
        }


        for(int i=0;i<=digits;i++) {
            if(!visited[i]) {
                visited[i] = true;
                solution[current] = i;
                permute(digits,visited,current+1,solution);
                visited[i] = false;
            }
        }
    }
}
