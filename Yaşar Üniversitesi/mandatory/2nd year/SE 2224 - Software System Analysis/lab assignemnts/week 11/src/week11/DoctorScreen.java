package week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorScreen {
    private JLabel CustomerScreenLabel;
    private JLabel ProfileLabel;
    private JButton viewProfileButton;
    private JButton editProfileButton;
    private JLabel AppointmentLabel;
    private JButton viewAppointmentButton;
    private JButton modifyAppointmentButton;
    private JButton cancelAppointmentButton;
    private JButton addTimeSlotButton;
    private JLabel EnquiryLabel;
    private JButton makeEnquiryButton;

    public DoctorScreen(Doctor doctor) {
        initScreen(doctor);
    }

    /*public DoctorScreen(){
        initScreen();
    }*/

    public void initScreen(Doctor doctor) {

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();

        jPanel1.add(CustomerScreenLabel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel2.add(ProfileLabel);
        jPanel2.add(AppointmentLabel);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel3.add(viewProfileButton);
        jPanel3.add(viewAppointmentButton);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel4.add(editProfileButton);

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout((new GridLayout(3, 2, 3, 3)));

        JPanel jPanel6 = new JPanel();
        jPanel6.add(addTimeSlotButton);


        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);

        jFrame.setTitle("Main Screen");

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new RequestedOperationScreen(doctor, "view profile");
            }
        });
        viewAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(doctor, "view appointment");
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(doctor, "edit profile");
            }
        });

        addTimeSlotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(doctor, "add time slot");
            }
        });

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        //new DoctorScreen();
    }
}
