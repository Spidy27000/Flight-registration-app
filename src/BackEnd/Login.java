package BackEnd;

import java.sql.*;

class DLogin extends IData {
  int id;
  String email;
  String password;
  int passengerId;

  DLogin(String email, String password, int passengerId) {
    this.id = 0;
    this.email = email;
    this.password = password;
    this.passengerId = passengerId;
  }
  public DLogin(){

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
  public void Insert(IData object) {

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
          object.id = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {

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
    }
  }

  @Override
  public void Update(int id, IData object) {

    PreparedStatement pstmt = null;
    DLogin data = (DLogin) object;

    String insertQuery = "UPDATE Login SET email = ?, password = ? WHERE id = ?";

    try {
      pstmt = conn.prepareStatement(insertQuery);

      pstmt.setString(1, data.email);
      pstmt.setString(2, data.password);
      pstmt.setInt(3, id);

      // Executing the insert query
      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void Delete(int id) {
    String query = "DELETE FROM Login WHERE id = ?";
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      int rowsAffected = stmt.executeUpdate();
      System.out.println("Rows affected: " + rowsAffected);

    } catch (SQLException e) { 
      e.printStackTrace();
    } finally {

    if (stmt != null){
      try{
        stmt.close();
        } catch (SQLException e) { 
        e.printStackTrace();
        }
      }
    }
  }
}
