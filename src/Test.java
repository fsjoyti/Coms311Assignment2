import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {


		WikiCrawler w = new WikiCrawler("/wiki/Complexity Theory", 100, "t2.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		//w.crawl();
		//System.out.println("Done");
		String webpage = "";
        try {
        	 webpage = readFile("sample4.txt");
        } catch (IOException e) {
            fail("Couldn't open file '" + "'! Make sure it's in the project's test directory");
        }
        String[] actual = w.extractLinks(webpage).toArray(new String[0]);
        for (String line : actual) {
        	System.out.println(line);
        }
		//System.out.println(w.map());

	GraphProcessor g = new GraphProcessor("sampleGraph.txt");
	System.out.println("The BFS path is: " +g.bfsPath("Ames","Minneapolis"));
	////System.out.println("OutDegree is: " +g.outDegree("Ames"));
	

	//System.out.println(g.componentVertices("Minneapolis"));
		//System.out.println(g.outDegree("/wiki/Complexity_theory"));
		//LinkedHashMap<String, LinkedHashSet<String>> newMap = 
		//System.out.println("Reversed List: " +g.("Chicago"));

		//g.stronglyConnectedComponents();
		
	    //String path = new File("_WikiCrawlerTest_crawl_results.txt").getCanonicalPath();
	   // System.out.println("path of file is: " +path);
	}
	private static String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append('\n');
        }
        reader.close();
        return sb.toString();
    }
}
