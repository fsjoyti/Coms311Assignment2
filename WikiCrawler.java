

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Queue;
import java.util.Set;
import java.net.*;

public class WikiCrawler {
    String BASE_URL = "https://en.wikipedia.org";
	String seedUrl;
	int max;
	String fileName;
	String new_doc = "";
	String htmldoc = "htmldoc.txt";

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
		
		 ArrayList<String> links = new ArrayList<String>();
		 Queue<String> q = new LinkedList<>();
		 q.add(seedUrl);
		 Set<String> marked = new HashSet<>();
		 marked.add(seedUrl);
		 int count = 0;
		 while(!q.isEmpty()){
			 String v = q.poll();
			 v = v.replaceAll("^\"|\"$", "");
			 count ++;
			 System.out.println("count is: " +count);
			 System.out.println("v is: " +v);
			 String currentPage = BASE_URL+v;
			 
			 if (marked.size() <= max){
			 readhtml(currentPage);
			 System.out.println(extractLinks(htmldoc));
			 links = extractLinks(htmldoc);
			 // i am not sure if this is 100 % correct yet and i don'tknow how to set a wait period for sending requests
			 for (int i = 0; i < links.size(); i++){
				 String unmarked = links.get(i);
				 if (!marked.contains(unmarked)){
					 q.add(unmarked);
					 marked.add(unmarked);
				 }
			 }
			 }
				 
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
		System.out.println("Current page: " +currentPage);
		URL url = new URL(currentPage);
		URLConnection yc = url.openConnection(); 
		
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		 StringBuilder sb = new StringBuilder();
		 String tmp = null;
		 while( br.readLine()!=null){
			 tmp = br.readLine();
			// System.out.println("the HTML doc is: " +tmp);
			 writer.print(tmp);
			
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

		modified_doc = modified_doc.substring(modified_doc.lastIndexOf("<p>") + 3); // Ignore
											// for
																					// <P>

		return modified_doc;
	}

}
