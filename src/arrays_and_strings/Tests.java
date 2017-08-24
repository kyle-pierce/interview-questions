package arrays_and_strings;

import org.junit.*;

import static org.junit.Assert.*;

public class Tests {

	@Test
	public void testIsUniqueBasic() {
		assertFalse(Strings.isUnique("hello"));
		assertTrue(Strings.isUnique("abcdefg"));
		
		assertFalse(Strings.isUniqueNoExtraStructures("hello"));
		assertTrue(Strings.isUniqueNoExtraStructures("abcdefg"));
		
		assertFalse(Strings.isUniqueJavaEight("hello"));
		assertTrue(Strings.isUniqueJavaEight("abcdefg"));
	}
	
	@Test
	public void testIsUniquePunctuation() {
		assertFalse(Strings.isUnique("!!..??"));
		assertTrue(Strings.isUnique("hey!?."));
		
		assertFalse(Strings.isUniqueNoExtraStructures("!!..??"));
		assertTrue(Strings.isUniqueNoExtraStructures("hey!?."));
		
		assertFalse(Strings.isUniqueJavaEight("!!..??"));
		assertTrue(Strings.isUniqueJavaEight("hey!?."));
	}
	
	@Test
	public void testArePermutationsBasic() {
		assertFalse(Strings.arePermutations("hello", "elhl"));
		assertTrue(Strings.arePermutations("hello", "elhlo"));
		
		assertFalse(Strings.arePermutationsASCII("hello", "elhl"));
		assertTrue(Strings.arePermutationsASCII("hello", "elhlo"));
	}
	
	@Test
	public void testArePermutationsPunctuation() {
		assertFalse(Strings.arePermutations("hello!", "elhlo"));
		assertTrue(Strings.arePermutations("!hel!lo", "!elhlo!"));
		
		assertFalse(Strings.arePermutationsASCII("h!ello", "elhlo"));
		assertTrue(Strings.arePermutationsASCII("hello!!", "el!!hlo"));
	}
	
	@Test
	public void testToURL() {
		assertEquals("Java%20is%20fun!",
				Strings.toURL("Java is fun!    ", 12));
		assertEquals("Nospaceshere!", 
				Strings.toURL("Nospaceshere!", 13));
	}
	
	@Test
	public void testIsPermutationOfPalindrome() {
		assertFalse(Strings.isPermutationOfPalindrome("hello world!"));
		assertTrue(Strings.isPermutationOfPalindrome("tactcoa"));
		
		assertFalse(Strings.isPermutationOfPalindromeASCII("hello world!"));
		assertTrue(Strings.isPermutationOfPalindromeASCII("tactcoa"));
	}
	
	@Test
	public void testOneAwayReplacement() {
		assertTrue(Strings.oneAway("abcdefg", "abkdefg"));
		assertFalse(Strings.oneAway("abcdefg", "ajkdefg"));
	}
	
	@Test
	public void testOneAwayInsertion() {
		assertTrue(Strings.oneAway("abdefg", "abcdefg"));
		assertFalse(Strings.oneAway("abcde", "abcdefg"));
		assertFalse(Strings.oneAway("abcdef", "abkdefg"));
	}
	
	@Test
	public void testOneAwayRemoval() {
		assertTrue(Strings.oneAway("abcdefg", "abcefg"));
		assertFalse(Strings.oneAway("abcdefg", "acdfg"));
		assertFalse(Strings.oneAway("abcdefh", "acdefg"));
	}
	
	@Test
	public void testCompress() {
		assertEquals(Strings.compress("abcdefg"), "abcdefg");
		assertEquals(Strings.compress("aaaaaaa"), "a7");
	}
	
	@Test
	public void testRotateSquareMatrix() {
		int[][] matrix = {{0, 0, 0, 0}, 
						  {1, 1, 1, 1},
						  {2, 2, 2, 2},
						  {3, 3, 3, 3}};
		Array.rotateSquareMatrix(matrix);
		int[][] after = {{3, 2, 1, 0},
					     {3, 2, 1, 0},
					     {3, 2, 1, 0},
					     {3, 2, 1, 0}};
		
		assertTrue(equals(after, matrix));
	}
	
	/* Returns true if the two non-empty square matrices contain
	 * the same elements in the same locations. */
	private boolean equals(int[][] expected, int[][] observed) {
		for (int row = 0; row < expected.length; row++) {
			for (int col = 0; col < expected.length; col++) {
				if (expected[row][col] != observed[row][col]) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Test
	public void testZeroMatrix() {
		int[][] matrix = {{1, 1, 1, 1, 1},
						  {1, 1, 1, 1, 1},
						  {1, 1, 0, 1, 1},
						  {1, 1, 1, 1, 1},
						  {1, 1, 1, 1, 1}};
		int [][] zeroedMatrix = {{1, 1, 0, 1, 1},
				  				 {1, 1, 0, 1, 1},
				  				 {0, 0, 0, 0, 0},
				  				 {1, 1, 0, 1, 1},
				  				 {1, 1, 0, 1, 1}};
		
		Array.zeroMatrix(matrix);
		assertTrue(equals(zeroedMatrix, matrix));
	}
	
	@Test
	public void testIsRotation() {
		assertTrue(Strings.isRotation("waterbottle", "terbottlewa"));
		assertFalse(Strings.isRotation("terbottlew", "waterbottle"));
		assertFalse(Strings.isRotation("waterbottle", "terbottlewb"));
	}
}
