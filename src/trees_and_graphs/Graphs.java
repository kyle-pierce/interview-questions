package trees_and_graphs;

import java.util.HashSet;
import java.util.Set;

public class Graphs {
	
	/* Returns true if there is a path between the given src and dst nodes
	 * in the given directed graph. Uses breadth-first search. */
	public boolean pathBetweenNodesBFS(AdjacencyList directedGraph, Node src, Node dst) {
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
	public boolean pathBetweenNodesDFS(AdjacencyList directedGraph, Node src, Node dst) {
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
	
}
