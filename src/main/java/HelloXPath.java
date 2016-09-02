import java.io.IOException;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException; 
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class HelloXPath {

	public static void main(String[] args) {

		Document doc = createDocument("person.xml");
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
}
