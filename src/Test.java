import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {


		WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 100, "WikiCS.txt");
		System.out.println(w.extractLinks("htmldoc.txt"));
		//w.crawl();
		//System.out.println("Done");
		//w.extractLinks("sample.txt");
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

}
