package week4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends  JFrame{
    private JButton EXITButton;
    private JButton CREATEFLIGHTButton;
    private JButton BUYTICKETButton;
    private JButton SHOWFLIGHTSButton;
    private JButton SHOWTICKETSButton;
    private JPanel mainPanel;

    public static Flight flight = new Flight();
    public static Ticket ticket = new Ticket();



    public Main(){
        setContentPane(mainPanel);
        setTitle("Main Panel");
        setSize(1000,500);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);


        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        BUYTICKETButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.setVisible(true);
            }
        });
        CREATEFLIGHTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flight.setVisible(true);
            }
        });
        SHOWFLIGHTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LIST_OF_FLIGHTS list_of_flights = new LIST_OF_FLIGHTS(FlightController.getFlightList());

            }
        });
        SHOWTICKETSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LIST_OF_TICKETS list_of_tickets = new LIST_OF_TICKETS(TicketController.getTicketList());


            }
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        flight.setVisible(false);
        ticket.setVisible(false);
    }
}
