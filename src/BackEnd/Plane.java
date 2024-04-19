package BackEnd;

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


	@Override
	public void Create() {
	}

	@Override
	public int Insert(IData object) {
	  DPlane data = (DPlane)object;
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
  

