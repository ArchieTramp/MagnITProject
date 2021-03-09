import jdbc.JDBC;
import xml.XmlCreateClass;
import xml.XmlParseClass;
import xml.XmlTransformClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

/**
 * @author Artur Gilyazov
 */

public class MagnitMain {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "20612")) {

            System.out.println("Сколько элементов добавить в базу?");
            Scanner scanner = new Scanner(System.in);
            int capacity = scanner.nextInt();

            JDBC.newDB(connection, capacity);

            XmlCreateClass.xmlCreator(connection, capacity);

            XmlTransformClass.xmlTTransformer();

            XmlParseClass.xmlParser();


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
