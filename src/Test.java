import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException,InterruptedException {
		// TODO Auto-generated method stub
		WikiCrawler w = new WikiCrawler("/wiki/Complexity theory", 100, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
	}

}
