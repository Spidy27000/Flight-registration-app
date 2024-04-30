package FrontEnd;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrontApp extends JFrame {
  private LoginPage loginPage;
  private SignUpPage signUpPage;
  private HomePage homePage;
  private UpdatePage updatePage;
  private CardLayout cardLayout;
  private JPanel cardPanel;

  public MainFrontApp() {

    setTitle("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.loginPage = new LoginPage(this);
    this.signUpPage = new SignUpPage(this);
    this.homePage = new HomePage(this);
    this.updatePage = new UpdatePage(this);


    cardLayout = new CardLayout();
    cardPanel = new JPanel();
    cardPanel.setLayout(cardLayout);
    cardPanel.add(this.loginPage, "login");
    cardPanel.add(this.signUpPage, "signup");
    cardPanel.add(this.homePage,"home");
    cardPanel.add(this.updatePage,"update");
    cardPanel.setBorder(null);
    cardLayout.show(cardPanel, "login");
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(cardPanel, BorderLayout.CENTER);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    
    setVisible(true);
  }

  public void showSignUp() {
    cardLayout.show(cardPanel, "signup");
    setTitle("SignUp Form");
  }

  public void showLogin() {
    cardLayout.show(cardPanel, "login");
    setTitle("Login Form");
  }

  public void showHome(int id) {
    cardLayout.show(cardPanel, "home");
    setTitle("Home");
    homePage.id = id;
  }
  public void showUpdate(int id){
    cardLayout.show(cardPanel, "update");
    setTitle("Update");
    updatePage.loginId = id;
    updatePage.AddDataTofField();

  }

  public void showBooking(int loginId , int flightId){
    
  }

}