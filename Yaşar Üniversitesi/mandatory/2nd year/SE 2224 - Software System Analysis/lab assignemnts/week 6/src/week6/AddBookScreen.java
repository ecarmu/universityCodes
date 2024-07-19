package week6;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddBookScreen extends JFrame{
    private javax.swing.JPanel JPanel;
    private JLabel TitleLabel;
    private JLabel AuthorLabel;
    private JLabel ISBNLabel;
    private JLabel PublisherLabel;
    private JLabel PublicatioOfDateLabel;
    private JLabel NoOfCopiesLabel;
    private JTextField ISBNText;
    private JTextField NoOfCopiesText;
    private JComboBox AuthorComboBox;
    private JComboBox PublisherComboBox;
    private JButton ADDBOOKButton;
    private JTextField titleText;
    private JTextField PublicationOfDateText;
    private JFormattedTextField PublicationOfDateFormattedText;

    public AddBookScreen() {
        initStart();
    }

    private void initStart() {
        JFrame jFrame = new JFrame();
        setVisible(true);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);
        setTitle("Add Book");

        //bunu düzelt
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        PublicationOfDateFormattedText = new JFormattedTextField(dateFormatter);


        setContentPane(JPanel);
        // AuthorComboBox.addItem("sdfsd");

        String[] author_data = FileOperations.readFirstData("author.txt");
        AuthorComboBox.setModel(new DefaultComboBoxModel(author_data));

        String[] publisher_data = FileOperations.readFirstData("publisher.txt");
        PublisherComboBox.setModel(new DefaultComboBoxModel(publisher_data));

        ADDBOOKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] author_searched_data = FileOperations.findDataFromFile((String) AuthorComboBox.getSelectedItem(), "author.txt");
                Author author = new Author(author_searched_data[0], author_searched_data[1], author_searched_data[2]);

                String[] publisher_searched_data = FileOperations.findDataFromFile((String) PublisherComboBox.getSelectedItem(), "publisher.txt");
                Publisher publisher = new Publisher(publisher_searched_data[0], publisher_searched_data[1], publisher_searched_data[2]);


                Book book = new Book(titleText.getText(), author, ISBNText.getText(), PublicationOfDateText.getText(), publisher, Integer.parseInt(NoOfCopiesText.getText()));
                if(!FileOperations.isDataInFileForAddingBookAlreadyExists(book.getTitle(), book.getAuthor().getName(), book.getPublisher().getName(), "book.txt")){
                    Librarian.books.add(book);
                    FileOperations.writeToFile(book, "book.txt");
                    FileOperations.reWriteToFile("book.txt");
                }else{
                    FileOperations.booksIncreaseAvailabilityReWriteBooksFile(book.getTitle(), book.getAuthor().getName(), book.getPublisher().getName(), "book.txt", NoOfCopiesText.getText());
                }

            }
        });
    }

    public static void main(String[] args) {
        new AddBookScreen();
    }
}
