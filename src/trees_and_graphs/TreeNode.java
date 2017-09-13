package trees_and_graphs;

public class TreeNode {
	
	public int data;
	public TreeNode[] children;
	
	// only usable when specified in problem
	public TreeNode parent;
	
	public TreeNode(int data) {
		this.data = data;
		children = new TreeNode[10];
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else {
			return other instanceof TreeNode && ((TreeNode)other).data == data;
		}
	}
	
	@Override
	public int hashCode() {
		return data;
	}

}
