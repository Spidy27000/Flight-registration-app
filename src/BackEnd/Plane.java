package BackEnd;
import java.sql.*;

class DPlane implements IData {
  int id;
  String name;
  int economySeats;
  int bussinessClassSeats;
  DPlane(int id,String name, int economySeats,int bussinessClassSeats){
    this.id = id;
    this.name = name;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
  }
}

public class Plane implements ITable{

  private static Connection conn;
  Plane(Connection connection){
    conn = connection;
  }

	@Override
	public void Create() {
	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Plane("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "name VARCHAR(30) NOT NULL,"+
      "economy_seats SMALLINT NOT NULL,"+
      "bussiness_class_seats SMALLINT NOT NULL);";
    try {
      stmt = conn.createStatement();
      stmt.execute(query);
    } catch (SQLException e) {
      System.err.println("Error creating statement: " + e.getMessage());
      return;
    }
    finally{
      if (stmt != null){
        try{
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
	  DPlane data = (DPlane)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
  

