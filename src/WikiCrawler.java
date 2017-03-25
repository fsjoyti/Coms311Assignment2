

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
	Map<String, List<String>> edges = new HashMap<String, List<String>>();

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

		new_doc = after_p(doc);
		
		ArrayList<String> links = new ArrayList<String>();
		String html = new_doc;
		String regex = "<a\\s?href\\s?=\\s?\"/(\\bwiki)[(/\\w+)]+\"";
		
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
		 Long startTime = System.currentTimeMillis()/1000;
		 ArrayList<String> links = new ArrayList<String>();
		 Queue<String> q = new LinkedList<>();
		 q.add(seedUrl);
		 Set<String> marked = new HashSet<>();
		 marked.add(seedUrl);
		
		 int reachable_size = 0;
		 
		 while(!q.isEmpty() && count <= max ){
			 
			 String v = q.poll();
			 v = v.replaceAll("^\"|\"$", "");
			 
			 String currentPage = BASE_URL+v;
			 
			 readhtml(currentPage);
			 links = extractLinks(htmldoc);
			 reachable_size = links.size();
	
			 for (int i = 0; i < reachable_size; i++){
				 String unmarked = links.get(i);
				 //addEdge(v, unmarked);
				 if (!marked.contains(unmarked) && unmarked != v){
					 q.add(unmarked);
					 marked.add(unmarked);
					 addEdge(v, unmarked);
				 }
			 }
			 
			 
			 count++;

		}
		 System.out.println("The marked hashSet is: " +marked);
		 
		// Long endTime = System.currentTimeMillis()/1000;
		// System.out.println("Total time: " +(endTime - startTime)) ;

	}

	
	private void addEdge(String src, String dest){
		List<String> srcNeighbours = this.edges.get(src);
		if(srcNeighbours == null){
			this.edges.put(src, srcNeighbours = new ArrayList<String>());
		}
		srcNeighbours.add(dest);
		
	}
	
	public Map<String, List<String>> map(){
		return edges;
		
	}
	
	private Iterable<String> getNeighbours(String vertex){
		List<String> neighbours = this.edges.get(vertex);
		if(neighbours == null){
			return Collections.emptyList();
		}
		else{
			return Collections.unmodifiableList(neighbours);
		}
	}
	
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
