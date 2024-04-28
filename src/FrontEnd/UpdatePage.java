package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdatePage extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  JLabel UpdateLabel = new JLabel("Change the details you wish to update and press the update button");
  JLabel emailLabel = new JLabel("EMAIL:");
  JLabel addLabel = new JLabel("ADDRESS:");
  JLabel dobLabel = new JLabel("DATE OF BIRTH:");
  JLabel phoneLabel = new JLabel("Phone number:");
  JLabel userLabel = new JLabel("FULL NAME:");
  JLabel passwordLabel = new JLabel("PASSWORD:");
  JLabel confirmpasswordLabel = new JLabel("CONFIRM PASSWORD:");
  JLabel passportLabel = new JLabel("PASSPORT NO:");
  JTextField emailTextField = new JTextField();
  JTextField userTextField = new JTextField();
  JTextField dobTextField = new JTextField();
  JTextField addTextField = new JTextField();
  JTextField phoneTextField = new JTextField();
  JTextField passportTextField = new JTextField();
  JPasswordField passwordField = new JPasswordField();
  JPasswordField confirmpasswordField = new JPasswordField();
  JButton confirmButton = new JButton("CONFIRM");
  JButton cancleButton = new JButton("CANCEL");
  JCheckBox showPassword = new JCheckBox("Show Password");
  JCheckBox showPassword2 = new JCheckBox("Show Password");
  MainFrontApp app;

  public UpdatePage(MainFrontApp app) {
    this.app = app;
    setLayout(null);
    setLocationAndSize();
    addComponentsToContainer();
    addActionEvent();
  }

  public void setLocationAndSize() {

    UpdateLabel.setBounds(550, 80, 400, 50);

    emailLabel.setBounds(650, 150, 100, 30);
    emailTextField.setBounds(720, 150, 150, 30);

    passwordLabel.setBounds(620, 220, 100, 30);
    passwordField.setBounds(720, 220, 150, 30);
    showPassword.setBounds(720, 250, 150, 30);

    confirmpasswordLabel.setBounds(555, 290, 140, 30);
    confirmpasswordField.setBounds(720, 290, 150, 30);
    showPassword2.setBounds(720, 320, 150, 30);

    userLabel.setBounds(620, 360, 100, 30);
    userTextField.setBounds(720, 360, 150, 30);

    dobLabel.setBounds(600, 430, 100, 30);
    dobTextField.setBounds(720, 430, 150, 30);

    addLabel.setBounds(640, 500, 100, 30);
    addTextField.setBounds(720, 500, 150, 50);

    phoneLabel.setBounds(620, 570, 100, 30);
    phoneTextField.setBounds(720, 570, 150, 30);

    passportLabel.setBounds(620, 640, 140, 30);
    passportTextField.setBounds(720, 640, 150, 30);

    confirmButton.setBounds(620, 710, 100, 30);
    cancleButton.setBounds(770, 710, 100, 30);

  }

  public void AddDataTofField(int id) {

  }

  public void addComponentsToContainer() {
    add(emailLabel);
    add(userLabel);
    add(addLabel);
    add(passwordLabel);
    add(confirmpasswordLabel);
    add(confirmpasswordField);
    add(emailTextField);
    add(addTextField);
    add(userTextField);
    add(passwordField);
    add(confirmButton);
    add(showPassword);
    add(showPassword2);
    add(cancleButton);
    add(phoneLabel);
    add(phoneTextField);
    add(dobLabel);
    add(dobTextField);
    add(passportLabel);
    add(passportTextField);
    add(UpdateLabel);
  }

  public void addActionEvent() {
    confirmButton.addActionListener(this);
    cancleButton.addActionListener(this);
    showPassword.addActionListener(this);
    showPassword2.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == showPassword) {
      if (showPassword.isSelected()) {
        passwordField.setEchoChar((char) 0);
      } else {
        passwordField.setEchoChar('*');
      }

    }
    if (e.getSource() == showPassword2) {
      if (showPassword2.isSelected()) {
        confirmpasswordField.setEchoChar((char) 0);
      } else {
        confirmpasswordField.setEchoChar('*');
      }
    }

  }
}
