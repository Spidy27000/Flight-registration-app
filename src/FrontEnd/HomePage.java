package FrontEnd;

import javax.swing.*;
import javax.swing.border.Border;

import BackEnd.Db;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

class FlightPanel extends JPanel implements ActionListener {
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
    HomePage app;

    public FlightPanel(int flightId, HomePage app) {
        this.app =app;
        this.flightId = flightId;
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setLayout(null);
        setLocationAndSize();
        addComponents();
        addActionEvent();
    }

    public void FillData(Map<String, String> data) {
        flightLabel.setText("Name: " + data.get("FlightName"));
        fromLabel.setText("From: " + data.get("FromLocation"));
        toLabel.setText("To: " + data.get("ToLocation"));
        departureLabel.setText("Departure: " + data.get("DepartureTime"));
        arrivalLabel.setText("Arrival: " + data.get("ArrivalTime"));
        ecopriceLabel.setText("Economey Price: " + data.get("EconomyPrice"));
        buspriceLabel.setText("Bussness Price: " + data.get("BusinessClassPrice"));
        dateLabel.setText("Date: " + data.get("Date"));
    }

    public void setLocationAndSize() {
        flightLabel.setBounds(70, 10, 200, 30);
        fromLabel.setBounds(40, 40, 100, 30);
        toLabel.setBounds(170, 40, 100, 30);
        dateLabel.setBounds(350, 10, 200, 30);
        departureLabel.setBounds(280, 50, 150, 30);
        arrivalLabel.setBounds(450, 50, 150, 30);
        ecopriceLabel.setBounds(680, 30, 175, 30);
        buspriceLabel.setBounds(950, 30, 170, 30);
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
        if (e.getSource() == bookButton) {
            app.app.showBooking(app.id,  flightId);
        }
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
    Db db;
    int id;
    //JPanel flightPanel = new JPanel();

    public HomePage(MainFrontApp app) {
        this.app = app;
        setLayout(null);
        //flightPanel.setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setVisible(true); // Ensure the panel is visible
        //flightPanel.setVisible(true);
        //flightPanel.setBackground(Color.YELLOW); 
  
        setBounds(100, 200, 1350, 100); // Adjust position and size of Panel_1
        db = Db.getInst();
        
    }

    public void setLocationAndSize() {
        fromLabel.setBounds(515, 120, 100, 30);
        fromTextField.setBounds(568, 120, 150, 30);

        toLabel.setBounds(729, 120, 100, 30);
        toTextField.setBounds(768, 120, 150, 30);

        findButton.setBounds(950, 120, 100, 30);
        accountButton.setBounds(1400, 10, 100, 30);
        ticketButton.setBounds(1225, 10, 150, 30);
    }

    public void addComponentsToContainer() {
        add(toLabel);
        add(toTextField);
        add(fromLabel);
        add(fromTextField);
        add(findButton);
        add(accountButton);
        add(ticketButton);
    }

    public void addActionEvent() {
        findButton.addActionListener(this);
        accountButton.addActionListener(this);
        ticketButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == accountButton) {
            app.showUpdate(this.id);
        }
        if (e.getSource() == findButton) {
            if (toTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " To field should not be empty");
                return;
            }
            String to = toTextField.getText();
            if (fromTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " From field should not be empty");
                return;
            }
            String from = fromTextField.getText();
            Vector<Map<String,String>> data = db.getAvailableFlights(from, to);
            if (data.size() == 0) {
                JOptionPane.showMessageDialog(this, "No available Flights");
                return;
            }
            /*  
            FlightPanel f = new FlightPanel( 1 , this);
            f.setVisible(true);
            f.setBackground(Color.YELLOW);
            add(f);
            f.setBounds(150,300,1295, 100);
            */
            for(int i = 0; i<data.size();i++){
                int flightId = Integer.parseInt(data.get(i).get("id"));
                FlightPanel fPanel = new FlightPanel(flightId, this);
                fPanel.setVisible(true);
                fPanel.FillData(data.get(i));
                add(fPanel);
                fPanel.setBounds(300,200 + (i * 120), 1295, 100);
            }
            System.out.println(data.size());

        }
    }
}