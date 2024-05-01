package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import BackEnd.Db;

public class PaymentPage extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  JLabel pleaseLabel = new JLabel("Please select your prefered payment method ");

  ButtonGroup buttonGroup = new ButtonGroup();

  JRadioButton upiButton = new JRadioButton("UPI");
  JRadioButton netbankingButton = new JRadioButton("NET BANKING");
  JRadioButton cardsButton = new JRadioButton("CARDS");

  JButton paymentButton = new JButton("PAY");
  int loginId, flightId, economy, business ;

  MainFrontApp app;
  Db db;

  public PaymentPage(MainFrontApp app) {
    this.app = app;
    db = Db.getInst();
    setLayout(null);
    setLocationAndSize();
    addComponentsToContainer();
    addActionEvent();
    setVisible(true);
  }

  public void setLocationAndSize() {
    pleaseLabel.setBounds(650, 190, 500, 30);
    upiButton.setBounds(720, 260, 150, 30);

    netbankingButton.setBounds(720, 330, 150, 30);
    cardsButton.setBounds(720, 400, 150, 30);

    paymentButton.setBounds(690, 470, 150, 30);
  }

  public void addComponentsToContainer() {
    // add(emailLabel);
    add(pleaseLabel);
    add(upiButton);
    add(netbankingButton);
    add(cardsButton);
    add(paymentButton);
    buttonGroup.add(upiButton);
    buttonGroup.add(netbankingButton);
    buttonGroup.add(cardsButton);

  }

  public void addActionEvent() {
    upiButton.addActionListener(this);
    netbankingButton.addActionListener(this);
    cardsButton.addActionListener(this);
    paymentButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == paymentButton) {
      if (buttonGroup.getSelection() == null) {
        JOptionPane.showMessageDialog(null, "Please select a Payment Method");
        return;
      }
      String mode;
      if (buttonGroup.getSelection().equals(upiButton.getModel())) {
        mode = "UPI";
      } else if (buttonGroup.getSelection().equals(netbankingButton.getModel())) {
        mode = "Net Banking";
      } else if (buttonGroup.getSelection().equals(cardsButton.getModel())) {
        mode = "Card";
      } else{
        mode = "";
      }
      db.bookTicket(loginId, flightId, economy, business, mode);

    }

  }
}
