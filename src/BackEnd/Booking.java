package BackEnd;
import java.sql.*;

class DBooking implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int flightId;
  int passengerId;
  int paymentId;

  DBooking(int economySeats, int bussinessClassSeats, int flightId, int passengerId, int paymentId) {
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.flightId = flightId;
    this.passengerId = passengerId;
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
        "   ON DELETE CASCADE" +
        "   ON UPDATE CASCADE," +
        "passenger_id int NOT NULL," +
        "FOREIGN KEY (passenger_id)" +
        "   REFERENCES Passenger(id)" +
        "   ON DELETE CASCADE" +
        "   ON UPDATE CASCADE," +
        "payment_id int NOT NULL," +
        "FOREIGN KEY (payment_id)" +
        "   REFERENCES Payment(id)" +
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
    DBooking data = (DBooking) object;

    String insertQuery = "INSERT INTO Booking (economy_seats,bussness_class_seats,flight_id,passenger_id,payment_id) VALUES (?, ?, ?,?,?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setInt(1, data.economySeats);
      pstmt.setInt(2, data.bussinessClassSeats);
      pstmt.setInt(3, data.flightId);
      pstmt.setInt(4, data.passengerId);
      pstmt.setInt(5, data.paymentId);

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
