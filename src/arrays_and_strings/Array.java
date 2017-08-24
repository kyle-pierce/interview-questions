package arrays_and_strings;

public class Array {
	
	/* Rotates the given square matrix by 90 degrees clockwise */
	public static void rotateSquareMatrix(int[][] matrix) {
		for (int layer = 0; layer < matrix.length / 2; layer++) {
			int from = layer;
			int to = matrix.length - layer - 1;
			
			for (int i = from; i < to; i++) {
				int temp = matrix[from][i];
				
				matrix[from][i] = matrix[to - i][from];		// UL <- LL
				matrix[to - i][from] = matrix[to][to - i];	// LL <- LR
				matrix[to][to - i] = matrix[from + i][to];	// LR <- UR
				matrix[from + i][to] = temp;				// UR <- UL
			}
		}
	}
	
	/* If any element in the given matrix is 0, sets all elements in
	 * the same row and column to 0.  No extra space needed! */
	public static void zeroMatrix(int[][] matrix) {
		boolean firstRowNeedsZeros = false;
		boolean firstColNeedsZeros = false;
		
		// check if there are 0's in the first row
		for (int col = 0; col < matrix[0].length && !firstRowNeedsZeros; col++) {
			if (matrix[0][col] == 0) {
				firstRowNeedsZeros = true;
			}
		}
		
		// check if there are 0's in the first column
		for (int row = 0; row < matrix.length && !firstColNeedsZeros; row++) {
			if (matrix[row][0] == 0) {
				firstColNeedsZeros = true;
			}
		}
		
		// check each elements in the matrix; if it is a 0:
		//    put a 0 in this row of the first column
		//    put a 0 in this column of the first row
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == 0) {
					matrix[row][0] = 0;
					matrix[0][col] = 0;
				}
			}
		}
		
		// zero any row for which there is a 0 in the first column except the first
		for (int row = 1; row < matrix.length; row++) {
			if (matrix[row][0] == 0) {
				zeroRow(matrix, row);
			}
		}
		
		// zero any column for which there is a 0 in the first row except the first
		for (int col = 1; col < matrix[0].length; col++) {
			if (matrix[0][col] == 0) {
				zeroCol(matrix, col);
			}
		}
		
		// zero the entire first row if it originally had a 0
		if (firstRowNeedsZeros) {
			zeroRow(matrix, 0);
		}
		
		// zero the entire first column if it originally had a 0
		if (firstColNeedsZeros) {
			zeroCol(matrix, 0);
		}
		
	}
	
	/* Zeros the given row in the given matrix */
	private static void zeroRow(int[][] matrix, int row) {
		for (int col = 0; col < matrix[0].length; col++) {
			matrix[row][col] = 0;
		}
	}
	
	/* Zeros the given column in the given matrix */
	private static void zeroCol(int[][] matrix, int col) {
		for (int row = 0; row < matrix.length; row++) {
			matrix[row][col] = 0;
		}
	}

}
