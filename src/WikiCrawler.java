import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		max = max;
		fileName = fileName;
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
		//lol right now its getting the link we are NOT supposed to extract. Gotta understand what pattern and matcher does
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
	 * 
	 */
	public void crawl() {
		
		 Queue<String> q = new LinkedList<>();
		 q.add(seedUrl);
		 Set<String> marked = new HashSet<>();
		 marked.add(seedUrl);
		 while(!q.isEmpty()){
			 String v = q.remove();
			 String s = BASE_URL+v;
			 System.out.println(s);
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
