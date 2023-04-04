package me.deltaorion.euler.P101;

public class Problem101 {

    //1 − n + n2 − n3 + n4 − n5 + n6 − n7 + n8 − n9 + n10
    private static final Polynomial generator = new Polynomial(1,-1,1,-1,1,-1,1,-1,1,-1,1);

    public static void main(String[] args) {
        long bopSum = 0;
        langereGen();

        for(int i=0;i<generator.getDegree()-1;i++) {
            Polynomial polynomial = generatePolynomial(i);
            int x = 1;
            long bop = polynomial.getIntY(x);
            long genY = generator.getIntY(x);
            while (genY==bop) {
                x++;
                bop = polynomial.getIntY(x);
                genY = generator.getIntY(x);
            }
            System.out.println(bop);
            bopSum+=bop;
        }
        System.out.println(bopSum);
    }

    private static Polynomial generatePolynomial(int degree) {
        int terms = degree+1;
        Matrix A = new Matrix(terms,terms);
        Matrix B = new Matrix(terms,1);
        for(int i=1;i<=terms;i++) {
            int row = i-1;
            B.set(row,0,generator.getIntY(i));
            long exponent = 1;
            for(int col=degree;col>=0;col--) {
                A.set(row,col,exponent);
                exponent = exponent * i;
            }
        }

        return new Polynomial(A.guassianEliminate(B));
    }

    public static void langereGen() {
        //Generate the generator polynomial
        double[] coefficients = {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
        Polynomial poly = new Polynomial(coefficients);

        //Generate the points
        long[] y = new long[coefficients.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = (long) poly.getY(i + 1);
        }

        long fits = 0;
        for (long n = 1; n <= coefficients.length - 1; n++) {
            long result = 0;
            for (int i = 1; i <= n; i++) {

                long temp1 = 1;
                long temp2 = 1;

                for (long j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    } else {
                        temp1 *= n + 1 - j;
                        temp2 *= i - j;
                    }
                }
                result += temp1 * y[i - 1] / temp2;
            }

            fits += result;
            System.out.println(fits);
        }
    }

}
