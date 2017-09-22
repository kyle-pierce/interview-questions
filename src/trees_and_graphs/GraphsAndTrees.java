package trees_and_graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphsAndTrees {
	
	/* Returns true if there is a path between the given src and dst nodes
	 * in the given directed graph. Uses breadth-first search. */
	public static <E> boolean pathBetweenNodesBFS(AdjacencyList<E> graph, 
												  E src, E dst) {
		Set<E> visiting = new HashSet<E>();
		Set<E> visited = new HashSet<E>();
		visiting.add(src);
		
		while (!visiting.isEmpty()) {
			Set<E> toBeVisited = new HashSet<E>();
			for (E node : visiting) {
				if (node.equals(dst)) {
					return true;
				} else {
					visited.add(node);
					for (E child : graph.getChildren(node)) {
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
	public static <E> boolean pathBetweenNodesDFS(AdjacencyList<E> graph, 
											  	  E src, E dst) {
		if (src == null || dst == null) {
			return false;
		}
		if (src.equals(dst)) {
			return true;
		} else {
			for (E child : graph.getChildren(src)) {
				if (pathBetweenNodesDFS(graph, child, dst)) {
					return true;
				}
			}
			return false;
		}
	}
	
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
	
	/* Given the list of projects and dependencies, returns a project order in which
	 * no projects appear before those on which they depend. Assumes there are no
	 * projects with identical names. */
	public static List<String> buildOrder(List<String> projects, List<Pair> dependencies) {
		AdjacencyList<String> graph = new AdjacencyList<>();	// dependency graph
		List<String> buildOrder = new ArrayList<>();			// store the build order
		
		// this is not strictly necessary, but it will make looking up placed projects
		// much faster than searching the list
		Set<String> handledProjects = new HashSet<>();			// set of placed projects
		
		// loop over the dependencies
		for (Pair p : dependencies) {
			// add projects to graph if necessary
			if (!graph.contains(p.from)) {
				graph.add(p.from);
			}
			if (!graph.contains(p.to)) {
				graph.add(p.to);
			}
			// each project points to the project(s) which must be completed
			// before that project
			graph.addChild(p.to, p.from);
		}
		
		// loop over the projects, safely adding each to the build order
		for (String project : projects) {
			addBuildOrder(buildOrder, graph, handledProjects, project);
		}
		
		// return the created build order
		return buildOrder;
	}
	
	/* Adds the given currentProject to the given buildOrder in such a way that all the of
	 * projects in the given dependencies which must be completed first are done so. Ensures
	 * no projects are planned for completion multiple times using the given handledProjects. */
	private static void addBuildOrder(List<String> buildOrder, AdjacencyList<String> dependencies,
							   		  Set<String> handledProjects, String currentProject) {
		if (!handledProjects.contains(currentProject)) {
			// the project has not been handled, handle it
			handledProjects.add(currentProject);
			
			if (dependencies.getChildren(currentProject).size() > 0) {
				// children are projects which must be completed first, so we
				// loop through the children and create their build orders
				for (String child : dependencies.getChildren(currentProject)) {
					addBuildOrder(buildOrder, dependencies, handledProjects, child);
				}
			}
			// finally add the current project in its safe location
			buildOrder.add(currentProject);
		}
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
	public static void possibleBSTSequences(TreeNode<Integer> root) {
		int size = getSizeOfTree(root);
		printSequences(root, new ArrayList<Integer>(), size);
	}
	
	/* Returns the size of the given tree. The empty tree has size of 0. */
	private static int getSizeOfTree(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + getSizeOfTree(root.children[0]) + getSizeOfTree(root.children[1]);
		}
	}
	
	private static void printSequences(TreeNode<Integer> root, List<Integer> sequence, 
									   int nodesRemaining) {
		if (nodesRemaining == 0) {
			System.out.println(sequence);
		} else if (root != null) {
			sequence.add(root.data);
			
			
		}
	}

}
