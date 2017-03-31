import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {


		WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 100, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
		System.out.println("Done");
		//w.extractLinks("sample.txt");
		//System.out.println(w.map());
		/*GraphProcessor g = new GraphProcessor("wikiCC.txt");
		System.out.println(g.outDegree("Chicago"));
		System.out.println(g.outDegree("/wiki/Complexity_theory"));*/

	}

}
