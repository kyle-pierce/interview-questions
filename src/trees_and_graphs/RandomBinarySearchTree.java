package trees_and_graphs;

import java.util.Random;

/**
 * This class represents a Binary Search Tree in which a Random Node can
 * be requested by the client.
 * 
 * @author Kyle
 *
 * @param <E> Type of data stored in the nodes
 */
public class RandomBinarySearchTree<E extends Comparable<E>> {
	private RandomTreeNode<E> overallRoot;
	
	/**
	 * Constructs an empty RandomBinarySearchTree.
	 */
	public RandomBinarySearchTree() {
	    overallRoot = null;
	}
	
	/**
	 * Inserts the given element into this tree in the proper location. If
	 * the element is already contained in this tree, nothing will happen.
	 * 
	 * @param element element to be added to this tree
	 */
	public void insert(E element) {
	    overallRoot = insert(overallRoot, element);
	}
	
	/* Given a root and an element, inserts the element at the proper
	 * location in the tree with the given root. Returns the updated
	 * reference to the root of the tree. */
	private RandomTreeNode<E> insert(RandomTreeNode<E> root, E element) {
	    if (root == null) {
		root = new RandomTreeNode<E>(element);
	    } else if (element.compareTo(root.data) < 0) {
		root.left = insert(root.left, element);
		root.incrementSize();
	    } else if (element.compareTo(root.data) > 0) {
		root.right = insert(root.right, element);
		root.incrementSize();
	    } 
	    return root;
	}
 	
	/**
	 * Removes the given element from this tree.  If the element is
	 * not contained in this tree, nothing happens.
	 * 
	 * @param element element to be removed from this tree
	 */
	public void delete(E element) {
	    overallRoot = delete(overallRoot, element);
	}
	
	/* Given a root and an element, removes the element from the tree
	 * with the given root.  Returns the updated reference to the
	 * root of the tree.
	 * 
	 * Moving the data around seems like bad style, but this is by
	 * far the simplest, most efficient way of which I can think. */
	private RandomTreeNode<E> delete(RandomTreeNode<E> root, E element) {
	    if (root != null) {
		if (element.compareTo(root.data) < 0) {
		    root.left = delete(root.left, element);
		    root.decrementSize();
		} else if (element.compareTo(root.data) > 0) {
		    root.right = delete(root.right, element);
		    root.decrementSize();
		} else {
		    if (root.left == null && root.right == null) {
			root = null;
		    } else if (root.right == null) {
			root = root.left;
		    } else if (root.left == null) {
			root = root.right;
		    } else {
			E minValue = findMinValue(root.right);
			root.data = minValue;
			root.right = delete(root.right, minValue);
			root.decrementSize();
		    }
		}
	    }
	    return root;
	}
	
	/* Finds and returns the minimum value in the tree with the given root. */
	private E findMinValue(RandomTreeNode<E> root) {
	    if (root == null) {
		return null;
	    } else if (root.left == null) {
		return root.data;
	    } else {
		return findMinValue(root.left);
	    }
	}
	
	/**
	 * Returns a RandomTreeNode with the given element which is contained in
	 * this tree.  If the element is not contained in this tree, returns null.
	 * 
	 * @param element element to search for in this tree
	 * @return the node with the given element or null is there is no such node
	 */
	public RandomTreeNode<E> find(E element) {
	    return find(overallRoot, element);
	}
	
	/* Returns the node with the given element in the tree with the given
	 * root or null if there is no such node. */
	private RandomTreeNode<E> find(RandomTreeNode<E> root, E element) {
	    if (root == null || root.data.equals(element)) {
		return root;
	    } else if (element.compareTo(root.data) < 0) {
		return find(root.left, element);
	    } else {
		return find(root.right, element);
	    }
	}
	
	/**
	 * Returns a random node from this tree or null if this tree is empty.
	 * 
	 * @return random node in this tree or null if the tree is empty
	 */
	public RandomTreeNode<E> getRandomNode() {
	    if (overallRoot == null) {
		return null;
	    } else {
    	    	int index = new Random().nextInt(overallRoot.size);
    	    	return overallRoot.getNodeAt(index);
	    }
	}
	
	/**
	 * A RandomTreeNode is a single node in a RandomBinarySearchTree.  It stores
	 * its own data, references to its children, and the size of the tree
	 * of which it is the root.
	 * 
	 * @author Kyle
	 *
	 * @param <E> Type of data stored in the node.
	 */
	public static class RandomTreeNode<E> {
	    public E data;
	    public RandomTreeNode<E> left;
	    public RandomTreeNode<E> right;
	    private int size;
	    
	    /**
	     * Constructs a new RandomTreeNode with the given data.
	     * 
	     * @param data
	     */
	    public RandomTreeNode(E data) {
		this.data = data;
		this.size = 1;
	    }
	    
	    /**
	     * Gets the node at the given index when performing a preorder
	     * traversal of the tree of which this is the root.  Assumes
	     * the given index is valid.
	     * 
	     * @param index index of the requested node
	     * @return the requested node
	     */
	    public RandomTreeNode<E> getNodeAt(int index) {
		int leftSize = left == null ? 0 : left.size;
		if (index == 0) {
		    return this;
		} else if (index < leftSize) {
		    return left.getNodeAt(index - 1);
		} else {
		    return right.getNodeAt(index - 1 - leftSize);
		}
	    }
	    
	    /**
	     * Increments the stored size of the tree of which this is the root.
	     */
	    public void incrementSize() {
		size++;
	    }
	    
	    /**
	     * Decrements the stored size of the tree of which this is the root.
	     */
	    public void decrementSize() {
		size--;
	    }
	}
}
