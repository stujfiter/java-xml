import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Child {
	private String name;
	private int age;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public Element createElementForDocument(Document doc) {

		String namespace = "http://www.domoroboto.com/child";
		
		Element childElement = doc.createElementNS(namespace, "dc:child");
		
		Element nameElement = doc.createElementNS(namespace, "dc:name");
		nameElement.appendChild(doc.createTextNode(name));
		childElement.appendChild(nameElement);

		Element ageElement = doc.createElementNS(namespace, "dc:age");
		ageElement.appendChild(doc.createTextNode(Integer.toString(age)));
		childElement.appendChild(ageElement);

		return childElement;
	}
}
