package me.deltaorion.euler;

import me.deltaorion.euler.test.Assert;

import java.util.HashMap;
import java.util.Map;

public class ProblemSeventeen {

    private final static Map<Integer,String> wordMap = new HashMap<>();
    private final static Map<Integer,String> exponentWord = new HashMap<>();

    public static void main(String[] args) {
        wordMap.put(1,"one");
        wordMap.put(2,"two");
        wordMap.put(3,"three");
        wordMap.put(4,"four");
        wordMap.put(5,"five");
        wordMap.put(6,"six");
        wordMap.put(7,"seven");
        wordMap.put(8,"eight");
        wordMap.put(9,"nine");
        wordMap.put(10,"ten");
        wordMap.put(11,"eleven");
        wordMap.put(12,"twelve");
        wordMap.put(13,"thirteen");
        wordMap.put(14,"fourteen");
        wordMap.put(15,"fifteen");
        wordMap.put(16,"sixteen");
        wordMap.put(17,"seventeen");
        wordMap.put(18,"eighteen");
        wordMap.put(19,"nineteen");
        wordMap.put(20,"twenty");
        wordMap.put(30,"thirty");
        wordMap.put(40,"forty");
        wordMap.put(50,"fifty");
        wordMap.put(60,"sixty");
        wordMap.put(70,"seventy");
        wordMap.put(80,"eighty");
        wordMap.put(90,"ninety");

        exponentWord.put(2,"hundred");
        exponentWord.put(3,"thousand");

        //0-19 fixed
        //20-99 = twenty + three, ninety nine
        //100s = one hundred and
        //1000 = 1000 + 100 + 10s

        //String word = getWord(132);
        Assert.assertEquals(getWordCount(115),20);
        Assert.assertEquals(getWordCount(342),23);

        System.out.println(getSumWordCount(1,1000));
    }
    /**
     * Loop through the numbers, construct the words and count the amount of letters
     * in each word
     *
     * O(n) as we need to loop through the numbers
     */
    private static int getSumWordCount(int start ,int total) {
        int sum = 0;
        for(int i=start;i<=total;i++)
            sum += getWordCount(i);

        return sum;
    }

    private static int getWordCount(int number) {
        String word = getWord(number);
        int count = 0;
        for(int i=0;i<word.length();i++) {
            char token = word.charAt(i);
            if(token==' ')
                continue;

            if(token=='-')
                continue;

            count++;
        }

        return count;
    }

    private static String getWord(int number) {
        return getWord(number, (int) Math.log10(number));
    }

    private static String getWord(int number, int exponent) {
        if(wordMap.containsKey(number))
            return wordMap.get(number);

        int radix = getRadix(number,exponent);
        if(radix == 0)
            return "";

        int nextNumber = (int) (number - radix * Math.pow(10,exponent));

        if(exponent==1)
            return wordMap.get(radix*10) + "-" + getWord(nextNumber, 0);

        String word = wordMap.get(radix) + " " + exponentWord.get(exponent);
        if(exponent==2) {
            if(number%100!=0)
                word = word + " and";
        }

        return word + " " + getWord(nextNumber,exponent-1);
    }

    private static int getRadix(int number, int exponent) {
        int exp = (int) Math.pow(10,exponent);
        return (number/exp) % 10;
    }
}
