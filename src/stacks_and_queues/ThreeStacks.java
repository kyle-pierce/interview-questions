package stacks_and_queues;

public class ThreeStacks {
	
	private int[] elementData;
	private int size1;
	private int size2;
	private int size3;
	
	public static int DEFAULT_CAPACITY;
	
	/* Initializes 3 stacks with the DEFAULT_CAPACITY. */
	public ThreeStacks() {
		this(DEFAULT_CAPACITY);
	}
	
	/* Initializes three stacks with the given initial capacity for 
	 * each of the three stacks. */
	public ThreeStacks(int initialCapacity) {
		elementData = new int[initialCapacity * 3];
		size1 = size2 = size3 = 0;
	}
	
	/* Removes and returns the top element of the first stack. */
	public int pop1() {
		return -1;
	}
	
	/* Removes and returns the top element of the second stack. */
	public int pop2() {
		return -1;
	}
	
	/* Removes and returns the top element of the third stack. */
	public int pop3() {
		return -1;
	}
	
	/* Returns the top element of the first stack. */
	public int peek1() {
		return -1;
	}
	
	/* Returns the top element of the second stack. */
	public int peek2() {
		return -1;
	}
	
	/* Returns the top element of the third stack. */
	public int peek3() {
		return -1;
	}
	
	/* Adds the given element to the top of the first stack. */
	public void push1(int element) {
		
	}
	
	/* Adds the given element to the top of the second stack. */
	public void push2(int element) {
		
	}
	
	/* Adds the given element to the top of the third stack. */
	public void push3(int element) {
		
	}
	
	/* Returns true if the first stack is empty, false otherwise. */
	public boolean isEmpty1() {
		return size1 == 0;
	}
	
	/* Returns true if the second stack is empty, false otherwise. */
	public boolean isEmpty2() {
		return size2 == 0;
	}
	
	/* Returns true if the third stack is empty, false otherwise. */
	public boolean isEmpty3() {
		return size3 == 0;
	}
	
}
