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

		Child audrey = new Child();
		audrey.setName("Audrey");
		audrey.setAge(1);

		Person korey = new Person();
		korey.setName("Korey");
		korey.setAge(36);
		korey.setId(27);
		korey.getChildren().add(audrey);

		marshalPerson(korey, "person-with-ns.xml", namespaceUri);
		marshalPerson(korey, "person-jaxb.xml");

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
			XmlProcessor xmlProcessor = new XmlProcessor();
			Document doc = xmlProcessor.newDocument();

			Element personElement = person.createElementForDocument(doc);
			doc.appendChild(personElement);

			xmlProcessor.transform(doc, fileName);	

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
