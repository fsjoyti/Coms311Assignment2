
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GraphProcessor {

	LinkedHashMap<String, LinkedHashSet<String>> adjacency_list = new LinkedHashMap<String, LinkedHashSet<String>>();

	Map<String, Integer> FinishTime = new HashMap<String, Integer>();
	HashSet<String> visited = new HashSet<>();
	int numVertices;
	int count = 1;
	int counter = 0; // used in compute order & finish dfs
	// String[] vertices;
	HashSet<String> visited_ordered = new HashSet<>();// for compute order and
														// finish dfs

	public GraphProcessor(String graphData) throws FileNotFoundException {

		int verticesCounter = 0;
		File f = new File(graphData);
		Scanner input = new Scanner(f);
		this.numVertices = (Integer.parseInt(input.next()));
		// vertices = new String[numVertices];

		while (input.hasNext()) {
			String v = input.next();
			String e = input.next();
			if (!adjacency_list.containsKey(v)) {
				adjacency_list.put(v, new LinkedHashSet<String>());

			}

			adjacency_list.get(v).add(e);

		}

		System.out.println("OriginalList: " + adjacency_list);
		// System.out.println("map: " +myMap);
		// System.out.println("My vertices Array: " +Arrays.asList(vertices));
	}

	public int outDegree(String v) {

		int outDegree = 0;
		if (adjacency_list.containsKey(v)) {
			// LinkedList<String> list = (LinkedList<String>)
			// adjacency_list.get(v);
			LinkedHashSet<String> set = adjacency_list.get(v);
			outDegree = set.size();
		}
		// getReversedGraph(adjacency_list);
		// DFS(adjacency_list,v);

		// System.out.println("V is : " +v);

		computeOrder(adjacency_list, v);
		System.out.println();
		return outDegree;

	}

	public boolean sameComponent(String u, String v) {
		return true;
	}

	public ArrayList<String> componentVertices(String v) {
		return null;

	}

	public String largestComponent() {
		return null;

	}

	public int numComponents() {
		return 0;

	}

	public ArrayList<String> bfsPath(String u, String v) {
		return null;

	}

	private void stronglyConnectedComponents() {
		Deque<String> stack = new ArrayDeque<>();

	}

	private void computeOrder(LinkedHashMap<String, LinkedHashSet<String>> Graph, String v) {

		// Compute the reversed adjacency list
		LinkedHashMap<String, LinkedHashSet<String>> revGraph = getReversedGraph(Graph);

		// keep counter
		//counter = 0;
		
		String currVertex = v; //The v that we will pass in into finishDFS
		//visited_ordered.add(v);
		LinkedHashSet<String> set = revGraph.get(v);

		ArrayList<String> allVertices = new ArrayList<String>();
		// Store all keys into arrayList
		allVertices.addAll(revGraph.keySet());
		// Check for null condition (If there are no edges from this vertex, check for the next vertex with an edge)
		while(set == null) {
			visited_ordered.add(currVertex); //Maybe?
			FinishTime.put(currVertex, counter);
			Iterator<String> iterator = allVertices.iterator();
			currVertex = iterator.next();
			set = revGraph.get(currVertex);
			counter++;
		}
		//System.out.println("curr vertex is " +currVertex);
		// System.out.println("Set is: " +set);
		//System.out.println("visited ordered array: " +(visited_ordered));
		if(!visited_ordered.contains(currVertex)){
			
			FinishDFS(revGraph, currVertex);
			//System.out.println("Call finish DFS");
		}

	}

	private void FinishDFS(LinkedHashMap<String, LinkedHashSet<String>> graph, String v) {

		//int[] finishTime = new int[numVertices];

		visited_ordered.add(v);
		//System.out.println("v in FinishDFS is: " + v);
		
		//System.out.println("Set is: " + set);
		
		/*if (!visited_ordered.contains(v)) {
			visited_ordered.add(v);
		}*/
		LinkedHashSet<String> set = graph.get(v);

		Iterator<String> iterator = set.iterator();
		String currVertex = v;
		while (iterator.hasNext()) {
			String nextVertex = iterator.next();
			if(!visited_ordered.contains(nextVertex)){
				DFS(graph, currVertex);
				currVertex = nextVertex;
			}
			//System.out.println("next Vertex is " +nextVertex);
			counter++;
			FinishTime.put(currVertex, counter);
		}
		
		System.out.println("Finish time is: " +Arrays.asList(FinishTime));
	}

	private void DFS(LinkedHashMap<String, LinkedHashSet<String>> graph, String v) {

		visited.add(v);
		//System.out.print("DFS order is: " + v);
		LinkedHashSet<String> set = graph.get(v);

		if (set != null) {
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String neighbor = iterator.next();
				// System.out.println("Neighbour is: " +neighbor);
				if (!visited.contains(neighbor)) {
					DFS(graph, neighbor);
				}

			}

		}
		
		

	}

	/**
	 * Finding the reverse ordering of the edges
	 * 
	 * @param adjacencyList
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashSet<String>> getReversedGraph(
			LinkedHashMap<String, LinkedHashSet<String>> adjacencyListMap) {

		LinkedHashMap<String, LinkedHashSet<String>> revAdjListMap = new LinkedHashMap<String, LinkedHashSet<String>>();

		for (String key : adjacency_list.keySet()) {
			HashSet<String> set = adjacency_list.get(key);
			Iterator<String> iterator = set.iterator();

			while (iterator.hasNext()) {
				String element = iterator.next();

				if (!revAdjListMap.containsKey(element))
					revAdjListMap.put(element, new LinkedHashSet<String>());
				revAdjListMap.get(element).add(key);
			}

		}

		System.out.println("Reversed List is: " + revAdjListMap);

		return revAdjListMap;

	}
}
