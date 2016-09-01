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

	String name;
	int age;
	int id;
	
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

	public Element createElementForDocument(Document doc) {
		String namespace = "http://www.domoroboto.com";
		
		Element nameElement = doc.createElementNS(namespace, "domo:name");
		nameElement.appendChild(doc.createTextNode(name));

		Element ageElement = doc.createElementNS(namespace, "domo:age");
		ageElement.appendChild(doc.createTextNode(Integer.toString(age)));

		Element personElement = doc.createElementNS(namespace, "domo:person");
		personElement.setAttribute("xmlns:domo", namespace);
		personElement.setAttribute("domo:id", Integer.toString(id));
		personElement.appendChild(nameElement);
		personElement.appendChild(ageElement);

		return personElement;
	}
}
