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
	public static Node removeKthToLast(int k, Node front) {
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
	
}
