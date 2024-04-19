package BackEnd;

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


	@Override
	public void Create() {
	}

	@Override
	public int Insert(IData object) {
	  DBooking data = (DBooking)object;
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
  

