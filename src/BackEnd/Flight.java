package BackEnd;

import java.sql.*;

class DFlight implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  String toLocation;
  String fromLocation;
  int departureTime;
  int arrivingTime;
  int economyPrice;
  int bussinessClassPrice;
  int planeId;

  DFlight(int economySeats, int bussinessClassSeats, int planeId, String toLocation, String fromLocation,
      int departureTime, int arrivingTime, int bussinessClassPrice, int economyPrice) {
    this.economyPrice = economyPrice;
    this.bussinessClassPrice = bussinessClassPrice;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.planeId = planeId;
    this.toLocation = toLocation;
    this.fromLocation = fromLocation;
    this.departureTime = departureTime;
    this.arrivingTime = arrivingTime;
  }
}

public class Flight implements ITable {

  private static Connection conn;

  Flight(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {
    Statement stmt = null;
    String query = "CREATE TABLE IF NOT EXISTS Flight(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "economy_seats SMALLINT NOT NULL," +
        "bussiness_class_seats SMALLINT NOT NULL," +
        "to_location VARCHAR(30) NOT NULL," +
        "from_location VARCHAR(30) NOT NULL," +
        "economy_prize INT NOT NULL," +
        "bussiness_class_prize INT NOT NULL," +
        "departure_time DATETIME NOT NULL," +
        "arriving_time DATETIME NOT NULL," +
        "plane_id int NOT NULL," +
        "FOREIGN KEY (plane_id)" +
        "   REFERENCES Plane(id)" +
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
    DFlight data = (DFlight) object;

    String insertQuery = "INSERT INTO Flight (economy_seats,bussness_class_seats,to_location,from_loaction,economy_prize,bussness_class_prize,departure_time,arriving_time,plane_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {
      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);
      pstmt.setInt(1, data.economySeats);
      pstmt.setInt(2, data.bussinessClassSeats);
      pstmt.setString(3, data.toLocation);
      pstmt.setString(4, data.fromLocation);
      pstmt.setInt(5, data.economyPrice);
      pstmt.setInt(6, data.bussinessClassPrice);
      pstmt.setTimestamp(7, Timestamp.parse(data.departureTime));
      pstmt.setTimestamp(8, Timestamp.parse(data.arrivingTime));
      pstmt.setInt(9, data.planeId);

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
