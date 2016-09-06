import java.io.IOException;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class HelloXPath {

	public static void main(String[] args) {

        Person korey = new Person();
        korey.setId(1);
        korey.setName("Korey");
        korey.setAge(37);

        Child audrey = new Child();
        audrey.setName("Audrey");
        audrey.setAge(1);
        korey.getChildren().add(audrey);

        String fileName = "person-xpath.xml";
        new HelloXPath().marshalPersonXPath(korey, fileName);

        Document doc = createDocument(fileName);
		if (doc != null) {

			XPath xpath = XPathFactory.newInstance().newXPath();
			String name = "";
			try {
				name = xpath.compile("/person/name").evaluate(doc);			
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}

			System.out.println("Hello, " + name + "!");
		} else {
			System.out.println("Error Parsing Document");
		}
	}

	public static Document createDocument(String filePath) {
		Document doc = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = builder.parse(new FileInputStream(filePath));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

        return doc;
	}

    public void marshalPersonXPath(Person person, String fileName) {
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
}
