import BackEnd.*;
import FrontEnd.*;

public class MainApp{
  public static void main(String args []){
    MainDb db =  MainDb.getInst();
    db.insert();
  }
}


