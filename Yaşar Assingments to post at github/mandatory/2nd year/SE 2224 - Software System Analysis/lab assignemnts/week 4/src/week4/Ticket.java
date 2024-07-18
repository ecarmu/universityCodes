package week4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ticket extends JFrame{
    public int CAPACITY = 30;
    public int EXTRA = 20;
    public int ECONOMY = 500;
    public int BUSINESS = 1000;
    public int FIRST = 1500;


    public int getLuggage() {
        return luggage;
    }

    public JLabel getFlightNumber() {
        return FlightNumber;
    }


    private Flight flight;
    private int price;
    private int seat_number;
    private int luggage;
    private JPanel mainPanel;
    private JButton Button;
    private JTextField FlightNumberText;
    private JTextField SeatNumberText;
    private JTextField LuggageText;


    private JLabel Luggage;
    private JLabel SeatNumber;
    private JLabel FlightNumber;
    private int flightNumber;



    public Ticket(Flight flight, ArrayList<Integer> info){
        this.flight = flight;
        seat_number = info.get(0);
        //price = info.get(1);
        luggage = info.get(1);
        flightNumber = info.get(2);
    }



    @Override
    public String toString() {
        return "Ticket{" +
                "CAPACITY=" + CAPACITY +
                ", EXTRA=" + EXTRA +
                ", ECONOMY=" + ECONOMY +
                ", BUSINESS=" + BUSINESS +
                ", FIRST=" + FIRST +
                ", flight=" + flight +
                ", price=" + price +
                ", seat_number=" + seat_number +
                ", mainPanel=" + mainPanel +
                '}';
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }




    public void setPrice(int seat_number, int luggage_money) {

       /* price calculated based on seat number+ luggage money
    if seat number between 0-20 it will cost 1500, between 20-70 1000 and 70-120 500
    if luggage is greater than 30, EXTRA will be added for every 3 kg*/

        if(seat_number >= 0 && seat_number <= 20)
            this.price = FIRST + luggage_money;

        else if(seat_number > 20 && seat_number <= 70)
            this.price = BUSINESS + luggage_money;

        else if(seat_number > 70 && seat_number <= 120)
            this.price = ECONOMY + luggage_money;

        if(luggage > CAPACITY) {
            if ((price % CAPACITY) / 3 > 0)
                this.price += EXTRA * ((price % CAPACITY) / 3);
        }
    }

    public int getSeat_number() {
        return seat_number;
    }


    public Ticket() {
        setTitle("Create Ticket");
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(1000,500);
        setVisible(true);


        Button.addActionListener(new ActionListener() {
            TicketController ticketController = new TicketController();

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> ticketArrayList = new ArrayList<>();



                ticketArrayList.add(Integer.parseInt(SeatNumberText.getText()));

                ticketArrayList.add(Integer.parseInt(LuggageText.getText()));

                ticketArrayList.add(Integer.parseInt(FlightNumberText.getText()));


                Ticket ticket = new Ticket(new Flight(false), ticketArrayList);
                ticketController.create(ticket);
            }
        });
    }
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
    }
}
