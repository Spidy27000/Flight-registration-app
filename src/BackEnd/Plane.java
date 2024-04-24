package BackEnd;

import java.sql.*;

class DPlane implements IData {
  int id;
  String name;
  int economySeats;
  int bussinessClassSeats;

  DPlane(String name, int economySeats, int bussinessClassSeats) {
    this.name = name;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
  }
}

public class Plane implements ITable {

  private static Connection conn;

  Plane(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {
    Statement stmt = null;
    String query = "CREATE TABLE IF NOT EXISTS Plane(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "name VARCHAR(30) NOT NULL," +
        "economy_seats SMALLINT NOT NULL," +
        "bussiness_class_seats SMALLINT NOT NULL);";
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
    DPlane data = (DPlane) object;

    String insertQuery = "INSERT INTO Plane (name,economy_seats,bussiness_class_seats) VALUES (?, ?, ?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setString(1, data.name);
      pstmt.setInt(2, data.economySeats);
      pstmt.setInt(3, data.bussinessClassSeats);

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
