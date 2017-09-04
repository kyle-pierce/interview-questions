package trees_and_graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphsAndTrees {
	
	/* Returns true if there is a path between the given src and dst nodes
	 * in the given directed graph. Uses breadth-first search. */
	public static boolean pathBetweenNodesBFS(AdjacencyList directedGraph, Node src, Node dst) {
		Set<Node> visiting = new HashSet<Node>();
		Set<Node> visited = new HashSet<Node>();
		visiting.add(src);
		
		while (!visiting.isEmpty()) {
			Set<Node> toBeVisited = new HashSet<Node>();
			for (Node node : visiting) {
				if (node.equals(dst)) {
					return true;
				} else {
					visited.add(node);
					for (Node child : node.children) {
						if (child != null && !visited.contains(child)) {
							toBeVisited.add(child);
						}
					}
				}
			}
			visiting = toBeVisited;
		}
		return false;
	}
	
	/* Returns true if there is a path between the given src and dst nodes
	 * in the given directed graph. Uses depth-first search. */
	public static boolean pathBetweenNodesDFS(AdjacencyList directedGraph, Node src, Node dst) {
		if (src == null || dst == null) {
			return false;
		}
		if (src.equals(dst)) {
			return true;
		} else {
			for (int i = 0; i < src.children.length; i++) {
				if (pathBetweenNodesDFS(directedGraph, src.children[i], dst)) {
					return true;
				}
			}
			return false;
		}
	}
	
	/* Returns the root of a Binary Search Tree of minimal height made from
	 * the given sorted array of distinct integers. */
	public static Node generateBST(int[] arr) {
		return generateBST(arr, 0, arr.length);
	}
	
	/* Returns the root of a Binary Search Tree of minimal height made from
	 * the given sorted array of distinct integers between the given indices.
	 * Note that 'left' is inclusive, 'right' is exclusive. */
	private static Node generateBST(int[] arr, int left, int right) {
		if (left == right) {
			return null;
		} else {
			int middle = left + (right - left) / 2;
			Node root = new Node(arr[middle]);
			
			root.children[0] = generateBST(arr, left, middle);
			root.children[1] = generateBST(arr, middle + 1, right);
			
			return root;
		}
	}
	
	/* Returns a list of LinkedLists, one LinkedList for each level of
	 * the tree with given overallRoot filled with the nodes of that level. */
	public static List<LinkedList<Node>> listsByLevel(Node overallRoot) {
		List<LinkedList<Node>> result = new ArrayList<>();
		fillListWithNodes(overallRoot, result, 0);
		return result;
	}
	
	/* Fills the given list of LinkedLists, one LinkedList for each level of the
	 * tree with given root with given level filled with the nodes of that level.  */
	private static void fillListWithNodes(Node root, List<LinkedList<Node>> lists,
								   		  int level) {
		if (root != null) {
			if (lists.size() == level) {
				lists.add(new LinkedList<Node>());
			}
			lists.get(level).add(root);
			fillListWithNodes(root.children[0], lists, level + 1);
			fillListWithNodes(root.children[1], lists, level + 1);
		}
	}
	
	/* Returns true if all left and right subtrees in the given tree have
	 * heights no more than 1 apart and false otherwise. */
	public static boolean isBalanced(Node overallRoot) {
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
	private static int getHeight(Node root) {
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
	public static boolean isSearchTree(Node overallRoot) {
		return validateSearchTree(overallRoot, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/* Returns true if the tree with given root is a binary search tree and contains
	 * only values between the given min and max inclusive. */
	private static boolean validateSearchTree(Node root, int min, int max) {
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
	public Node getSuccessor(Node root) { 
		if (root == null) {
			return null;
		}
		
		if (root.children[1] != null) {
			// right subtree -> find leftmost node in that tree
			return getLeftMostNode(root.children[1]);
		} else {
			// no right subtree -> go up until we were on a left child
			// or get all the way back to the top of the tree
			Node parent = root.parent;
			Node child = root;
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
	private Node getLeftMostNode(Node root) {
		Node current = root;
		while (current != null && current.children[0] != null) {
			current = current.children[0];
		}
		return current;
	}
	
	public List<String> buildOrder(List<String> projects, List<Pair> dependencies) {
		AdjacencyList graph = new AdjacencyList();
		for (Pair p : dependencies) {
			if (!graph.contains(p.from)) {
				graph.addNode(p.from);
			}
			if (!graph.contains(p.to)) {
				graph.addNode(p.to);
			}
			graph.addConnection(p.from, p.to);
		}
	}
	
	private static class Pair {
		public String from;
		public String to;
	}
	
}
