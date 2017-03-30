

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Queue;
import java.util.Set;
import java.net.*;

public class WikiCrawler {
    String BASE_URL = "https://en.wikipedia.org";
	String seedUrl;
	int max;
	int count = 0;
	String fileName;
	String new_doc = "";
	String htmldoc = "htmldoc.txt";
	Map<String, List<String>> inedge = new HashMap<String, List<String>>();
	
	//List<String> srcNeighbours = (List<String>) edges.keySet();
	/**
	 * 
	 * @param seedUrl
	 *            relative address of the seed url (within Wiki domain)
	 * @param max
	 *            Maximum number pages to be crawled
	 * @param fileName
	 *            name of a file–The graph will be written to this file
	 */
	public WikiCrawler(String seedUrl, int max, String fileName) {
		this.seedUrl = seedUrl;
		this.max = max;
		this.fileName = fileName;
		
		
	}

	/**
	 * Find required wikilinks from the given doc Change it to return a list of
	 * all the required URL's This method can be merged in with the extract
	 * links method.
	 * 
	 * @param doc
	 * @return
	 * @throws IOException
	 */

	public ArrayList<String> extractLinks(String doc) throws IOException {
		//PrintWriter writer = new PrintWriter("my_edges.txt", "UTF-8");
		new_doc = after_p(doc);
		
		ArrayList<String> links = new ArrayList<String>();
		String html = new_doc;
		String regex = "<a\\s?href\\s?=\\s?\"/(\\bwiki)[(/\\w+)%]+\"";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		int index = 0;
		while(matcher.find(index)){
			    String wholething = matcher.group();
				String link = matcher.group();
				int x =link.indexOf("\"");
				String resulturl = link.substring(x);
				links.add(resulturl);
				index++;

		        index = matcher.end();
		}
		
		return links;

	}

	/**
	 * @throws IOException, 
	 * 
	 */
	public void crawl() throws IOException,MalformedURLException  {

		 PrintWriter writer = new PrintWriter("my_edges.txt", "UTF-8");
		 writer.println(max);

		// writer = new PrintWriter(this.fileName, "UTF-8");

		 Long startTime = System.currentTimeMillis()/1000;
		 ArrayList<String> links = new ArrayList<String>();
		 Set<String> modified_unmarked = new HashSet<>();
		 Queue<String> q = new LinkedList<>();
		 q .add(seedUrl);
		 Set<String> marked = new HashSet<>();
		
		 
		 marked.add(seedUrl);
		
		 int reachable_size = 0;
		 int count_vertices = 0;
		 
		 while(!q.isEmpty()){
			
			 //System.out.println(count);
			 String v = q.poll();
			 System.out.println(v);
			 modified_unmarked.add(v);
			 if(!inedge.containsKey(v)) inedge.put(v, new LinkedList());
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
					 System.out.println(v + "" +unmarked);
					 inedge.get(v).add(unmarked);
					 
					 if (count <= max){
					
					 q.add(unmarked);
					 count++;
					
					 
					 marked.add(unmarked);
					 //System.out.println(v + "" +unmarked);
					// System.out.println(v + "" +unmarked);
					 //System.out.println(v + "" +unmarked);
				     writer.println(v + " "  +unmarked);
					 }
				 }
				
			 }
			
