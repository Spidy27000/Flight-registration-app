package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class HomePage extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    Container container = getContentPane();
    JLabel toLabel = new JLabel("TO:");
    JTextField toTextField = new JTextField();
    JLabel fromLabel = new JLabel("FROM:");
    JTextField fromTextField = new JTextField();
    JButton findButton = new JButton("FIND");
    JButton accountButton = new JButton("ACCOUNT");

    public HomePage() {
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
        toLabel.setBounds(500, 120, 100, 30);
        toTextField.setBounds(550, 120, 150, 30);

        fromLabel.setBounds(715, 120, 100, 30);
        fromTextField.setBounds(768, 120, 150, 30);

        findButton.setBounds(950, 120, 100, 30);
        accountButton.setBounds(1400, 10, 100, 30);

    }

    public void addComponentsToContainer() {
        container.add(toLabel);
        container.add(toTextField);
        container.add(fromLabel);
        container.add(fromTextField);
        container.add(findButton);
        container.add(accountButton);

    }

    public void addActionEvent() {
        findButton.addActionListener(this);
        accountButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
