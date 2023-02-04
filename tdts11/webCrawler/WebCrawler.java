package tdts11.webCrawler;

import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//This class uses the jsoup library to analys HTML docs  
public class WebCrawler extends LinkFormating{

	LinkedList<String> domainsVisited = new LinkedList<>(); //A list used to make sure a domain is not visited twice or more
	private final int MAX_DEPTH = 3;
	private final int MAX_LINKS_PER_LEVEL = 3;

	// ------------------------------------------------------------------------------------------------------

	void search(String url, int depth) throws IOException {
		if(depth == 0) {
			domainsVisited.add(getDomain(url));
		}
		if (depth > 1000) {//This will never be reached. Just used in testing
			System.out.println("Depth: " + depth + ", " + getDomain(url));
			domainsVisited.add(getDomain(url));
			System.exit(0);
		}
		if (depth < MAX_DEPTH) {//Recursion exit condition
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			int statuscode = con.response().statusCode();		
			if (statuscode == 200) {
				Elements links = doc.select("[href]");
				Elements imports = doc.select("link[href]");
				links.addAll(imports);
				
				int loopdepth = 1;
				depth++;
				for (Element e : links) { //Gather and print links
					try {
						String linkString = e.attr("abs:href").toString();
						if (filter(linkString)) {
							
							String linkDomain = getDomain(linkString);
							domainsVisited.add(linkDomain);

							loopdepth++;
							
							if(depth == 1) {
								System.out.println("Depth: " + depth + ", " + linkDomain);
							}else if(depth == 2) {
								System.out.println("Depth: " + depth + ",        " + linkDomain);
							}
							else {
								System.out.println("Depth: " + depth + ",                " + linkDomain);
							}
						
							search(linkString, depth); //Recursion

							if (loopdepth > MAX_LINKS_PER_LEVEL) {
								break;
							}

							
						}
					} catch (Exception ex) {
						//System.out.println(ex);
					}

				}
			}
		}

	}

	// ------------------------------------------------------------------------------------------------------

	private boolean filter(String url) throws IOException {
		String[] badWords = {
				"tel:",
				"mailto:",
				".pdf",
				".ogg",
				"dispenser.toolforge.org/",
				"https://legal.twitter.com/imprint",
				".mp3",
				".mp4",
				".css",
				"reddit",
				
		};
		
		if(url.length() > 100) {return false;}
		if (domainsVisited.contains(getDomain(url))) {return false;}

		if(url.contains("://")){
			for(String badWord : badWords) {
				if(url.contains(badWord)) {
					return false;
				}
			}			
			return true;
		}
		return false;
	}

	// ------------------------------------------------------------------------------------------------------

}