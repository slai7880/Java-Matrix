public class Matrix {
   private int m;
   private int n;
   private double[][] matrix;
   private double[][] transpose;
   
   /* Takes a matrix of integer, checks whether or not it is legal, program will
      stop if it is, otherwise stores it. */
   public Matrix(int[][] matrix) {
      isValid(matrix);
      m = matrix.length;
      n = matrix[0].length;
      transpose = new double[n][m];
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[i].length; j++) {
            this.matrix[i][j] = matrix[i][j];
            transpose[j][i] = matrix[i][j];
         }
      }
   }
   
   /* Takes a matrix of double, checks whether or not it is legal, program will
      stop if it is, otherwise stores it. */
   public Matrix(double[][] matrix) {
      isValid(matrix);
      m = matrix.length;
      n = matrix[0].length;
      transpose = new double[n][m];
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[i].length; j++) {
            transpose[j][i] = matrix[i][j];
         }
      }
      this.matrix = matrix;
   }
   
   public void printMatrix() {
      print(matrix);
   }
   
   public void printTranspose() {
      print(transpose);
   }
   
   // Changes the value at a specific position.
   public void change(int row, int col, double value) {
      matrix[row - 1][col - 1] = value;
      transpose[col - 1][row - 1] = value;
   }
   
   // Returns the transpose of the matrix.
   public Matrix transpose() {
      return new Matrix(transpose);
   }
   
   /* Returns a matrix formed by the current matrix multiplied by the other one. Program will
      stop if the size does not match. */
   public Matrix multiply(Matrix other) {
      if (n != other.m) {
         System.out.println("Invalid command.");
         System.out.println("n != other.m");
         System.exit(1);
      }
      double[][] result = new double[m][other.n];
      for (int i = 0; i < m; i++) {
         double[] row1 = new double[n];
         for (int j = 0; j < m; j++) {
            double[] row2 = new double[n];
            double sum = 0;
            for (int k = 0; k < n; k++) {
               sum += row1[k] * row2[k];
            }
            result[i][j] = sum;
         }
      }
      return new Matrix(result);
   }
   
   // Returns a matrix formed by the current matrix multiplied by a scalar.
   public Matrix multiply(double scalar) {
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            matrix[i][j] *= scalar;
         }
      }
      return new Matrix(matrix);
   }
   
   /* Returns an addition of the current matrix and the other one. Program will
      stop if the size does not match. */
   public Matrix add(Matrix other) {
      sizeCheck(other);
      double[][] result = new double[m][n];
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            result[i][j] = matrix[i][j] + other.matrix[i][j];
         }
      }
      return new Matrix(result);
   }
   
   // Returns a matrix formed by the current matrix subtracting the other one.
   public Matrix subtract(Matrix other) {
      return add(multiply(-1));
   }
   
   
   
   // Returns the value at a specific position.
   public double getValueFromMatrix(int row, int col) {
      return matrix[row][col];
   }
   
   // Returns the value at a specific position in the transpose.
   public double getValueFromTranspose(int row, int col) {
      return transpose[row][col];
   }
   
   // Returns the number of the row.
   public int getM() {
      return m;
   }
   
   // Returns the number of the column.
   public int getN() {
      return n;
   }
   
   public double[] getRow(int i) {
      indexCheck(m, i);
      return matrix[i - 1];
   }
   
   public double[] getColumn(int i) {
      indexCheck(n, i);
      return transpose[i - 1];
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////

/*                                       Private Methods                                         */
   
   private void isValid(int[][] matrix) {
      if (matrix != null && matrix.length > 0) {
         int col = matrix[0].length;
         for (int i = 1; i < matrix.length; i++) {
            if (col != matrix[i].length) {
               System.out.println("Invalid matrix.");
               System.out.println("The length of each row is not equal.");
               System.exit(1);
            }
         }
      } else {
         System.out.println("The matrix passed in is null.");
         System.exit(1);
      }
   }
   
   private void isValid(double[][] matrix) {
      if (matrix != null && matrix.length > 0) {
         int col = matrix[0].length;
         for (int i = 1; i < matrix.length; i++) {
            if (col != matrix[i].length) {
               System.out.println("Invalid matrix.");
               System.out.println("The length of each row is not equal.");
               System.exit(1);
            }
         }
      } else {
         System.out.println("The matrix passed in is null.");
         System.exit(1);
      }
   }
   
   // Returns the transpose of the matrix, for self-use only.
   private double[][] getTranspose(double[][] matrix) {
      double[][] transpose = new double[n][m];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            transpose[i][j] = matrix[j][i];
         }
      }
      return transpose;
   }
   
   private void print(double[][] matrix) {
      for (int i = 0; i < m; i++) {
         String row = "| ";
         for (int j = 0; j < n; j++) {
            row += "" + matrix[i][j] + " ";
         }
         row += "|";
         System.out.println(row);
      }
   }
   
   private void sizeCheck(Matrix other) {
      if (m != other.m || n != other.n) {
         System.out.println("Invalid command.");
         System.out.println("The matrices do not match in size.");
         System.exit(1);
      }
   }
   
   private void indexCheck(int range, int i) {
      if (i < 1 || i > range ) {
         System.out.println("Bad index: " + i + ".");
         System.exit(1);
      }
   }
}