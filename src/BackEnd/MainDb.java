package BackEnd;
import java.sql.*;

public class MainDb { 
  private static MainDb inst;
  private Connection conn ;
  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/FlightDb";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "password"; 

  private MainDb() {
    try{
    conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    System.out.println("Database is connected");
    } catch (SQLException e) {
      e.printStackTrace();
    } 
  }
  public static MainDb getInst() {
    if (inst == null) {
      inst = new MainDb();
    }
    return inst;
  }
  /*
  public void close(){
    if (inst != null){
      conn.close();
      inst= null;
    }
  }
  */
}
