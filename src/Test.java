import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {


		//WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 100, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		//w.crawl();
		//System.out.println("Done");
		//w.extractLinks("sample.txt");
		//System.out.println(w.map());
		GraphProcessor g = new GraphProcessor("sampleGraph.txt");
		System.out.println("OutDegree is: " +g.outDegree("Chicago"));
		//System.out.println(g.outDegree("/wiki/Complexity_theory"));
		//LinkedHashMap<String, LinkedHashSet<String>> newMap = 
		//System.out.println("Reversed List: " +g.("Chicago"));


	}

}
