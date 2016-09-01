import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@XmlRootElement(namespace = "http://www.domoroboto.com")
@XmlType(propOrder = { "name", "age"})
public class Person {

	public static final String NAMESPACE = "http://www.domoroboto.com";

	private String name;
	private int age;
	private int id;
	private List<Child> children = new ArrayList<Child>();

	
	public String getName() {
		return name;
	}

	@XmlElement(namespace = NAMESPACE)
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@XmlElement(namespace = NAMESPACE)
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute(namespace = NAMESPACE)
	public void setId(int id) {
		this.id = id;
	}

	public List<Child> getChildren() {
		return children;
	}	

	public Element createElementForDocument(Document doc) {
		String namespace = "http://www.domoroboto.com";
		
		Element personElement = doc.createElementNS(namespace, "domo:person");
		personElement.setAttribute("xmlns:domo", namespace);
		personElement.setAttribute("domo:id", Integer.toString(id));
		
		Element nameElement = doc.createElementNS(namespace, "domo:name");
		nameElement.appendChild(doc.createTextNode(name));
		personElement.appendChild(nameElement);

		Element ageElement = doc.createElementNS(namespace, "domo:age");
		ageElement.appendChild(doc.createTextNode(Integer.toString(age)));
		personElement.appendChild(ageElement);

		Element childrenElement = doc.createElementNS(namespace, "domo:children");
		for (Child c : children) {
			Element childElement = c.createElementForDocument(doc);
			childrenElement.appendChild(childElement);
		}
		personElement.appendChild(childrenElement);

		return personElement;
	}
}
