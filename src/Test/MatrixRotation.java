package Test;

public class MatrixRotation {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Original Matrix:");
        printMatrix(matrix);

        int[][] rotated90 = rotateMatrix90(matrix);
        System.out.println("Rotated Matrix by 90 degrees clockwise:");
        printMatrix(rotated90);

		/*
		 * int[][] rotated180 = rotateMatrix180(matrix);
		 * System.out.println("Rotated Matrix by 180 degrees clockwise:");
		 * printMatrix(rotated180);
		 * 
		 * int[][] rotated270 = rotateMatrix270(matrix);
		 * System.out.println("Rotated Matrix by 270 degrees clockwise:");
		 * printMatrix(rotated270);
		 */
    }

    public static int[][] rotateMatrix90(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	System.out.println( matrix[i][j]+" result[j][n - 1 - i] "+result[j][n - 1 - i]);
                result[j][n - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][] rotateMatrix180(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[n - 1 - i][n - 1 - j] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][] rotateMatrix270(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[n - 1 - j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.print(elem + " ");
            }
            System.out.println();
        }
    }
}
