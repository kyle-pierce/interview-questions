package trees_and_graphs;

/** Represents a pair of projects; 'from' must be completed before 'to'. */
public class Pair {
	public String from;
	public String to;
	
	/**
	 * Creates a pair with the given Strings.
	 * 
	 * @param from project which must be completed first
	 * @param to project which must be completed second
	 */
	public Pair(String from, String to) {
		this.from = from;
		this.to= to;
	}
}