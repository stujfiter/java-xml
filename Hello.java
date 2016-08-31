import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Hello {
	public static void main(String[] args) {
		String namespaceUri = "http://www.domoroboto.com";

		Person korey = new Person();
		korey.setName("Korey");
		korey.setAge(36);
		korey.setId(27);

		marshalPerson(korey, "person-with-ns.xml", namespaceUri);
		marshalPerson(korey, "person.xml");

		Person p = unmarshalPerson("person.xml");

		System.out.println("Hello, " + p.getName() + "!");
		
	}

	public static void marshalPerson(Person person, String fileName) {
		
		try {
			File f = new File(fileName);
			JAXBContext context = JAXBContext.newInstance(Person.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(person, f);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	
	}

	public static void marshalPerson(Person person, String fileName, String namespace) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element nameElement = doc.createElementNS(namespace, "p:name");
			nameElement.appendChild(doc.createTextNode(person.getName()));

			Element personElement = doc.createElementNS(namespace, "p:person");
			personElement.setAttribute("xmlns:p", namespace);
			personElement.setAttribute("p:id", Integer.toString(person.getId()));
			personElement.appendChild(nameElement);
			doc.appendChild(personElement);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			StreamResult result = new StreamResult(writer);
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static Person unmarshalPerson(String fileName) {
		Person p = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(Person.class);
	 		Unmarshaller u = jc.createUnmarshaller();
	 		p = (Person) u.unmarshal(new File(fileName ));

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return p;
	} 
}
