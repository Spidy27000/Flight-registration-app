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
  int planeId;
  DFlight(int id,int economySeats,int bussinessClassSeats,int planeId,String toLocation,String fromLocation,int departureTime,int arrivingTime){
    this.id = id;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.planeId = planeId;
    this.toLocation = toLocation;
    this.fromLocation = fromLocation;
    this.departureTime = departureTime;
    this.arrivingTime = arrivingTime;
  }
}

public class Flight implements ITable{

  private static Connection conn;
  Flight(Connection connection){
    conn = connection;
  }

	@Override
	public void Create() {
	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Flight("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "economy_seats SMALLINT NOT NULL,"+
      "bussiness_class_seats SMALLINT NOT NULL,"+
      "to_loacation VARCHAR(30) NOT NULL,"+
      "from_loaction VARCHAR(30) NOT NULL,"+
      "departure_time DATETIME NOT NULL,"+
      "arriving_time DATETIME NOT NULL,"+
      "plane_id int NOT NULL,"+
      "FOREIGN KEY (plane_id)"+
      "   REFERENCES Plane(id)"+
      "   ON DELETE CASCADE"+
      "   ON UPDATE CASCADE);";
      
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
	  DFlight data = (DFlight)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
