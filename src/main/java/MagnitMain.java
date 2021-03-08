import jdbc.JDBC;
import xml.XML;

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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
