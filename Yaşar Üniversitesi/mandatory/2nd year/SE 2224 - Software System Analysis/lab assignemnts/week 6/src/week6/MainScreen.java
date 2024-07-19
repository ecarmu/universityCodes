package week6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    private JButton LIBRARYLOGINButton;
    private JButton BORROWERLOGINButton;

    public MainScreen() {
        initScreen();
    }

    public void initScreen() {

        LIBRARYLOGINButton = new JButton("Library Login");
        BORROWERLOGINButton = new JButton("Borrower Login");

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();

        jPanel1.add(LIBRARYLOGINButton);
        jPanel2.add(BORROWERLOGINButton);

        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        setTitle("Main Screen");

        LIBRARYLOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrarianScreen librarianScreen = new LibrarianScreen();
            }
        });
        BORROWERLOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrowerScreen borrowerScreen = new BorrowerScreen();
            }
        });


        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
