package trees_and_graphs;

public class Node {
	
	public int data;
	public Node[] children;
	
	// only usable when specified in problem
	public Node parent;
	
	public Node(int data) {
		this.data = data;
		children = new Node[10];
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
			return other instanceof Node && ((Node)other).data == data;
		}
	}
	
	@Override
	public int hashCode() {
		return data;
	}

}
