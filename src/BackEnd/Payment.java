package BackEnd;
import java.sql.*;

class DPayment implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int ammount;
  String mode;
  int registrationId;
  DPayment(int id,String mode, int economySeats,int bussinessClassSeats,int ammount,int registrationId){
    this.id = id;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.mode = mode;
    this.ammount = ammount;
    this.registrationId = registrationId;
  }
}

public class Payment implements ITable{

  private static Connection conn;
  Payment(Connection connection){
    conn = connection;
  }

	@Override
	public void Create() {
	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Login("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "ammount int NOT NULL,"+
      "mode VARCHAR(30) NOT NULL,"+
      "economy_seats SMALLINT NOT NULL,"+
      "bussiness_class_seats SMALLINT NOT NULL,"+
      "registration_id int NOT NULL,"+
      "FOREIGN KEY (registration_id)"+
      "   REFERENCES Registration(id)"+
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
	  DPayment data = (DPayment)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
  

