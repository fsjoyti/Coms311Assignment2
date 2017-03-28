import java.io.*;
import java.util.*;
public class Test2 {
	
	static String firstNode;
	/*
	public static void readFile(String filename) throws FileNotFoundException{
		File f = new File(filename);
		Scanner input = new Scanner(f);
		while(input.hasNext()){
			String v  = input.next();
			String e = input.next();
			System.out.println(v);
			System.out.println(e);
		}
		
	}
	
	public static void BFS(){
		 ArrayList<String> links = new ArrayList<String>();
		 Queue<String> q = new LinkedList<>();
		 q .add(firstNode);
		 Set<String> marked = new HashSet<>();
		 marked.add(seedUrl);
		
		 int reachable_size = 0;
		 
		 while(!q.isEmpty() && count <= max ){
			 //System.out.println(count);
			 String v = q.poll();
			 v = v.replaceAll("^\"|\"$", "");
			 //System.out.println(v);
			 
			 String currentPage = BASE_URL+v;
			 
			 readhtml(currentPage);
			 links = extractLinks(htmldoc);
			 reachable_size = links.size();
	
			 for (int i = 0; i < reachable_size; i++){
				 String unmarked = links.get(i);

				 //writer.println(unmarked);
				 //System.out.println("v is: " +v);
				 unmarked = unmarked.replaceAll("^\"|\"$", "");
				 if (!marked.contains(unmarked) && unmarked.equals(v) == false){
					 q.add(unmarked);
					 marked.add(unmarked);
				     System.out.println(v + "" +unmarked);
				     writer.println(v + " "  +unmarked);
				    
				 }
			
			 
			 }
			 count++;
			 
		 }
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		readFile("Test.txt.txt");

	}
*/
}
