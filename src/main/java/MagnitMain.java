import jdbc.JDBC;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;
import xml.XML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MagnitMain {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "20612")) {

            System.out.println("Пиши значение");
            Scanner scanner = new Scanner(System.in);
            int capacity = scanner.nextInt();
            JDBC.newDB(connection);
            JDBC.addNumbers(connection,capacity);
            XML.xmlCreator(connection,capacity);
            XML.xmlTTransformer();
            XML.xmlParser();


        } catch (SQLException | TransformerException | ParserConfigurationException | IOException | SAXException | JDOMException throwables) {
            throwables.printStackTrace();
        }
    }
}
