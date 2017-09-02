package trees_and_graphs;

import org.junit.*;

import static org.junit.Assert.*;

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
		
		assertTrue(GraphsAndTrees.checkBalanced(observed));
		assertTrue(GraphsAndTrees.validateSearchTree(observed));
	}
	
	@Test
	public void testGenerateBSTNonFull() {
		int[] arr = {1, 2, 3, 4, 5, 7};
		Node observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.checkBalanced(observed));
		assertTrue(GraphsAndTrees.validateSearchTree(observed));
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
