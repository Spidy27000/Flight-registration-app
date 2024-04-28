package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

class panel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    JLabel flightLabel = new JLabel("Flight name:");
    JLabel FromLabel = new JLabel("FROM:");
    JLabel ToLabel = new JLabel("TO:");
    JLabel departureLabel = new JLabel("DEPARTURE TIME:");
    JLabel arrivaltureLabel = new JLabel("ARRIVAL TIME:");
    JLabel ecopriceLabel = new JLabel("ECONOMY PRICE:");
    JLabel buspriceLabel = new JLabel("BUSINESS PRICE:");
    JButton bookButton = new JButton("BOOK");

    public panel() 
    {
        setBounds(650,160,500,100);
        setLayoutManager();
        setLocationAndSize();
        addComponents();
        addActionEvent();
    }
    public void setLocationAndSize() 
    {
        flightLabel.setBounds(650, 100, 100, 30);
        FromLabel.setBounds(650, 170, 100, 30);
        ToLabel.setBounds(650, 240, 100, 30);
        departureLabel.setBounds(650, 310, 100, 30);
        arrivaltureLabel.setBounds(650, 380, 100, 30);
        ecopriceLabel.setBounds(650, 450, 100, 30);
        buspriceLabel.setBounds(650, 520, 100, 30);
        bookButton.setBounds(650, 590, 100, 30);
    }

    public void addComponents() 
    {
        add(flightLabel);
        add(FromLabel);
        add(ToLabel);
        add(departureLabel);
        add(arrivaltureLabel);
        add(ecopriceLabel);
        add(buspriceLabel);
        add(bookButton);
    }
    public void addActionEvent(){
        bookButton.addActionListener(this);

    }
    public void setLayoutManager(){
    setLayout(null);

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
    JButton tiketButton = new JButton("TIKET BOOKED");

    panel p;

    public HomePage() {
        p = new panel();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Home page");
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
        fromLabel.setBounds(515, 120, 100, 30);
        fromTextField.setBounds(568, 120, 150, 30);

        toLabel.setBounds(729, 120, 100, 30);
        toTextField.setBounds(768, 120, 150, 30);

        findButton.setBounds(950, 120, 100, 30);
        accountButton.setBounds(1400, 10, 100, 30);
        tiketButton.setBounds(1225, 10, 150, 30); 
    }

    public void addComponentsToContainer() {
        container.add(toLabel);
        container.add(toTextField);
        container.add(fromLabel);
        container.add(fromTextField);
        container.add(findButton);
        container.add(accountButton);
        container.add(p);
        container.add(tiketButton);
    }

    public void addActionEvent() {
        findButton.addActionListener(this);
        accountButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
