
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class GraphProcessor {

	LinkedHashMap<String, LinkedHashSet<String>> adjacency_list = new LinkedHashMap<String, LinkedHashSet<String>>();
	
	Map<String, Integer> myMap = new HashMap<String, Integer>();
	 HashSet<String> visited = new HashSet<>();
	int numVertices;
	int count = 1;
	String[] vertices;
	public GraphProcessor(String graphData) throws FileNotFoundException {
		//vertices = new String[numVertices];
		int verticesCounter = 0;
		File f = new File(graphData);
		Scanner input = new Scanner(f);
		this.numVertices = (Integer.parseInt(input.next()));
		vertices = new String[numVertices];
		
		while (input.hasNext()) {
			String v = input.next();
			String e = input.next();
			if (!adjacency_list.containsKey(v)){
				adjacency_list.put(v, new LinkedHashSet<String>());
				vertices[verticesCounter] = v;
				verticesCounter++;
			}
				
			adjacency_list.get(v).add(e);
			

		}

		System.out.println("OriginalList: " +adjacency_list);
		//System.out.println("map: " +myMap);
		//System.out.println("My vertices Array: " +Arrays.asList(vertices));
	}

	public int outDegree(String v) {
		
		int outDegree = 0;
		if (adjacency_list.containsKey(v)) {
			// LinkedList<String> list = (LinkedList<String>)
			// adjacency_list.get(v);
			LinkedHashSet<String> set = adjacency_list.get(v);
			outDegree = set.size();
		}
		//getReversedGraph(adjacency_list);
		DFS(adjacency_list,v);
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

	private void computeOrder(LinkedHashMap<String, LinkedHashSet<String>> OrigadjacencyList) {

		//Compute the reversed adjacency list
		LinkedHashMap<String, LinkedHashSet<String>> revAdjList = getReversedGraph(OrigadjacencyList);
		
		//Unmark every vertex v
		int[] unmarked = new int[numVertices];
		
		for(int i = 0; i<unmarked.length; i++){
			unmarked[i] = 0;
		}
		
		//
		int counter = 0;
		
		
		
	}
	
	private void DFSAlgo(LinkedHashMap<String, LinkedHashSet<String>> graph,int n){
		
		//Unmark every vertex
		boolean[] visited = new boolean[numVertices];
		//int[] parent = new int[numVertices];
		//int[] DFSNum = new int[numVertices];
		
		//DFS(adjacency_list, visited, vertices[n]);		
		
		
	}
	
	private void DFS(LinkedHashMap<String, LinkedHashSet<String>> graph,  String v){
		visited.add(v);
		System.out.print(v);
		LinkedHashSet<String> set = graph.get(v);
		
		if (set!=null){
			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()){
				 String neighbor = iterator.next();
				 if (!visited.contains(neighbor)){
					 DFS(graph,neighbor);
				 }
				 
			}
			
			
		}
		
	}
	
	
	

	/**
	 * Finding the reverse ordering of the edges
	 * @param adjacencyList
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashSet<String>> getReversedGraph(LinkedHashMap<String, LinkedHashSet<String>> adjacencyListMap) {

		LinkedHashMap<String, LinkedHashSet<String>> revAdjListMap = new LinkedHashMap<String, LinkedHashSet<String>>();
		
		for (String key : adjacency_list.keySet()){
			 HashSet <String> set = adjacency_list.get(key);
			 Iterator<String> iterator = set.iterator();
			
			 while (iterator.hasNext()){
				 String element = iterator.next();
				
				 if(! revAdjListMap.containsKey(element))  revAdjListMap.put(element,new  LinkedHashSet<String>());
				 revAdjListMap.get(element).add(key);
			 }
			 
			 
			 
		}
		
		System.out.println("Reversed List is: "  +revAdjListMap);
		
		return revAdjListMap;

	}
}
