package arrays_and_strings;

import java.util.*;

public class Strings {
	
	public static void main(String[] args) {
		System.out.println(isUnique("hello") + " == false?");
		System.out.println(isUnique("abcdefg") + " == true?");
		
		System.out.println(isUniqueNoExtraStructures("still works") + " == false?");
		System.out.println(isUniqueNoExtraStructures("oh yea") + " == true?");
		
		System.out.println(isUniqueJavaEight("with eight?") + " == false?");
		System.out.println(isUniqueJavaEight("yes!") + " == true?");
		
		System.out.println(arePermutations("hello", "elhl") + " == false?");
		System.out.println(arePermutations("hello", "elhlo") + " == true?");
		
		System.out.println(arePermutationsASCII("hello", "elhl") + " == false?");
		System.out.println(arePermutationsASCII("hello", "elhlo") + " == true?");
		
		String first = "Java is fun!    ";
		String second = "Nospaceshere!";
		
		System.out.println(first + " -> " + toURL(first, 12));
		System.out.println(second + " -> " + toURL(second, 13));
	}
	
	/* Returns true if the given String contains only unique
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
	
	/* Returns true if the given String contains only unique
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
	
	/* Returns true if the given String contains only unique
	 * characters, false otherwise using Java 8 features */
	public static boolean isUniqueJavaEight(String s) {
		return s.chars().parallel().distinct().count() == s.length();
	}
	
	/* Returns true if the two given Strings are permutations and
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
	
	/* Returns true if the two given Strings are permutations and
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
	
	/* Replaces every space in the given String with '%20' using the
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
}
