package xml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Класс описывает трансформацию 1.xml в 2.xml с использованием transformer.xsl
 */

public class XmlTransformClass {

    public static void xmlTTransformer() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("src/transformer.xsl"));
        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(new File("src/1.xml"));
        transformer.transform(xml, new StreamResult(new File("src/2.xml")));
    }
}
