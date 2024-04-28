package BackEnd;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Db {
  private static Db inst;
  private Connection conn;
  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/FlightDb";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "password";
  private static ITable[] tables = new ITable[6];

  private Db() {
    try {
      conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
      System.out.println("Database is connected");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    tables[Table.PASSENGER.val] = new Passenger(conn);
    tables[Table.LOGIN.val] = new Login(conn);
    tables[Table.PLANE.val] = new Plane(conn);
    tables[Table.FLIGHT.val] = new Flight(conn);
    tables[Table.PAYMENT.val] = new Payment(conn);
    tables[Table.BOOKING.val] = new Booking(conn);
    CreateTables();

  }

  public static Db getInst() {
    if (inst == null) {
      inst = new Db();
    }
    return inst;
  }

  public void close() {
    if (inst != null) {
      try {
        conn.close();

      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        inst = null;
      }
    }
  }

  private static void CreateTables() {
    for (ITable tab : tables) {
      tab.Create();
    }
  }

  public int insertUser(String email, String password, String name, int age, Date dob, String address, long phoneNo,
      int passportNo) {

    IData passenger = new DPassenger(name, age, dob, address, phoneNo, passportNo);
    tables[Table.PASSENGER.val].Insert(passenger);

    IData login = new DLogin(email, password, passenger.id);
    tables[Table.LOGIN.val].Insert(login);
    return login.id;
  }

  public int doesUserExist(String email, String password) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int ret = 0;
    String query = "SELECT id FROM Login where email = ? AND password = ?";
    try {
      pstmt = conn.prepareStatement(query);

      pstmt.setString(1, email);
      pstmt.setString(2, password);
      rs = pstmt.executeQuery();
      if (rs.next()) {

        ret = rs.getInt(1);
      }

    } catch (SQLException e) {
      e.getStackTrace();
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }
    }
    return ret;
  }

  /**
   * @param from
   * @param to
   * @return
   *         format name,to,from,departure,arival,avalable economy seats, avalable
   *         bussiness class seats ,economy prize,bussness class price
   */
  public Map<String, Object>[] getAvailableFlights(String from, String to) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @SuppressWarnings("unchecked")
    Map<String, Object>[] ret = new HashMap[10]; // Initialize the array to store flight details

    try {
      String query = "SELECT p.name, f.to_location, f.from_location, f.departure_time, f.arriving_time, f.economy_seats, f.bussiness_class_seats, f.economy_prize, f.bussiness_class_prize "
          + "FROM Flight f, Plane p "
          + "WHERE f.plane_id = p.id AND f.from_location = ? AND f.to_location = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, from);
      pstmt.setString(2, to);

      rs = pstmt.executeQuery();

      int index = 0;
      while (rs.next()) {
        Map<String, Object> flightDetails = new HashMap<>();
        flightDetails.put("FlightName", rs.getString("name"));
        flightDetails.put("FromLocation", rs.getString("from_location"));
        flightDetails.put("ToLocation", rs.getString("to_location"));
        flightDetails.put("DepartureTime", rs.getTimestamp("departure_time"));
        flightDetails.put("ArrivalTime", rs.getTimestamp("arriving_time"));
        flightDetails.put("EconomySeats", rs.getInt("economy_seats"));
        flightDetails.put("BusinessClassSeats", rs.getInt("bussiness_class_seats"));
        flightDetails.put("EconomyPrice", rs.getInt("economy_prize"));
        flightDetails.put("BusinessClassPrice", rs.getInt("bussiness_class_prize"));

        ret[index++] = flightDetails;
        if (index >= 10)
          break;
      }
    } catch (SQLException e) {
      e.printStackTrace(); // Handle exceptions appropriately
    } finally {
      // Close the resources
      try {
        if (rs != null)
          rs.close();
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
      }
    }

    return ret;
  }

  // add data to flight table
  public void insertFlightData() {

  }

  // to see number of tickets
  public Map<String, Object>[] getMyTickets(int userId) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @SuppressWarnings("unchecked")
    Map<String, Object>[] ret = new HashMap[10]; // Initialize the array to store ticket details

    try {

      String query = "SELECT pl.name, b.economy_seats, b.bussiness_class_seats, f.to_location, f.from_location, f.departure_time, f.arriving_time "
          +
          "FROM Flight f, Passenger p, Booking b, Plane pl " +
          "WHERE p.id = b.passenger_id AND b.flight_id = f.id AND f.plane_id = pl.id AND p.id = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, userId); // Set the user ID parameter

      rs = pstmt.executeQuery();

      int index = 0;
      while (rs.next()) {
        Map<String, Object> ticketDetails = new HashMap<>();
        ticketDetails.put("FlightName", rs.getString("name"));
        ticketDetails.put("EconomySeats", rs.getInt("economy_seats"));
        ticketDetails.put("BusinessClassSeats", rs.getInt("bussiness_class_seats"));
        ticketDetails.put("ToLocation", rs.getString("to_location"));
        ticketDetails.put("FromLocation", rs.getString("from_location"));
        ticketDetails.put("DepartureTime", rs.getTimestamp("departure_time"));
        ticketDetails.put("ArrivalTime", rs.getTimestamp("arriving_time"));

        ret[index++] = ticketDetails;
        if (index >= 10) // We've filled the array, break out of the loop
          break;
      }
    } catch (SQLException e) {
      e.printStackTrace(); // Handle exceptions appropriately
    } finally {
      // Close the resources
      try {
        if (rs != null)
          rs.close();
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
      }
    }
    return ret;
  }

  // for updating info
  public Map<String, Object> getUserInfomation(int id) {
    Map<String, Object> ret = new HashMap<String, Object>();

    return ret;
  }

  public void doPayment(int userId, String mode, int ammont) {

  }

  public int bookTicket(int userId, int economySeats, int bussinessClassSeats, int flightId) {

    return 0;
  }

  public void cancelTicket(int bookingId) {

  }

  public int calculateAmount(int flightId, int economySeats, int bussinessClassSeats) {
    return 0;
  }
}
