import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WikiCrawler {

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
		seedUrl = seedUrl;
		max = max;
		fileName = fileName;
	}
	/**
	 * Find required wikilinks from the given doc
	 * Change it to return a list of all the required URL's This method can be merged in with the extract links method. 
	 * @param doc
	 * @return
	 * @throws IOException
	 */
	
	private ArrayList<String> extractLinks(String doc) throws IOException {
		new_doc = after_p(doc);
		return null;

	}
	/**
	 * 
	 */
	private void crawl() {

	}
	
	/**
	 * Gets the string beyond the <p> point
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
	
		modified_doc = modified_doc.substring(modified_doc.lastIndexOf("<p>") + 3); // Ignore Case?? Test for <P>
		
		return modified_doc;
	}

}
