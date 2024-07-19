package week11;

import week10.AdminScreen;
import week10.CarOwnerScreen;
import week10.ShopWindowScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CreateAccountScreen {
    private JTextField EmailDoctorText;
    private JTextField QualificationsDoctorText;
    private JPasswordField PasswordDoctorText;
    private JButton createAccountAsDoctorButton;
    private JFormattedTextField DateOfBirthCustomerText;
    private JTextField EmailCustomerText;
    private JPasswordField PasswordCustomerText;
    private JButton createAccountAsCustomerButton1;
    private JTextField DepartmentDoctorText;
    private JLabel EmailDoctorLabel;
    private JLabel CreateAccountAsCustomerLabel;
    private JLabel EmailCustomerLabel;
    private JLabel PasswordDoctorLabel;
    private JLabel PasswordCustomerLabel;
    private JLabel QualificationsDoctorLabel;
    private JLabel DepartmentDoctorLabel;
    private javax.swing.JPanel JPanel;
    private JLabel DateOfBirthCustomerLabel;
    private JTextField IDText;
    private JTextField NameText;
    private JTextField SurnameText;
    private JTextField MobileText;
    private JTextField CountryText;
    private JLabel GeneralInfoLabel;
    private JLabel IDLabel;
    private JLabel NameLabel;
    private JLabel SurnameLabel;
    private JLabel MobileLabel;
    private JLabel CountryLabel;
    private JLabel CreateAccountAsDoctorLabel;

    public CreateAccountScreen() {
        initScreen();
    }

    public void initScreen() {

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setLayout(new GridLayout(6, 3));


        JPanel jPanel1 = new JPanel();

        jPanel1.add(GeneralInfoLabel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout((new GridLayout(3, 2)));
        jPanel2.add(IDLabel);
        jPanel2.add(IDText);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2)));
        jPanel3.add(NameLabel);
        jPanel3.add(NameText);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2)));
        jPanel4.add(SurnameLabel);
        jPanel4.add(SurnameText);

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout((new GridLayout(3, 2)));
        jPanel5.add(MobileLabel);
        jPanel5.add(MobileText);

        JPanel jPanel6 = new JPanel();
        jPanel6.setLayout((new GridLayout(3, 2)));
        jPanel6.add(CountryLabel);
        jPanel6.add(CountryText);

        JPanel jPanel7 = new JPanel();
        jPanel7.add(CreateAccountAsDoctorLabel);

        JPanel jPanel8 = new JPanel();
        jPanel8.setLayout((new GridLayout(3, 2)));
        jPanel8.add(EmailDoctorLabel);
        jPanel8.add(EmailDoctorText);

        JPanel jPanel9 = new JPanel();
        jPanel9.setLayout((new GridLayout(3, 2)));
        jPanel9.add(QualificationsDoctorLabel);
        jPanel9.add(QualificationsDoctorText);

        JPanel jPanel10 = new JPanel();
        jPanel10.setLayout((new GridLayout(3, 2)));
        jPanel10.add(DepartmentDoctorLabel);
        jPanel10.add(DepartmentDoctorText);

        JPanel jPanel11 = new JPanel();
        jPanel11.setLayout((new GridLayout(3, 2)));
        jPanel11.add(PasswordDoctorLabel);
        jPanel11.add(PasswordDoctorText);


        JPanel jPanel12 = new JPanel();
        jPanel12.add(createAccountAsDoctorButton);

        JPanel jPanel13 = new JPanel();
        jPanel13.add(CreateAccountAsCustomerLabel);

        JPanel jPanel14 = new JPanel();
        jPanel14.setLayout((new GridLayout(3, 2)));
        jPanel14.add(EmailCustomerLabel);
        jPanel14.add(EmailCustomerText);

        JPanel jPanel15 = new JPanel();
        jPanel15.setLayout((new GridLayout(3, 2)));
        jPanel15.add(DateOfBirthCustomerLabel);
        jPanel15.add(DateOfBirthCustomerText);

        JPanel jPanel16 = new JPanel();
        jPanel16.setLayout((new GridLayout(3, 2)));
        jPanel16.add(PasswordCustomerLabel);
        jPanel16.add(PasswordCustomerText);

        JPanel jPanel17 = new JPanel();
        jPanel17.add(createAccountAsCustomerButton1);




        //jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);
        jFrame.add(jPanel7);
        jFrame.add(jPanel8);
        jFrame.add(jPanel9);
        jFrame.add(jPanel10);
        jFrame.add(jPanel11);
        jFrame.add(jPanel12);
        //jFrame.add(jPanel13);
        jFrame.add(jPanel14);
        jFrame.add(jPanel15);
        jFrame.add(jPanel16);
        jFrame.add(jPanel17);

        jFrame.setTitle("Main Screen");

        createAccountAsDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor doctor = new Doctor(
                        Integer.parseInt(IDText.getText()),
                        NameText.getText(),
                        SurnameText.getText(),
                        EmailDoctorText.getText(),
                        PasswordDoctorText.getPassword(),
                        Integer.parseInt(MobileText.getText()),
                        CountryText.getText(),
                        QualificationsDoctorText.getText(),
                        DepartmentDoctorText.getText()
                );

                if(FileOperations.isInDoctorFile(EmailCustomerText.getText(), PasswordCustomerText.getPassword()) == null){
                    doctor.addDoctor();
                    JOptionPane.showMessageDialog(null, "Doctor Account created successfully", "You can now log in", JOptionPane.YES_OPTION);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something is wrong", "Customer account creation failed", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        createAccountAsCustomerButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(DateOfBirthCustomerText.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Patient patient = new Patient(
                        Integer.parseInt(IDText.getText()),
                        NameText.getText(),
                        SurnameText.getText(),
                        EmailCustomerText.getText(),
                        PasswordCustomerText.getPassword(),
                        Integer.parseInt(MobileText.getText()),
                        CountryText.getText(),
                        date
                );
/*
                Patient patient = new Patient(
                        Integer.parseInt(IDText.getText()),
                        NameText.getText(),
                        SurnameText.getText(),
                        EmailCustomerText.getText(),
                        PasswordCustomerText.getPassword(),
                        Integer.parseInt(MobileText.getText()),
                        CountryText.getText(),
                        (Date) DateOfBirthCustomerText.getValue()
                );*/

                if(FileOperations.isInCustomerFile(EmailCustomerText.getText(), PasswordCustomerText.getPassword()) == null){
                    patient.addPatient();
                    JOptionPane.showMessageDialog(null, "Customer Account created successfully", "You can now log in", JOptionPane.YES_OPTION);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Something is wrong", "Customer account creation failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        new CreateAccountScreen();
    }
}
