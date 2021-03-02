import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MagnitMain {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "20612")) {
            JDBC.newDB(connection);
            JDBC.addNumbers(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
