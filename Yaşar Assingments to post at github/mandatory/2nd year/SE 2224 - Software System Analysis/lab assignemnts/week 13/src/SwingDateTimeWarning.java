import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SwingDateTimeWarning {
    JFrame frame;
    LocalDateTime enteredDateTime;
    Timer timer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SwingDateTimeWarning window = new SwingDateTimeWarning();
            window.frame.setVisible(true);
        });
    }

    SwingDateTimeWarning() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        enteredDateTime = LocalDateTime.parse("2023-05-02 13:36:50", formatter);

        frame = new JFrame("DateTime Warning");
        frame.setBounds(100, 100, 400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                LocalDateTime currentDateTime = LocalDateTime.now();
                if (currentDateTime.isAfter(enteredDateTime)) {
                    try {
                        /*Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/filename.wav")));
                        clip.start();*/
                        JOptionPane.showMessageDialog(null, "WARNING: The entered date and time has been reached", "WARNING", JOptionPane.INFORMATION_MESSAGE);
                        timer.cancel();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
