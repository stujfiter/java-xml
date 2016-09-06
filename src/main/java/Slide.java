import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Slide {

    private String type;
    private String title;
    private List<String> items;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name="item")
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
            this.items = items;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        List<String> itemList;

        if (items == null) {
            itemList = new ArrayList<>();
        }
        else {
            itemList = items;
        }

        result.append("Title: " + title + " | Type: " + type + " | Item Count:" + itemList.size());

        for (String item: itemList) {
            result.append("\n\t\tItem - " + item);
        }
        return result.toString();
    }
}
