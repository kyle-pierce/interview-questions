package trees_and_graphs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a node in an adjacency list graph.
 * 
 * @author Kyle
 * 
 * @param <E> data stored in the graph and this node
 */
public class GraphNode<E> {
	
	private E data;
	private Set<GraphNode<E>> children;
	
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
	 * Returns the data stored in this node.
	 * 
	 * @return the data stored in this node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Returns a view of the children of this node.
	 * 
	 * @return the children of this node
	 */
	public Set<GraphNode<E>> getChildren() {
		return Collections.unmodifiableSet(children);
	}
	
	/**
	 * Adds the given node to the children of this node.
	 * 
	 * @param child the node to become this node's child
	 */
	public void addChild(GraphNode<E> child) {
		children.add(child);
	}

}
