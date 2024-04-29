
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PaymentPage extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  JLabel pleaseLabel = new JLabel("Please select your prefered payment method ");
  
  ButtonGroup buttonGroup = new ButtonGroup();

  JRadioButton upiButton = new JRadioButton("UPI");
  JRadioButton netbankingButton = new JRadioButton("NET BANKING");
  JRadioButton cardsButton = new JRadioButton("CARDS");

  JButton paymentButton = new JButton("PAY");

  //MainFrontApp app;

  public PaymentPage() {
    //this.app = app;
    setLayout(null);
    setLocationAndSize();
    addComponentsToContainer();
    addActionEvent();
    setVisible(true);
  }

  public void setLocationAndSize() {
    pleaseLabel.setBounds(650, 150, 100, 30);
    upiButton.setBounds(720, 150, 150, 30);

    netbankingButton.setBounds(620, 220, 100, 30);
    cardsButton.setBounds(720, 220, 150, 30);
    
    paymentButton.setBounds(620, 290, 100, 30);
  }

  public void addComponentsToContainer() {
    //add(emailLabel); 
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
  }
  public static void main(String[] args) {
    new PaymentPage();
  }
}
