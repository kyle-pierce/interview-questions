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

}
