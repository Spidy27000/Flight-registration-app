package BackEnd;

import java.sql.*;

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

  public int insertUser() {

    IData passenger = new DPassenger("name", 122, Date.valueOf("1999-05-15"), "paa nahi", 1234567890,234);
    tables[Table.PASSENGER.val].Insert(passenger);
    System.out.println(passenger.id);
    IData login = new DLogin("tanish", "tanish", passenger.id);
    tables[Table.LOGIN.val].Insert(login);
    return login.id;
    // System.out.println(tables[Table.PASSENGER.val].Insert(data));
  }

  public boolean doesUserExist(String email, String password) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean ret = false;
    String query = "SELECT 1 FROM Login where email = ? AND password = ?";
    try {
      pstmt = conn.prepareStatement(query);

      pstmt.setString(1, email);
      pstmt.setString(2, password);
      rs = pstmt.executeQuery();
      int i;
      if (rs.next()) {

        i = rs.getInt(1);
        if (i== 1) {
          ret = true;
        }
      }

    } catch (SQLException e) {
      e.getStackTrace();
    } finally {
      try {
        if (pstmt == null) {
          pstmt.close();
        }
        if (rs == null) {
          rs.close();
        }
      } catch (SQLException e) {
        e.getStackTrace();
      }
    }
    return ret;
  }
}
