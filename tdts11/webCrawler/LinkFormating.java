package tdts11.webCrawler;

public class LinkFormating {
	public static String getDomain(String URL) { //Used to not have to rewrite this one method in both classes.
		String Domain = "";

		int start = URL.indexOf('/') + 2;
		if (URL.contains("www.")) {
			start += 4;
		}

		for (int i = start; i < URL.length(); i++) {
			if (URL.charAt(i) != '/') {
				Domain += URL.charAt(i);

			} else {
				break;
			}
		}
		return Domain;
	}
}
