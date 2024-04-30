package BackEnd;

import java.sql.*;

class DBooking extends IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int flightId;
  int loginId;
  int paymentId;

  DBooking(int economySeats, int bussinessClassSeats, int flightId, int loginId, int paymentId) {
    this.id = 0;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.flightId = flightId;
    this.loginId= loginId;
    this.paymentId = paymentId;
  }
}

public class Booking implements ITable {
  private static Connection conn;

  Booking(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {

    Statement stmt = null;
    String query = "CREATE TABLE IF NOT EXISTS Booking(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "economy_seats SMALLINT NOT NULL," +
        "bussiness_class_seats SMALLINT NOT NULL," +
        "flight_id int NOT NULL," +
        "FOREIGN KEY (flight_id)" +
        "   REFERENCES Flight(id)" +
        "   ON UPDATE CASCADE," +
        "login_id int NOT NULL," +
        "FOREIGN KEY (login_id)" +
        "   REFERENCES Login(id)" +
        "   ON UPDATE CASCADE," +
        "payment_id int NOT NULL," +
        "FOREIGN KEY (payment_id)" +
        "   REFERENCES Payment(id)" +
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
    DBooking data = (DBooking) object;

    String insertQuery = "INSERT INTO Booking (economy_seats,bussness_class_seats,flight_id,login_id,payment_id) VALUES (?, ?, ?,?,?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setInt(1, data.economySeats);
      pstmt.setInt(2, data.bussinessClassSeats);
      pstmt.setInt(3, data.flightId);
      pstmt.setInt(4, data.loginId);
      pstmt.setInt(5, data.paymentId);

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
    DBooking data = (DBooking) object;

    String updateQuery = "UPDATE Booking SET economy_seats = ?, bussness_class_seats = ?, flight_id = ?, login_id = ?, payment_id = ? WHERE id = ?";

    try {
      pstmt = conn.prepareStatement(updateQuery);
      pstmt.setInt(1, data.economySeats);
      pstmt.setInt(2, data.bussinessClassSeats);
      pstmt.setInt(3, data.flightId);
      pstmt.setInt(4, data.loginId);
      pstmt.setInt(5, data.paymentId);
      pstmt.setInt(6, id);

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
    String query = "DELETE FROM Booking WHERE id = ?";
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
