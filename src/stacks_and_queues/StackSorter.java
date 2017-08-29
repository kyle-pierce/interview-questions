package stacks_and_queues;

import java.util.Stack;

public class StackSorter {
	
	/* Sorts the given stack of comparable elements with smaller elements 
	 * being stored at the top of the stack. */
	public static <E extends Comparable<E>> void sortIterative(Stack<E> stack) {
		Stack<E> aux = new Stack<>();
		
		while (!stack.isEmpty()) {
			aux.push(stack.pop());
		}
		
		while (!aux.isEmpty()) {
			E top = aux.pop();
			sortedInsert(stack, aux, top);
		}
	}
	
	/* Transfers element from stack into aux until the given element can be added
	 * into stack without breaking the sorted ordering. */
	private static <E extends Comparable<E>> void sortedInsert(Stack<E> stack, 
															   Stack<E> aux, E element) {
		while (!stack.isEmpty() && stack.peek().compareTo(element) < 0) {
			aux.push(stack.pop());
		}
		stack.push(element);
	}

	/* Sorts the given stack of comparable elements with smaller elements 
	 * being stored at the top of the stack. */
	public static <E extends Comparable<E>> void sortRecursive(Stack<E> stack) {
		if (!stack.isEmpty()) {
			E top = stack.pop();
			sortRecursive(stack);
			sortedInsertRecursive(stack, top);
		}
	}
	
	/* Inserts the given element into the stack in the sorted position. Smaller
	 * elements will appear closer to the top of the stack. */
	private static <E extends Comparable<E>> void sortedInsertRecursive(Stack<E> stack,
													   		   			E element) {
		if (stack.isEmpty() || stack.peek().compareTo(element) > 0) {
			stack.push(element);
		} else {
			E top = stack.pop();
			sortedInsertRecursive(stack, element);
			stack.push(element);
		}
	}
	
}
