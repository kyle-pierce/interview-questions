package arrays_and_strings;

import java.util.*;

public class Strings {
	
	/* Returns true if the given String contains only unique
	 * characters, false otherwise */
	public static boolean isUnique(String s) {
		Set<Character> letters = new HashSet<Character>();
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
		return s.chars().distinct().count() == s.length();
	}
	
	

}
