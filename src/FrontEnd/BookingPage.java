package FrontEnd;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookingPage extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    Container container;
    JLabel needLabel = new JLabel("Please enter how many tickets are needed");
    JLabel ecoLabel = new JLabel("ECONOMY Class:");
    JLabel bussLabel = new JLabel("BUSINESS CLASS:");
    JLabel priceLabel = new JLabel("PRICE = ");

    JTextField ecoTextField = new JTextField();
    JTextField bussTextField = new JTextField();

    JButton checkButton = new JButton("CHECK PRICE");
    JButton bookButton = new JButton("BOOK");

    MainFrontApp app;

    public BookingPage() {
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
        setVisible(true);
        // frame.setBounds(10, 10, 370, 600);
        setSize(370, 600);
        
        // frame.setResizable(false);
    }

    public void setLocationAndSize() {

        needLabel.setBounds(650, 220, 400, 50);

        ecoLabel.setBounds(650, 290, 200, 30);
        ecoTextField.setBounds(780, 290, 150, 30);

        bussLabel.setBounds(650, 360, 200, 30);
        bussTextField.setBounds(780, 360, 150, 30);

        priceLabel.setBounds(650, 430, 200, 30);

        checkButton.setBounds(710, 500, 150, 30);
        bookButton.setBounds(710, 570, 150, 30);

    }

    public void AddDataToFead() {

    }

    public void addComponentsToContainer() {
        add(needLabel);
        add(ecoLabel);
        add(ecoTextField);
        add(bussLabel);
        add(bussTextField);
        add(checkButton);
        add(priceLabel);
        add(bookButton);
    }

    public void addActionEvent() {
        bookButton.addActionListener(this);
        checkButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}