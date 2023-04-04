package me.deltaorion.euler.P101;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

/**
 */
public class Matrix {

    private final double[][] values;

    public Matrix(int rows, int columns) {
        this.values = new double[rows][columns];
    }

    protected Matrix(double[][] array) {
        this.values = array;
    }

    public Matrix randomize() {
        Random random = new Random();
        Matrix matrix = new Matrix(getRows(),getColumns());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                matrix.set(row,col,random.nextDouble());
            }
        }
        return matrix;
    }

    public Matrix add(Matrix matrix) {
        if(matrix.getRows()!=getRows())
            throw new IllegalArgumentException();

        if(matrix.getColumns()!=getColumns())
            throw new IllegalArgumentException();

        Matrix result = new Matrix(getRows(),getColumns());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result.set(row,col,matrix.get(row,col)+get(row,col));
            }
        }

        return result;
    }

    public Matrix subtract(Matrix matrix) {
        if(matrix.getRows()!=getRows())
            throw new IllegalArgumentException();

        if(matrix.getColumns()!=getColumns())
            throw new IllegalArgumentException();

        Matrix result = new Matrix(getRows(),getColumns());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result.set(row,col,get(row,col)-matrix.get(row,col));
            }
        }

        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(this.getColumns(),this.getRows());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result.set(col,row,get(row,col));
            }
        }
        return result;
    }

    public Matrix multiplyElementWise(Matrix matrix) {
        if(matrix.getRows()!=getRows())
            throw new IllegalArgumentException();

        if(matrix.getColumns()!=getColumns())
            throw new IllegalArgumentException();

        Matrix result = new Matrix(getRows(),getColumns());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                result.set(row,col,matrix.get(row,col)*get(row,col));
            }
        }

        return result;
    }

    public Matrix multiply(Matrix matrix) {
        if(getColumns()!=matrix.getRows())
            throw new IllegalArgumentException();

        Matrix result = new Matrix(getRows(),matrix.getColumns());
        for(int i=0;i<getRows();i++) {
            for(int j=0;j<matrix.getColumns();j++) {
                double dotProduct = 0;
                for(int k=0;k<matrix.getRows();k++) {
                    dotProduct += this.get(i,k)*matrix.get(k,j);
                }
                result.set(i,j,dotProduct);
            }
        }

        return result;
    }

    public Matrix multiply(double scalar) {
        return map(input -> input*scalar);
    }

    public Matrix concatLeft(Matrix B) {
        if(B.getRows()!=getRows())
            throw new IllegalArgumentException("Both matrices must have the same amount of rows");

        double[][] resultant = new double[getRows()][getColumns()+B.getColumns()];
        for(int i=0;i<getRows();i++) {
            for(int j=0;j<getColumns();j++) {
                resultant[i][j] = get(i,j);
            }

            for(int j=0;j<B.getColumns();j++) {
                resultant[i][j+getColumns()] = B.get(i,j);
            }
        }

        return new Matrix(resultant);
    }

    public Matrix guassianEliminate(Matrix B) {
        Matrix rowEchelon = this.concatLeft(B);
        //calculate row echelon form
        if(rowEchelon.getRows()>1) {
            for (int i = 1; i < rowEchelon.getRows(); i++) {
                int column = i-1;
                int base = i-1;
                for (int j = i; j < rowEchelon.getRows(); j++) {
                    double ratio = rowEchelon.get(j,column)/rowEchelon.get(base,column);
                    rowEchelon.rowOperation(base,j,ratio);
                }
            }
        }
        //solve the matrix
        for(int col = rowEchelon.getRows()-1;col>=0;col--) {
            if(col<rowEchelon.getRows()-1) {
                //subtract every row after it
                for(int row=rowEchelon.getRows()-1;row>col;row--) {
                    double ratio = rowEchelon.get(col,row);
                    rowEchelon.rowOperation(row,col,ratio);
                }
            }
            rowEchelon.divideRow(col,rowEchelon.get(col,col));
        }
        //now split off the augmented
        Matrix result = new Matrix(rowEchelon.getRows(),1);
        for(int row=0;row<getRows();row++) {
            result.set(row,0,rowEchelon.get(row,rowEchelon.getColumns()-1));
        }
        return result;
    }

    private void divideRow(int row, double scalar) {
        for(int col=0;col<getColumns();col++) {
            values[row][col] = values[row][col]/scalar;
        }
    }

    //calculates r2 = r2-r1
    private void rowOperation(int r1, int r2, double multiply) {
        for(int col=0;col<getColumns();col++) {
            values[r2][col] = values[r2][col] - values[r1][col]*multiply;
        }
    }

    public Matrix map(Function<Double,Double> mappingFunction) {
        Matrix matrix = new Matrix(getRows(),getColumns());
        for(int row=0;row<getRows();row++) {
            for(int col=0;col<getColumns();col++) {
                matrix.set(row,col,mappingFunction.apply(get(row,col)));
            }
        }
        return matrix;
    }

    public void set(int row, int col, double value) {
        values[row][col] = value;
    }

    public double get(int row, int col) {
        return values[row][col];
    }

    public double[][] toArray() {
        return values;
    }

    public static Matrix fromArray(double[][] array) {
        return new Matrix(array);
    }

    public static Matrix fromArray(double[] array, boolean row) {
        if(row)
            return fromArrayRow(array);

        return fromArrayCol(array);
    }

    private static Matrix fromArrayCol(double[] array) {
        Matrix matrix = new Matrix(array.length,1);
        for(int i=0;i<array.length;i++) {
            matrix.set(i,0,array[i]);
        }
        return matrix;
    }

    private static Matrix fromArrayRow(double[] array) {
        Matrix matrix = new Matrix(1,array.length);
        for(int i=0;i<array.length;i++) {
            matrix.set(0,i,array[i]);
        }

        return matrix;
    }

    public int getRows() {
        return values.length;
    }

    public int getColumns() {
        if(values.length==0)
            return 0;

        return values[0].length;
    }

    public Matrix clone() {
        Matrix matrix = new Matrix(getRows(),getColumns());
        for(int i=0;i<getRows();i++) {
            for(int j=0;j<getColumns();j++) {
                matrix.set(i,j,get(i,j));
            }
        }

        return matrix;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "values=" + Arrays.deepToString(values) +
                '}';
    }
}
