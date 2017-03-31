


import java.io.*;
import java.util.*;

public class GraphProcessor {

		Map<String, List<String>> adjacency_list = new HashMap<String, List<String>>();
		int numVertices;
		int numComponents;
	    /**
	     * 
	     * @param graphData
	     * hold the absolute path of a file that stores a
	     * directed graph. This file will be of the following format:
	     * First line indicates number of vertices
	     * Each subsequent line lists a directed edge of the graph
	     * @throws FileNotFoundException
	     */
		public GraphProcessor(String graphData) throws FileNotFoundException{
			File f = new File(graphData);
			Scanner input = new Scanner(f);
			this.numVertices = (Integer.parseInt(input.next()));
			while(input.hasNext()){
				String v  = input.next();
				String e = input.next();
				 if(!adjacency_list.containsKey(v)) adjacency_list.put(v, new LinkedList());
				 adjacency_list.get(v).add(e);
				
			}
			
			System.out.println(adjacency_list);
			
		}
		
		/**
		 * Returns the out degree of v
		 * @param v
		 * @return
		 */
		public int outDegree(String v){
			int outDegree = 0;
			if (adjacency_list.containsKey(v)){
				LinkedList<String> list = (LinkedList<String>) adjacency_list.get(v);
				outDegree = list.size();
			}
			return outDegree;
			
		}
		
		/**
		 * Returns true if u and v belong to the same SCC; otherwise
		 * returns false.
		 * @param u
		 * @param v
		 * @return
		 */
		
		public boolean sameComponent(String u, String v){
			return true;
		}
		
		/**
		 * Return all the vertices that belong to the same Strongly Connected
		 * Component of v (including v).
		 * @param v
		 * @return
		 */
		public ArrayList<String> componentVertices(String v){
			return null;
			
		}
		
		/**
		 * Returns the size of the largest component.
		 * @return
		 */
		public String largestComponent(){
			return null;
			
		}
		
		/**
		 *  Returns the number of strongly connect components.
		 * @return
		 */
		public int numComponents(){
			return numComponents;
			
		}
		
		/**
		 * This method returns an array
		 * list of strings that represents the BFS path from u to v.
		 * Note that this method must return an array list of Strings. 
		 * First vertex in the path must be u and the last vertex must be v.
		 * If there is no path from u to v, then this method returns an empty list
		 * @param u
		 * @param v
		 * @return
		 * Returns the BFS path from u to v
		 */
		public ArrayList<String> bfsPath(String u, String v){
			
			return null;
			
		}
		/**
		 * 
		 */
		private void stronglyConnectedComponents(){
			Deque <String> stack = new ArrayDeque<>();
			
			
		}

}

