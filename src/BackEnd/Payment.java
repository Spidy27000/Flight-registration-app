package BackEnd;

import java.sql.*;

class DPayment extends IData {
  int id;
  int ammount;
  String mode;
  int loginId;

  DPayment(String mode, int ammount, int loginId) {
    this.id = 0;
    this.mode = mode;
    this.ammount = ammount;
    this.loginId = loginId;
  }
}

public class Payment implements ITable {

  private static Connection conn;

  Payment(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {
    Statement stmt = null;
    String query = "CREATE TABLE IF NOT EXISTS Payment(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "ammount int NOT NULL," +
        "mode VARCHAR(30) NOT NULL," +
        "login_id int NOT NULL," +
        "FOREIGN KEY (login_id)" +
        "   REFERENCES Login(id)" +
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
  public void Insert(IData object) {

    PreparedStatement pstmt = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    DPayment data = (DPayment) object;

    String insertQuery = "INSERT INTO Payment (ammount,mode,login_id) VALUES (?, ?, ?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setInt(1, data.ammount);
      pstmt.setString(2, data.mode);
      pstmt.setInt(3, data.loginId);

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
    DPayment data = (DPayment) object;

    String insertQuery = "UPDATE Payment SET ammount = ?, mode = ?, login_id = ? WHERE id = ?";

    try {
      pstmt = conn.prepareStatement(insertQuery);

      pstmt.setInt(1, data.ammount);
      pstmt.setString(2, data.mode);
      pstmt.setInt(3, data.loginId);
      pstmt.setInt(4, id);

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
    String query = "DELETE FROM Payment WHERE id = ?";
    PreparedStatement stmt = null;

    try  {
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
