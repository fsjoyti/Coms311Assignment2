import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {
		// TODO Auto-generated method stub
		WikiCrawler w = new WikiCrawler("/wiki/Complexity_theory", 20, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
		//System.out.println(w.map());
		//w.map();
		System.out.println("done");
	}

}
