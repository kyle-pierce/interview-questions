package trees_and_graphs;

/**
 * Represents a node in an n-ary tree, storing data and links
 * to the children.  
 * 
 * @author Kyle
 */
public class TreeNode {
	
	public static final int DEFAULT_CHILDREN = 2;
	
	public int data;
	public TreeNode[] children;
	
	// only usable when specified in problem
	public TreeNode parent;
	
	/**
	 * Constructs a new TreeNode with the given data.
	 * 
	 * @param data data to be stored in this node
	 */
	public TreeNode(int data) {
		this(data, null, null, null);
	}
	
	/**
	 * Constructs a new TreeNode with the given data and parent.
	 * 
	 * @param data data to be stored in this node
	 * @param parent parent node of the node to be constructed
	 */
	public TreeNode(int data, TreeNode parent) {
		this(data, null, null, parent);
	}
	
	/** 
	 * Constructs a new TreeNode with the given data and children.
	 * 
	 * @param data data to be stored in this node
	 * @param left TreeNode which should be the left child
	 * @param right TreeNode which should be the right child
	 */
	public TreeNode(int data, TreeNode left, TreeNode right) {
		this(data, left, right, null);
	}
	
	/**
	 * Constructs a new TreeNode with the given data and left and
	 * right children.
	 * 
	 * @param data data to be stored in the node
	 * @param left TreeNode which should be the left child
	 * @param right TreeNode which should be the right child
	 * @boolean parent the parent of this node or null if this node
	 * should not store its parent
	 */
	public TreeNode(int data, TreeNode left, TreeNode right, TreeNode parent) {
		this.data = data;
		children = new TreeNode[DEFAULT_CHILDREN];
		children[0] = left;
		children[1] = right;
		this.parent = parent;
	}
	
	/**
	 * Returns true if this node is a leaf, false otherwise.
	 * 
	 * @return true if this node has only null children and 
	 * false otherwise.  
	 */
	public boolean isLeaf() {
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				return false;
			}
		}
		return true;
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
