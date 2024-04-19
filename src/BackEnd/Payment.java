
package BackEnd;

class DPayment implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int ammount;
  String mode;
  DPayment(int id,String mode, int economySeats,int bussinessClassSeats,int ammount){
    this.id = id;
    this.economySeats = economySeats;
    this.bussinessClassSeats = bussinessClassSeats;
    this.mode = mode;
    this.ammount = ammount;
  }
}

public class Payment implements ITable{


	@Override
	public void Create() {
	}

	@Override
	public int Insert(IData object) {
	  DPayment data = (DPayment)object;
    //.GetData();
    return data.id;
	}
  
	@Override
  public void Update(int id, IData data){

  }
	@Override
  public void Delete(int id){

  }
}
  

