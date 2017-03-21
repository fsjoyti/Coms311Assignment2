import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WikiCrawler w = new WikiCrawler("/wiki/Computer_Science", 5, "WikiCS.txt");
		//System.out.println(w.extractLinks("sample.txt"));
		w.crawl();
	}

}
