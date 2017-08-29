package stacks_and_queues;

import java.util.Stack;

import org.junit.*;

import static org.junit.Assert.*;

public class SortingTests {

	@Test
	public void testIterativeSortEmpty() {
		assertTrue(isSorted(new Stack<Integer>()));
	}
	
	@Test
	public void testRecursiveSortEmpty() {
		assertTrue(isSorted(new Stack<Integer>()));
	}
	
	@Test
	public void testIterativeSortAlreadySorted() {
		Stack<Integer> s = initializeStack(new int[] {1, 2, 3, 4, 5});
		StackSorter.sortIterative(s);
		assertTrue(isSorted(s));
	}
	
	@Test
	public void testRecursiveSortAlreadySorted() {
		Stack<Integer> s = initializeStack(new int[] {1, 2, 3, 4, 5});
		StackSorter.sortRecursive(s);
		assertTrue(isSorted(s));
	}
	
	@Test
	public void testIterativeSortReverseSorted() {
		Stack<Integer> s = initializeStack(new int[] {5, 4, 3, 2, 1});
		StackSorter.sortIterative(s);
		assertTrue(isSorted(s));
	}
	
	@Test
	public void testRecursiveSortReverseSorted() {
		Stack<Integer> s = initializeStack(new int[] {5, 4, 3, 2, 1});
		StackSorter.sortRecursive(s);
		assertTrue(isSorted(s));
	}
	
	@Test
	public void testIterativeSortNotYetSorted() {
		Stack<Integer> s = initializeStack(new int[] {4, 1, 3, 5, 2});
		StackSorter.sortIterative(s);
		assertTrue(isSorted(s));
	}
	
	@Test
	public void testRecursiveSortNotYetSorted() {
		Stack<Integer> s = initializeStack(new int[] {4, 1, 3, 5, 2});
		StackSorter.sortRecursive(s);
		assertTrue(isSorted(s));
	}
	
	/* Constructs and returns a stack from the given array of 
	 * integers.  Numbers at the end of the array will appear
	 * at the bottom of the stack. */
	private Stack<Integer> initializeStack(int[] input) {
		Stack<Integer> result = new Stack<>();
		for (int i = input.length - 1; i >= 0; i--) {
			result.push(input[i]);
		}
		return result;
	}
	
	/* Returns true if the given stack is sorted and false otherwise. */
	private <E extends Comparable<E>> boolean isSorted(Stack<E> s) {
		Stack<E> aux = new Stack<>();
		boolean flag = true;
		
		while (!s.isEmpty()) {
			E first = s.pop();
			if (!s.isEmpty()) {
				E second = s.pop();
				if (first.compareTo(second) > 0) {
					flag = false;
				}
				aux.push(second);
			}
			aux.push(first);
		}
		
		return flag;
	}
	
}
