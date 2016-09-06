import com.sun.deploy.xml.XMLNode;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Collection;
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
}
