package trees_and_graphs;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class AdjacencyList<E> {
	
	public Map<E, GraphNode<E>> nodes;
	
	/**
	 * Constructs an empty AdjacencyList
	 */
	public AdjacencyList() {
		nodes = new HashMap<E, GraphNode<E>>();
	}
	
	/**
	 * Adds the given element to this graph. Duplicate elements are
	 * not allowed.
	 * 
	 * @param element to this graph
	 */
	public void add(E element) {
		if (nodes.containsKey(element)) {
			throw new IllegalArgumentException();
		}
		nodes.put(element, new GraphNode<E>(element));
	}
	
	/**
	 * Adds the given child element to the children of the given
	 * parent element, adding either node to this graph if needed.
	 * 
	 * @param parent parent to which's children to add a child
	 * @param child child to be added to the parent's children
	 */
	public void addChild(E parent, E child) {
		GraphNode<E> parentNode = findNode(parent);
		GraphNode<E> childNode = findNode(child);
		
		parentNode.addChild(childNode);
	}
	
	/* Searches the graph for a node with the given data. If
	 * found, returns the node.  Otherwise creates a new node
	 * with the given data, adds it to the graph, and returns it. */
	private GraphNode<E> findNode(E data) {
		if (!nodes.containsKey(data)) {
			nodes.put(data, new GraphNode<E>(data));
		}
		return nodes.get(data);
	}
	
	/**
	 * Returns a set view of all the children of the given element
	 * in this graph.
	 * 
	 * @param parent parent of the requested children
	 * @return set of the children of the given parent or null if the
	 * given parent is not a node in this graph.
	 */
	public Set<E> getChildren(E parent) {
		if (!nodes.containsKey(parent)) {
			return null;
		}
		Set<E> children = new HashSet<E>();
		
		for (GraphNode<E> child : nodes.get(parent).children) {
			children.add(child.data);
		}
		
		return children;
	}
	
	/**
	 * Returns true if this contains the given element and false otherwise.
	 * 
	 * @param element element to search for in this graph
	 * @return true if the element is found, false otherwise
	 */
	public boolean contains(E element) {
		return nodes.containsKey(element);
	}
	
	/**
	 * Represents a node in an adjacency list graph.
	 * 
	 * @author Kyle
	 * 
	 * @param <E> data stored in the graph and this node
	 */
	private static class GraphNode<E> {
		
		public E data;
		public Set<GraphNode<E>> children;
		
		/**
		 * Initializes a new node with the given data.
		 * 
		 * @param data
		 */
		public GraphNode(E data) {
			this.data = data;
			this.children = new HashSet<GraphNode<E>>();
		}
		
		/**
		 * Adds the given node to the children of this node.
		 * 
		 * @param child the node to become this node's child
		 */
		public void addChild(GraphNode<E> child) {
			children.add(child);
		}
		
		@Override
		public boolean equals(Object other) {
			if (other == null) {
				return false;
			} else {
				return other instanceof GraphNode<?> && 
						this.data.equals(((GraphNode<?>)other).data);
			}
		}
		
		@Override
		public int hashCode() {
			return data.hashCode();
		}

	}


}
