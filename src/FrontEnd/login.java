package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

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
    private JLabel background;

    public MyFrame() {
        super("Login Page");
        setLayout(new FlowLayout());

        // Load the background image
        URL url = getClass().getResource("/C:\\Users\\rushi\\Flight-registration-app\\airplane.jpeg");
        ImageIcon icon = new ImageIcon(url);
        background = new JLabel(icon);
        background.setLayout(new FlowLayout());

        // Add the components to the background
        emailLabel = new JLabel("Email:");
        background.add(emailLabel);

        emailField = new JTextField(20);
        background.add(emailField);

        passwordLabel = new JLabel("Password:");
        background.add(passwordLabel);

        passwordField = new JPasswordField(20);
        background.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your login logic here
            }
        });
        background.add(loginButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your sign up logic here
            }
        });
        background.add(signUpButton);

        // Add the background to the frame
        add(background);

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}