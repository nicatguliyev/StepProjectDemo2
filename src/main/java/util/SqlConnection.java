package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {
    public final static String url = "jdbc:postgresql://localhost:5432/StepProjectDemo2";
    public final static String user = "postgres";
    public final static String pass = "12345";

    public static Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