			 count_vertices = 0;
		 }
		 System.out.println("Count is: "+count);
		 System.out.println(Arrays.asList(inedge));
		 System.out.println("Vertices count: " +count_vertices);
		 writer.close();
		 //System.out.println("The marked hashSet size is: " +marked.size());
		// Long endTime = System.currentTimeMillis()/1000;
		// System.out.println("Total time: " +(endTime - startTime)) ;
		// System.out.println(marked);
	}
	/*
	public void crawl() throws IOException,MalformedURLException  {
		
		PrintWriter writer = new PrintWriter("my_edges.txt", "UTF-8");
		writer.println(max);
		String edges = null;
		//Counter to keep track of total number of pages visited
		int numVerticesVisited = 0;
		
		//Initialize a Queue Q and a list visited.
		Queue<String> q = new LinkedList<>();
		q .add(seedUrl);
		ArrayList<String> values = new ArrayList<String>();
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();
		//ArrayList<String> maxtoVisit = new ArrayList<String>(max);
		// Place seed url in Q and visited.
		q.add(seedUrl);
		visited.add(seedUrl);
		
		//while Q is not empty Do
		while(!q.isEmpty() ){
			
			//Let currentPage be the first element of Q.
			String v = q.poll();
			System.out.println("v: "+v);
			v = v.replaceAll("^\"|\"$", "");
			//maxtoVisit.add(v);
			String currentPage = BASE_URL+v;
			// Send a request to server at currentPage and download currentPage
			readhtml(currentPage);
			//Extract all links from currentPage
			links = extractLinks(htmldoc);
			//For every link u that appears in currentPage
			//numVerticesVisited = 1;
			//for(String u  : links){
			for (int i = 0; i <= max; i++){
				System.out.println("getting links");
				edges = links.get(i);
				//u = u.replaceAll("^\"|\"$", "");
				edges = edges.replaceAll("^\"|\"$", "");
				//Counter to keep track of total number of pages visited
				
				//If u /∈ visited add u to the end of Q, and add u to visited.
<<<<<<< HEAD
				if(!visited.contains(u) && u.equals(v) == false ){//&& numVerticesVisited < max){
					
					//System.out.println("u is: " +u);
					if (count <= max){
					q.add(u);
					count ++;
					System.out.println("u:"+u);
					visited.add(u);
					numVerticesVisited ++;
				//	if(numVerticesVisited < max){
					//	String firstStrings = u;
						////System.out.println("firstStrings:"+u);
						//System.out.println(firstStrings);
						writer.println(v + " ");
					//}
					}
=======
				if(!visited.contains(edges) && edges.equals(v) == false ){//&& numVerticesVisited < max){
					//System.out.println("u is: " +u);
					q.add(edges);
					visited.add(edges);
					numVerticesVisited ++;
					writer.println(v + " "  +edges);
				/*	if(numVerticesVisited <= max){
						//String firstStrings = u;
						//writer.println(v + " "  +firstStrings);
						//maxtoVisit.add(u);
						values.add(edges);

						System.out.println("num vertices visited " +numVerticesVisited );
					}*/
					
					
					//When you crawl one site, 
					//writer.println(v + " "  +u);
			

		
		
		
	/*	for(int i= 0; i<maxtoVisit.size() ; i++){
			for(int j =0; j<visited.size(); j++){
				if(maxtoVisit.get(i) == visited.get(j)){
					values.add(visited.get(j));
				}
			}
		}
		for(int i = 0; i<values.size(); i++){

			System.out.println("value pages: " +values.get(i));
		//	System.out.println("max visited pages: " +maxtoVisit.get(i));
		}*/
		/*for(int i = 1; i<maxtoVisit.size(); i++){

		//	System.out.println("visited list: " +visited.get(i));
			System.out.println("max visited pages: " +maxtoVisit.get(i));
		}*/
		
	

	/**
	 * gets the HTML tag passed in and writes to a file
	 * @param currentPage
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void readhtml(String currentPage) throws MalformedURLException, IOException {
		PrintWriter writer = new PrintWriter(htmldoc, "UTF-8");
		//System.out.println("Current page: " +currentPage);
		URL url = new URL(currentPage);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		if(count > 1 && count%100 == 1){
			System.out.println("Count is: "+count);
			System.out.println("Waiting 3 secs");
			connection.setReadTimeout(3*1000);
		}
		//String temp = null;
		String inputLine = in.readLine();
		while((inputLine != null)){
			writer.print(inputLine);
			writer.println();
			inputLine = in.readLine();
			//System.out.println(inputLine);
		}
	}

	/**
	 * Gets the string beyond the
	 * <p>
	 * point
	 * 
	 * @param doc
	 * @return
	 * @throws IOException
	 */

	private String after_p(String doc) throws IOException {

		String modified_doc = "";

		FileReader file = new FileReader(doc);

		try (BufferedReader br = new BufferedReader(file)) {

			String line;

			while ((line = br.readLine()) != null) {

				modified_doc += line;

			}

		}
		try{
			modified_doc = modified_doc.substring(modified_doc.lastIndexOf("<p>") + 3); // Ignore
		}
		catch (Exception e){
			System.out.println("No p tags exist");
		}
       																		// <P>

		return modified_doc;
	}

}
