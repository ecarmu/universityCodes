package week6;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPublisherScreen extends JFrame{
    private JPanel JPanel;
    private JTextField PublisherNameText;
    private JTextField emailText;
    private JTextField addressText;
    private JButton ADDPUBLISHERButton;
    private JLabel emailTextLabel;
    private JLabel addressLabel;
    private JLabel PublisherNameLabel;

    public AddPublisherScreen() {
        initScreen();
    }

    private void initScreen() {
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
        setContentPane(JPanel);
        setTitle("Add Publisher");
        ADDPUBLISHERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Publisher publisher = new Publisher(PublisherNameText.getText(), emailText.getText(), addressText.getText());
                Librarian.publishers.add(publisher);
                FileOperations.writeToFile(publisher, "publisher.txt");
            }
        });
    }

    public static void main(String[] args) {
        new AddPublisherScreen();
    }
}
