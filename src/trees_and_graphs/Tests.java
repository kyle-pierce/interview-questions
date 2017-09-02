package trees_and_graphs;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tests {
	
	@Test
	public void testPathBetweenNodesBFS() {
		AdjacencyList graph = new AdjacencyList();
		Node src = new Node(1);
		Node dst = new Node(3);
		Node intermediate = new Node(2);
		Node unConnected = new Node(4);
		
		graph.nodes[0] = src;
		graph.nodes[1] = intermediate;
		graph.nodes[2] = dst;
		graph.nodes[3] = unConnected;
		
		src.children[0] = intermediate;
		intermediate.children[0] = dst;
		
		assertTrue(GraphsAndTrees.pathBetweenNodesBFS(graph, src, dst));
		assertFalse(GraphsAndTrees.pathBetweenNodesBFS(graph, src, unConnected));
	}
	
	@Test
	public void testPathsBetweenNodesDFS() {
		AdjacencyList graph = new AdjacencyList();
		Node src = new Node(1);
		Node dst = new Node(3);
		Node intermediate = new Node(2);
		Node unConnected = new Node(4);
		
		graph.nodes[0] = src;
		graph.nodes[1] = intermediate;
		graph.nodes[2] = dst;
		graph.nodes[3] = unConnected;
		
		src.children[0] = intermediate;
		intermediate.children[0] = dst;
		
		assertTrue(GraphsAndTrees.pathBetweenNodesDFS(graph, src, dst));
		assertFalse(GraphsAndTrees.pathBetweenNodesDFS(graph, src, unConnected));
	}
	
	@Test
	public void testGenerateBSTFull() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		Node observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testGenerateBSTNonFull() {
		int[] arr = {1, 2, 3, 4, 5, 7};
		Node observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testListsByLevel() {
		Node overallRoot = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5, 6, 7});
		List<LinkedList<Node>> lists = GraphsAndTrees.listsByLevel(overallRoot);
		
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node five = new Node(5);
		Node six = new Node(6);
		Node seven = new Node(7);
		
		assertTrue(lists.get(0).equals(Arrays.asList(new Node[] {four})));
		assertTrue(lists.get(1).equals(Arrays.asList(new Node[] {two, six})));
		assertTrue(lists.get(2).equals(Arrays.asList(new Node[] {one, three, 
																five, seven})));
	}
	
	@Test
	public void testIsBalancedTrue() {
		Node root = null;
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root = new Node(2);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0] = new Node(1);
		root.children[1] = new Node(3);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0] = new Node(4);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[1] = new Node(7);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new Node(5);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[0] = new Node(6);
		assertTrue(GraphsAndTrees.isBalanced(root));
	}
	
	@Test
	public void testIsBalancedFalse() {
		Node root = new Node(0);
		root.children[0] = new Node(1);
		root.children[0].children[0] = new Node(2);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new Node(3);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0].children[0] = new Node(4);
		assertFalse(GraphsAndTrees.isBalanced(root));
	}
	
	/* Returns true if the two trees with the given roots are equal,
	 * meaning they have the exact same nodes in the exact same
	 * locations.  Assumes the two trees have the same number of
	 * branches from each root. Returns false otherwise. */
	private boolean treesAreEqual(Node observed, Node expected) {
		if (observed == null && expected == null) {
			// both null -> true
			return true;
		} else if (observed == null ^ expected == null) {
			// only one null -> false
			return false;
		} else if (!observed.equals(expected)) {
			// unequal nodes -> false
			return false;
		} else {
			// recurse on each neighbor
			for (int i = 0; i < observed.children.length; i++) {
				if (!treesAreEqual(observed.children[i], expected.children[i])) {
					return false;
				}
			}
		}
		return true;
	}
	
	/* Prints the tree with given root to System.out in a pre-order
	 * traversal format. */
	private void printInOrder(Node root) {
		if (root != null) {
			printInOrder(root.children[0]);
			System.out.print(root.data + " ");
			printInOrder(root.children[1]);
		}
	}

}
