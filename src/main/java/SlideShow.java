import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class SlideShow {
	
	private String title;
	private String date;
	private String author;
    private List<Slide> slides;

    @XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    @XmlAttribute
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    @XmlAttribute
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Title: " + title + " | Author: " + author + " | Date: " + date + " | Slide Count: " + slides.size());
        for (Slide slide: slides) {
            result.append("\n\tSlide - " + slide);
        }
        return result.toString();
    }

    @XmlElement(name="slide")
    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public Element createElementForDocument(Document doc, SimpleNamespaceContext context) {
        SimpleNamespaceContext.Namespace namespace = context.addNamespace("http://www.domoroboto.com", "domo");
        String uri = namespace.uri;
        String prefix = namespace.prefix;

        Element element = doc.createElementNS(uri, prefix + ":slideshow");
        element.setAttribute("xmlns:" + prefix, uri);
        element.setAttribute(prefix + ":title", title);
        element.setAttribute(prefix + ":date", date);
        element.setAttribute(prefix + ":author", author);

        for (Slide slide: slides) {
            element.appendChild(slide.createElementForDocument(doc, context));
        }

        return element;
    }
}
