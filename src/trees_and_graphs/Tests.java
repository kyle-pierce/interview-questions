package trees_and_graphs;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tests {
	
	@Test
	public void testPathBetweenNodesBFS() {
		AdjacencyList<Integer> graph = new AdjacencyList<>();
		int src = 1;
		int dst = 3;
		int intermediate = 2;
		int unConnected = 4;
		
		graph.add(src);
		graph.add(intermediate);
		graph.add(dst);
		graph.add(unConnected);
		
		graph.addChild(src, intermediate);
		graph.addChild(intermediate, dst);
		
		assertTrue(GraphsAndTrees.pathBetweenNodesBFS(graph, src, dst));
		assertFalse(GraphsAndTrees.pathBetweenNodesBFS(graph, src, unConnected));
	}
	
	@Test
	public void testPathsBetweenNodesDFS() {
		AdjacencyList<Integer> graph = new AdjacencyList<>();
		int src = 1;
		int dst = 3;
		int intermediate = 2;
		int unConnected = 4;
		
		graph.add(src);
		graph.add(intermediate);
		graph.add(dst);
		graph.add(unConnected);
		
		graph.addChild(src, intermediate);
		graph.addChild(intermediate, dst);
		
		assertTrue(GraphsAndTrees.pathBetweenNodesDFS(graph, src, dst));
		assertFalse(GraphsAndTrees.pathBetweenNodesDFS(graph, src, unConnected));
	}
	
	@Test
	public void testGenerateBSTFull() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		TreeNode observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testGenerateBSTNonFull() {
		int[] arr = {1, 2, 3, 4, 5, 7};
		TreeNode observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testListsByLevel() {
		TreeNode overallRoot = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5, 6, 7});
		List<LinkedList<TreeNode>> lists = GraphsAndTrees.listsByLevel(overallRoot);
		
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(5);
		TreeNode six = new TreeNode(6);
		TreeNode seven = new TreeNode(7);
		
		assertTrue(lists.get(0).equals(Arrays.asList(new TreeNode[] {four})));
		assertTrue(lists.get(1).equals(Arrays.asList(new TreeNode[] {two, six})));
		assertTrue(lists.get(2).equals(Arrays.asList(new TreeNode[] {one, three, 
																five, seven})));
	}
	
	@Test
	public void testIsBalancedTrue() {
		TreeNode root = null;
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root = new TreeNode(2);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0] = new TreeNode(1);
		root.children[1] = new TreeNode(3);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0] = new TreeNode(4);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[1] = new TreeNode(7);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new TreeNode(5);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[0] = new TreeNode(6);
		assertTrue(GraphsAndTrees.isBalanced(root));
	}
	
	@Test
	public void testIsBalancedFalse() {
		TreeNode root = new TreeNode(0);
		root.children[0] = new TreeNode(1);
		root.children[0].children[0] = new TreeNode(2);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new TreeNode(3);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0].children[0] = new TreeNode(4);
		assertFalse(GraphsAndTrees.isBalanced(root));
	}
	
	@Test
	public void testIsSearchTree() {
		TreeNode overallRoot = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5});
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.children[0].children[0].data = -1;
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.children[1].children[1] = new TreeNode(100);
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.data = 42;
		assertFalse(GraphsAndTrees.isSearchTree(overallRoot));
	}
	
	/* Returns true if the two trees with the given roots are equal,
	 * meaning they have the exact same nodes in the exact same
	 * locations.  Assumes the two trees have the same number of
	 * branches from each root. Returns false otherwise. */
	private boolean treesAreEqual(TreeNode observed, TreeNode expected) {
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
	private void printInOrder(TreeNode root) {
		if (root != null) {
			printInOrder(root.children[0]);
			System.out.print(root.data + " ");
			printInOrder(root.children[1]);
		}
	}

}
