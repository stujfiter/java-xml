import java.io.File;
import java.math.BigInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.domoroboto.Children;
import com.domoroboto.PersonXjc;
import com.domoroboto.Child;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Hello {
	public static void main(String[] args) {
		Child audrey = new Child();
		audrey.setName("Audrey");
		audrey.setAge(BigInteger.valueOf(1));

		Child lucy = new Child();
		lucy.setName("Lucy");
		lucy.setAge(BigInteger.valueOf(3));

		PersonXjc korey = new PersonXjc();
		korey.setName("Korey");
		korey.setAge(BigInteger.valueOf(36));
		korey.setId(BigInteger.valueOf(27));

        Children children = new Children();
        korey.setChildren(children);
		korey.getChildren().getChild().add(audrey);
		korey.getChildren().getChild().add(lucy);

//		marshalPersonXPath(korey, "person-with-ns.xml");
		marshalPerson(korey, "person-jaxb.xml");

//		Person p = unmarshalPerson("person-jaxb.xml");

//		System.out.println("Hello, " + p.getName() + "!");
		
	}

	public static void marshalPerson(PersonXjc person, String fileName) {
		
		try {
			File f = new File(fileName);
			JAXBContext context = JAXBContext.newInstance(PersonXjc.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(person, f);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	
	}

//	public static void marshalPersonXPath(Person person, String fileName) {
//		try {
//			XmlProcessor xmlProcessor = new XmlProcessor();
//			Document doc = xmlProcessor.newDocument();
//
//			SimpleNamespaceContext context = new SimpleNamespaceContext();
//
//			Element personElement = person.createElementForDocument(doc, context);
//
//			for (SimpleNamespaceContext.Namespace ns : context.getNamespaces()) {
//				personElement.setAttribute("xmlns:" + ns.prefix, ns.uri);
//			}
//
//			doc.appendChild(personElement);
//
//			xmlProcessor.transform(doc, fileName);
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
