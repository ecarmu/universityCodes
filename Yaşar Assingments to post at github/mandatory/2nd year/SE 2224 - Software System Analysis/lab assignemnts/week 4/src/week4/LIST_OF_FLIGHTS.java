package week4;

import javax.swing.*;

public class LIST_OF_FLIGHTS extends JFrame{


    private JLabel ListOfFlights;
    private JPanel mainPanel;
    private JList<String> flightList;

    private Flight[] flights;

    DefaultListModel<String> model;

    public LIST_OF_FLIGHTS(Flight[] list) {

        flights = list;


        model = new DefaultListModel<String>();
        flightList.setModel(model);
        setContentPane(mainPanel);
        setTitle("Flight List");
        setSize(1000,500);

        for(int i = 0; i < list.length; i ++){
            String st;
            st = list[i].getFlight_code() + " "+ list[i].getDeparture() +" to " + list[i].getDestination();
            model.addElement(st);

        }

        setVisible(true);



    }

    public void printList(){

        for(int i = 0; i < flights.length; i ++){
            String st;
            st = flights[i].getFlight_code() + " "+ flights[i].getDeparture() +" to " + flights[i].getDestination();
            model.addElement(st);

        }
    }
}
