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
    JLabel fromLabel = new JLabel("FROM");
    JLabel toLabel = new JLabel("TO:");
    JLabel departureLabel = new JLabel("DEPARTURE TIME:");
    JLabel arrivalLabel = new JLabel("ARRIVAL TIME:");
    JLabel ecopriceLabel = new JLabel("ECONOMY PRICE:");
    JLabel buspriceLabel = new JLabel("BUSINESS PRICE:");
    JButton cancle1Button = new JButton("CANCLE FLIGHT");
    JLabel dateLabel = new JLabel("DATE:");
   
    int flightId;

    public PlanePanal(int id, int flightId) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setLayout(null);
        setLocationAndSize();
        addComponents();
        addActionEvent();
        setVisible(true); // Ensure the panel is visible
        setBounds((150 *id) + 100, 200, 1350, 100); // Adjust position and size of Panel_1
    }
    public void FillData(Map<String, String> data){
        flightLabel.setText("Name: "+data.get("FlightName"));
        fromLabel.setText("From: "+data.get("FromLocation"));
        toLabel.setText("To: "+ data.get("ToLocation"));
        departureLabel.setText("Departure: "+ data.get("DepartureTime"));
        arrivalLabel.setText("Arrival: "+data.get("ArrivalTime"));
        ecopriceLabel.setText("Economey Price: "+data.get("EconomyPrice"));
        buspriceLabel.setText("Bussness Price: "+data.get("Buss"));
        dateLabel.setText("Date: "+data.get("Date"));
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
        cancle1Button.setBounds(1180, 30, 100, 30);
    }

    public void addComponents() {
        add(flightLabel);
        add(fromLabel);
        add(toLabel);
        add(departureLabel);
        add(arrivalLabel);
        add(ecopriceLabel);
        add(buspriceLabel);
        add(cancle1Button);
        add(dateLabel);
    }

    public void addActionEvent() {
        cancle1Button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancle1Button){
        }
    }

}


public class TicketBooked extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    JLabel HeadLabel = new JLabel("THESE ARE THE TICKETS YOU HAVE BOOKED");
    
    JButton Homebutton = new JButton("HOME");

    MainFrontApp app;
    int id;

    public TicketBooked(MainFrontApp app) {
        this.app = app;
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setBounds(100, 200, 1350, 100); // Adjust position and size of Panel_1
    }

    public void setLocationAndSize() {
        HeadLabel.setBounds(515, 120, 300, 30);

        Homebutton.setBounds(950, 120, 100, 30);
        
    }

    public void addComponentsToContainer() {
        add(HeadLabel);
        add(Homebutton);
    }

    public void addActionEvent() {
        Homebutton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Homebutton){
            app.showUpdate(this.id);
        }

    }
}