package trees_and_graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graphs {

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
}
