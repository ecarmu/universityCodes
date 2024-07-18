package week10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AdminScreen {
    private JTable table;
    private javax.swing.JPanel JPanel;
    private JLabel TitleLabel;
    private JButton acceptRequestButton;
    private JButton rejectRequestButton;

    public String[][] tableStrArr;


    public AdminScreen() {
        initStart();
    }

    private void initStart() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);

        // Data to be displayed in the JTable
        String[][] data = {};

        // Column Names
        String[] columnNames = { "Ad Publishing Requests" };

        // Initializing the JTable
        table = new JTable(data, columnNames);
        table.setSize(500,500);
        table.setVisible(true);
        table.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        //jFrame.setContentPane(scrollPane);

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(acceptRequestButton);
        buttonPanel.add(rejectRequestButton);
        buttonPanel.add(scrollPane);

        buttonPanel.setVisible(true);
        jFrame.add(buttonPanel);

        jFrame.setTitle("Admin Screen");
        jFrame.setVisible(true);


        tableStrArr= FileOperations.readAndPopulateTable("requested_published_ad.txt");

        DefaultTableModel model = new DefaultTableModel(tableStrArr, columnNames);
        table.setModel(model);

        acceptRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[]={ "Ad Publishing Requests" };
                String fileName = "requested_published_ad.txt";
                updateTableToCurrent(fileName, column, true);
            }
        });
        rejectRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[]={ "Ad Publishing Requests" };
                String fileName = "requested_published_ad.txt";
                updateTableToCurrent(fileName, column, false);
            }
        });
    }

    public void updateTableToCurrent(String filePath, String[] column, boolean acceptOrReject){
        tableStrArr= FileOperations.readAndPopulateTable(filePath);

        String str_deleted = tableStrArr[0][0];

        tableStrArr = filterTable(tableStrArr, acceptOrReject);

        String fileName = "requested_published_ad.txt";
        FileOperations.deleteLineFromFile(fileName, str_deleted);

        DefaultTableModel model = new DefaultTableModel(tableStrArr, column);
        table.setModel(model);
    }


    public String[][] filterTable(String[][] tableStrArr, boolean acceptOrReject) {

        if(acceptOrReject == true)
            FileOperations.writeToFile("acceptedPublishes.txt", tableStrArr[0][0]);


        String[][] filtered_tableStrArr = new String[tableStrArr.length - 1][];

        for (int i = 0; i < filtered_tableStrArr.length; i++) {
            filtered_tableStrArr[i] = new String[tableStrArr[i+1].length];
            for (int j = 0; j < filtered_tableStrArr[i].length; j++) {
                filtered_tableStrArr[i][j] = tableStrArr[i+1][j];
            }
        }
        return filtered_tableStrArr;
    }

    public static void main(String[] args) {
        new AdminScreen();
    }
}
