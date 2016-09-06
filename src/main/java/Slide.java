import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

        result.append("Title: ").append(title).append(" | Type: ").append(type).append(" | Item Count:").append(itemList.size());

        for (String item: itemList) {
            result.append("\n\t\tItem - ").append(item);
        }
        return result.toString();
    }

    public Element createElementForDocument(Document doc, SimpleNamespaceContext context) {

        SimpleNamespaceContext.Namespace namespace = context.addNamespace("http://www.domoroboto.com", "korey");
        String uri = namespace.uri;
        String prefix = namespace.prefix;

        Element element = doc.createElementNS(uri, prefix + ":slide");
        element.setAttribute("type", type);

        Element titleElement = doc.createElementNS(uri, prefix + ":title");
        titleElement.appendChild(doc.createTextNode(title));
        element.appendChild(titleElement);

        if (items != null) {
            for (String item: items) {
                Element itemElement = doc.createElementNS(uri, prefix + ":item");
                itemElement.appendChild(doc.createTextNode(item));
                element.appendChild(itemElement);
            }
        }

        return element;
    }
}
