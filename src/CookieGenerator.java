import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

public class CookieGenerator {
	public Map<String,String> generateCookie() throws IOException{
		Connection.Response response = Jsoup.connect("http://www.itftennis.com")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
				.referrer("http://www.google.com")
				.method(Method.GET)
				.timeout(10000)
				.cookie("incap_ses_32_178373", "vuc/Zt77WTR4q32eE7FxADiH6lgAAAAAXJWhxEG8V166Ch0ciFmSbQ==")
				.followRedirects(false)
				.execute();
		
		Map<String,String> cookieMap = response.cookies();
		return cookieMap;
		
	}
}
