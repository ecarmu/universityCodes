package week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen {

    private JButton addUserButton;
    private JButton deleteUserButton;
    private JButton editUserInformationButton;
    private JButton replyEnquiriesButton;
    private JButton deletePastAppointmentsButton;

    public AdminScreen() {

        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jFrame.setVisible(true);

        jPanel.add(addUserButton);
        jPanel.add(deleteUserButton);
        jPanel.add(editUserInformationButton);
        jPanel.add(replyEnquiriesButton);
        jPanel.add(deletePastAppointmentsButton);

        jFrame.add(jPanel);


        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountScreen();
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(new Object(), "delete user");
            }
        });
        editUserInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(new Object(), "edit user");
            }
        });
        replyEnquiriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(new Object(), "reply enquiry");
            }
        });
        deletePastAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestedOperationScreen(new Object(), "delete past appointment");
            }
        });


    }
}
