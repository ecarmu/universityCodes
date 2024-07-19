package week6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SearchScreen extends JFrame{
    private javax.swing.JPanel JPanel;
    private JLabel SearchedTextLabel;
    private JTextField SearchedTextText;
    private JButton findBookByTitleButton;
    private JButton findBookByAuthorButton;
    private JButton findBorrowerByNameButton;
    private JButton findBorrowerByEmailButton;
    private JTable table;
    private JScrollPane scrollPane;

    public String[][] tableStrArr;

    public SearchScreen() {
        initStart();
    }

    private void initStart() {
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
        setContentPane(JPanel);

        // Data to be displayed in the JTable
        String[][] data = {};

        // Column Names
        String[] columnNames = { "Book Name", "Author", "ISBN", "Publisher", "Publication Date", "Available" };

        // Initializing the JTable
        table = new JTable(data, columnNames);
        table.setSize(500,500);
        table.setVisible(true);
        table.setLayout(new FlowLayout());

        scrollPane.setViewportView(table);

        findBookByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[]={ "Book Name", "Author", "ISBN", "Publisher", "Publication Date", "Available" };
                updateTableToCurrent("book.txt", SearchedTextText.getText(), 0, column);
            }
        });
        findBorrowerByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[] = {"Name", "Email", "Books"};
                updateTableToCurrent("borrower.txt", SearchedTextText.getText(), 0, column);
            }
        });
        findBookByAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[]={ "Book Name", "Author", "ISBN", "Publisher", "Publication Date", "Available" };
                updateTableToCurrent("book.txt", SearchedTextText.getText(), 1, column);
            }
        });
        findBorrowerByEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column[] = {"Name", "Email", "Books"};
                updateTableToCurrent("borrower.txt", SearchedTextText.getText(), 1, column);
            }
        });
    }

    public void updateTableToCurrent(String filePath, String bookNameSearched, int index, String[] column){
        tableStrArr= FileOperations.readAndPopulateTable(filePath);
        tableStrArr = filterTable(tableStrArr, bookNameSearched, index);
        JTable jt=new JTable(tableStrArr,column);



        jt.setSize(500, 500);
        jt.setVisible(true);

        jt.setLayout(new FlowLayout());

        scrollPane.setViewportView(jt);
    }

    public String[][] filterTable(String[][] tableStrArr, String bookNameSearched, int index) {
        ArrayList<ArrayList<String>> filterProcessedTable = new ArrayList<>();

        int k = 0;
        System.out.println("tableStrArr uzunluk: " + tableStrArr.length);
        for(String[] strings: tableStrArr ){
            System.out.println(k + ". deneme -> " + "uzunluk: " + strings.length + " -> metin: " + strings[index]);
            if(Objects.equals(bookNameSearched, strings[index])){
                ArrayList<String> tmp_arr = new ArrayList<>();
                for (String str: strings) {
                    tmp_arr.add(str);
                }
                filterProcessedTable.add(tmp_arr);
            }
            k++;
        }

        String[][] filtered_tableStrArr = new String[filterProcessedTable.size()][];

        for (int i = 0; i < filtered_tableStrArr.length; i++) {
            filtered_tableStrArr[i] = new String[filterProcessedTable.get(i).size()];
            for (int j = 0; j < filtered_tableStrArr[i].length; j++) {
                filtered_tableStrArr[i][j] = filterProcessedTable.get(i).get(j);
            }
        }
        return filtered_tableStrArr;
    }


    public static void main(String[] args) {
        new SearchScreen();
    }
}
