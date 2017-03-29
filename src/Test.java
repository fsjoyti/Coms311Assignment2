import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {

<<<<<<< HEAD
		//WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 20, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		//w.crawl();
=======
		WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 5, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
		System.out.println("Done");
>>>>>>> 6753edd0c8a6ef204ef85cdc3ad931632f4dfe73
		//System.out.println(w.map());
		GraphProcessor g = new GraphProcessor("sampleGraph.txt");
		System.out.println(g.outDegree("Chicago"));

	}

}
