package BackEnd;

import java.sql.*;

class DLogin implements IData {
  int id;
  String email;
  String password;
  int passengerId;

  DLogin(String email, String password, int passengerId) {
    this.email = email;
    this.password = password;
    this.passengerId = passengerId;
  }
}

public class Login implements ITable {
  private static Connection conn;

  Login(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {
    Statement stmt = null;
    String query = "CREATE TABLE IF NOT EXISTS Login(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "email VARCHAR(40) NOT NULL," +
        "password VARCHAR(30) NOT NULL," +
        "passenger_id int NOT NULL," +
        "FOREIGN KEY (passenger_id)" +
        "   REFERENCES Passenger(id)" +
        "   ON DELETE CASCADE" +
        "   ON UPDATE CASCADE);";
    try {
      stmt = conn.createStatement();
      stmt.execute(query);
    } catch (SQLException e) {
      System.err.println("Error creating statement: " + e.getMessage());
      return;
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          System.err.println("Error creating statement: " + e.getMessage());
          return;
        }
      }
    }
  }

  @Override
  public int Insert(IData object) {

    PreparedStatement pstmt = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    DLogin data = (DLogin) object;

    String insertQuery = "INSERT INTO Login (email,password,passenger_id) VALUES (?, ?, ?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setString(1, data.email);
      pstmt.setString(2, data.password);
      pstmt.setInt(3, data.passengerId);

      // Executing the insert query
      int rowsInserted = pstmt.executeUpdate();

      if (rowsInserted > 0) {
        // If the insertion was successful, retrieving the ID of the newly inserted row
        rs = stmt.executeQuery();
        if (rs.next()) {
          data.id = rs.getInt(1);
          System.out.println("New row inserted with ID: " + data.id);
        }
      }

      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return data.id;
  }

  @Override
  public void Update(int id, IData data) {

  }

  @Override
  public void Delete(int id) {

  }
}
