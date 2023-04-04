package me.deltaorion.euler.P22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProblemTwentyTwo {

    public static void main(String[] args) {
        String path = null;
        if(args.length==0) {
            path = "src" + File.separator + "me" + File.separator + "deltaorion" + File.separator + "euler" + File.separator + "P22" + File.separator + "names.txt";
        } else {
            path = args[0];
        }
        File file = new File(path);
        try {
            List<String> names = getNames(file);
            long before = System.nanoTime();
            sort(names);
            System.out.println(getCount(names));
            long after = System.nanoTime();
            System.out.println("Elapsed Time '"+(after-before)+"'");

            names = getNames(file);
            before = System.nanoTime();
            names.sort(Comparator.naturalOrder()); //this is actually faster why? kn < nlogn ? only for certain values of k
            //does this work, can we work out what values of k this holds true

            System.out.println(getCount(names));
            after = System.nanoTime();
            System.out.println("Elapsed Time '"+(after-before)+"'");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    private static int getCount(List<String> names) {
        int position = 1;
        int count = 0;
        for(String name : names) {
            int value = 0;
            for(int i=0;i<name.length();i++) {
                value += name.charAt(i) - 'A' + 1;
            }
            value *= position;
            count += value;
            position++;
        }

        return count;
    }

    private static List<String> getNames(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        List<String> names = new ArrayList<>();
        int count =0;
        while (scanner.hasNext()) {
            names.add(scanner.next().replaceAll("\"",""));
        }
        return names;
    }

    /**
     * Use Radix sort to sort the names. A shorter name has a higher position
     * then a longer one with the same starting characters.
     *
     * Complexity: O(kN) where k is the longest string in the input
     * for the data set provided O(11N)
     */
    private static void sort(List<String> names) {
        String max = getMax(names);
        //use radix sort, go char by char
        //O(kN) where k is the max radix
        for(int i=max.length()-1;i>=0;i--) {
            countingSort(names,i);
        }
    }

    /*

     */
    private static void countingSort(List<String> names, int index) {
        int[] count = new int[27];
        //0 - A, 1 = B
        for(String name : names) {
            if(index>=name.length()) {
                count[0]++;
            } else {
                char character = name.charAt(index);
                count[getKey(character)]++;
            }
        }

        for(int i=1;i<count.length;i++) {
            count[i] = count[i] + count[i-1];
        }

        String[] output = new String[names.size()];
        for(int i=names.size()-1;i>=0;i--) {
            String name = names.get(i);

            int position = 0;
            if(index < name.length()) {
                char character = name.charAt(index);
                position = count[getKey(character)]--;
            } else {
                position = count[0]--;
            }
            output[position-1] = name;
        }

        names.clear();
        names.addAll(Arrays.asList(output));
    }

    private static int getKey(char character) {
        return character - 'A' + 1;
    }

    private static String getMax(List<String> names) {
        String max = names.get(0);
        for(String name : names) {
            if(name.length() > max.length())
                max = name;
        }
        return max;
    }


}
