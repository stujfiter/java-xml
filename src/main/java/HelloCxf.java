import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.XPathProvider;

import javax.ws.rs.core.Response;
import java.util.*;

public class HelloCxf {

	public static void main(String[] args) {
        get();
        post();
	}

    private static void post() {
        SlideShowMessageBodyWriter writer = new SlideShowMessageBodyWriter();

        WebClient client = WebClient.create("http://requestb.in/1a8916t1", Collections.singletonList(writer));

        SlideShow slideShow = new SlideShow();
        slideShow.setTitle("Brian and Korey");
        Response response = client.post(slideShow);
        System.out.println(response);
    }

    private static void get() {
        XPathProvider provider = new XPathProvider();
        provider.setExpression("/slideshow");

        WebClient client = WebClient.create("http://httpbin.org/xml", Collections.singletonList(provider));
        SlideShow s = client.get(SlideShow.class);
        System.out.println(s.toString());
    }


}
