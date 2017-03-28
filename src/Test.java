import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {

		WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 5, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
		System.out.println("Done");
		//System.out.println(w.map());

	}

}
