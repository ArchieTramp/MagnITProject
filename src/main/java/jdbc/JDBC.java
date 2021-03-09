package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для описания, создания и добавления N элементов в БД
 */


public class JDBC {

    public static void newDB(Connection connection, int capacity) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("-- Database: postgres\n "
                    + "DROP TABLE IF EXISTS test;"
                    + "\n"
                    + "CREATE TABLE test (\n"
                    + "id bigserial primary key,\n"
                    + "field integer NOT NULL);"
            );


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO test(field) VALUES (?)");
            for (int i = 1; i <= capacity; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}



