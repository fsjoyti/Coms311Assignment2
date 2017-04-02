
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GraphProcessor {

	LinkedHashMap<String, LinkedHashSet<String>> adjacency_list = new LinkedHashMap<String, LinkedHashSet<String>>();

	Map<String, Integer> FinishTime = new TreeMap<String, Integer>();
	HashSet<String> visited = new HashSet<>();
	int numVertices;
	int numComponents;
	int count = 1;
	int counter; // used in compute order & finish dfs
	int t; //[This keeps track of the number of vertices that have been fully explored
	String s = "";
	// String[] vertices;
	HashSet<String> visited_ordered = new HashSet<>();// for compute order and
														// finish dfs
	ArrayList<String> finishTimeList = new ArrayList<String>();
	
	 Deque<String> result = new ArrayDeque<>();
	 
	// List<Set<String>> components = new ArrayList<>(); //List of SCcomponents

	// ArrayList<String> allVertices = new ArrayList<String>();
	// String[] sortedList;

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

		computeOrder(adjacency_list);
		stronglyConnectedComponents(adjacency_list);
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
		
		//numComponents = components.size();
		System.out.println("Total number of components are: " +numComponents);
		return numComponents;

	}

	public ArrayList<String> bfsPath(String u, String v) {
		return null;

	}

	

	private LinkedHashMap<String, Integer> sortMap() {

		// TODO make private

		// String[] sortedList = new String[numVertices];

		// List list = new ArrayList(FinishTime.entrySet());

		List<Integer> mapValues = new ArrayList<Integer>(FinishTime.values());
		List<String> mapKeys = new ArrayList<String>(FinishTime.keySet());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

		Iterator<Integer> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			int val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				String key = keyIt.next();
				int comp1 = FinishTime.get(key);
				int comp2 = val;

				if (comp1 == comp2) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}

		System.out.println(Arrays.asList(sortedMap));

		return sortedMap;

	}
	/*
	 * For testing purposes I made it public
	 */

	public void computeOrder(LinkedHashMap<String, LinkedHashSet<String>> Graph) {

		
		// Compute the reversed adjacency list
		
		// keep counter
		counter = 0;

		ArrayList<String> allVertices = new ArrayList<String>();
		// Store all keys into arrayList
	
		for (String key : Graph.keySet()) {
			

			if (!visited.contains(key)) {
				// DFS(revGraph,key);
				DFS(Graph, key);

			}
		}
		System.out.println(FinishTime);

	}
	
	private void stronglyConnectedComponents(LinkedHashMap<String, LinkedHashSet<String>> Graph) {
		
		
		LinkedHashMap<String, LinkedHashSet<String>> revGraph = getReversedGraph(Graph);
		computeOrder(Graph);
		System.out.println(result);
		List<Set<String>> components = new ArrayList<>();
		visited.clear();
		while (!result.isEmpty()){
			String vertice = result.poll();
			if (visited.contains(vertice)){
				continue;
			}
			HashSet<String> set= new HashSet<String>();
			DFSReverse(revGraph,vertice,set);
			components.add(set);

			
		}
		numComponents = components.size();
		System.out.println("Size of SCC: " +components.size());
		System.out.println("Strongly connected components list"+components);
		

	}
	
	


	private void DFS(LinkedHashMap<String, LinkedHashSet<String>> graph, String v) {
		// sortedList = new String[numVertices];
		
		/*
		 * for(int i = 0; i<sortedList.length; i++){ sortedList[i] = ""; }
		 */
		visited.add(v);
		// counter++;
		// System.out.print("DFS order is: " + v);
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

		counter++;
		FinishTime.put(v, counter);
		result.push(v);
		

	}
	
	private void DFSReverse(LinkedHashMap<String, LinkedHashSet<String>> graph, String v, Set <String> component){
		
		visited.add(v);
		component.add(v);
		LinkedHashSet<String> set = graph.get(v);
		if (set != null) {
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String neighbor = iterator.next();
				// System.out.println("Neighbour is: " +neighbor);
				if (!visited.contains(neighbor)) {
					DFSReverse(graph, neighbor,component);
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
