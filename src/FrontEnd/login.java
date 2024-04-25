package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public login() {
        setTitle("Login Page");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2,3,3));
        panel.setSize(  new Dimension(2, 2));

        JLabel emailLabel = new JLabel("Email:"); 
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.setLayout(new GridLayout(3, 2,3,3));

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(loginButton);

        loginButton.addActionListener(this);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        
        JOptionPane.showMessageDialog(this, "Email: " + email + "\nPassword: " + password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login();
            }
        });
    }
}

