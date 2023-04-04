package me.deltaorion.euler.P101;

import java.math.BigDecimal;

public class Polynomial {

    private final Matrix values;

    public Polynomial(double... values) {
        this.values = new Matrix(values.length,1);
        for(int row=0;row<values.length;row++) {
            this.values.set(row,0,values[row]);
        }
    }

    public Polynomial(Matrix values) {
        this.values = values;
    }

    public int getDegree() {
        return values.getRows();
    }

    public double getY(double x) {
        double res = 0;
        double power = 1;
        for(int i=getDegree()-1;i>=0;i--) {
            res = res + power*values.get(i,0);
            power = power * x;
        }

        return res;
    }

    public long getIntY(int x) {
        long res = 0;
        long power = 1;
        for(int i=getDegree()-1;i>=0;i--) {
            long base = (long) Math.round(values.get(i,0));
            res = res + power*base;
            power = power * x;
        }

        return res;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int power=getDegree()-1;power>=0;power--) {
            int term = getDegree()-power-1;
            double an = values.get(term,0);
            if(an!=0) {
                if(an!=1) {
                    builder.append(an);
                }
                builder.append("x^")
                        .append(power)
                        .append(" ");
            }
        }
        return builder.toString();
    }
}
