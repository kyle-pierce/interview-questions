package arrays_and_strings;

import java.util.*;

public class Strings {
	
	/* 1.0: Returns true if the given String contains only unique
	 * characters, false otherwise */
	public static boolean isUnique(String s) {
		Set<Character> letters = new HashSet<>();
		
		for (int i = 0; i < s.length(); i++) {
			if (letters.contains(s.charAt(i))) {
				return false;
			}
			letters.add(s.charAt(i));
		}
		
		return true;
	}
	
	/* 1.1: Returns true if the given String contains only unique
	 * characters, false otherwise using no extra structures */
	public static boolean isUniqueNoExtraStructures(String s) {
		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);
			for (int j = i + 1; j < s.length(); j++) {
				if (letter == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/* 1.2: Returns true if the given String contains only unique
	 * characters, false otherwise using Java 8 features */
	public static boolean isUniqueJavaEight(String s) {
		return s.chars().parallel().distinct().count() == s.length();
	}
	
	/* 2.0: Returns true if the two given Strings are permutations and
	 * false otherwise using sorting */
	public static boolean arePermutations(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		return sort(s1).equals(sort(s2));
	}
	
	/* Returns the sorted version of the given String */
	private static String sort(String s) {
		char[] letters = s.toCharArray();
		java.util.Arrays.sort(letters);
		return new String(letters);
	}
	
	/* 2.1: Returns true if the two given Strings are permutations and
	 * false otherwise.  Assumes the Strings contain only ASCII 
	 * characters but is faster than sorting */
	public static boolean arePermutationsASCII(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		
		char[] letters = new char[128];
		
		for (int i = 0; i < s1.length(); i++) {
			letters[s1.charAt(i)]++;
			letters[s2.charAt(i)]--;
		}
		
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/* 3.0: Replaces every space in the given String with '%20' using the
	 * given length of the String.  Assumes there is enough space
	 * at the end of the String after the trueLength for this operation.
	 * Returns the created String */
	public static String toURL(String s, int trueLength) {
		char[] letters = s.toCharArray();
		
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == ' ') {
				makeRoomAt(letters, i);
				letters[i] = '%';
				letters[i + 1] = '2';
				letters[i + 2] = '0';
			}
		}
		
		return new String(letters);
	}
	
	/* Makes room in letters at index to insert a 3-character String
	 * in place of the assumed space */
	private static void makeRoomAt(char[] letters, int index) {
		for (int i = letters.length - 1; i > index + 2; i--) {
			letters[i] = letters[i - 2];
		}
	}
	
	/* 4.1: Returns true if the given String is a permutation of a palindrome.
	 * No assumptions make about the characters in the String. */
	public static boolean isPermutationOfPalindrome(String s) {
		Map<Character, Boolean> switches = new HashMap<>();
		
		// make a map from character -> boolean
		//   true indicates the character was seen an odd number of times
		//   false indicates the character was seen an even number of times
		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);
			if (!switches.containsKey(letter)) {
				switches.put(letter, true);
			} else {
				switches.put(letter, !switches.get(letter));
			}
		}
		
		boolean trueSeen = false;
		
		// if more than one character was seen an odd number times, 
		// the String cannot be the permutation of a palindrome
		for (Character ch : switches.keySet()) {
			if (switches.get(ch) && !trueSeen) {
				trueSeen = true;
			} else if (switches.get(ch)) {
				return false;
			}
		}
		return true;
	}
	
	/* 4.1: Returns true if the given String is a permutation of a palindrome.
	 * Assumes the given String contains only ASCII characters */
	public static boolean isPermutationOfPalindromeASCII(String s) {
		boolean[] switches = new boolean[128];
		
		// true indicates the character was seen an odd number of times
		// false indicates the character was seen an even number of times
		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);
			switches[letter] = !switches[letter];
		}
		
		boolean trueSeen = false;
		
		// if more than one character was seen an odd number times, 
		// the String cannot be the permutation of a palindrome
		for (int i = 0; i < switches.length; i++) {
			if (switches[i] && !trueSeen) {
				trueSeen = true;
			} else if (switches[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/* Returns true if either one replacement, one insertion or one
	 * removal would turn the given s1 into the given s2, false otherwise */
	public static boolean oneAway(String s1, String s2) {
		if (Math.abs(s1.length() - s2.length()) > 1) {
			return false;
		}
		
		String shorter = s1.length() <= s2.length() ? s1 : s2;
		String longer = s1.length() <= s2.length() ? s2: s1;
		
		boolean oneDifference = false;
		
		for (int i = 0, j = 0; i < shorter.length(); i++, j++) {
			char shortChar = shorter.charAt(i);
			char longChar = longer.charAt(j);
			if (shortChar != longChar && oneDifference) {
				return false;
			} else if (shortChar != longChar) {
				oneDifference = true;
				
				if (shorter.length() != longer.length()) {
					i--;
				}
			}
		}
		
		return true;
	}
	
	/* Attempts to compress the given String.  If the compressed length is
	 * not shorter than the original String, the original is returned.  Otherwise
	 * returns a compressed version of the String. */
	public static String compress(String s) {
		int compressedLength = getCompressedLength(s);
		
		if (s.length() <= compressedLength) {
			return s;
		}
		
		StringBuilder compressed = new StringBuilder(compressedLength);
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			count++;
			if (i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
				compressed.append(s.charAt(i));
				compressed.append(count);
				count = 0;
			}
		}
		
		return compressed.toString();
	}
	
	/* Returns the length of the compressed version of the given String */
	private static int getCompressedLength(String s) {
		int compressedLength = 0;
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			count++;
			if (i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
				compressedLength += String.valueOf(count).length() + 1;
				count = 0;
			}
		}
		
		return compressedLength;
	}
}
