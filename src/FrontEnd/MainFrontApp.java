package FrontEnd;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrontApp extends JFrame {
  private LoginPage loginPage;
  private SignUpPage signUpPage; 
  private CardLayout cardLayout;
  private JPanel cardPanel;
  public MainFrontApp() {

    setTitle("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(370, 600);
    this.loginPage = new LoginPage(this);
    this.signUpPage= new SignUpPage(this);
    this.loginPage.setVisible(true);

    cardLayout = new CardLayout();
    cardPanel = new JPanel();
    cardPanel.setLayout(cardLayout);
    cardPanel.add(this.loginPage, "login");
    cardPanel.add(this.signUpPage, "signup");
    cardLayout.show(cardPanel, "login");
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(cardPanel,BorderLayout.CENTER);

    setVisible(true);
  }

  public void showSignUp() {
    cardLayout.show(cardPanel, "signup");
  }

  public void showLogin() {
    cardLayout.show(cardPanel, "login");
  }
    
}