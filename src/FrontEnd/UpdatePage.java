package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BackEnd.Db;

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
  JButton deleteButton = new JButton("DELETE ACCOUNT");
  MainFrontApp app;
  Db db ;
  int loginId = 0;

  public UpdatePage(MainFrontApp app) {
    this.db= Db.getInst();
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

    confirmButton.setBounds(570, 710, 100, 30);
    cancleButton.setBounds(720, 710, 100, 30);
    deleteButton.setBounds(880, 710, 180, 30);

  }

  public void AddDataTofField() {

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
    add(deleteButton);
  }

  public void addActionEvent() {
    confirmButton.addActionListener(this);
    cancleButton.addActionListener(this);
    showPassword.addActionListener(this);
    showPassword2.addActionListener(this);
    deleteButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirmButton) {
      String emailText = emailTextField.getText();
      

      if (emailText.length() ==0){
        JOptionPane.showMessageDialog(this, "Email should not be empty");
        return;
      }

      String pwdText;
      char[] passwordChars = passwordField.getPassword();
      pwdText = new String(passwordChars);
      Arrays.fill(passwordChars, ' ');
      System.out.println(pwdText);

      String confirmPwdText;
      char[] cpasswordChars = confirmpasswordField.getPassword();
      confirmPwdText = new String(cpasswordChars);
      Arrays.fill(cpasswordChars, ' ');
      System.out.println(confirmPwdText);

      if((!pwdText.equals(confirmPwdText)) || pwdText.equals("")){
        JOptionPane.showMessageDialog(this, "Password does not match");
        return;
      }

      String name = userTextField.getText();
      if (name.length() ==0){
        JOptionPane.showMessageDialog(this, "name should not be empty");
        return;
      }

      String dobText = dobTextField.getText();
      java.sql.Date dateOfBirth ;
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
        java.util.Date utilDate = dateFormat.parse(dobText);
        dateOfBirth = new java.sql.Date(utilDate.getTime());

      } catch (ParseException err) {
        JOptionPane.showMessageDialog(this, "Invalid Date format");
        return;
      }

      LocalDate dob = dateOfBirth.toLocalDate();
      LocalDate currentDate = LocalDate.now();
      Period period = Period.between(dob, currentDate);
      int age = period.getYears();

      String address = addTextField.getText();
      System.out.println(address);
      if (address.length() ==0){
        JOptionPane.showMessageDialog(this, "Adddress should not be empty");
        return;
      }

      String phoneNoText = phoneTextField.getText();
      if (phoneNoText.length() ==0){
        JOptionPane.showMessageDialog(this, "Number should not be empty");
        return;
      }
      if (phoneNoText.length() !=10){
        JOptionPane.showMessageDialog(this, "Invalid number ");
        return;
      }
      long phoneNo;
      try {
        phoneNo = Long.parseLong(phoneNoText);
      } catch (NumberFormatException err) {
        JOptionPane.showMessageDialog(this, " the phone no may only contain numeric data");
        return;
      }

      String passportNo = passportTextField.getText();
      if (passportNo.length() ==0){
        JOptionPane.showMessageDialog(this, "passport Number should not be empty");
        return;
      }

      db.UpdateUser(loginId,emailText, pwdText, name, age, dateOfBirth, address, phoneNo, passportNo);   
      app.showHome(loginId);
      
    }
    if (e.getSource() == deleteButton) {
      int rs = JOptionPane.showConfirmDialog(null, "Do u want to delete ur account", "Confirmation",JOptionPane.YES_NO_OPTION);
      if (rs == JOptionPane.YES_OPTION) {
        this.db.DeleteUser(this.loginId);
        this.app.showLogin();
      }
    }

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
