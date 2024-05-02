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
  private BookingPage bookingPage;
  private PaymentPage paymentPage;
  private TicketBookedPage ticketBookedPage;
  private CardLayout cardLayout;
  private JPanel cardPanel;

  public MainFrontApp() {

    setTitle("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.loginPage = new LoginPage(this);
    this.signUpPage = new SignUpPage(this);
    this.homePage = new HomePage(this);
    this.updatePage = new UpdatePage(this);
    this.bookingPage = new BookingPage(this);
    this.paymentPage = new PaymentPage(this);
    this.ticketBookedPage = new TicketBookedPage(this);

    cardLayout = new CardLayout();
    cardPanel = new JPanel();
    cardPanel.setLayout(cardLayout);
    cardPanel.add(this.loginPage, "login");
    cardPanel.add(this.signUpPage, "signup");
    cardPanel.add(this.homePage, "home");
    cardPanel.add(this.updatePage, "update");
    cardPanel.add(this.bookingPage, "booking");
    cardPanel.add(this.paymentPage, "payment");
    cardPanel.add(this.ticketBookedPage,"myTicket");
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

  public void showUpdate(int id) {
    cardLayout.show(cardPanel, "update");
    setTitle("Update");
    updatePage.loginId = id;
    updatePage.AddDataTofField();

  }

  public void showBooking(int loginId, int flightId) {
    cardLayout.show(cardPanel, "booking");
    setTitle("Booking");
    bookingPage.loginId = loginId;
    bookingPage.flightId = flightId;

  }

  public void showPayment(int loginId, int flightId, int economy, int business) {
    cardLayout.show(cardPanel, "payment");
    setTitle("Payment");
    paymentPage.loginId = loginId;
    paymentPage.flightId = flightId;
    paymentPage.economy = economy;
    paymentPage.business = business;
  }
  public void showTicketBooked(int loginId){
    cardLayout.show(cardPanel, "myTicket");
    setTitle("My Ticket");
    ticketBookedPage.addTickets(loginId);
    ticketBookedPage.loginId = loginId;

  }

}