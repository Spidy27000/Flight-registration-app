package BackEnd;

import java.sql.*;

class DPassenger extends IData {
  int id;
  String name;
  int age;
  Date dateOfBirth;
  String address;
  long phoneNo;
  String passportNo;

  DPassenger(String name, int age, Date dateOfBirth, String address, long phoneNo, String passportNo) {
    this.id = 0;
    this.name = name;
    this.age = age;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.phoneNo = phoneNo;
    this.passportNo = passportNo;
  }
  public DPassenger(){

  }
}

public class Passenger implements ITable {

  private static Connection conn;

  Passenger(Connection connection) {
    conn = connection;
  }

  @Override
  public void Create() {
    Statement stmt = null;

    String query = "CREATE TABLE IF NOT EXISTS Passenger(" +
        "id INT AUTO_INCREMENT PRIMARY KEY," +
        "name VARCHAR(30) NOT NULL," +
        "age SMALLINT NOT NULL," +
        "date_of_birth DATE NOT NULL," +
        "address TEXT NOT NULL," +
        "phone_no NUMERIC(10) NOT NULL," +
        "passport_no varchar(13) NOT NULL);";

    try {
      stmt = conn.createStatement();
      stmt.execute(query);
    } catch (SQLException e) {
      System.err.println("Error creating statement: " + e.getMessage());
      return;
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          System.err.println("Error creating statement: " + e.getMessage());
          return;
        }
      }
    }
  }

  @Override
  public void Insert(IData object) {

    PreparedStatement pstmt = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    DPassenger data = (DPassenger) object;

    String insertQuery = "INSERT INTO Passenger (name,age,date_of_birth,address,phone_no, passport_no) VALUES (?, ?, ?, ?, ?,?)";
    String idQuery = "SELECT LAST_INSERT_ID()";

    try {

      pstmt = conn.prepareStatement(insertQuery);
      stmt = conn.prepareStatement(idQuery);

      pstmt.setString(1, data.name);
      pstmt.setInt(2, data.age);
      pstmt.setDate(3, data.dateOfBirth);
      pstmt.setString(4, data.address);
      pstmt.setLong(5, data.phoneNo);
      pstmt.setString(6, data.passportNo);

      // Executing the insert query
      int rowsInserted = pstmt.executeUpdate();

      if (rowsInserted > 0) {
        // If the insertion was successful, retrieving the ID of the newly inserted row
        rs = stmt.executeQuery();
        if (rs.next()) {
          object.id = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {

      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (rs != null) {
          rs.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void Update(int id, IData object) {

    PreparedStatement pstmt = null;
    DPassenger data = (DPassenger) object;

    String insertQuery = "UPDATE Passenger SET name = ?, age = ?, date_of_birth = ?, address = ?, phone_no = ?, passport_no = ? WHERE id = ?;";

    try {
      pstmt = conn.prepareStatement(insertQuery);
System.out.println("inside update");
      pstmt.setString(1, data.name);
      pstmt.setInt(2, data.age);
      pstmt.setDate(3, data.dateOfBirth);
      pstmt.setString(4, data.address);
      pstmt.setLong(5, data.phoneNo);
      pstmt.setString(6, data.passportNo);
      pstmt.setInt(7, data.id);

      // Executing the insert query
      pstmt.executeUpdate();
    
       }

      catch(SQLException e ){
        e.printStackTrace();
      }
      finally{
      try {
        if (pstmt != null) {
          pstmt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void Delete(int id) {
    String query = "DELETE FROM Passenger WHERE id = ?";
    PreparedStatement stmt = null;

    try  {
      stmt = conn.prepareStatement(query);
      stmt.setInt(1, id);
      int rowsAffected = stmt.executeUpdate();
      System.out.println("Rows affected: " + rowsAffected);

    } catch (SQLException e) { 
      e.printStackTrace();
    } finally {

    if (stmt != null){
      try{
        stmt.close();
        } catch (SQLException e) { 
        e.printStackTrace();
        }
      }
    }
  }
}
