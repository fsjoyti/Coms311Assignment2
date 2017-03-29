


import java.io.*;
import java.util.*;

public class GraphProcessor {

	Map<String, List<String>> adjacency_list = new HashMap<String, List<String>>();
	int numVertices;

	
		

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
		
		public int outDegree(String v){
			int outDegree = 0;
			if (adjacency_list.containsKey(v)){
				LinkedList<String> list = (LinkedList<String>) adjacency_list.get(v);
				outDegree = list.size();
			}
			return outDegree;
			
		}
		
		public boolean sameComponent(String u, String v){
			return true;
		}
		
		public ArrayList<String> componentVertices(String v){
			return null;
			
		}
		
		public String largestComponent(){
			return null;
			
		}
		
		public int numComponents(){
			return 0;
			
		}
		
		public ArrayList<String> bfsPath(String u, String v){
			return null;
			
		}
		
		private void stronglyConnectedComponents(){
			Deque <String> stack = new ArrayDeque<>();
			
			
		}

}

