package stacks_and_queues;

import java.util.EmptyStackException;

/* Holds three stacks which can be accessed by their respective numbers. 
 * Standard stack operations are supported: push, pop, peek, isEmpty  */
public class TriStack {
	
	private int[] elementData;		// holds all elements in the three stacks
	private StackInfo[] stacks;		// references to information on the stacks
	
	public static final int DEFAULT_STACK_CAPACITY = 10;
	public static final int NUMBER_OF_STACKS = 3;
	
	/* Initializes a new TriStack with three stack of the default capacity. */
	public TriStack() {
		this(DEFAULT_STACK_CAPACITY);
	}
	
	/* Initializes a new TriStack with three stacks of the given capacity. */
	public TriStack(int stackCapacity) {
		elementData = new int[NUMBER_OF_STACKS * stackCapacity];
		stacks = new StackInfo[NUMBER_OF_STACKS];
		for (int i = 0; i < NUMBER_OF_STACKS; i++) {
			stacks[i] = new StackInfo(stackCapacity * i, stackCapacity);
		}
	}
	
	/* Pushes the given element onto the stack with the given number. */
	public void push(int element, int stackNumber) {
		StackInfo stack = stacks[stackNumber];
		
		if (stack.isFull()) {
			expand(stackNumber);
		}
		
		stack.size++;
		elementData[stack.lastFilledIndex()] = element;
	}
	
	/* Doubles the capacity of the stack with the given number. */
	private void expand(int stackNumber) {
		int priorCapacity = stacks[stackNumber].capacity;
		int[] newElementData = new int[elementData.length + priorCapacity];
		int indexOffset = 0;
		
		// loop over all the stored stacks
		for (int i = 0; i < NUMBER_OF_STACKS; i++) {
			StackInfo stack = stacks[i];
			
			// copy the values into the new array 
			for (int j = stack.start; j < stack.lastFilledIndex(); j++) {
				newElementData[j + indexOffset] = elementData[j];
			}
			
			// for all stacks right of the indicated stack, shift all elements to
			// the right to make room for the expanded stack
			if (i == stackNumber) {
				indexOffset = priorCapacity;
			}
		}
		
		// update the elementData and stored stack capacity
		elementData = newElementData;
		stacks[stackNumber].capacity *= 2;
	}
	
	/* Returns the value on top of the stack indicated by the given stackNumber. */
	public int peek(int stackNumber) {
		StackInfo stack = stacks[stackNumber];
		
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		
		return elementData[stack.lastFilledIndex()];
	}
	
	/* Removes and returns the value on top of the stack indicated by the
	 * given stackNumber. */
	public int pop(int stackNumber) {
		StackInfo stack = stacks[stackNumber];
		
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		
		stack.size--;
		return elementData[stack.lastFilledIndex() + 1];
	}
	
	/* Returns true if the indicated stack is empty and false otherwise. */
	public boolean isEmpty(int stackNumber) {
		return stacks[stackNumber].isEmpty();
	}
	
	/* Class holding information on an array implementation of a stack. */
	private static class StackInfo {
		
		public int start, size, capacity;
		
		/* Initializes a StackInfo with the given start index and initial capacity. */
		public StackInfo(int start, int capacity) {
			this.start = start;
			this.capacity = capacity;
			size = 0;
		}
		
		/* Returns the last filled index in the associated stack. */
		public int lastFilledIndex() {
			return start + size - 1;
		}
		
		/* Returns true if the associated stack is empty, false otherwise. */
		public boolean isEmpty() {
			return size == 0;
		}
		
		/* Returns true if the associated stack is full, false otherwise. */
		public boolean isFull() {
			return size == capacity;
		}
	}
	
}
