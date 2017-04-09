import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebGather {
	
	private Document docFile;
	Elements playerName;
	
	public WebGather(String inLink, Map<String,String> session) throws IOException{
		/*
		 * Here I am connecting to each individual page
		 * and using a spoofing technique to avoid the dreaded
		 * captcha problem I've been facing. It is currently pretty slow however,
		 * only getting 1-2 results per second.
		 * 
		 * userAgent to spoof browser
		 * referrer to avoid bot detection
		 * timout to avoid hanging
		 * 
		 * Below I store cookies because I believe that will help but I'm not entirely sure
		 * 
		 * I found most of these methods through googling and API reference and didn't realize
		 * jsoup had the potential to do so much.
		 */

		
		Connection.Response response = Jsoup.connect(inLink)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
				.referrer("http://www.google.com")
				.method(Method.GET)
				.cookies(session)
				.timeout(10000)
				.execute();
		
		Document webDoc = response.parse();
		//playerName = webDoc.select("#PlayerDiv > div.pn-in.clfx > div > div.playerDetails.fl > ul > li:nth-child(1) > strong");
		playerName = webDoc.select("html");
		
	}
	
	public String returnPlayerName(){
		return playerName.text();
	}
}
