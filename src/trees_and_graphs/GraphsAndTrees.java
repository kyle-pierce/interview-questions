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
						if (!visited.contains(child)) {
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
			Node root = new Node();
			
			root.data = arr[middle];
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
	
}
