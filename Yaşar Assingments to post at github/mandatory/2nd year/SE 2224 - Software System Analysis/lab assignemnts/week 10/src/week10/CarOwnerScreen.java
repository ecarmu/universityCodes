package week10;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarOwnerScreen extends JFrame{
    private JPanel JPanel;
    private JTextArea CarAdInfoTextArea;
    private JButton postAdPublishingRequestButton;
    private JLabel EnterYourAddLabel;


    public CarOwnerScreen() {
        initScreen();
    }

    public void initScreen() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Car Owner Ad Publish Screen");

        JPanel jPanel2 = new JPanel();
        jPanel2.add(EnterYourAddLabel);
        jFrame.add(jPanel2);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

        CarAdInfoTextArea = new JTextArea(10, 30);
        CarAdInfoTextArea.setLineWrap(true);
        CarAdInfoTextArea.setWrapStyleWord(true);
        jPanel1.add(new JScrollPane(CarAdInfoTextArea));

        postAdPublishingRequestButton = new JButton("Publish Ad");
        postAdPublishingRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "requested_published_ad.txt";
                FileOperations.writeToFile(fileName, FileOperations.replaceNewlines(CarAdInfoTextArea.getText()));
            }
        });
        jPanel1.add(postAdPublishingRequestButton);

        jFrame.add(jPanel1);
        jFrame.pack();
        jFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new CarOwnerScreen();
    }
}
