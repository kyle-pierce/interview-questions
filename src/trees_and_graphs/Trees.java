package trees_and_graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Trees {
	
	/* Returns the root of a Binary Search Tree of minimal height made from
	 * the given sorted array of distinct integers. */
	public static TreeNode<Integer> generateBST(int[] arr) {
		return generateBST(arr, 0, arr.length);
	}
	
	/* Returns the root of a Binary Search Tree of minimal height made from
	 * the given sorted array of distinct integers between the given indices.
	 * Note that 'left' is inclusive, 'right' is exclusive. */
	private static TreeNode<Integer> generateBST(int[] arr, int left, int right) {
		if (left == right) {
			return null;
		} else {
			int middle = left + (right - left) / 2;
			return new TreeNode<Integer>(arr[middle], generateBST(arr, left, middle),
											 generateBST(arr, middle + 1, right));
		}
	}
	
	/* Returns a list of LinkedLists, one LinkedList for each level of
	 * the tree with given overallRoot filled with the nodes of that level. */
	public static List<LinkedList<TreeNode<Integer>>> listsByLevel(TreeNode<Integer> overallRoot) {
		List<LinkedList<TreeNode<Integer>>> result = new ArrayList<>();
		fillListWithNodes(overallRoot, result, 0);
		return result;
	}
	
	/* Fills the given list of LinkedLists, one LinkedList for each level of the
	 * tree with given root with given level filled with the nodes of that level.  */
	private static void fillListWithNodes(TreeNode<Integer> root, List<LinkedList<TreeNode<Integer>>> lists,
								   		  int level) {
		if (root != null) {
			if (lists.size() == level) {
				lists.add(new LinkedList<TreeNode<Integer>>());
			}
			lists.get(level).add(root);
			fillListWithNodes(root.children[0], lists, level + 1);
			fillListWithNodes(root.children[1], lists, level + 1);
		}
	}
	
	/* Returns true if all left and right subtrees in the given tree have
	 * heights no more than 1 apart and false otherwise. */
	public static boolean isBalanced(TreeNode<Integer> overallRoot) {
		if (overallRoot == null) {
			return true;
		} else {
			int leftHeight = getHeight(overallRoot.children[0]);
			int rightHeight = getHeight(overallRoot.children[1]);
			boolean heightsOk = Math.abs(leftHeight - rightHeight) <= 1;
			return heightsOk && isBalanced(overallRoot.children[0]) 
							 && isBalanced(overallRoot.children[1]);
		}
	}
	
	/* Returns the height of the tree beginning with the given root.  The empty
	 * tree is defined to have a height of 0. */
	private static int getHeight(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		} else {
			int leftHeight = getHeight(root.children[0]);
			int rightHeight = getHeight(root.children[1]);
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}
	
	/* Returns true if the binary tree with given root is a binary search tree
	 * and false otherwise. The empty tree is a valid binary search tree. */
	public static boolean isSearchTree(TreeNode<Integer> overallRoot) {
		return validateSearchTree(overallRoot, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/* Returns true if the tree with given root is a binary search tree and contains
	 * only values between the given min and max inclusive. */
	private static boolean validateSearchTree(TreeNode<Integer> root, int min, int max) {
		if (root == null) {
			return true;
		} else {
			if (root.data < min || root.data > max) {
				return false;
			} 
			
			boolean leftIsValid = validateSearchTree(root.children[0], min, root.data);
			boolean rightIsValid = validateSearchTree(root.children[1], root.data, max);
			
			return leftIsValid && rightIsValid;
		}
	}
	
	/* Returns the immediate successor of the given root in the BST in which
	 * the given root exists.  Returns null if there is no successor. */
	public TreeNode<Integer> getSuccessor(TreeNode<Integer> root) { 
		if (root == null) {
			return null;
		}
		
		if (root.children[1] != null) {
			// right subtree -> find leftmost node in that tree
			return getLeftMostNode(root.children[1]);
		} else {
			// no right subtree -> go up until we were on a left child
			// or get all the way back to the top of the tree
			TreeNode<Integer> parent = root.parent;
			TreeNode<Integer> child = root;
			while (parent != null && parent.children[0] != child) {
				child = parent;
				parent = child.parent;
			}
			// return the first parent to the right of the child or null
			return parent;
		}
	}
	
	/* Returns the leftmost node in the tree with the given root.  Returns
	 * null if the given root is null. */
	private TreeNode<Integer> getLeftMostNode(TreeNode<Integer> root) {
		TreeNode<Integer> current = root;
		while (current != null && current.children[0] != null) {
			current = current.children[0];
		}
		return current;
	}
	
	/* Returns the first common ancestor of firstNode and secondNode in the
	 * tree with the given root.  The first common ancestor is the branch node
	 * which contains both firstNode and secondNode in its subtrees and has the
	 * highest possible depth down the tree. Returns null if there is no common
	 * ancestor between the given elements. */
	public static TreeNode<Integer> firstCommonAncestor(TreeNode<Integer> root, 
							    int elementOne, int elementTwo) {
		return findCommonAncestor(root, elementOne, elementTwo).node;
	}
	
	/* Returns a Result object which will indicate the status of the search for the
	 * given elementOne and elementTwo in the tree with the given root. The Result object
	 * will contain a null node if the ancestor was not found.  If the ancestor was
	 * found, the node will be non-null. */
	private static Result findCommonAncestor(TreeNode<Integer> root, int elementOne, 
																	 int elementTwo) {
		if (root == null) {
			// the empty tree does not have an ancestor of any given elements
			return new Result(null, false, false);
		} else {
			/* Check the left subtree for the elements.  If the ancestor was found below this
			 * node in the left tree, return that Result directly. */
			Result leftResult = findCommonAncestor(root.children[0], elementOne, elementTwo);
			if (leftResult.node != null) {
				return leftResult;
			}
			
			/* Check the right subtree for the elements.  If the ancestor was found below this
			 * node in the right tree, return that Result directly. */
			Result rightResult = findCommonAncestor(root.children[1], elementOne, elementTwo);
			if (rightResult.node != null) {
				return rightResult;
			}
			
			/* The result was not found in subtrees so we will need to return a new Result
			 * which is the combination of the results of the left and right subtrees. */
			
			// We found elementOne if it was in either subtree or is this current element
			boolean elementOneFound = leftResult.elementOneFound  || 
									  rightResult.elementOneFound ||
									  root.data == elementOne;
			// We found elementTwo if it was in either subtree or is this current element
			boolean elementTwoFound = leftResult.elementTwoFound  || 
									  rightResult.elementTwoFound ||
									  root.data == elementTwo;
			
			/* If this is the ancestor, the node value should be non-null.  Otherwise
			 * it should be null to make the conditional logic more clear later. */
			TreeNode<Integer> nodeValue = (elementOneFound && elementTwoFound) ? root : null;
			return new Result(nodeValue, elementOneFound, elementTwoFound);
		}
	}
	
	/* This class is used for the firstCommonAncestor problem.  It stores a node
	 * value which should be non-null if and only if elementOneFound and elementTwoFound
	 * are both true.  It allows for more information to be returns from recursive
	 * calls looking for an ancestor down the tree. */
	private static class Result {
		/* Final fields because they should never be changed after the 
		 * constructor anyway. */
		public final TreeNode<Integer> node;
		public final boolean elementOneFound;
		public final boolean elementTwoFound;
		
		/* Construct a result with the given node, elementOneFound, and elementTwoFound. */
		public Result(TreeNode<Integer> node, boolean elementOneFound, boolean elementTwoFound) {
			this.node = node;
			this.elementOneFound = elementOneFound;
			this.elementTwoFound = elementTwoFound;
		}
	}
	
	/* Given the root of a binary search tree formed by inserting all elements from
	 * an array of distinct integers in order, prints all possible arrays which
	 * would produce this binary search tree. */
	public static void printBSTSequences(TreeNode<Integer> root) {
		List<List<Integer>> sequences = getSequences(root);
		for (List<Integer> sequence : sequences) {
			System.out.println(sequence);
		}
	}
	
	/* Returns a list of all possible sequences of integers which could have
	 * produced the tree with the given root. */
	public static List<List<Integer>> getSequences(TreeNode<Integer> root) {
		List<List<Integer>> sequences = new ArrayList<>();
		
		if (root == null) {		// empty -> only the empty sequence
			sequences.add(new LinkedList<Integer>());
		} else {				// nonempty -> combine left and right sequences
			List<List<Integer>> leftSequences = getSequences(root.children[0]);
			List<List<Integer>> rightSequences = getSequences(root.children[1]);
			
			// add the current node's data to the current sequence
			List<Integer> currentSequence = new LinkedList<>();
			currentSequence.add(root.data);
			
			// loop through all found sequences and combine them
			for (List<Integer> left : leftSequences) {
				for (List<Integer> right : rightSequences) {
					List<List<Integer>> weavedSequences = new ArrayList<>();
					weaveSequences(left, right, weavedSequences, currentSequence);
					sequences.addAll(weavedSequences);
				}
			}
		}
		
		return sequences;
	}
	
	/* Given two lists, weaved them in all possible orders with the given currentSequence
	 * and adds these weaved lists to the given weavedSequences. */
	private static void weaveSequences(List<Integer> left, List<Integer> right,
									   List<List<Integer>> weavedSequences, 
									   List<Integer> currentSequence) {
		if (left.size() == 0 || right.size() == 0) {
			// make a copy to avoid reference-related bugs
			List<Integer> result = new LinkedList<Integer>(currentSequence);
			
			// only one of these will be nonempty but there is no need to check which
			result.addAll(left);
			result.addAll(right);
			
			// add the combined result to the list of sequences being passed around
			weavedSequences.add(result);
		} else {
			/* Remove the front of the left list.  Add it to the back of the current sequence.
			 * Find all sequences after making this change.  Completely undo this change. */
			int headOfLeft = left.remove(0);
			currentSequence.add(headOfLeft);
			weaveSequences(left, right, weavedSequences, currentSequence);
			currentSequence.remove(currentSequence.size() - 1);
			left.add(0, headOfLeft);
			
			/* Remove the front of the right list.  Add it to the back of the current sequence.
			 * Find all sequences after making this change.  Completely undo this change. */
			int headOfRight = right.remove(0);
			currentSequence.add(headOfRight);
			weaveSequences(left, right, weavedSequences, currentSequence);
			currentSequence.remove(currentSequence.size() - 1);
			right.add(0, headOfRight);
		}
	}
	
	/* Returns true if the given inner tree is a subtree of the given outer tree. */
	public static <E> boolean contains(TreeNode<E> outer, TreeNode<E> inner) {
		/* Possible signs of containment:
		 * 	1) inner is a subtree of outer starting at the roots
		 *  2) outer's left subtree contains inner 
		 *  3) outer's right subtree contains inner 
		 *  
		 *  note: must ensure outer is non-null before checking subtrees */
		return isSubtree(outer, inner) || outer != null &&
				(contains(outer.children[0], inner) ||
				 contains(outer.children[1], inner));
	}
	
	/* Returns true if the tree with the given two root is a subtree
	 * of the tree with the given one root. */
	private static <E> boolean isSubtree(TreeNode<E> one, TreeNode<E> two) {
		if (two == null) {
			/* The empty tree is a subtree of any given tree */
			return true;
		} else if (one == null) {
			/* The non-empty tree cannot be a subtree of the empty tree */
			return false;
		} else {
			/* If neither are empty, compare the current nodes as well as
			 * the left and right subtrees. */
			return one.data.equals(two.data) &&
					isSubtree(one.children[0], two.children[0]) &&
					isSubtree(one.children[1], two.children[1]);
		}
	}
	
	/* Returns the number of paths down the tree with the given root with
	 * the given sum.  Paths may not start at the root and may not end 
	 * at a leaf node. */
	public static int countPathsWithSum(TreeNode<Integer> root, int sum) {
	    return countPathsWithSum(root, sum, 0);
	}

	private static int countPathsWith(TreeNode<Integer> root, int goal, int currentSum) {
	    int paths = 0;
	    if (root != null) {
		if (goal - root.data == 0) {
		    paths++;
		}
		paths += countPathsWithSum(root.children[0], goal, currentSum)
	    }
	}
}
