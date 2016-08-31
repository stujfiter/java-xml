import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
}
