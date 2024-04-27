package FrontEnd;
import javax.swing.JFrame;

import BackEnd.Db;

public class MainFrontApp extends JFrame {
  public MainFrontApp() {

    setTitle("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(370, 600);
    LoginPage login = new LoginPage(this);
    setVisible(true);
  }
  public void showSignUp(){
    // TODO: add a signup


  }

}
