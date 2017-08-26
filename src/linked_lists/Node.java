package linked_lists;

public class Node {
	
	public int data;		// data stord in node
	public Node next;		// reference to next node in list
	
	/* Initializes a new Node with the given data. */
	public Node(int data) {
		this(data, null);
	}
	
	/* Initializes a new Node with the given data and given reference
	 * to the next Node in the list. */
	public Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}

}
