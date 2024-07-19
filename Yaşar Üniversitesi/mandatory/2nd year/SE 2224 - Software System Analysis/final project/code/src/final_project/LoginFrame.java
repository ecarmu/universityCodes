package final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class LoginFrame {
    private JTextField usernameTextArea;
    private JPasswordField passwordTextArea;
    private JButton LOGINButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel LOGIN_TitleLabel;

    LoginFrame(){
        initScreen();
    }

    public void initScreen() {


        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();

        jPanel1.add(LOGIN_TitleLabel);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel3.add(usernameLabel);
        jPanel3.add(usernameTextArea);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
        jPanel4.add(passwordLabel);
        jPanel4.add(passwordTextArea);

        JPanel jPanel5 = new JPanel();
        jPanel5.add(LOGINButton);




        jFrame.add(jPanel1);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);


        jFrame.setTitle("Main Screen");

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String matchingLine = FileOperations.isInUserFile(usernameTextArea.getText(), passwordTextArea.getPassword());
                if(matchingLine != null){

                    System.out.println("şifre bulundu");

                    JOptionPane.showMessageDialog(null, "Hesap bulundu! Log in başarılı!");

                    new MainFrame();

                    jFrame.setVisible(false);
                    jFrame.dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null, "Hesap bulunamadı! Log in başarısız!");
                    System.out.println("şifre yok");
                }

            }
        });


        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
