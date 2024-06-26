package BackEnd;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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

    insertFlightData();

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
   *         format name,to,from,departure,arival,economy prize,bussness class
   *         price
   */
  public Vector<Map<String, String>> getAvailableFlights(String from, String to) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector<Map<String, String>> ret = new Vector<>(); // Initialize the array to store flight details

    try {
      String query = "SELECT f.id ,p.name, f.to_location, f.from_location, f.departure_time, f.arriving_time, f.economy_price, f.bussiness_class_price "
          + "FROM Flight f, Plane p "
          + "WHERE f.plane_id = p.id AND f.from_location = ? AND f.to_location = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, from);
      pstmt.setString(2, to);

      rs = pstmt.executeQuery();

      int index = 0;
      System.out.println(from + " " + to);
      while (rs.next()) {
        Map<String, String> flightDetails = new HashMap<>();
        flightDetails.put("FlightName", rs.getString("name"));
        flightDetails.put("FromLocation", rs.getString("from_location"));
        flightDetails.put("ToLocation", rs.getString("to_location"));
        Timestamp departureTime = rs.getTimestamp("departure_time");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        flightDetails.put("DepartureTime", sdf.format(departureTime));
        flightDetails.put("ArrivalTime", sdf.format(rs.getTimestamp("arriving_time")));
        flightDetails.put("EconomyPrice", Integer.toString(rs.getInt("economy_price")));
        flightDetails.put("BusinessClassPrice", Integer.toString(rs.getInt("bussiness_class_price")));
        flightDetails.put("id", Integer.toString(rs.getInt("id")));
        Date date = new Date(departureTime.getTime());
        flightDetails.put("Date", date.toString());

        ret.add(flightDetails);
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
    // Define 15 planes
    IData plane1 = new DPlane("SkyLink 345", 150, 40);
    IData plane2 = new DPlane("AirSwift 721", 120, 30);
    IData plane3 = new DPlane("IndiWave 119", 140, 35);
    IData plane4 = new DPlane("SpiceWings 512", 130, 35);
    IData plane5 = new DPlane("GoFly 633", 160, 45);
    IData plane6 = new DPlane("VistaJet 815", 110, 25);
    IData plane7 = new DPlane("BlueSky 420", 170, 50);
    IData plane8 = new DPlane("JetExpress 901", 125, 30);
    IData plane9 = new DPlane("FlyIndia 707", 145, 40);
    IData plane10 = new DPlane("AeroSwift 312", 155, 45);
    IData plane11 = new DPlane("FlyMax 505", 135, 35);
    IData plane12 = new DPlane("WingStar 209", 165, 50);
    IData plane13 = new DPlane("SkyRise 123", 120, 25);
    IData plane14 = new DPlane("AirFleet 777", 140, 40);

    tables[Table.PLANE.val].Insert(plane1);

    tables[Table.PLANE.val].Insert(plane2);
    tables[Table.PLANE.val].Insert(plane3);
    tables[Table.PLANE.val].Insert(plane4);
    tables[Table.PLANE.val].Insert(plane5);
    tables[Table.PLANE.val].Insert(plane6);
    tables[Table.PLANE.val].Insert(plane7);
    tables[Table.PLANE.val].Insert(plane8);
    tables[Table.PLANE.val].Insert(plane9);
    tables[Table.PLANE.val].Insert(plane10);
    tables[Table.PLANE.val].Insert(plane11);
    tables[Table.PLANE.val].Insert(plane12);
    tables[Table.PLANE.val].Insert(plane13);
    tables[Table.PLANE.val].Insert(plane14);

    // Define 40 flights around India
    // You can adjust the timestamps and locations as needed

    // Flight 1: Delhi to Mumbai
    IData flight1 = new DFlight(150, 40, plane1.id, "Delhi", "Mumbai",
        Timestamp.valueOf("2024-05-15 09:30:00"),
        Timestamp.valueOf("2024-05-15 11:45:00"),
        10000, 4500);
    tables[Table.FLIGHT.val].Insert(flight1);

    // Flight 2: Mumbai to Delhi
    IData flight2 = new DFlight(150, 40, plane1.id, "Mumbai", "Delhi",
        Timestamp.valueOf("2024-05-16 13:30:00"),
        Timestamp.valueOf("2024-05-16 15:45:00"),
        10000, 4500);
    tables[Table.FLIGHT.val].Insert(flight2);

    // Flight 3: Chennai to Hyderabad
    IData flight3 = new DFlight(120, 30, plane2.id, "Chennai", "Hyderabad",
        Timestamp.valueOf("2024-07-10 08:45:00"),
        Timestamp.valueOf("2024-07-10 10:30:00"),
        11000, 4200);
    tables[Table.FLIGHT.val].Insert(flight3);

    // Flight 4: Hyderabad to Chennai
    IData flight4 = new DFlight(120, 30, plane2.id, "Hyderabad", "Chennai",
        Timestamp.valueOf("2024-07-11 12:30:00"),
        Timestamp.valueOf("2024-07-11 14:15:00"),
        11000, 4200);
    tables[Table.FLIGHT.val].Insert(flight4);

    // Define more flights similarly for other locations and planes
    // You can add flights connecting various cities in India using different planes

    // Add more flights as needed...
    // Flight 5: Bangalore to Kolkata
    IData flight5 = new DFlight(140, 35, plane3.id, "Bangalore", "Kolkata",
        Timestamp.valueOf("2024-06-02 11:00:00"),
        Timestamp.valueOf("2024-06-02 13:15:00"),
        9500, 3800);
    tables[Table.FLIGHT.val].Insert(flight5);

    // Flight 6: Kolkata to Bangalore
    IData flight6 = new DFlight(140, 35, plane3.id, "Kolkata", "Bangalore",
        Timestamp.valueOf("2024-06-03 15:00:00"),
        Timestamp.valueOf("2024-06-03 17:15:00"),
        9500, 3800);
    tables[Table.FLIGHT.val].Insert(flight6);

    // Flight 7: Delhi to Mumbai
    IData flight7 = new DFlight(130, 35, plane4.id, "Delhi", "Mumbai",
        Timestamp.valueOf("2024-08-21 12:15:00"),
        Timestamp.valueOf("2024-08-21 14:30:00"),
        9800, 3600);
    tables[Table.FLIGHT.val].Insert(flight7);

    // Flight 8: Mumbai to Delhi
    IData flight8 = new DFlight(130, 35, plane4.id, "Mumbai", "Delhi",
        Timestamp.valueOf("2024-08-22 16:00:00"),
        Timestamp.valueOf("2024-08-22 18:15:00"),
        9800, 3600);
    tables[Table.FLIGHT.val].Insert(flight8);

    // Flight 9: Hyderabad to Bangalore
    IData flight9 = new DFlight(160, 45, plane5.id, "Hyderabad", "Bangalore",
        Timestamp.valueOf("2024-09-05 10:00:00"),
        Timestamp.valueOf("2024-09-05 12:30:00"),
        10500, 4000);
    tables[Table.FLIGHT.val].Insert(flight9);

    // Flight 10: Bangalore to Hyderabad
    IData flight10 = new DFlight(160, 45, plane5.id, "Bangalore", "Hyderabad",
        Timestamp.valueOf("2024-09-06 14:00:00"),
        Timestamp.valueOf("2024-09-06 16:30:00"),
        10500, 4000);
    tables[Table.FLIGHT.val].Insert(flight10);

    // Flight 11: Mumbai to Kolkata
    IData flight11 = new DFlight(110, 25, plane6.id, "Mumbai", "Kolkata",
        Timestamp.valueOf("2024-10-15 09:30:00"),
        Timestamp.valueOf("2024-10-15 11:45:00"),
        9200, 3900);
    tables[Table.FLIGHT.val].Insert(flight11);

    // Flight 12: Kolkata to Mumbai
    IData flight12 = new DFlight(110, 25, plane6.id, "Kolkata", "Mumbai",
        Timestamp.valueOf("2024-10-16 13:30:00"),
        Timestamp.valueOf("2024-10-16 15:45:00"),
        9200, 3900);
    tables[Table.FLIGHT.val].Insert(flight12);

    // Flight 13: Chennai to Delhi
    IData flight13 = new DFlight(125, 30, plane7.id, "Chennai", "Delhi",
        Timestamp.valueOf("2024-11-20 10:45:00"),
        Timestamp.valueOf("2024-11-20 13:00:00"),
        10300, 3800);
    tables[Table.FLIGHT.val].Insert(flight13);

    // Flight 14: Delhi to Chennai
    IData flight14 = new DFlight(125, 30, plane7.id, "Delhi", "Chennai",
        Timestamp.valueOf("2024-11-21 14:30:00"),
        Timestamp.valueOf("2024-11-21 16:45:00"),
        10300, 3800);
    tables[Table.FLIGHT.val].Insert(flight14);

    // Flight 15: Hyderabad to Kolkata
    IData flight15 = new DFlight(170, 50, plane8.id, "Hyderabad", "Kolkata",
        Timestamp.valueOf("2024-12-10 08:00:00"),
        Timestamp.valueOf("2024-12-10 10:45:00"),
        11500, 4200);
    tables[Table.FLIGHT.val].Insert(flight15);

    // Flight 16: Kolkata to Hyderabad
    IData flight16 = new DFlight(170, 50, plane8.id, "Kolkata", "Hyderabad",
        Timestamp.valueOf("2024-12-11 12:30:00"),
        Timestamp.valueOf("2024-12-11 15:15:00"),
        11500, 4200);
    tables[Table.FLIGHT.val].Insert(flight16);

    // Flight 17: Bangalore to Mumbai
    IData flight17 = new DFlight(145, 40, plane9.id, "Bangalore", "Mumbai",
        Timestamp.valueOf("2025-01-05 09:00:00"),
        Timestamp.valueOf("2025-01-05 11:15:00"),
        9900, 3700);
    tables[Table.FLIGHT.val].Insert(flight17);

    // Flight 18: Mumbai to Bangalore
    IData flight18 = new DFlight(145, 40, plane9.id, "Mumbai", "Bangalore",
        Timestamp.valueOf("2025-01-06 13:30:00"),
        Timestamp.valueOf("2025-01-06 15:45:00"),
        9900, 3700);
    tables[Table.FLIGHT.val].Insert(flight18);

    // Flight 19: Kolkata to Delhi
    IData flight19 = new DFlight(155, 45, plane10.id, "Kolkata", "Delhi",
        Timestamp.valueOf("2025-02-20 10:45:00"),
        Timestamp.valueOf("2025-02-20 13:00:00"),
        10400, 3900);
    tables[Table.FLIGHT.val].Insert(flight19);

    // Flight 20: Delhi to Kolkata
    IData flight20 = new DFlight(155, 45, plane10.id, "Delhi", "Kolkata",
        Timestamp.valueOf("2025-02-21 14:30:00"),
        Timestamp.valueOf("2025-02-21 16:45:00"),
        10400, 3900);
    tables[Table.FLIGHT.val].Insert(flight20);

    // Define more flights similarly for other locations and planes
    // You can add flights connecting various cities in India using different planes

    // Add more flights as needed...
    // Flight 21: Chennai to Bangalore
    IData flight21 = new DFlight(135, 35, plane11.id, "Chennai", "Bangalore",
        Timestamp.valueOf("2025-03-10 08:00:00"),
        Timestamp.valueOf("2025-03-10 10:15:00"),
        9700, 3600);
    tables[Table.FLIGHT.val].Insert(flight21);

    // Flight 22: Bangalore to Chennai
    DFlight flight22 = new DFlight(135, 35, plane11.id, "Bangalore", "Chennai",
        Timestamp.valueOf("2025-03-11 12:30:00"),
        Timestamp.valueOf("2025-03-11 14:45:00"),
        9700, 3600);
    tables[Table.FLIGHT.val].Insert(flight22);

    // Flight 23: Mumbai to Hyderabad
    IData flight23 = new DFlight(165, 50, plane12.id, "Mumbai", "Hyderabad",
        Timestamp.valueOf("2025-04-05 09:30:00"),
        Timestamp.valueOf("2025-04-05 11:45:00"),
        10200, 3800);
    tables[Table.FLIGHT.val].Insert(flight23);

    // Flight 24: Hyderabad to Mumbai
    IData flight24 = new DFlight(165, 50, plane12.id, "Hyderabad", "Mumbai",
        Timestamp.valueOf("2025-04-06 13:30:00"),
        Timestamp.valueOf("2025-04-06 15:45:00"),
        10200, 3800);
    tables[Table.FLIGHT.val].Insert(flight24);

    // Flight 25: Kolkata to Bangalore
    IData flight25 = new DFlight(120, 25, plane13.id, "Kolkata", "Bangalore",
        Timestamp.valueOf("2025-05-15 08:45:00"),
        Timestamp.valueOf("2025-05-15 10:30:00"),
        9500, 3600);
    tables[Table.FLIGHT.val].Insert(flight25);

    // Flight 26: Bangalore to Kolkata
    IData flight26 = new DFlight(120, 25, plane13.id, "Bangalore", "Kolkata",
        Timestamp.valueOf("2025-05-16 12:30:00"),
        Timestamp.valueOf("2025-05-16 14:45:00"),
        9500, 3600);
    tables[Table.FLIGHT.val].Insert(flight26);

    // Flight 27: Delhi to Hyderabad
    IData flight27 = new DFlight(170, 45, plane14.id, "Delhi", "Hyderabad",
        Timestamp.valueOf("2025-06-10 09:00:00"),
        Timestamp.valueOf("2025-06-10 11:15:00"),
        10800, 4000);
    tables[Table.FLIGHT.val].Insert(flight27);

    // Flight 28: Hyderabad to Delhi
    IData flight28 = new DFlight(170, 45, plane14.id, "Hyderabad", "Delhi",
        Timestamp.valueOf("2025-06-11 13:30:00"),
        Timestamp.valueOf("2025-06-11 15:45:00"),
        10800, 4000);
    tables[Table.FLIGHT.val].Insert(flight28);

    // Define more flights similarly for other locations and planes
    // You can add flights connecting various cities in India using different planes

    // Add more flights as needed...

  }

  // to see number of tickets
  public Vector<Map<String, String>> getMyTickets(int loginId) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Vector<Map<String, String>> ret = new Vector<>(); // Initialize the array to store ticket details

    try {

      String query = "SELECT b.id,p.name, b.economy_seats, b.bussiness_class_seats, f.to_location, f.from_location, f.departure_time, f.arriving_time "
          +
          "FROM Flight f, Login l, Booking b, Plane p " +
          "WHERE l.id = b.login_id AND b.flight_id = f.id AND f.plane_id = p.id AND l.id = ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, loginId); // Set the user ID parameter

      rs = pstmt.executeQuery();

      while (rs.next()) {
        Map<String, String> ticketDetails = new HashMap<>();
        ticketDetails.put("FlightName", rs.getString("name"));
        int economy = rs.getInt("economy_seats");
        int bussiness = rs.getInt("bussiness_class_seats");
        ticketDetails.put("EconomySeats", Integer.toString(economy));
        ticketDetails.put("BusinessClassSeats", Integer.toString(bussiness));
        ticketDetails.put("ToLocation", rs.getString("to_location"));
        ticketDetails.put("FromLocation", rs.getString("from_location"));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Timestamp tempTime = rs.getTimestamp("departure_time");
        ticketDetails.put("DepartureTime", sdf.format(tempTime));
        ticketDetails.put("ArrivalTime", sdf.format(rs.getTimestamp("arriving_time")));
        Date date = new Date(tempTime.getTime());
        ticketDetails.put("Date", date.toString());
        int fid = rs.getInt("id");
        ticketDetails.put("id", Integer.toString(fid));
        ticketDetails.put("Amount",Integer.toString(calculateAmount(fid,economy, bussiness)));

        ret.add(ticketDetails);
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
        ret.put("date_of_birth", resultSet.getDate("date_of_birth").toString());
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

  public int cancelTicket(int bookingId) {
    int ammount = 0;
    int paymentId = 0;
    int economy = 0, business = 0, flightId = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String query = "select p.id ,p.ammount,b.economy_seats,b.bussiness_class_seats , b.flight_id from Payment p, Booking b where b.payment_id = p.id and b.id = ?";
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, bookingId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        paymentId = rs.getInt(1);
        ammount = rs.getInt(2);
        economy = rs.getInt(3);
        business = rs.getInt(4);
        flightId = rs.getInt(5);
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
    PreparedStatement pst = null;
    String updatequery = "UPDATE Flight Set economy_seats = economy_seats + ? ,bussiness_class_seats = bussiness_class_seats+? WHERE id = ?";
    try {
      pst = conn.prepareStatement(updatequery);
      pst.setInt(1, economy);
      pst.setInt(2, business);
      pst.setInt(3, flightId);
      pst.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }

    }
    tables[Table.BOOKING.val].Delete(bookingId);
    tables[Table.PAYMENT.val].Delete(paymentId);

    return ammount;
  }

  public int calculateAmount(int flightId, int economySeats, int bussinessClassSeats) {
    int ret = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String query = "SELECT economy_price,bussiness_class_price From Flight WHERE id = ?";
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, flightId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        int eco = rs.getInt("economy_price");
        int bussiness = rs.getInt("bussiness_class_price");
        ret = (eco * economySeats) + (bussinessClassSeats * bussiness);

      }
    } catch (SQLException e) {
      e.printStackTrace();
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

  public boolean isSeatAvailable(int flightId, int economySeats, int bussinessClassSeats) {
    boolean ret = false;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String query = "SELECT economy_seats,bussiness_class_seats From Flight WHERE id = ?";
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, flightId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        if (rs.getInt("economy_seats") >= economySeats && rs.getInt("bussiness_class_seats") >= bussinessClassSeats) {
          ret = true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
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

  public void bookTicket(int loginId, int flightId, int economySeats, int bussinessClassSeats, String mode) {
    int ammount = calculateAmount(flightId, economySeats, bussinessClassSeats);
    IData paymenData = new DPayment(mode, ammount, loginId);
    tables[Table.PAYMENT.val].Insert(paymenData);
    IData bookingData = new DBooking(economySeats, bussinessClassSeats, flightId, loginId, paymenData.id);
    tables[Table.BOOKING.val].Insert(bookingData);

    PreparedStatement pstmt = null;
    String query = "Update Flight set economy_seats = economy_seats=? ,bussiness_class_seats = bussiness_class_seats - ? where id = ?";
    try {
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, economySeats);
      pstmt.setInt(2, bussinessClassSeats);
      pstmt.setInt(3, flightId);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }
    }

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

    login.id = loginId;
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

    tables[Table.LOGIN.val].Update(loginId, login);
    tables[Table.PASSENGER.val].Update(passengerId, pass);
  }
}
