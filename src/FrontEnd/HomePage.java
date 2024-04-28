package FrontEnd;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Panel_1 extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel flightLabel = new JLabel("Flight name:");
    JLabel FromLabel = new JLabel("FROM:");
    JLabel ToLabel = new JLabel("TO:");
    JLabel departureLabel = new JLabel("DEPARTURE TIME:");
    JLabel arrivalLabel = new JLabel("ARRIVAL TIME:");
    JLabel ecopriceLabel = new JLabel("ECONOMY PRICE:");
    JLabel buspriceLabel = new JLabel("BUSINESS PRICE:");
    JButton bookButton = new JButton("BOOK");

    public Panel_1() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setLayout(null);
        setLocationAndSize();
        addComponents();
        addActionEvent();
        setVisible(true); // Ensure the panel is visible
    }

    public void setLocationAndSize() {
        flightLabel.setBounds(100, 00, 100, 30);
        FromLabel.setBounds(100, 170, 100, 30);
        ToLabel.setBounds(100, 240, 100, 30);
        departureLabel.setBounds(100, 310, 150, 30);
        arrivalLabel.setBounds(100, 380, 150, 30);
        ecopriceLabel.setBounds(100, 450, 150, 30);
        buspriceLabel.setBounds(100, 520, 150, 30);
        bookButton.setBounds(100, 590, 100, 30);
    }

    public void addComponents() {
        add(flightLabel);
        add(FromLabel);
        add(ToLabel);
        add(departureLabel);
        add(arrivalLabel);
        add(ecopriceLabel);
        add(buspriceLabel);
        add(bookButton);
    }

    public void addActionEvent() {
        bookButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


public class HomePage extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    Container container = getContentPane();
    JLabel toLabel = new JLabel("TO:");
    JTextField toTextField = new JTextField();
    JLabel fromLabel = new JLabel("FROM:");
    JTextField fromTextField = new JTextField();
    JButton findButton = new JButton("FIND");
    JButton accountButton = new JButton("ACCOUNT");
    JButton ticketButton = new JButton("TICKET BOOKED");

    Panel_1 p = new Panel_1();

    public HomePage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Home page");
        setVisible(true);
        setSize(1500, 700); // Adjust the size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        fromLabel.setBounds(515, 120, 100, 30);
        fromTextField.setBounds(568, 120, 150, 30);

        toLabel.setBounds(729, 120, 100, 30);
        toTextField.setBounds(768, 120, 150, 30);

        findButton.setBounds(950, 120, 100, 30);
        accountButton.setBounds(1400, 10, 100, 30);
        ticketButton.setBounds(1225, 10, 150, 30);
        p.setBounds(300, 300, 800, 600); // Adjust position and size of Panel_1
    }

    public void addComponentsToContainer() {
        container.add(toLabel);
        container.add(toTextField);
        container.add(fromLabel);
        container.add(fromTextField);
        container.add(findButton);
        container.add(accountButton);
        container.add(p);
        container.add(ticketButton);
    }

    public void addActionEvent() {
        findButton.addActionListener(this);
        accountButton.addActionListener(this);
        ticketButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}