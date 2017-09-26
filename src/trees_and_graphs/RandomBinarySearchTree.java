package trees_and_graphs;

import java.util.Random;

public class RandomBinarySearchTree<E extends Comparable<E>> {
	private RandomTreeNode<E> overallRoot;
	
	public void insert(E element) {
	    overallRoot = insert(overallRoot, element);
	}
	
	private RandomTreeNode<E> insert(RandomTreeNode<E> root, E element) {
	    if (root == null) {
		root = new RandomTreeNode<E>(element);
	    } else if (root.data.compareTo(element) > 0) {
		root.left = insert(root.left, element);
		root.incrementSize();
	    } else {
		root.right = insert(root.right, element);
		root.incrementSize();
	    }
	    return root;
	}
 	
	public void delete(E element) {
	    overallRoot = delete(overallRoot, element);
	}
	
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
	
	private E findMinValue(RandomTreeNode<E> root) {
	    if (root == null) {
		return null;
	    } else if (root.left == null) {
		return root.data;
	    } else {
		return findMinValue(root.left);
	    }
	}
	
	public RandomTreeNode<E> find(E element) {
	    return find(overallRoot, element);
	}
	
	private RandomTreeNode<E> find(RandomTreeNode<E> root, E element) {
	    if (root == null || root.data.equals(element)) {
		return root;
	    } else {
		RandomTreeNode<E> foundOnLeft = find(root.left, element);
		return foundOnLeft == null ? find(root.right, element) : foundOnLeft;
	    }
	}
	
	public RandomTreeNode<E> getRandomNode() {
	    if (overallRoot == null) {
		return null;
	    } else {
    	    	int index = new Random().nextInt(overallRoot.size);
    	    	return overallRoot.getNodeAt(index);
	    }
	}
	
	public static class RandomTreeNode<E> {
	    public E data;
	    public RandomTreeNode<E> left;
	    public RandomTreeNode<E> right;
	    private int size;
	    
	    public RandomTreeNode(E data) {
		this.data = data;
		this.size = 1;
	    }
	    
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
	    
	    public void incrementSize() {
		size++;
	    }
	    
	    public void decrementSize() {
		size--;
	    }
	}
}
