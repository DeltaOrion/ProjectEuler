package me.deltaorion.euler.P102;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem102 {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = null;
        if(args.length==0) {
            filename = "src" + File.separator + "me" + File.separator + "deltaorion" + File.separator + "euler" + File.separator + "P102" + File.separator + "triangles.txt";
        } else {
            filename = args[0];
        }
        Scanner scanner = new Scanner(new File(filename));
        int points = 0;
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(",");
            Point p1 = new Point(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
            Point p2 = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]));
            Point p3 = new Point(Integer.parseInt(split[4]),Integer.parseInt(split[5]));
            if(pointInTriangle(new Point(0,0),p1,p2,p3))
                points++;
        }
        System.out.println(points);
    }

    private static float sign (Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    private static boolean pointInTriangle(Point pt, Point v1, Point v2, Point v3) {
        float d1, d2, d3;
        boolean has_neg, has_pos;

        d1 = sign(pt, v1, v2);
        d2 = sign(pt, v2, v3);
        d3 = sign(pt, v3, v1);

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);
    }

}
