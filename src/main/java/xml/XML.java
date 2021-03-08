package xml;

import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import pojo.POJO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XML {
/*
классы разделить на отдельные для удобства но по отдельным папкам!
 */
    public static void xmlCreator(Connection connection, int capacity) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {

            dbf.setNamespaceAware(true);
            documentBuilder = dbf.newDocumentBuilder();

            Document doc = documentBuilder.newDocument();

            Element root = doc.createElementNS("", "entries");

            doc.appendChild(root);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT field FROM test WHERE id = ?");
            for (int i = 1; i <= capacity; i++) {
                preparedStatement.setInt(1, i);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int field = resultSet.getInt(1);
                    root.appendChild(getEntry(doc, field));
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File("src/1.xml"));

            transformer.transform(source, file);


        } catch (ParserConfigurationException | TransformerException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Node getEntry(Document doc, int resultSet) {
        Element entry = doc.createElement("entry");
        entry.appendChild(getEntryElements(doc, resultSet));
        return entry;
    }

    private static Node getEntryElements(Document doc, int resultSet) {
        Element node = doc.createElement("field");
        node.appendChild(doc.createTextNode(String.valueOf(resultSet)));
        return node;
    }


    public static void xmlTTransformer() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("src/transformer.xsl"));
        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(new File("src/1.xml"));
        transformer.transform(xml, new StreamResult(new File("src/2.xml")));
    }


    public static void xmlParser() throws ParserConfigurationException, IOException, SAXException, JDOMException {
        String fileName = "src/2.xml";
        org.jdom2.Document jDoc = createJDOMusingSAXParser(fileName);
        org.jdom2.Element root = jDoc.getRootElement();
        List<org.jdom2.Element> elements = root.getChildren("entries");
        List<POJO> entrieses = new ArrayList<>();
        for (org.jdom2.Element entriesList : elements) {
            POJO pojo = new POJO();
            pojo.setId(Integer.parseInt(entriesList.getAttributeValue("id")));
            pojo.setField(Integer.parseInt(entriesList.getChildText("field")));
            entrieses.add(pojo);
        }
        for (POJO pojo : entrieses) {
            System.out.println(pojo.toString());
        }

    }

    private static org.jdom2.Document createJDOMusingDOMParser(String fileName) throws ParserConfigurationException, IOException, SAXException {
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
     DocumentBuilder documentBuilder;
     documentBuilder = dbf.newDocumentBuilder();
     Document doc = documentBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(doc);
    }

    private static org.jdom2.Document createJDOMusingSAXParser(String fileName)
            throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(new File(fileName));
    }
}
