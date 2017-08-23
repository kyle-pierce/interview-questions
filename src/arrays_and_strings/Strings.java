package arrays_and_strings;

import java.util.*;
import java.util.stream.IntStream;

public class Strings {
	
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

	
	
}
