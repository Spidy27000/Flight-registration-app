package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class LoginPage extends JFrame {
  private JLabel emailLabel, passwordLabel;
  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton, signUpButton;
  private JLabel background;

  public LoginPage() {
    super("Login Page");
    setLayout(new GridLayout(3, 2));

    // Load the background image
    URL url = getClass().getResource("/airplane.jpeg");
    ImageIcon icon = new ImageIcon(url);
    background = new JLabel(icon);
    background.setSize(400, 400);
    background.setLayout(new GridLayout(3, 2));

    // Add the components to the background
    emailLabel = new JLabel("Email:");
    background.add(emailLabel);

    emailField = new JTextField(20);
    background.add(emailField);

    passwordLabel = new JLabel("Password:");
    background.add(passwordLabel);

    passwordField = new JPasswordField(20);
    add(passwordField);

    loginButton = new JButton("Login");
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO:
      }
    });
    add(loginButton);

    signUpButton = new JButton("Sign Up");
    signUpButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO:
      }
    });
    add(signUpButton);

    // Add the background to the frame
    add(background);

    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}
