package FrontEnd;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BackEnd.Db;

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
    JButton cancelButton = new JButton("CANCEL");
    int loginId, flightId;
    int ammont;

    MainFrontApp app;
    Db db;

    public BookingPage(MainFrontApp app) {
        this.app = app;
        this.db = Db.getInst();
        setLayout(null);
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setVisible(true);
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
        cancelButton.setBounds(710, 640, 150, 30);

    }

    public void UpdatePrice(int ammont) {
        priceLabel.setText("Price = " + ammont);

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
        add(cancelButton);
    }

    public void addActionEvent() {
        bookButton.addActionListener(this);
        checkButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            if (ecoTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " Enter number of economey tickets ");
                return;
            }
            if (bussTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " Enter number of business tickets");
                return;
            }
            int economy;
            try {
                economy = Integer.parseInt(ecoTextField.getText());
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "enter economoey seats in numaric form");
                return;
            }
            int business;
            try {
                business = Integer.parseInt(bussTextField.getText());
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "enter business seats in numaric form");
                return;
            }
            if(db.isSeatAvailable(flightId, economy, business) == false) {
                JOptionPane.showMessageDialog(this, "No seats available");
                return;
            }
            app.showPayment(loginId, flightId, economy, business);

        }
        if (e.getSource() == checkButton) {
            if (ecoTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " Enter number of economey tickets ");
                return;
            }
            if (bussTextField.getText() == "") {
                JOptionPane.showMessageDialog(this, " Enter number of business tickets");
                return;
            }
            int economy;
            try {
                economy = Integer.parseInt(ecoTextField.getText());
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "enter economoey seats in numaric form");
                return;
            }
            int business;
            try {
                business = Integer.parseInt(bussTextField.getText());
            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(this, "enter business seats in numaric form");
                return;
            }
            if(db.isSeatAvailable(flightId, economy, business) == false) {
                JOptionPane.showMessageDialog(this, "No seats available");
                return;
            }
            int ammont = db.calculateAmount(flightId, economy, business);
            UpdatePrice(ammont);

        }
        if (e.getSource() == cancelButton) {
            app.showHome(loginId);
        }

    }
}