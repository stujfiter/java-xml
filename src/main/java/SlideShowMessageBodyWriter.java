import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class SlideShowMessageBodyWriter implements MessageBodyWriter<SlideShow> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == SlideShow.class;
    }

    @Override
    public long getSize(SlideShow slideShow, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(SlideShow slideShow, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {

        try {
            XmlProcessor processor = new XmlProcessor();
            Document doc = processor.newDocument();

            SimpleNamespaceContext context = new SimpleNamespaceContext();

            Element slideShowElement = slideShow.createElementForDocument(doc, context);

            for (SimpleNamespaceContext.Namespace ns : context.getNamespaces()) {
                slideShowElement.setAttribute("xmlns:" + ns.prefix, ns.uri);
            }

            doc.appendChild(slideShowElement);
            processor.transform(doc, entityStream);

        } catch (TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
