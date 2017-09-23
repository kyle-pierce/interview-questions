package trees_and_graphs;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
		TreeNode<Integer> observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testGenerateBSTNonFull() {
		int[] arr = {1, 2, 3, 4, 5, 7};
		TreeNode<Integer> observed = GraphsAndTrees.generateBST(arr);
		
		assertTrue(GraphsAndTrees.isBalanced(observed));
		assertTrue(GraphsAndTrees.isSearchTree(observed));
	}
	
	@Test
	public void testListsByLevel() {
		TreeNode<Integer> overallRoot = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5, 6, 7});
		List<LinkedList<TreeNode<Integer>>> lists = GraphsAndTrees.listsByLevel(overallRoot);
		
		TreeNode<Integer> one = new TreeNode<Integer>(1);
		TreeNode<Integer> two = new TreeNode<Integer>(2);
		TreeNode<Integer> three = new TreeNode<Integer>(3);
		TreeNode<Integer> four = new TreeNode<Integer>(4);
		TreeNode<Integer> five = new TreeNode<Integer>(5);
		TreeNode<Integer> six = new TreeNode<Integer>(6);
		TreeNode<Integer> seven = new TreeNode<Integer>(7);
		
		assertTrue(lists.get(0).equals(Arrays.asList(new Object[] {four})));
		assertTrue(lists.get(1).equals(Arrays.asList(new Object[] {two, six})));
		assertTrue(lists.get(2).equals(Arrays.asList(new Object[] {one, three, 
																   five, seven})));
	}
	
	@Test
	public void testIsBalancedTrue() {
		TreeNode<Integer> root = null;
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root = new TreeNode<Integer>(2);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0] = new TreeNode<Integer>(1);
		root.children[1] = new TreeNode<Integer>(3);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0] = new TreeNode<Integer>(4);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[1] = new TreeNode<Integer>(7);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new TreeNode<Integer>(5);
		assertTrue(GraphsAndTrees.isBalanced(root));
		
		root.children[1].children[0] = new TreeNode<Integer>(6);
		assertTrue(GraphsAndTrees.isBalanced(root));
	}
	
	@Test
	public void testIsBalancedFalse() {
		TreeNode<Integer> root = new TreeNode<Integer>(0);
		root.children[0] = new TreeNode<Integer>(1);
		root.children[0].children[0] = new TreeNode<Integer>(2);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[1] = new TreeNode<Integer>(3);
		assertFalse(GraphsAndTrees.isBalanced(root));
		
		root.children[0].children[0].children[0] = new TreeNode<Integer>(4);
		assertFalse(GraphsAndTrees.isBalanced(root));
	}
	
	@Test
	public void testIsSearchTree() {
		TreeNode<Integer> overallRoot = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5});
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.children[0].children[0].data = -1;
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.children[1].children[1] = new TreeNode<Integer>(100);
		assertTrue(GraphsAndTrees.isSearchTree(overallRoot));
		
		overallRoot.data = 42;
		assertFalse(GraphsAndTrees.isSearchTree(overallRoot));
	}
	
	@Test
	public void testBuildOrder() {
		List<String> firstProjects = 
				Arrays.asList(new String[] {"1", "2", "3", "4"});
		List<String> secondProjects = 
				Arrays.asList(new String[] {"1.0", "1.1", "2.0", "2.1", "3.0"});
		List<String> thirdProjects = 
				Arrays.asList(new String[] {"2.1", "1.1", "3.0", "1.0", "2.0"});
		
		List<Pair> firstPairs = new ArrayList<>();
		firstPairs.add(new Pair("1", "2"));
		firstPairs.add(new Pair("2", "3"));
		firstPairs.add(new Pair("3", "4"));
		
		List<Pair> secondPairs = new ArrayList<>();
		secondPairs.add(new Pair("1.0", "2.0"));
		secondPairs.add(new Pair("1.1", "2.0"));
		secondPairs.add(new Pair("1.0", "2.1"));
		secondPairs.add(new Pair("1.1", "2.1"));
		secondPairs.add(new Pair("2.0", "3.0"));
		secondPairs.add(new Pair("2.1", "3.0"));
		
		
		List<String> firstBuildOrder = GraphsAndTrees.buildOrder(firstProjects, firstPairs);
		verifyBuildOrder(firstProjects, firstPairs, firstBuildOrder);
		
		List<String> secondBuildOrder = GraphsAndTrees.buildOrder(secondProjects, secondPairs);
		verifyBuildOrder(secondProjects, secondPairs, secondBuildOrder);
		
		List<String> thirdBuildOrder = GraphsAndTrees.buildOrder(thirdProjects, secondPairs);
		verifyBuildOrder(thirdProjects, secondPairs, thirdBuildOrder);
	}
	
	/* Verifies that the provided build order is a valid for the given projects
	 * and dependencies. */
	private void verifyBuildOrder(List<String> projects, List<Pair> dependencies, 
								  List<String> order) {
		assertSame(projects.size(), order.size());
		assertTrue(order.containsAll(projects));
		for (Pair pair : dependencies) {
			assertTrue(order.indexOf(pair.from) < order.indexOf(pair.to));
		}
	}
	
	@Test
	public void testFirstCommonAncestor() {
		// note: tested with a BST but the code does not expect a BST
		TreeNode<Integer> root = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5, 6, 7});
		
		assertSame(2, GraphsAndTrees.firstCommonAncestor(root, 1, 3).data);
		assertSame(null, GraphsAndTrees.firstCommonAncestor(root, 1, 8));
		assertSame(4, GraphsAndTrees.firstCommonAncestor(root, 1, 4).data);
		assertSame(4, GraphsAndTrees.firstCommonAncestor(root, 1, 7).data);
	}
	
	@Test
	public void testGetBSTSequencesBalanced() {
		int[] baseSequence = {1, 2, 3, 4, 5, 6, 7};
		TreeNode<Integer> initialTree = GraphsAndTrees.generateBST(baseSequence);
		List<List<Integer>> sequences = GraphsAndTrees.getSequences(initialTree);
		
		for (List<Integer> sequence : sequences) {
			int[] arraySequence = new int[sequence.size()];
			
			for (int i = 0; i < arraySequence.length; i++) {
				arraySequence[i] = sequence.get(i);
			}
			
			TreeNode<Integer> currentTree = buildBST(arraySequence);
			assertTrue(treesAreEqual(initialTree, currentTree));
		}
	}
	
	@Test
	public void testGetBSTSequencesUnbalanced() {
		int[] baseSequence = {1, 2, 3, 4, 5, 6, 7, 8};
		TreeNode<Integer> initialTree = GraphsAndTrees.generateBST(baseSequence);
		List<List<Integer>> sequences = GraphsAndTrees.getSequences(initialTree);
		
		for (List<Integer> sequence : sequences) {
			int[] arraySequence = new int[sequence.size()];
			
			for (int i = 0; i < arraySequence.length; i++) {
				arraySequence[i] = sequence.get(i);
			}
			
			TreeNode<Integer> currentTree = buildBST(arraySequence);
			assertTrue(treesAreEqual(initialTree, currentTree));
		}
	}
	
	@Test
	public void testContainsBothNull() {
		assertTrue(GraphsAndTrees.contains(null, null));
	}
	
	@Test
	public void testContainsOneNull() {
		assertTrue(GraphsAndTrees.contains(new TreeNode<Integer>(42), null));
		assertFalse(GraphsAndTrees.contains(null, new TreeNode<Integer>(42)));
	}
	
	@Test
	public void testContainsNonNullTreesAreContained() {
		TreeNode<Integer> outer = GraphsAndTrees.generateBST(new int[] {1, 2, 3, 4, 5, 6, 7});
		TreeNode<Integer> innerOne = outer;
		TreeNode<Integer> innerTwo = GraphsAndTrees.generateBST(new int[] {1, 2, 3});
		TreeNode<Integer> innerThree = GraphsAndTrees.generateBST(new int[] {5, 6, 7});
		TreeNode<Integer> innerFour = GraphsAndTrees.generateBST(new int[] {2, 4, 6});
		
		assertTrue(GraphsAndTrees.contains(outer, innerOne));
		assertTrue(GraphsAndTrees.contains(outer, innerTwo));
		assertTrue(GraphsAndTrees.contains(outer, innerThree));
		assertTrue(GraphsAndTrees.contains(outer, innerFour));
	}
	
	/* Builds and returns a valid binary search tree created by inserting all elements
	 * in the given arraySequence in order from left to right. */
	private TreeNode<Integer> buildBST(int[] arraySequence) {
		TreeNode<Integer> root = null;
		
		if (arraySequence.length > 0) {
			for (int i = 0; i < arraySequence.length; i++) {
				root = searchTreeAdd(root, arraySequence[i]);
			}
		}
		
		return root;
	}
	
	/* Adds the given value into the tree with the given root in the correct
	 * location for a binary search tree. Returns the updated root. */
	private TreeNode<Integer> searchTreeAdd(TreeNode<Integer> root, int value) {
		if (root == null) {
			root = new TreeNode<Integer>(value);
		} else {
			if (root.data >= value) {
				root.children[0] = searchTreeAdd(root.children[0], value);
			} else {
				root.children[1] = searchTreeAdd(root.children[1], value);
			}
		}
		return root;
	}
	
	/* Returns true if the two trees with the given roots are equal,
	 * meaning they have the exact same nodes in the exact same
	 * locations.  Assumes the two trees have the same number of
	 * branches from each root. Returns false otherwise. */
	private boolean treesAreEqual(TreeNode<Integer> observed, TreeNode<Integer> expected) {
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
	private void printInOrder(TreeNode<Integer> root) {
		if (root != null) {
			printInOrder(root.children[0]);
			System.out.print(root.data + " ");
			printInOrder(root.children[1]);
		}
	}

}
