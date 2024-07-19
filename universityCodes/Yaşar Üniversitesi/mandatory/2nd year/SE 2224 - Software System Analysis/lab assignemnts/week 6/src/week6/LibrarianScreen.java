package week6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianScreen extends  JFrame{
    private JButton ADDBOOKButton;
    private JButton ADDAUTHORButton;
    private JButton ADDPUBLISHERButton;
    private JButton SEARCHButton;
    private javax.swing.JPanel JPanel;

    public LibrarianScreen() {
        initScreen();
    }

    public void initScreen(){
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
        setContentPane(JPanel);
        setTitle("Librarian Screen");

        

        ADDBOOKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AddBookScreen addBookScreen = new AddBookScreen();
            }
        });
        ADDAUTHORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AddAuthorScreen authorScreen = new AddAuthorScreen();
            }
        });
        ADDPUBLISHERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AddPublisherScreen addPublisherScreen = new AddPublisherScreen();
            }
        });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            SearchScreen searchScreen = new SearchScreen();
            }
        });
    }


    public static void main(String[] args) {
        new LibrarianScreen();
    }
}
