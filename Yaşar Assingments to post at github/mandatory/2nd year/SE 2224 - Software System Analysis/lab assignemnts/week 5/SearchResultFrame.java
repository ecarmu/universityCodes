package week5;

import javax.swing.*;

public class SearchResultFrame extends  JFrame{
    private JLabel Name;
    private JLabel Birthdate;
    private JLabel Position;
    private JLabel Salary;
    private JLabel IDNo;
    private JLabel IDNoText;
    private JLabel NameText;
    private JLabel BirthdateText;
    private JLabel PositionText;
    private JLabel SalaryText;
    private JLabel AgeText;
    private JLabel Age;
    private javax.swing.JPanel JPanel;

    public SearchResultFrame(){

    }

    public SearchResultFrame(String id, String firstName, String lastName, String birthMonth, String birthDay, String birthYear, String position, String salary) {
        setContentPane(JPanel);
        setTitle("Search Result");
        setSize(1000,500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);

        IDNoText.setText(id);
        NameText.setText(firstName + " " + lastName);
        BirthdateText.setText(birthMonth + " " + birthDay + " " + birthYear);
        AgeText.setText( Integer.toString(2023 - Integer.parseInt(birthYear)) );
        PositionText.setText(position);
        SalaryText.setText(salary);
    }

    public static void main(String[] args) {
        SearchResultFrame searchResultFrame = new SearchResultFrame();
    }
}
