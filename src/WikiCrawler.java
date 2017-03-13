import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WikiCrawler {
	
	String seedUrl;
	int max;
	String fileName;
	String new_doc;
	/**
	 * 
	 * @param seedUrl
	 * relative address of the seed url (within Wiki domain)
	 * @param max
	 * Maximum number pages to be crawled
	 * @param fileName
	 * name of a file–The graph will be written to this file
	 */
	public WikiCrawler(String seedUrl, int max, String fileName){
		seedUrl = seedUrl;
		max = max;
		fileName = fileName;
	}
	
	private ArrayList<String> extractLinks(String doc){
		
		return null;
		
	}
	
	private Boolean isWikiLink(String doc){
		
		return true;
	}
	
	//change to private -- TODO
	public String after_p(String doc) throws IOException{
		String new_doc = "";
		FileReader file = new FileReader(doc);
		try(BufferedReader br = new BufferedReader(file)){
			String line;
			while((line = br.readLine()) != null){
				new_doc += line;
			}
		}
		
		new_doc = new_doc.substring(new_doc.lastIndexOf("<p>") + 3); //Ignore Case?? Test for <P>
		System.out.println(new_doc);
		return new_doc;
	}
	
	private void crawl(){
		
	}

}
