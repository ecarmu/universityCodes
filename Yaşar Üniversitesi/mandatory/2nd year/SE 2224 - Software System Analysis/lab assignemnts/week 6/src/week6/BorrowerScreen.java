package week6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BorrowerScreen extends JFrame{
    private JPanel panel1;
    private JTextField BorrowerNameText;
    private JTextField BookNameText;
    private JButton GIVEButton;
    private JTable table;
    private JScrollPane scrollPane;

    public String[][] tableStrArr;

    public BorrowerScreen() {
        initStart();
    }

    private void initStart() {
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
        setContentPane(panel1);

        // Data to be displayed in the JTable
        String[][] data = FileOperations.readAndPopulateTable("book.txt");

        // Column Names
        String[] columnNames = { "Book Name", "Author", "ISBN", "Publisher", "Publication Date", "Available" };

        // Initializing the JTable
        table = new JTable(data, columnNames);
        table.setSize(500,500);
        table.setVisible(true);
        table.setLayout(new FlowLayout());
        setTitle("Borrow Book");

        scrollPane.setViewportView(table);

        GIVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOperations.booksReduceAvailabilityReWriteBooksFile(BookNameText.getText(),"book.txt");
                updateTableToCurrent("book.txt", BookNameText.getText());


                if(!FileOperations.isDataInFile(BorrowerNameText.getText(), "borrower.txt")){
                    Random random = new Random();
                    int rand_number = random.nextInt(1, 1000);

                    Borrower borrower = new Borrower(BorrowerNameText.getText(), BorrowerNameText.getText() + rand_number + "@gmail.com");
                    borrower.borrowBook(FileOperations.borrowSelectedBooks( BookNameText.getText(), "book.txt"));
                    FileOperations.writeToFile(borrower, "borrower.txt");
                    Librarian.borrowers.add(borrower);
                }else{
                    for(Book book: FileOperations.borrowSelectedBooks( BookNameText.getText(), "book.txt")){
                        FileOperations.addBookToLine(BorrowerNameText.getText(), book, "borrower.txt");
                        Librarian.findBorrower(BorrowerNameText.getText(), book);
                    }

                }



            }
        });
    }

    public void updateTableToCurrent(String filePath, String bookNameSearched){
        tableStrArr= FileOperations.readAndPopulateTable(filePath);
        String column[]={ "Book Name", "Author", "ISBN", "Publisher", "Publication Date", "Available" };
        JTable jt=new JTable(tableStrArr,column);



        jt.setSize(500, 500);
        jt.setVisible(true);

        jt.setLayout(new FlowLayout());

        scrollPane.setViewportView(jt);
    }



    public static void main(String[] args) {
        new BorrowerScreen();
    }
}
