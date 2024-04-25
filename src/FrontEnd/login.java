package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login {
    public static void main(String[] args) {
        new MyFrame();
    }
}

class MyFrame extends JFrame {
    private JLabel emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public MyFrame() {
        super("Login Page");
        setLayout(new FlowLayout());

        emailLabel = new JLabel("Email:");
        add(emailLabel);

        emailField = new JTextField(20);
        add(emailField);

        passwordLabel = new JLabel("Password:");
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        signUpButton = new JButton("Sign Up");
        add(signUpButton);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}