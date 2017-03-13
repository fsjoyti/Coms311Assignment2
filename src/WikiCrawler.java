import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String regex = "<a href\\s?=\\s?\"([^\"]+)\">";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		int index = 0;
		while(matcher.find(index)){
			String wholething = matcher.group(); // includes "<a href" and ">"
				String link = matcher.group(1);
				links.add(link);
				//index++;


		        index = matcher.end();
		}
		
		return links;

	}

	/**
	 * 
	 */
	private void crawl() {

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
																					// Case??
																					// Test
																					// for
																					// <P>

		return modified_doc;
	}

}
