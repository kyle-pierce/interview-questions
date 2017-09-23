package trees_and_graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RandomBinaryTree<E> {
	private int size;
	private TreeNode<E> overallRoot;
	
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
		if (overallRoot == null) {
			overallRoot = new TreeNode<E>(element);
		} else {
			Queue<TreeNode<E>> currentLevel = new LinkedList<>();
			Queue<TreeNode<E>> nextLevel = new LinkedList<>();
			
			currentLevel.add(overallRoot);
			boolean inserted = false;
			
			while (!inserted) {
				while (!currentLevel.isEmpty() && !inserted) {
					TreeNode<E> current = currentLevel.remove();
					if (current.children[0] == null) {
						current.children[0] = new TreeNode<E>(element);
						inserted = true;
					} else if (current.children[1] == null) {
						current.children[1] = new TreeNode<E>(element);
						inserted = true;
					} else {
						nextLevel.add(current.children[0]);
						nextLevel.add(current.children[1]);
					}
				}
				currentLevel = nextLevel;
			}
			++size;
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
	private TreeNode<E> remove(TreeNode<E> root, E element) {
		if (root != null) {
			if (root.data.equals(element)) {
				if (root.children[0] != null) {
					root = root.children[0];
				} else {
					root = root.children[1];
				}
				remove(root, element);
			} else {
				root.children[0] = remove(root.children[0], element);
				root.children[1] = remove(root.children[1], element);
			}
		}
		return root;
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
	private boolean contains(TreeNode<E> root, E element) {
		if (root == null) {
			return false;
		} else {
			if (root.data.equals(element)) {
				return true;
			} else {
				return contains(root.children[0], element) ||
					   contains(root.children[1], element);
			}
		}
	}
}
