package BackEnd;
import java.sql.*;

class DLogin implements IData {
  int id;
  String email;
  String password;
  int registrationId;
  DLogin(int id,String email,String password ,int registrationId){
    this.id = id;
    this.email = email;
    this.password= password;
    this.registrationId = registrationId;
  }
}

public class Login implements ITable{
  private static Connection conn;
  Login(Connection connection){
    conn = connection;
  }

	@Override
	public void Create() {
	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Login("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "email VARCHAR(40) NOT NULL,"+
      "password VARCHAR(30) NOT NULL,"+
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
	  DLogin data = (DLogin)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
