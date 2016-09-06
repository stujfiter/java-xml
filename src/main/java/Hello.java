import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Hello {
	public static void main(String[] args) {
		Child audrey = new Child();
		audrey.setName("Audrey");
		audrey.setAge(1);

		Person korey = new Person();
		korey.setName("Korey");
		korey.setAge(36);
		korey.setId(27);
		korey.getChildren().add(audrey);

		marshalPersonXPath(korey, "person-with-ns.xml");
		marshalPerson(korey, "person-jaxb.xml");

		Person p = unmarshalPerson("person-jaxb.xml");

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

	public static void marshalPersonXPath(Person person, String fileName) {
		try {
			XmlProcessor xmlProcessor = new XmlProcessor();
			Document doc = xmlProcessor.newDocument();

			SimpleNamespaceContext context = new SimpleNamespaceContext();

			Element personElement = person.createElementForDocument(doc, context);
			
			for (SimpleNamespaceContext.Namespace ns : context.getNamespaces()) {
				personElement.setAttribute("xmlns:" + ns.prefix, ns.uri);
			}

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
