package stacks_and_queues;

import java.util.Stack;

/**
 * Mimics a simple Queue using implemented with two stacks.
 * 
 * @author Kyle
 *
 * @param <E> element type
 */
public class MyQueue<E> {
	
	private Stack<E> forward, reverse;
	
	/**
	 * Initializes a new MyQueue.
	 */
	public MyQueue() {
		forward = new Stack<>();
		reverse = new Stack<>();
	}
	
	/**
	 * Adds the given element to the back of this.
	 * 
	 * @param element given element to be added.
	 * @return the given element.
	 */
	public E add(E element) {
		forward.push(element);
		return element;
	}
	
	/**
	 * Removes and returns the first element added to this.
	 * 
	 * @return the first element added to this.
	 */
	public E remove() {
		fillReverse();
		return reverse.pop();
	}
	
	/**
	 * Returns the first element added to this.
	 * 
	 * @return
	 */
	public E peek() {
		fillReverse();
		return reverse.peek();
	}
	
	/**
	 * Returns a boolean indicating whether or not there are any elements
	 * in this queue.
	 * 
	 * @return true if this is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return forward.isEmpty() && reverse.isEmpty();
	}
	
	/* If the reverse stack is empty, empties the elements from the forward
	 * stack into it so the top element in reverse can be peeked at
	 * and/or removed quickly. */
	private void fillReverse() {
		if (reverse.isEmpty()) {
			while (!forward.isEmpty()) {
				reverse.push(forward.pop());
			}
		}
	}

}
