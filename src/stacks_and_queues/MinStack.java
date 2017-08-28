package stacks_and_queues;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A MinStack is a Stack which also allows the client to view the minimum
 * element in constant time.
 * 
 * @author Kyle
 *
 * @param <E> types of elements stored in this stack
 */
public class MinStack<E extends Comparable<E>> extends Stack<E> {

	private static final long serialVersionUID = 2926778671600061968L;
	
	private Stack<E> minValues;		// holds the history of minimum values

	/**
	 * Initializes a new MinStack.
	 */
	public MinStack() {
		super();
		minValues = new Stack<E>();
	}
	
	@Override
	public E push(E value) {
		if (minValues.isEmpty() || value.compareTo(min()) < 0) {
			minValues.push(value);
		}
		return super.push(value);
	}
	
	@Override
	public E pop() {
		E value = super.pop();
		if (value.equals(min())) {
			minValues.pop();
		}
		return value;
	}
	
	/**
	 * Returns the minimum value in this stack.
	 * 
	 * @return the minimum value in this stack
	 * @throws EmptyStackException if this stack is empty.
	 */
	public E min() {
		return minValues.peek();
	}
}
