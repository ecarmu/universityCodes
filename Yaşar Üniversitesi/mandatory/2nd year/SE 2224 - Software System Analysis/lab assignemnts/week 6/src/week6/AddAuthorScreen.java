package week6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAuthorScreen extends JFrame{
    private JTextField authorNameText;
    private JTextField emailText;
    private JTextField biographyText;
    private JPanel JPanel;
    private JLabel emailLabel;
    private JLabel biographyLabel;
    private JButton ADDAUTHORButton;
    private javax.swing.JScrollPane JScrollPane;
    private JLabel authorNameLabel;
    private JPanel JPanel3;
    private JPanel JPanel2;
    private JPanel JPanel1;
    private JPanel JPanel4;

    public AddAuthorScreen() {
        initScreen();
    }

    public void initScreen(){
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(1000, 1000);
        setContentPane(JPanel);
        setTitle("Add Author");


        ADDAUTHORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author author = new Author(authorNameText.getText(), emailText.getText(), biographyText.getText());
                Librarian.authors.add(author);
                FileOperations.writeToFile(author, "author.txt");
            }
        });
    }

    public static void main(String[] args) {
        new AddAuthorScreen();
    }
}
