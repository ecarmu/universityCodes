package week5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddEmployeeFrame extends JFrame{
    private JPanel addEmployeePanel;
    private JScrollBar scrollBar1;
    private JTextField firstNameText;
    private JComboBox birthMonthComboBox;
    private JTextField idNumberText;
    private JTextField positionText;
    private JRadioButton pieceWorkerRadioButton;
    private JRadioButton hourlyEmployeeRadioButton;
    private JRadioButton comissionEmployeeRadioButton;
    private JTextField hoursText;
    private JTextField rateText;
    private JTextField lastNameText;
    private JTextField birthYearText;
    private JTextField birthDayText;
    private JButton ADDButton;
    private JButton CANCELButton;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel birthDate;
    private JLabel idNumber;
    private JLabel position;
    private JLabel employeeType;
    private JLabel hours;
    private JLabel rate;
    private JTextField no_of_items_text;
    private JTextField RateText;
    private JTextField baseSalaryTextt;
    private JTextField numItemsTextt;
    private JTextField itemPriceTextt;
    private JTextField comissionRateTextt;
    private JPanel ComissionEmployeePanelSalary;
    private JPanel PieceWorkerPanel;
    private JPanel HourlyEmployeePanel;
    private JLabel NumItems;
    private JLabel Rate;


    public AddEmployeeFrame() {

        setContentPane(addEmployeePanel);
        setTitle("Add Employee");
        setSize(1000,500);
        setVisible(true);

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try{
                    if(pieceWorkerRadioButton.isSelected()){
                        PieceWorker pieceWorker = new PieceWorker(firstNameText.getText(), lastNameText.getText(),
                                birthMonthComboBox.getSelectedItem().toString(),
                                Integer.parseInt(birthDayText.getText()),
                                Integer.parseInt(birthYearText.getText()),
                                Integer.parseInt(idNumberText.getText()),
                                positionText.getText(),
                                Integer.parseInt(no_of_items_text.getText()),
                                Integer.parseInt(RateText.getText()));
                        System.out.println(pieceWorker.trueToString());
                        MainFrame.allEmployeesList.add(pieceWorker);
                        MainFrame.pieceWorkerArrayList.add(pieceWorker);
                        MainFrame.writeToFile(MainFrame.pieceWorkerArrayList, "pieceWorkers.txt");
                    }
                    else if(hourlyEmployeeRadioButton.isSelected()){
                        HourlyEmployee hourlyEmployee = new HourlyEmployee(firstNameText.getText(), lastNameText.getText(),
                                birthMonthComboBox.getSelectedItem().toString(),
                                Integer.parseInt(birthDayText.getText()),
                                Integer.parseInt(birthYearText.getText()),
                                Integer.parseInt(idNumberText.getText()),
                                positionText.getText(),
                                Integer.parseInt(hoursText.getText()),
                                Integer.parseInt(rateText.getText()));
                        System.out.println(hourlyEmployee.trueToString());
                        MainFrame.allEmployeesList.add(hourlyEmployee);
                        MainFrame.hourlyEmployeeArrayList.add(hourlyEmployee);
                        MainFrame.writeToFile(MainFrame.hourlyEmployeeArrayList, "hourlyWorkers.txt");
                    }
                    else if(comissionEmployeeRadioButton.isSelected()){
                        ComissionEmployee comissionEmployee = new ComissionEmployee(firstNameText.getText(), lastNameText.getText(),
                                birthMonthComboBox.getSelectedItem().toString(),
                                Integer.parseInt(birthDayText.getText()),
                                Integer.parseInt(birthYearText.getText()),
                                Integer.parseInt(idNumberText.getText()),
                                positionText.getText(),
                                Integer.parseInt(baseSalaryTextt.getText()),
                                Integer.parseInt(numItemsTextt.getText()),
                                Integer.parseInt(itemPriceTextt.getText()),
                                Integer.parseInt(comissionRateTextt.getText())
                        );
                        System.out.println(comissionEmployee.trueToString());
                        MainFrame.allEmployeesList.add(comissionEmployee);
                        MainFrame.comissionEmployeeArrayList.add(comissionEmployee);
                        MainFrame.writeToFile(MainFrame.comissionEmployeeArrayList, "comissionEmployees.txt");
                    }
                    else
                        System.out.println("burada bir terslik var");
                }
                catch (Exception e1){
                    e1.printStackTrace();
                }
                finally {
                    System.out.println(MainFrame.allEmployeesList);
                    MainFrame.writeToFile(MainFrame.allEmployeesList, "allEmployees.txt");
                }



            }
        });
        pieceWorkerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hourlyEmployeeRadioButton.setSelected(false);
                HourlyEmployeePanel.setVisible(false);

                comissionEmployeeRadioButton.setSelected(false);
                ComissionEmployeePanelSalary.setVisible(false);

                pieceWorkerRadioButton.setSelected(true);
                PieceWorkerPanel.setVisible(true);
            }
        });
        hourlyEmployeeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pieceWorkerRadioButton.setSelected(false);
                PieceWorkerPanel.setVisible(false);

                comissionEmployeeRadioButton.setSelected(false);
                ComissionEmployeePanelSalary.setVisible(false);

                hourlyEmployeeRadioButton.setSelected(true);
                HourlyEmployeePanel.setVisible(true);
            }
        });
        comissionEmployeeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pieceWorkerRadioButton.setSelected(false);
                PieceWorkerPanel.setVisible(false);

                hourlyEmployeeRadioButton.setSelected(false);
                HourlyEmployeePanel.setVisible(false);

                comissionEmployeeRadioButton.setSelected(true);
                ComissionEmployeePanelSalary.setVisible(true);
            }
        });
        birthDayText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                if(Objects.equals(birthDayText.getText(), ""))
                    birthDayText.setText("DD");
            }
        });
        CANCELButton.addActionListener(e -> this.dispose());
    }

    public static void main(String[] args) {
        AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame();
    }
}
