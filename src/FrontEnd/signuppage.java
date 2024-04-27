package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class signuppage extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    Container container = getContentPane();
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel addLabel = new JLabel("ADDRESS");
    JLabel dobLabel = new JLabel("DATE OF BIRTH");
    JLabel phoneLabel = new JLabel("Phone number");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel confirmpasswordLabel = new JLabel("CONFIRM PASSWORD");
    JTextField emailTextField = new JTextField();
    JTextField userTextField = new JTextField();
    JTextField dobTextField = new JTextField();
    JTextField addTextField = new JTextField();
    JTextField phoneTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmpasswordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton signUpButton = new JButton("Sign up");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JCheckBox showPassword2 = new JCheckBox("Show Password");


    public signuppage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Signup Form");
        setVisible(true);
        // frame.setBounds(10, 10, 370, 600);
        setSize(370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setResizable(false);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        emailLabel.setBounds(50, 150, 100, 30);
        emailTextField.setBounds(150, 150, 150, 30);
        
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);

        confirmpasswordLabel.setBounds(50, 290, 100, 30);
        confirmpasswordField.setBounds(150, 290, 150, 30);
        
        showPassword2.setBounds(150, 320, 150, 30);
        
        userLabel.setBounds(50, 360, 100, 30);
        userTextField.setBounds(150, 360, 150, 30);
        
        dobLabel.setBounds(50, 150, 100, 30);
        dobTextField.setBounds(150, 150, 150, 30);
        
        addLabel.setBounds(50,160 ,100 ,30 );
        addTextField.setBounds(150, 150, 150, 30);
        
        phoneLabel.setBounds(50,160 ,100 ,30 );
        phoneTextField.setBounds(150, 150, 150, 30);
        
        
        
        loginButton.setBounds(800, 360, 100, 30);
        signUpButton.setBounds(850, 360, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(emailLabel);
        container.add(userLabel);
        container.add(addLabel);
        container.add(passwordLabel);
        container.add(confirmpasswordLabel);
        container.add(emailTextField);
        container.add(addTextField);
        container.add(userTextField);
        container.add(passwordField);
        container.add(confirmpasswordField);
        container.add(showPassword);
        container.add(showPassword2);
        container.add(loginButton);
        container.add(signUpButton);
        container.add(phoneLabel);
        container.add(phoneTextField);
        container.add(dobLabel);
        container.add(dobTextField);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        showPassword.addActionListener(this);
        showPassword2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String emailText;
            String pwdText;
            emailText = emailTextField.getText();
            char[] passwordChars = passwordField.getPassword();
            pwdText = new String(passwordChars);
            Arrays.fill(passwordChars, ' ');
            if (emailText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (e.getSource() == signUpButton) {

            emailTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

            if (e.getSource() == showPassword2) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }

            }

        }
    }
}
