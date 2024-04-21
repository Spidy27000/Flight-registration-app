package BackEnd;
import java.sql.*;

class DRegistration implements IData {
  int id;
  String name;
  int age;
  String dateOfBirth;
  String address;
  int phoneNo;
  DRegistration(int id,String name, int age ,String dateOfBirth,String address,int phoneNo){
    this.id = id;
    this.name = name;
    this.age = age;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.phoneNo = phoneNo;
  }
}

public class Registration implements ITable{

  private static Connection conn;
  Registration(Connection connection){
    conn = connection;
  }

	@Override
	public void Create() {
	  Statement stmt = null;
    String query = 
      "CREATE TABLE IF NOT EXISTS Registration("+
      "id INT AUTO_INCREMENT PRIMARY KEY,"+
      "name VARCHAR(30) NOT NULL,"+
      "age SMALLINT NOT NULL,"+
      "date_of_birth DATE NOT NULL,"+
      "address TEXT NOT NULL,"+
      "phone_no NUMERIC(10) NOT NULL);";
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
	  DRegistration data = (DRegistration)object;
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
  

