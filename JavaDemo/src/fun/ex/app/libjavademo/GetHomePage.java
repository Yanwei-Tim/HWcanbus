package fun.ex.app.libjavademo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by APP03 on 2018/7/17.
 */

public class GetHomePage {
	public static final String NET = "http://www.beiwo.tv/"; 

	public void init() throws IOException {
		Document doc = Jsoup.connect(NET).get();
		log(doc.title());
		Elements newsHeadlines = doc.getElementsByClass("box");
		log("SSSSSSSSS " + newsHeadlines.size());
		for (Element headline : newsHeadlines) {
			// log("%s\n\t%s",
			// headline.attr("title"), headline.absUrl("href"));

			Elements elementsByClass = headline.getElementsByClass("img-list");
			log("\t " + elementsByClass.size());

			// for (Element element : elementsByClass) {
			//
			// log("\t\t " + element.attr("title"));
			//
			// }
			Element first = elementsByClass.first();
			Elements select = first.select("li");

			log("\t " + select.size());
			// log("\t " + select.first().getAllElements());

			for (Element element : select) {
				first = element.getAllElements().first().child(0);
//				log(">> " + first.toString());

				String webUrl = first.baseUri() + first.attr("href");
				String title = first.attr("title");

				String img = first.childNodeSize() + " ";
				String em = "";
 
				Elements allElements = first.getAllElements();
				for (Element e : allElements) {
//					log("@ " + e.toString());
					if (e.nodeName().equals("a")) {
						webUrl = e.baseUri() + e.attr("href");
						title = e.attr("title");
					} else if (e.nodeName().equals("img")) {
						img = e.attr("src");
					} else if (e.nodeName().equals("em")) {
						em = e.ownText();
					}
				}

				log("\t # " + webUrl + " \n # " + title + " \n #img " + img + "\n #em " + em);

			} 

//			break;

		}
		log("EEEEEEEE ");
	}

	private void log(String s, String... title) {
		String str = String.format(s, title);
		log(str);
	}

	private void log(String str) {
		System.out.println(str);
	}

}
