package week4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Flight extends JFrame{
    private static int SEAT_NUMBER = 120;
    private String flight_code;
    private String departure;
    private String destination;
    private boolean canceled;
    private int[] seats;



    private JButton Button;

    private JLabel DepartureCity;
    private JLabel DestinationCity;

    private JTextField flightCodeText;
    private JTextField DepartureCityText;
    private JTextField DestinationCityText;

    Flight(ArrayList<String> info){
        canceled = false;
        seats = new int[SEAT_NUMBER];

        flight_code = info.get(0);
        departure = info.get(1);
        destination = info.get(2);

    }

    public Flight(String depCity, String destCity) {
        this.departure = depCity;
        this.destination = destCity;
    }

    public Flight(boolean b) {
        canceled = b;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight_code='" + flight_code + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", canceled=" + canceled +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }


    public String getFlight_code() {
        return flight_code;
    }



    public String getDeparture() {
        return departure;
    }



    public String getDestination() {
        return destination;
    }



    public boolean isCanceled() {
        return canceled;
    }



    private JPanel mainPanel;
    private JLabel FlightCode;


    public Flight(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(1000,500);
        setVisible(true);
        setTitle("Create Flight");


        Button.addActionListener(new ActionListener() {
            FlightController flightController = new FlightController();

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list = new ArrayList<>();
                list.add(flightCodeText.getText());
                list.add(DepartureCityText.getText());
                list.add(DestinationCityText.getText());

                System.out.println("Flight Code: " + flightCodeText.getText());

                Flight flight = new Flight(list);

                flightController.create(flight);



            }
        });
    }


    public static void main(String[] args) {
        Flight flight = new Flight();
    }

}
