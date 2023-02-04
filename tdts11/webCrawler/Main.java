package tdts11.webCrawler;

import java.io.IOException;

public class Main extends LinkFormating{

	public static void main(String[] args) throws IOException {
		String[] url = {
				"https://www.reddit.com/r/worldnews/",
				"https://sv.wikipedia.org/wiki/Sverige",
				"https://twitter.com/",
				"https://riksdagen.se/",
				"http://www.energimyndigheten.se/",
				"https://www.hltv.org/",
				"http://it-programmet.gitlab-pages.liu.se/termin2/index.html",


		};
		
		WebCrawler spider = new WebCrawler();
		int startIndex = 6;
		System.out.println("Depth: 0, " + getDomain(url[startIndex]));
		spider.search(url[startIndex], 0);
	}
}
