package linked_lists;

import java.util.*;

public class LinkedLists {
	
	/* Removes all duplicates from the list beginning with the given node
	 * with no restrictions on use of data structures. */
	public static void removeDuplicates(Node front) {
		if (front != null) {
			Set<Integer> seen = new HashSet<>();
			seen.add(front.data);
			
			Node current = front;
			while (current.next != null) {
				if (seen.contains(current.next.data)) {
					current.next = current.next.next;
				} else {
					seen.add(current.data);
					current = current.next;
				}
			}
		}
	}
	
	/* Removes all duplicates from the list beginning with the given node
	 * without using any extra data structures (only Node variables). */
	public static void removeDuplicatesNoStructures(Node front) {
		Node current = front;
		
		while (current != null) {
			Node dupChecker = current;
			while (dupChecker.next != null) {
				if (dupChecker.next.data == current.data) {
					dupChecker.next = dupChecker.next.next;
				} else {
					dupChecker = dupChecker.next;
				}
			}
			current = current.next;
		}
	}
	
	/* Returns the node at the kth to last index in the list beginning
	 * with the given Node. */
	public static Node returnKthToLast(int k, Node front) {
		Node toEnd = front;
		Node toReturned = front;
		
		for (int i = 0; i < k; i++) {
			if (toEnd == null) {
				return null;
			}
			toEnd = toEnd.next;
		}
		
		while (toEnd != null) {
			toEnd = toEnd.next;
			toReturned = toReturned.next;
		}
		
		return toReturned;
	}
	
	/* Removes the given node from the list it is in.  Assumes the given node
	 * is neither the front nor last node in the list. */
	public static void removeMiddleNode(Node toBeRemoved) {
		if (toBeRemoved != null && toBeRemoved.next != null) {
			Node remaining = toBeRemoved.next;
			toBeRemoved.data = remaining.data;
			toBeRemoved.next = remaining.next;
		}
	}
	
	/* Partitions the list beginning with the given node around the given value
	 * x such that all values less than x come first, then all values equal to
	 * x, then all values greater than x. */
	public static Node partitionAround(Node front, int x) {
		// initialize the needed pointers
		Node smallerFront = null; Node smallerBack = null;
		Node equalFront = null; Node equalBack = null;
		Node greaterFront = null; Node greaterBack = null;
		
		// disassemble the list into 3 parts
		while (front != null) {
			Node next = front.next;
			front.next = null;
			if (front.data < x) {
				// add to the smaller list
				if (smallerFront == null) {
					smallerBack = front;
					smallerFront = front;
				} else {
					smallerBack.next = front;
					smallerBack = front;
				}
			} else if (front.data == x) {
				// add to the equal list
				if (equalFront == null) {
					equalBack = front;
					equalFront = front;
				} else {
					equalBack.next = front;
					equalBack = front;
				}
			} else {
				// add to the greater list
				if (greaterFront == null) {
					greaterBack = front;
					greaterFront = front;
				} else {
					greaterBack.next = front;
					greaterBack = front;
				}
			}
			front = next;
		}
		
		// set the back of the 3rd list to null if not already
		if (greaterBack != null) {
			greaterBack.next = null;
		}
		
		// assemble the 3 pieces
		if (smallerFront == null) {
			// there are no nodes smaller
			if (equalFront == null) {
				// there are no nodes equal
				// set front to the list of larger elements
				front = greaterFront;
			} else {
				// there are equal nodes
				// set front to equal elements followed by larger elements
				front = equalFront;
				equalBack.next = greaterFront;
			}
		} else {
			// there are some smaller nodes
			front = smallerFront;
			if (equalFront == null) {
				// there are no equal nodes, just smaller and greater
				smallerBack.next = greaterFront;
			} else {
				// there are all 3 classifications of elements
				smallerBack.next = equalFront;
				equalBack.next = greaterFront;
			}
		}
		return front;
	}
	
	/* Given two lists representing numbers stored in reverse order,
	 * returns the list that is the reverse of the sum of those
	 * two numbers.   */
	public static Node reverseSumLists(Node list1, Node list2) {
		return numberToListReverse(listToNumberReverse(list1, 1) + 
								   listToNumberReverse(list2, 1));
	}
	
	/* Converts a list to a number in reverse. */
	private static int listToNumberReverse(Node front, int factor) {
		if (front == null) {
			return 0;
		} else {
			return factor * front.data + listToNumberReverse(front.next, factor * 10);
		}
	}
	
	/* Converts a number to list in reverse. */
	private static Node numberToListReverse(int number) {
		if (number == 0) {
			return null;
		} else {
			Node node = new Node(number % 10, numberToListReverse(number / 10));
			return node;
		}
	}
	
	/* Returns a list which is the sum of the two given lists. */
	public static Node sumLists(Node list1, Node list2) {
		return numberToList(listToNumber(list1) + listToNumber(list2));
	}
	
	/* Converts the list beginning with the given node to a number. */
	private static int listToNumber(Node front) {
		StringBuilder builder = new StringBuilder();
		
		Node current = front;
		while (current != null) {
			builder.append(current.data);
			current = current.next;
		}
		
		return Integer.parseInt(builder.toString());
	}
	
	/* Returns a list representing the given number. */
	private static Node numberToList(int number) {
		String digits = String.valueOf(number);
		
		Node front = null;
		for (int i = digits.length() - 1; i >= 0; i--) {
			// 48 is the ASCII value for 0
			front = new Node(digits.charAt(i) - 48, front);
		}
		
		return front;
	}
}
