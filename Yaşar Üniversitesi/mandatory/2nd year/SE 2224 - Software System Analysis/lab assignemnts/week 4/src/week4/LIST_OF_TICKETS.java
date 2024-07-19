package week4;

import javax.swing.*;

public class LIST_OF_TICKETS extends JFrame {
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JList getJListOfTickets() {
        return JListOfTickets;
    }

    public JLabel getListOfTickets() {
        return ListOfTickets;
    }


    private JPanel mainPanel;
    private JList<String> JListOfTickets;
    private JLabel ListOfTickets;


    public LIST_OF_TICKETS(Ticket[] tList) {



        DefaultListModel<String> model = new DefaultListModel<String>();
        JListOfTickets.setModel(model);
        setContentPane(mainPanel);
        setTitle("Flight List");
        setSize(1000,500);

        for(int i = 0; i < tList.length; i ++){
            String st;
            st = tList[i].getFlightNumber().getText() + " "+ tList[i].getSeat_number() +" to " + tList[i].getLuggage();
            model.addElement(st);
        }

        setVisible(true);
    }
}
