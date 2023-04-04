package me.deltaorion.euler.P18_67;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Triangle {

    private final List<List<Integer>> rows;

    public Triangle(File file) throws FileNotFoundException {
        rows = readFile(file);
    }

    private List<List<Integer>> readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<List<Integer>> rows = new ArrayList<>();
        while (scanner.hasNextLine()) {
            List<Integer> row = new ArrayList<>();
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            for(String number : nums) {
                row.add(Integer.parseInt(number));
            }
            rows.add(row);
        }

        return rows;
    }

    public int getPathSumOpti() {
        int[] sum = new int[getRowCount()];
        int count = 0;
        for(int num : rows.get(getRowCount()-1)) {
            sum[count] = num;
            count++;
        } //set final row to sum

        for(int i=getRowCount()-1;i>0;i--) {
            int[] rowMax = new int[i];
            for(int j=0;j<sum.length-1;j++) {
                rowMax[j] = Math.max(sum[j],sum[j+1]);
            }

            count = 0;
            for(int num : rows.get(i-1)) {
                rowMax[count] += num;
                count++;
            }

            sum = rowMax;
        }

        return sum[0];
    }

    public int getRowCount() {
        return rows.size();
    }

    public int get(int row, int column) {
        return rows.get(row).get(column);
    }

    public void set(int row, int column, int newValue) {
        rows.get(row).set(column,newValue);
    }
}
