package FrontEnd;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

class MyTicket extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel flightLabel = new JLabel("Flight name:");
    JLabel fromLabel = new JLabel("FROM:");
    JLabel toLabel = new JLabel("TO:");
    JLabel departureLabel = new JLabel("DEPARTURE TIME:");
    JLabel arrivalLabel = new JLabel("ARRIVAL TIME:");
    JLabel ecoseatsLabel = new JLabel("ECONOMY PRICE:");
    JLabel busseatsLabel = new JLabel("BUSINESS PRICE:");
    JButton cancle1Button = new JButton("CANCEL");
    JLabel dateLabel = new JLabel("DATE:");
    JLabel amountLabel = new JLabel("amount:");
    int flightId;

    public MyTicket(int flightId) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(border);
        setLayout(null);
        setLocationAndSize();
        addComponents();
        addActionEvent();
        setVisible(true); // Ensure the panel is visible
        setBounds(150 + 100, 200, 1350, 100); // Adjust position and size of Panel_1
    }
    public void FillData(Map<String, String> data){
        flightLabel.setText("Name: "+data.get("FlightName"));
        fromLabel.setText("From: "+data.get("FromLocation"));
        toLabel.setText("To: "+ data.get("ToLocation"));
        departureLabel.setText("Departure: "+ data.get("DepartureTime"));
        arrivalLabel.setText("Arrival: "+data.get("ArrivalTime"));
        
        dateLabel.setText("Date: "+data.get("Date"));
    }

    public void setLocationAndSize() {
        flightLabel.setBounds(70, 10, 100, 30);
        fromLabel.setBounds(40, 40, 100, 30);
        toLabel.setBounds(140, 40, 100, 30);
        dateLabel.setBounds(400,10,100,30);
        departureLabel.setBounds(280, 50, 150, 30);
        arrivalLabel.setBounds(440, 50, 150, 30);
        ecoseatsLabel.setBounds(670, 10, 150, 30);
        busseatsLabel.setBounds(670, 50, 150, 30);
        amountLabel.setBounds(940, 30, 150, 30);
        cancle1Button.setBounds(1150, 30, 110, 30);
    }

    public void addComponents() {
        add(flightLabel);
        add(fromLabel);
        add(toLabel);
        add(departureLabel);
        add(arrivalLabel);
        add(ecoseatsLabel);
        add(busseatsLabel);
        add(cancle1Button);
        add(dateLabel);
        add(amountLabel);
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


public class TicketBookedPage extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    JLabel HeadLabel = new JLabel("THESE ARE THE TICKETS YOU HAVE BOOKED");
    
    JButton Homebutton = new JButton("HOME");
    MyTicket book = new MyTicket(5);

    MainFrontApp app;
    int loginId;
    int id;

    public TicketBookedPage(MainFrontApp app) {
        this.app = app;
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setBounds(100, 200, 1350, 100); // Adjust position and size of Panel_1
    }

    public void setLocationAndSize() {
        HeadLabel.setBounds(515, 120, 300, 30);
        Homebutton.setBounds(1410, 30, 100, 30);
        
        book.setBounds(120,190,1295,100);
        
    }

    public void addComponentsToContainer() {
        add(HeadLabel);
        add(Homebutton);
        add(book);
    }

    public void addActionEvent() {
        Homebutton.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Homebutton){
            app.showHome(this.id);
        }

    }
}