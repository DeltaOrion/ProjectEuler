package me.deltaorion.euler.P18_67;

import java.io.File;
import java.io.FileNotFoundException;

public class ProblemEighteen {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\User\\Dropbox\\Project Euler\\src\\me\\deltaorion\\euler\\P18_67\\67.txt";
        File file = new File(path);
        Triangle triangle = new Triangle(file);
        long before = System.nanoTime();
        System.out.println(triangle.getPathSumOpti());
        long after = System.nanoTime();
        System.out.println("Time Elapsed = "+(after-before));
        before = System.nanoTime();
        TriangleTree triangleTree = new TriangleTree(triangle);
        System.out.println(triangleTree.getPathSum());
        after = System.nanoTime();
        System.out.println("Time Elapsed = "+(after-before));
    }
}
