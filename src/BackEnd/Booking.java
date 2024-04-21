package BackEnd;
import java.sql.*;

class DBooking implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int flightId;
  int registrationId;
  int paymentId;
  DBooking(int id,int economySeats,int bussinessClassSeats,int flightId,int registrationId,int paymentId){
    this.id = id;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.flightId = flightId;
    this.registrationId = registrationId;
    this.paymentId = paymentId;
  }
}

public class Booking implements ITable{
  private static Connection conn;
  Booking(Connection connection){
    conn = connection;
  }


	@Override
	public void Create() {

	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Plane("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "economy_seats SMALLINT NOT NULL,"+
      "bussiness_class_seats SMALLINT NOT NULL,"+
      "flight_id int NOT NULL,"+
      "FOREIGN KEY (flight_id)"+
      "   REFERENCES Flight(id)"+
      "   ON DELETE CASCADE"+
      "   ON UPDATE CASCADE,"+
      "registration_id int NOT NULL,"+
      "FOREIGN KEY (registration_id)"+
      "   REFERENCES Registration(id)"+
      "   ON DELETE CASCADE"+
      "   ON UPDATE CASCADE,"+
      "payment_id int NOT NULL,"+
      "FOREIGN KEY (payment_id)"+
      "   REFERENCES Payment(id)"+
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
	  DBooking data = (DBooking)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
  

