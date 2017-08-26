package linked_lists;

import org.junit.*;

import static org.junit.Assert.*;

public class Tests {
	
	@Test
	public void testRemoveDuplicatesNoRestrictions() {
		Node list = initializeListFromArray(new int[] {1, 5, 3, 5, 7, 3});
		Node after = initializeListFromArray(new int[] {1, 5, 3, 7});
		
		LinkedLists.removeDuplicates(list);
		
		assertTrue(listsAreEqual(list, after));
	}
	
	@Test
	public void testRemoveDuplicatesNoExtraStructures() {
		Node list = initializeListFromArray(new int[] {1, 1, 1, 5, 3, 7, 1, 3, 5, 7});
		Node after = initializeListFromArray(new int[] {1, 5, 3, 7});
		
		LinkedLists.removeDuplicatesNoStructures(list);
		
		assertTrue(listsAreEqual(list, after));
	}
	
	/* Returns true if the two lists beginning with the given nodes are equal
	 * and false otherwise. */
	private boolean listsAreEqual(Node front1, Node front2) {
		Node current1 = front1;
		Node current2 = front2;
		
		// compare values in the two lists
		while (current1 != null && current2 != null) {
			if (current1.data != current2.data) {
				return false;
			}
			current1 = current1.next;
			current2 = current2.next;
		}
		
		// if the lists were different length, not equal
		if (current1 != null || current2 != null) {
			return false;
		}
		
		// passed all tests so lists are equal
		return true;
	}
	
	/* Returns the front of a LinkedList corresponding to the elements in the 
	 * given array. */
	private Node initializeListFromArray(int[] values) {
		Node front = null;
		for (int i = values.length - 1; i >= 0; i--) {
			front = new Node(values[i], front);
		}
		return front;
	}

}
