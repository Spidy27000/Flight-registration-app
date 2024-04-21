package BackEnd;
import java.sql.*;

public class MainDb { 
  private static MainDb inst;
  private Connection conn ;
  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/FlightDb";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "password"; 
  private static ITable[] tables = new ITable[6];

  private MainDb() {
    try{
      conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
      System.out.println("Database is connected");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    tables[Table.REGISTRATION.val] = new Registration(conn);
    tables[Table.LOGIN.val] = new Login(conn);
    tables[Table.PLANE.val] = new Plane(conn);
    tables[Table.FLIGHT.val] = new Flight(conn);
    tables[Table.PAYMENT.val] = new Payment(conn);
    tables[Table.BOOKING.val] = new Booking(conn);
    CreateTables();

  }
  public static MainDb getInst() {
    if (inst == null) {
      inst = new MainDb();
    }
    return inst;
  }
  public void close(){
    if (inst != null){
      try{
        conn.close();

      } catch (SQLException e){
        e.printStackTrace();
      }
      finally{
        inst= null;
      }
    }
  }
  private static void CreateTables(){
    for (ITable tab : tables){
      tab.Create();
    }
  }

  public void insert(){
    IData data = new DPayment(6,"df",3,3,4,3);
    System.out.println(tables[Table.PAYMENT.val].Insert(data));
  }
}