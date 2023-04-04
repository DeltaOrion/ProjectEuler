package me.deltaorion.euler.P18_67;

import java.io.File;
import java.io.FileNotFoundException;

public class ProblemEighteen {

    public static void main(String[] args) throws FileNotFoundException {
        String path = null;
        if(args.length==0) {
            path = "src" + File.separator + "me" + File.separator + "deltaorion" + File.separator + "euler" + File.separator + "P18_67" + File.separator + "67.txt";
        } else {
            path = args[0];
        }
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
