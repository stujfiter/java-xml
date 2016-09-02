import org.apache.cxf.jaxrs.client.WebClient;

public class HelloCxf {

	public static void main(String[] args) {
		WebClient client = WebClient.create("http://httpbin.org/xml");
		SlideShow s = client.get(SlideShow.class);
		System.out.println(s.getTitle());
	}
}
