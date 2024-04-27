package FrontEnd;

import javax.swing.*;

import BackEnd.Db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginPage extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;

  JLabel userLabel = new JLabel("USERNAME");
  JLabel passwordLabel = new JLabel("PASSWORD");
  JTextField userTextField = new JTextField();
  JPasswordField passwordField = new JPasswordField();
  JButton loginButton = new JButton("LOGIN");
  JButton signUpButton = new JButton("Sign up");
  JCheckBox showPassword = new JCheckBox("Show Password");
  Db db = Db.getInst();
  MainFrontApp app;

  public LoginPage(MainFrontApp app) {
    // frame.setBounds(10, 10, 370, 600);
    // frame.setResizable(false);
    this.app = app;
    setLayout(null);
    setLocationAndSize();
    addActionEvent();
    addComponentsToContainer();
    setVisible(true);
  }

  public void setLocationAndSize() {
    userLabel.setBounds(50, 150, 100, 30);
    passwordLabel.setBounds(50, 220, 100, 30);
    userTextField.setBounds(150, 150, 150, 30);
    passwordField.setBounds(150, 220, 150, 30);
    showPassword.setBounds(150, 250, 150, 30);
    loginButton.setBounds(50, 300, 100, 30);
    signUpButton.setBounds(200, 300, 100, 30);

  }

  public void addComponentsToContainer() {
    add(userLabel);
    add(passwordLabel);
    add(userTextField);
    add(passwordField);
    add(showPassword);
    add(loginButton);
    add(signUpButton);
  }

  public void addActionEvent() {
    loginButton.addActionListener(this);
    signUpButton.addActionListener(this);
    showPassword.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginButton) {
      String userText;
      String pwdText;
      userText = userTextField.getText();
      char[] passwordChars = passwordField.getPassword();
      pwdText = new String(passwordChars);
      Arrays.fill(passwordChars, ' ');
      if (this.db.doesUserExist(userText, pwdText)) {
        JOptionPane.showMessageDialog(this, "Login Successful");
      } else {
        JOptionPane.showMessageDialog(this, "Invalid Username or Password");
      }

    }
    if (e.getSource() == signUpButton) {
      app.showSignUp();
    }
    if (e.getSource() == showPassword) {
      if (showPassword.isSelected()) {
        passwordField.setEchoChar((char) 0);
      } else {
        passwordField.setEchoChar('*');
      }
    }
  }
}
