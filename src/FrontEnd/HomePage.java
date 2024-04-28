package FrontEnd;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

class PlanePanal extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel flightLabel = new JLabel("Flight name:");
    JLabel fromLabel = new JLabel("FROM:");
    JLabel toLabel = new JLabel("TO:");
    JLabel departureLabel = new JLabel("DEPARTURE TIME:");
    JLabel arrivalLabel = new JLabel("ARRIVAL TIME:");
    JLabel ecopriceLabel = new JLabel("ECONOMY PRICE:");
    JLabel buspriceLabel = new JLabel("BUSINESS PRICE:");
    JButton bookButton = new JButton("BOOK");
    JLabel dateLabel = new JLabel("DATE:");
    int flightId;

    public PlanePanal(int id) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setLayout(null);
        setLocationAndSize();
        addComponents();
        addActionEvent();
        setVisible(true); // Ensure the panel is visible
    }
    public void FillData(Map<String,Object> data){

    }

    public void setLocationAndSize() {
        flightLabel.setBounds(70, 10, 100, 30);
        fromLabel.setBounds(40, 40, 100, 30);
        toLabel.setBounds(140, 40, 100, 30);
        dateLabel.setBounds(400,10,100,30);
        departureLabel.setBounds(280, 50, 150, 30);
        arrivalLabel.setBounds(450, 50, 150, 30);
        ecopriceLabel.setBounds(680, 30, 150, 30);
        buspriceLabel.setBounds(950, 30, 150, 30);
        bookButton.setBounds(1180, 30, 100, 30);
    }

    public void addComponents() {
        add(flightLabel);
        add(fromLabel);
        add(toLabel);
        add(departureLabel);
        add(arrivalLabel);
        add(ecopriceLabel);
        add(buspriceLabel);
        add(bookButton);
        add(dateLabel);
    }

    public void addActionEvent() {
        bookButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


public class HomePage extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel toLabel = new JLabel("TO:");
    JTextField toTextField = new JTextField();
    JLabel fromLabel = new JLabel("FROM:");
    JTextField fromTextField = new JTextField();
    JButton findButton = new JButton("FIND");
    JButton accountButton = new JButton("ACCOUNT");
    JButton ticketButton = new JButton("TICKET BOOKED");
    MainFrontApp app;
    int id;

    PlanePanal p = new PlanePanal(1);

    public HomePage(MainFrontApp app) {
        this.app = app;
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLocationAndSize() {
        fromLabel.setBounds(515, 120, 100, 30);
        fromTextField.setBounds(568, 120, 150, 30);

        toLabel.setBounds(729, 120, 100, 30);
        toTextField.setBounds(768, 120, 150, 30);

        findButton.setBounds(950, 120, 100, 30);
        accountButton.setBounds(1400, 10, 100, 30);
        ticketButton.setBounds(1225, 10, 150, 30);
        p.setBounds(100, 200, 1350, 100); // Adjust position and size of Panel_1
    }

    public void addComponentsToContainer() {
        add(toLabel);
        add(toTextField);
        add(fromLabel);
        add(fromTextField);
        add(findButton);
        add(accountButton);
        add(p);
        add(ticketButton);
    }

    public void addActionEvent() {
        findButton.addActionListener(this);
        accountButton.addActionListener(this);
        ticketButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accountButton){
            app.showUpdate(this.id);
        }

    }
}