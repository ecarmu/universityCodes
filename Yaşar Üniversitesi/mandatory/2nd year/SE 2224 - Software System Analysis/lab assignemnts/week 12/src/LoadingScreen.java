import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JFrame{
    private JButton loginButton;
    private JPanel panell1;
    private JLabel text;




    LoadingScreen(){
        setContentPane(panell1);
        setTitle("Loading Screen");
        setSize(200,160);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            Timer timer;
            int count = 0;
            int delay = 100;

            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        text.setText(count + "%");
                        count++;
                        if (count > 100) {
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
        });
    }




    public static void main(String[] args) {
        LoadingScreen loadingScreen = new LoadingScreen();
    }
}
