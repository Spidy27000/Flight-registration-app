package BackEnd;

class DFlight implements IData {
  int id;
  int economySeats;
  int bussinessClassSeats;
  int planeId;
  String toLocation;
  String fromLocation;
  int departureTime;
  int arrivingTime;
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


	@Override
	public void Create() {
	}

	@Override
	public int Insert(IData object) {
	  DFlight data = (DFlight)object;
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
