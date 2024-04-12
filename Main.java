import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Establishing a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Database is connected");

        }  
        catch (SQLException e) {
            // Handling any SQL exceptions that may occur
            e.printStackTrace();
        } finally {
            // Closing resources in the finally block
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
