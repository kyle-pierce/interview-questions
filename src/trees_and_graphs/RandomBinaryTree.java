package trees_and_graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RandomBinaryTree<E> {
	private int size;
	private RandomTreeNode<E> overallRoot;
	
	/**
	 * Constructs an empty RandomBinaryTree
	 */
	public RandomBinaryTree() {
		size = 0;
		overallRoot = null;
	}
	
	/**
	 * Adds the given element at the location nearest to the overall
	 * root of this tree.
	 * 
	 * @param element element to be added to this tree
	 */
	public void add(E element) {
		overallRoot = add(overallRoot, element);
	}
	
	private RandomTreeNode<E> add(RandomTreeNode<E> root, E element) {
		if (root != null) {
			if (root.index == size)
		}
	}
	
	/**
	 * Removes all occurrences of the given element from this tree,
	 * replacing it with its left child by default or the right child 
	 * if there is no left child.
	 * 
	 * @param element element to be removed
	 */
	public void remove(E element) {
		overallRoot = remove(overallRoot, element);
	}
	
	/* Removes all occurrences of the given element from the tree with the
	 * given root, returning the updated root. */
	private RandomTreeNode<E> remove(RandomTreeNode<E> root, E element) {
		
	}
	
	/**
	 * Returns true if the given element is contained in this tree.
	 * 
	 * @param element element for which to check the tree
	 * @return true if the element was found, false otherwise
	 */
	public boolean contains(E element) {
		return contains(overallRoot, element);
	}
	
	/* Returns true if the tree with the given root contains the given
	 * element and false otherwise. */
	private boolean contains(RandomTreeNode<E> root, E element) {
		if (root == null) {
			return false;
		} else {
			if (root.data.equals(element)) {
				return true;
			} else {
				return contains(root.left, element) ||
					   contains(root.right, element);
			}
		}
	}
	
	/**
	 * Returns a random element from this tree.
	 * 
	 * @return a random element from this tree
	 */
	public E getRandomElement() {
		int index = new Random().nextInt(size);
		return getRandomElement(overallRoot, index);
	}
	
	/* Returns the data stored in the node with the given index in the 
	 * tree with the given root.  Returns null if there is no node
	 * with the given index in the given tree.  */
	private E getRandomElement(RandomTreeNode<E> root, int index) {
		if (root != null) {
			if (root.index == index) {
				return root.data;
			} else if (root.index > index) {
				return getRandomElement(root.left, index);
			} else {
				return getRandomElement(root.right, index);
			}
		}
		return null;
	}
	
	/* Node class for this tree; nodes are the same as normal TreeNodes except 
	 * they also store their location in this tree. */
	private static class RandomTreeNode<E> {
		public E data;
		public int index;
		public RandomTreeNode<E> left;
		public RandomTreeNode<E> right;
		
		/* Constructs a RandomTreeNode with the given data and index. */
		public RandomTreeNode(E data, int index) {
			this.data = data;
			this.index = index;
		}
	}
}
