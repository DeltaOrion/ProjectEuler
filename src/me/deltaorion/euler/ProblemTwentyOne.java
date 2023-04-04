package me.deltaorion.euler;

import me.deltaorion.euler.P18_67.TriangleTree;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProblemTwentyOne {

    public static void main(String[] args) {
        long before = System.nanoTime();
        System.out.println(getSumOfAmicableNumbers(10000));
        long after = System.nanoTime();
        System.out.println("Time Elapsed = " + (after - before));
        before = System.nanoTime();
        System.out.println(getSumOfAmicableNumbersOpti(10000));
        after = System.nanoTime();
        System.out.println("Time Elapsed = " + (after - before));

        System.out.println(getNode(30));
    }

    private static int getSumOfAmicableNumbersOpti(int n) {
        Node[] nodes = new Node[n];
        int amicableSum = 0;
        Set<Node> pairs = new HashSet<>();
        //start higher to get lower sums so we do not need to repeatedly calculate
        for (int i = n - 1; i > 0; i--) {
            //node might have been calculated already
            Node node = nodes[i];
            if (node == null) {
                node = getNode(i);
                nodes[i] = node;
            }

            if (node.number == node.divisorSum)
                continue;

            if (node.divisorSum >= n)
                continue;

            Node pair = nodes[node.divisorSum];
            if (pair == null) {
                pair = getNode(node.divisorSum);
                nodes[node.divisorSum] = pair;
            }
            if (pair.divisorSum == node.number) {
                if (!pairs.contains(node)) {
                    pairs.add(node);
                    amicableSum += node.number;
                }
            }

        }

        return amicableSum;
    }

    //O(n^2) or T(n^2+n)/2
    private static int getSumOfAmicableNumbers(int n) {
        int amicableSum = 0;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = getNode(i);
        }

        Set<Node> pairs = new HashSet<>();

        for (Node node : nodes) {
            if (node.divisorSum >= nodes.length)
                continue;

            Node pair = nodes[node.divisorSum];
            if (pair.number == node.number)
                continue;

            if (pairs.contains(node))
                continue;

            if (pair.divisorSum == node.number) {
                pairs.add(node);
                pairs.add(pair);
                amicableSum += node.number;
                amicableSum += pair.number;
            }
        }

        return amicableSum;
    }

    //O(n)
    private static Node getNode(int number) {
        Node node = new Node(number);
        /*
        for (int i = 1; i < number; ++i) {
            // if number is divided by i
            // i is the factor
            if (number % i == 0) {
                node.addDivisor(i);
            }
        }

         */

        double sqrt = Math.sqrt(number);
        for (int i = 1; i <= sqrt; i++) {
            if (number % i == 0) {
                // If divisors are equal, print only one
                if (number / i == i) {
                    node.addDivisor(i);
                }

                else { // Otherwise print both
                    node.addDivisor(i);
                    if(i!=1)
                        node.addDivisor(number/i);
                }
            }
        }
        return node;
    }

    private static class Node {
        private final int number;
        private int divisorSum;

        private Node(int number) {
            this.number = number;
        }

        public void addDivisor(int number) {
            divisorSum += number;
        }
    }

}
