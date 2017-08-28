package stacks_and_queues;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Represents a collection of stacks each of which is kept under
 * a certain capacity.
 * 
 * @author Kyle
 *
 * @param <E> type of elements inside the sub-stacks
 */
public class SetOfStacks<E> {
	
	private Stack<Stack<E>> stacks;
	private int maxCapacity;
	private int currentCapacity;
	
	/**
	 * Initializes a SetOfStacks with the given maximum capacity
	 * for each of the sub-stacks.
	 * 
	 * @param maxCapacity maximum capacity for each stack.
	 * @throws IllegalArgumentException if the given max capacity
	 * is less than 1.  
	 */
	public SetOfStacks(int maxCapacity) {
		if (maxCapacity < 1) {
			throw new IllegalArgumentException();
		}
		
		stacks = new Stack<>();
		stacks.push(new Stack<E>());
		this.maxCapacity = maxCapacity;
	}
	
	/**
	 * Pushes the given element onto the top of the stack
	 * which contains less elements than the max capacity.
	 * 
	 * @param element element to be added to the stack
	 * @return the given element
	 */
	public E push(E element) {
		// if this stack is full, make a new one
		if (currentCapacity == maxCapacity) {
			stacks.push(new Stack<E>());
			currentCapacity = 0;
		}
		// push onto the top of the top stack
		stacks.peek().push(element);
		currentCapacity++;
		
		return element;
	}
	
	/**
	 * Removes the topmost element in this set of stacks.
	 * 
	 * @return the topmost element in this set of stacks.
	 * @throws EmptyStackException if there are no elements to pop.
	 */
	public E pop() {
		E element = stacks.peek().pop();
		if (stacks.peek().isEmpty()) {
			stacks.pop();
		}
		return element;
	}
	
	/**
	 * Returns the topmost element in this set of stacks.
	 * 
	 * @return the topmost element in this set of stacks.
	 */
	public E peek() {
		return stacks.peek().peek();
	}
	
	/**
	 * Returns a boolean indicating whether or not this set
	 * of stacks is empty.
	 * 
	 * @return true if this set of stacks is empty, false otherwise
	 */
	public boolean isEmpty() {
		return stacks.isEmpty() || stacks.peek().isEmpty();
	}

}
