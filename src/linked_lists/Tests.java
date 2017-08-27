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
	
	@Test
	public void testRemoveKToLast() {
		Node list = initializeListFromArray(new int[] {1, 2, 3, 4, 5, 6, 7});
		
		Node returned = LinkedLists.returnKthToLast(3, list);
		assertSame(5, returned.data);
		
		returned = LinkedLists.returnKthToLast(1, list);
		assertSame(7, returned.data);
		
		returned = LinkedLists.returnKthToLast(7, list);
		assertSame(1, returned.data);
	}
	
	@Test
	public void testRemoveMiddle() {
		Node list = initializeListFromArray(new int[] {1, 2, 3, 4, 5});
		Node listAfter = initializeListFromArray(new int[] {1, 2, 4, 5});
		
		Node toBeRemoved = list.next.next;
		
		LinkedLists.removeMiddleNode(toBeRemoved);
		
		assertTrue(listsAreEqual(listAfter, list));
	}
	
	@Test
	public void testPartitionAroundValuePresent() {
		Node list = initializeListFromArray(new int[] {5, 4, 3, 2, 1});
		Node listAfter = initializeListFromArray(new int[] {2, 1, 3, 5, 4});
		
		list = LinkedLists.partitionAround(list, 3);
		
		assertTrue(listsAreEqual(listAfter, list));
	}
	
	@Test
	public void testPartitionAroundValueNotPresent() {
		Node list = initializeListFromArray(new int[] {5, 2, 6, 1, 0, 4});
		Node listAfter = initializeListFromArray(new int[] {2, 1, 0, 5, 6, 4});
		
		list = LinkedLists.partitionAround(list, 3);
		assertTrue(listsAreEqual(listAfter, list));
	}
	
	@Test
	public void testReverseSumLists() {
		Node list1 = initializeListFromArray(new int[] {7, 1, 6});
		Node list2 = initializeListFromArray(new int[] {5, 9, 2});
		Node expected = initializeListFromArray(new int[] {2, 1, 9});
		
		Node observed = LinkedLists.reverseSumLists(list1, list2);
		
		assertTrue(listsAreEqual(expected, observed));
	}
	
	@Test
	public void testSumLists() {
		Node list1 = initializeListFromArray(new int[] {6, 1, 7});
		Node list2 = initializeListFromArray(new int[] {2, 9, 5});
		Node expected = initializeListFromArray(new int[] {9, 1, 2});
		
		Node observed = LinkedLists.sumLists(list1, list2);
		
		assertTrue(listsAreEqual(expected, observed));
	}
	
	@Test
	public void testFindIntersection() {
		Node list1 = initializeListFromArray(new int[] {0, 2, 4, 6});
		Node list2 = initializeListFromArray(new int[] {0, 1, 2, 3, 4, 5, 6});
		Node list3 = initializeListFromArray(new int[] {0, 2, 4, 6});
		
		Node intersection = new Node(7, new Node(8, new Node(9)));
		
		append(list1, intersection);
		append(list2, intersection);
		
		assertEquals(intersection, LinkedLists.findIntersection(list1, list2));
		assertSame(null, LinkedLists.findIntersection(list1, list3));
	}
	
	@Test
	public void testFindLoopHead() {
		Node list1 = initializeListFromArray(new int[] {1, 2, 3, 4});
		Node list2 = initializeListFromArray(new int[] {5, 6, 7, 8});
		
		Node loopEnd = new Node(7);
		Node loopStart = new Node(5, new Node(6, loopEnd));
		loopEnd.next = loopStart;
		
		append(list1, loopStart);
		
		assertEquals(loopStart, LinkedLists.findLoopHead(list1));
		assertSame(null, LinkedLists.findLoopHead(list2));
	}
	
	/* Returns true if the two lists beginning with the given nodes are equal
	 * and false otherwise. */
	private boolean listsAreEqual(Node expected, Node observed) {
		Node current1 = expected;
		Node current2 = observed;
		
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
	
	/* Appends the given node toBeAdded to the end of the list beginning
	 * with the given front node. */
	private void append(Node front, Node toBeAdded) {
		Node current = front;
		while (current != null && current.next != null) {
			current = current.next;
		}
		current.next = toBeAdded;
	}
}
