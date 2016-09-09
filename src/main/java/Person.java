import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.domoroboto.com")
@XmlType(propOrder = { "name", "age", "children"})

public class Person {

	public static final String NAMESPACE = "http://www.domoroboto.com";

    @XmlAttribute(namespace = NAMESPACE)
    private int id;

    @XmlElement(namespace = NAMESPACE)
    protected String name;

    @XmlElement(namespace = NAMESPACE)
    private int age;

    @XmlElementWrapper(name="children", namespace = NAMESPACE)
    @XmlElement(name="child", namespace = NAMESPACE)
    private List<Child> children = new ArrayList<>();



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<Child> getChildren() {
		return children;
	}

	public Element createElementForDocument(Document doc, SimpleNamespaceContext context) {

		SimpleNamespaceContext.Namespace namespace = context.addNamespace("http://www.domoroboto.com", "domo");
		String uri = namespace.uri;
		String prefix = namespace.prefix;

		Element personElement = doc.createElementNS(uri, prefix + ":person");
		personElement.setAttribute("xmlns:" + prefix, uri);
		personElement.setAttribute(prefix + ":id", Integer.toString(id));

		Element nameElement = doc.createElementNS(uri, prefix + ":name");
		nameElement.appendChild(doc.createTextNode(name));
		personElement.appendChild(nameElement);

		Element ageElement = doc.createElementNS(uri, prefix + ":age");
		ageElement.appendChild(doc.createTextNode(Integer.toString(age)));
		personElement.appendChild(ageElement);

		Element childrenElement = doc.createElementNS(uri, prefix + ":children");
		for (Child c : children) {
			Element childElement = c.createElementForDocument(doc, context);
			childrenElement.appendChild(childElement);
		}
		personElement.appendChild(childrenElement);

		return personElement;
	}
}
