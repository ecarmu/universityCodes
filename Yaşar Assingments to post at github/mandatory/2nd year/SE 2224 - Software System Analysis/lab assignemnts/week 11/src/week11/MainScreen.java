package week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainScreen {
    private JTextField EmailText;
    private JButton loginAsCustomerButton;
    private JButton loginAsDoctorButton;
    private JButton loginAsAdminButton;
    private JButton createAccountButton;
    private JLabel LoginLabel;
    private JLabel EmailLabel;
    private JLabel PasswordLabel;
    private javax.swing.JPanel JPanel;
    private JPasswordField PasswordText;

    public MainScreen() {
        initScreen();
    }

    public void initScreen() {



        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();

        jPanel1.add(LoginLabel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel2.add(EmailLabel);
        jPanel2.add(EmailText);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel3.add(PasswordLabel);
        jPanel3.add(PasswordText);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel4.add(PasswordLabel);
        jPanel4.add(PasswordText);

        JPanel jPanel5 = new JPanel();
        jPanel5.add(loginAsCustomerButton);

        JPanel jPanel6 = new JPanel();
        jPanel6.add(loginAsDoctorButton);

        JPanel jPanel7 = new JPanel();
        jPanel7.add(loginAsAdminButton);

        JPanel jPanel8 = new JPanel();
        jPanel8.add(createAccountButton);


        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);
        jFrame.add(jPanel7);
        jFrame.add(jPanel8);

        jFrame.setTitle("Main Screen");

        loginAsCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String matchingLine = FileOperations.isInCustomerFile(EmailText.getText(), PasswordText.getPassword());
                if(matchingLine != null){
                    try {
                        new CustomerScreen(Patient.createPatientFromLine(matchingLine));
                        System.out.println(Patient.createPatientFromLine(matchingLine));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                }

            }
        });
        loginAsDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String matchingLine = FileOperations.isInDoctorFile(EmailText.getText(), PasswordText.getPassword());
                if(matchingLine != null){
                    try {
                        new DoctorScreen(Doctor.createDoctorFromLine(matchingLine));
                        System.out.println(Doctor.createDoctorFromLine(matchingLine));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        loginAsAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if(FileOperations.isInAdminFile()) {
                   new AdminScreen();
               }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountScreen();
            }
        });

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
