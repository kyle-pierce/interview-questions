package trees_and_graphs;

/**
 * Represents a node in an n-ary tree, storing data and links
 * to the children.  
 * 
 * @author Kyle
 */
public class TreeNode<E> {
	
	public static final int DEFAULT_CHILDREN = 2;
	
	public E data;
	public TreeNode<E>[] children;
	
	// only usable when specified in problem
	public TreeNode<E> parent;
	
	/**
	 * Constructs a new TreeNode with the given data.
	 * 
	 * @param data data to be stored in this node
	 */
	public TreeNode(E data) {
		this(data, null, null, null);
	}
	
	/**
	 * Constructs a new TreeNode with the given data and parent.
	 * 
	 * @param data data to be stored in this node
	 * @param parent parent node of the node to be constructed
	 */
	public TreeNode(E data, TreeNode<E> parent) {
		this(data, null, null, parent);
	}
	
	/** 
	 * Constructs a new TreeNode with the given data and children.
	 * 
	 * @param data data to be stored in this node
	 * @param left TreeNode which should be the left child
	 * @param right TreeNode which should be the right child
	 */
	public TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
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
	@SuppressWarnings("unchecked")
	public TreeNode(E data, TreeNode<E> left, TreeNode<E> right, 
					TreeNode<E> parent) {
		this.data = data;
		children = (TreeNode<E>[]) new Object[DEFAULT_CHILDREN];
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
			return other instanceof TreeNode<?> && 
					this.data.equals(((TreeNode<?>)other).data);
		}
	}
	
	@Override
	public int hashCode() {
		return data.hashCode();
	}

}