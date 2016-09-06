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
        slideShow.setAuthor("Tom Hanks");
        slideShow.setDate("11/24/2016");

        List<Slide> slides = new ArrayList<>();
        Slide slide1 = new Slide();
        slide1.setTitle("The First Slide to Rule Them All");
        slide1.setType("all");
        slides.add(slide1);

        Slide slide2 = new Slide();
        slide2.setTitle("Second the Best");
        slide2.setType("NONE!");
        List<String> items = new ArrayList<>();
        items.add("This is my item!");
        slide2.setItems(items);
        slides.add(slide2);

        slideShow.setSlides(slides);

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
