package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс описывающий создание 1.xml
 */

public class XmlCreateClass {
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
}
