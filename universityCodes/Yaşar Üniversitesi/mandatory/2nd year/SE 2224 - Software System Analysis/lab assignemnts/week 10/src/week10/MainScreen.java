package week10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainScreen {
    private JTextField UsernameText;
    private JTextField PasswordText;
    private JButton iAmAdminButton;
    private JLabel LoginScreenLabel;
    private JLabel Login_asCarOwnerLabel;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JLabel Enter_asAdminLabel;
    private JPanel JPanell;
    private JButton loginButton;
    private JButton createAccountButton;
    private JButton goToPublishedAdsButton;
    private JLabel viewShopWindowLabel;
    private JPanel AdminLoginPanel;
    private JPanel carOwnerPanel;

    public MainScreen() {
        initScreen();
    }

    public void initScreen() {



        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();

        jPanel1.add(LoginScreenLabel);

        JPanel jPanel2 = new JPanel();

        jPanel2.add(Login_asCarOwnerLabel);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel3.add(UsernameLabel);
        jPanel3.add(UsernameText);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel4.add(PasswordLabel);
        jPanel4.add(PasswordText);

        JPanel jPanel5 = new JPanel();
        jPanel5.add(loginButton);
        jPanel5.add(createAccountButton);

        JPanel jPanel6 = new JPanel();
        jPanel6.add(Enter_asAdminLabel);
        jPanel6.add(iAmAdminButton);

        JPanel jPanel7 = new JPanel();
        jPanel7.add(viewShopWindowLabel);
        jPanel7.add(goToPublishedAdsButton);

        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);
        jFrame.add(jPanel7);

        jFrame.setTitle("Main Screen");

        iAmAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminScreen adminScreen = new AdminScreen();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "login.txt";
                String username = UsernameText.getText();
                String password = PasswordText.getText();

                if (FileOperations.checkLogin(filename, username, password) ) {
                    // Login successful
                    CarOwnerScreen carOwnerScreen = new CarOwnerScreen();
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = "login.txt";
                String username = UsernameText.getText();
                String password = PasswordText.getText();

                if (!FileOperations.checkAccountExists(filename, username, password) && !Objects.equals(username, "") && username!= null && !Objects.equals(password, "") && password!= null) {
                    // Create account successful

                    FileOperations.writeToLogin(filename, username, password);
                    JOptionPane.showMessageDialog(null, "Account created successfully", "You can now log in", JOptionPane.YES_OPTION);
                } else {
                    // Create account fail
                    JOptionPane.showMessageDialog(null,"For some reason account creation failed. Probably reason of that is this account already exists!" , "Account creation fail", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goToPublishedAdsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopWindowScreen();
            }
        });

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
