package week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreen {
    private JButton viewProfileButton;
    private JButton editProfileButton;
    private JButton viewAppointmentButton;
    private JButton modifyAppointmentButton;
    private JButton cancelAppointmentButton;
    private JButton makeEnquiryButton;
    private JLabel CustomerScreenLabel;
    private JLabel ProfileLabel;
    private JLabel AppointmentLabel;
    private JLabel EnquiryLabel;
    private JButton makeAppointmentButton;

    public Patient patient;

    /*public CustomerScreen(){
        initScreen();
    }*/

    public CustomerScreen(Patient patient) {
        this.patient = patient;
        initScreen(patient);
    }

    public void initScreen(Patient patient) {

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();

        jPanel1.add(CustomerScreenLabel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel2.add(ProfileLabel);
        jPanel2.add(AppointmentLabel);

        JPanel jPanel7 = new JPanel();
        jPanel7.add(makeAppointmentButton);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel3.add(viewProfileButton);
        jPanel3.add(viewAppointmentButton);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel4.add(editProfileButton);

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel5.add(EnquiryLabel);

        JPanel jPanel6 = new JPanel();
        jPanel6.add(makeEnquiryButton);


        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel7);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);

        jFrame.setTitle("Main Screen");

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            new RequestedOperationScreen(patient, "view profile");
            }
        });
        viewAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(patient, "view appointment");
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(patient, "edit profile");
            }
        });

        makeEnquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(patient, "make enquiry");
            }
        });

        makeAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("make appointment button worked");
                new RequestedOperationScreen(patient, "make appointment");
            }
        });

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        //new CustomerScreen();
    }
}
