package week10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShopWindowScreen {
    private JTable table;
    private JPanel panel1;

    public String[][] tableStrArr;

    public ShopWindowScreen() {
        initStart();
    }

    private void initStart() {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);

        // Data to be displayed in the JTable
        String[][] data = {};

        // Column Names
        String[] columnNames = {"Ad Publishing Requests"};

        // Initializing the JTable
        table = new JTable(data, columnNames);
        table.setSize(500, 500);
        table.setVisible(true);
        table.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        jFrame.setContentPane(scrollPane);

        jFrame.setTitle("Admin Screen");
        jFrame.setVisible(true);

        String fileName = "acceptedPublishes.txt";
        viewCurrentAds(fileName, columnNames);
    }

    public void viewCurrentAds(String filePath, String[] column){
        tableStrArr= FileOperations.readAndPopulateTable(filePath);




        DefaultTableModel model = new DefaultTableModel(tableStrArr, column);
        table.setModel(model);
    }

    public static void main(String[] args) {
        new ShopWindowScreen();
    }
}
