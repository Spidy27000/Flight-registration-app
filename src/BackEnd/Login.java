
package BackEnd;

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

	@Override
	public void Create() {
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
