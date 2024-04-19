package BackEnd;

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


	@Override
	public void Create() {
	}

	@Override
	public int Insert(IData object) {
	  DRegistration data = (DRegistration)object;
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
  

