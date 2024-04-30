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

  public void DeleteUser(int id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String query = "Select passenger_id from login where id = ?";
    int passengerId = 0;
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        passengerId = rs.getInt(1);
      }

    } catch (SQLException e) {
      e.getStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }
    }
    tables[Table.LOGIN.val].Delete(id);
    tables[Table.PASSENGER.val].Delete(passengerId);
  }

  public int insertUser(String email, String password, String name, int age, Date dob, String address, long phoneNo,
      String passportNo) {

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
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
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
   *         format name,to,from,departure,arival,economy price,bussness class price
   */
  public Map<String, String>[] getAvailableFlights(String from, String to) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @SuppressWarnings("unchecked")
    Map<String, String>[] ret = new HashMap[10]; // Initialize the array to store flight details

    try {
      String query = "SELECT f.id p.name, f.to_location, f.from_location, f.departure_time, f.arriving_time, f.economy_price, f.bussiness_class_price "
          + "FROM Flight f, Plane p "
          + "WHERE f.plane_id = p.id AND f.from_location = ? AND f.to_location = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, from);
      pstmt.setString(2, to);

      rs = pstmt.executeQuery();

      int index = 0;
      while (rs.next()) {
        Map<String, String> flightDetails = new HashMap<>();
        flightDetails.put("FlightName", rs.getString("name"));
        flightDetails.put("FromLocation", rs.getString("from_location"));
        flightDetails.put("ToLocation", rs.getString("to_location"));
        Timestamp departureTime= rs.getTimestamp("departure_time");
        flightDetails.put("DepartureTime", departureTime.toString());
        flightDetails.put("ArrivalTime", rs.getTimestamp("arriving_time").toString());
        flightDetails.put("EconomyPrice", Integer.toString(rs.getInt("economy_price")));
        flightDetails.put("BusinessClassPrice", Integer.toString(rs.getInt("bussiness_class_price")));
        flightDetails.put("FlightId", Integer.toString(rs.getInt("id")));
        Date date = new Date(departureTime.getTime());
        flightDetails.put("Date", date.toString());

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
  public Map<String, Object>[] getMyTickets(int loginId) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @SuppressWarnings("unchecked")
    Map<String, Object>[] ret = new HashMap[10]; // Initialize the array to store ticket details

    try {

      String query = "SELECT p.name, b.economy_seats, b.bussiness_class_seats, f.to_location, f.from_location, f.departure_time, f.arriving_time "
          +"FROM Flight f, Login l, Booking b, Plane p " +
          "WHERE l.id = b.login_id AND b.flight_id = f.id AND f.plane_id = p.id AND l.id = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, loginId); // Set the user ID parameter

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
  public Map<String, String> getUserInfomation(int id) {
    Map<String, String> ret = new HashMap<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      // Establish connection
      String query = "SELECT l.email, l.password, p.name, p.date_of_birth, p.address, p.phone_no, p.passport_no " +
          "FROM Login l, Passenger p " +
          "WHERE l.passenger_id = p.id AND l.id = ?";
      statement = conn.prepareStatement(query);
      statement.setInt(1, id);

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        // Retrieve user information
        ret.put("email", resultSet.getString("email"));
        ret.put("password", resultSet.getString("password"));
        ret.put("name", resultSet.getString("name"));
        ret.put("date_of_birth",resultSet.getDate("date_of_birth").toString());
        ret.put("address", resultSet.getString("address"));
        ret.put("phone_no", resultSet.getString("phone_no"));
        ret.put("passport_no", resultSet.getString("passport_no"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (resultSet != null)
          resultSet.close();
        if (statement != null)
          statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return ret;
  }

  public int doPayment(int userId, String mode, int ammont) {
    DPayment data = new DPayment(mode, ammont, userId);
    tables[Table.PAYMENT.val].Insert(data);
    return data.id;
  
  }

  public int bookTicket(int userId, int economySeats, int bussinessClassSeats, int flightId,String Mode) {
    int ammont = calculateAmount(flightId, economySeats, bussinessClassSeats); 
    int paymentId = doPayment(userId, Mode, ammont); 

    DBooking booking = new DBooking(economySeats, bussinessClassSeats, flightId, userId, paymentId);
    tables[Table.BOOKING.val].Insert(booking);  
    return booking.id;
  }

  public int cancelTicket(int bookingId) {
    int ammont;
    PreparedStatement;
    String query = "select p.ammount from Payment p, Booking b where b.payment_id = p.id and b.id = ?";

    


  }

  public int calculateAmount(int flightId, int economySeats, int bussinessClassSeats) {
    int economyPrice,bussinessClassPrice;
    PreparedStatement pstmt=null;
    ResultSet rs = null;
    String query = "Select economy_price, bussiness_class_price from Flight where id = ?" ;
    try{
      pstmt = conn.prepareStatement(query);

      pstmt.setInt(1, flightId);
      rs = pstmt.executeQuery();

      if (rs.next()){
        economyPrice = rs.getInt(1);
        bussinessClassPrice=rs.getInt(2);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }finally{
      try{
        if (rs != null){
          rs.close(); 
        }
        if (pstmt != null){
          pstmt.close();
        }

      }catch(SQLException e){
        e.printStackTrace();
      }
      economyPrice = 0;
      bussinessClassPrice=0;
    }

    return economyPrice*economySeats + bussinessClassPrice*bussinessClassSeats;
  }

  public void UpdateUser(int loginId, String emailText, String pwdText, String name, int age, Date dateOfBirth,
      String address, long phoneNo, String passportNo) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String query = "Select passenger_id from login where id = ?";
    int passengerId = 0;
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, loginId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        passengerId = rs.getInt(1);
      }
      System.out.println(passengerId + "fasfseafgsgsaegsgsegsegwse:w");
    } catch (SQLException e) {
      e.getStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }
    }
    DPassenger pass = new DPassenger();
    DLogin login = new DLogin();
    
    login.id= loginId;
    login.email = emailText; 
    login.password = pwdText;
    login.passengerId = passengerId;
    pass.id = passengerId;
    pass.name = name;
    pass.age = age;
    pass.dateOfBirth = dateOfBirth;
    pass.address = address;
    pass.phoneNo = phoneNo;
    pass.passportNo = passportNo;

    tables[Table.LOGIN.val].Update(loginId,login);
    tables[Table.PASSENGER.val].Update(passengerId, pass);
  }
}
