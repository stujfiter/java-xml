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

	public Element createElementForDocument(Document doc, SimpleNamespaceContext context) {

		SimpleNamespaceContext.Namespace namespace = context.addNamespace("http://www.domoroboto.com/child", "dc");
		
		String uri = namespace.namespaceURI;
		String prefix = namespace.prefix;
		
		Element childElement = doc.createElementNS(uri, prefix + ":child");
		
		Element nameElement = doc.createElementNS(uri, prefix + ":name");
		nameElement.appendChild(doc.createTextNode(name));
		childElement.appendChild(nameElement);

		Element ageElement = doc.createElementNS(uri, prefix + ":age");
		ageElement.appendChild(doc.createTextNode(Integer.toString(age)));
		childElement.appendChild(ageElement);

		return childElement;
	}
}
