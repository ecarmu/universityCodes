package week11;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

public class RequestedOperationScreen {

    public String[][] tableStrArr;
    public JTable table;
    public JTable table2;
    int row;
    int column;




    //Object object, String pageTitle, String operationType, String filePath
    RequestedOperationScreen(Object object, String operationType) {

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setLayout(new GridLayout(6, 3));

        if (object instanceof Doctor) {

            switch (operationType) {
                case "view profile":
                    JLabel jLabel = new JLabel(String.valueOf(object));
                    jFrame.add(jLabel);
                    break;

                case "view appointment":
                    viewAppointment(object);
                    break;

                case "edit profile":
                    editProfile(object);
                    break;

                case "add time slot":
                    JLabel IDLabel = new JLabel("ID");
                    JTextField IDText = new JTextField();
                    JPanel jPanel1 = new JPanel();
                    jPanel1.setLayout((new GridLayout(3, 2)));
                    jPanel1.add(IDLabel);
                    jPanel1.add(IDText);

                    JLabel DoctorIDLabel = new JLabel("Doctor ID");
                    JTextField DoctorIDText = new JTextField();
                    DoctorIDText.setText(String.valueOf(((Doctor) object).ID));
                    DoctorIDText.setEnabled(false);

                    JPanel jPanel2 = new JPanel();
                    jPanel2.setLayout((new GridLayout(3, 2)));
                    jPanel2.add(DoctorIDLabel);
                    jPanel2.add(DoctorIDText);

                    JLabel DayLabel = new JLabel("Day");
                    JFormattedTextField DayText = new JFormattedTextField();
                    JPanel jPanel3 = new JPanel();
                    jPanel3.setLayout((new GridLayout(3, 2)));
                    jPanel3.add(DayLabel);
                    jPanel3.add(DayText);

                    JLabel Start_Time_Label = new JLabel("Start Time");
                    JFormattedTextField Start_Time_Text = new JFormattedTextField();
                    JPanel jPanel4 = new JPanel();
                    jPanel4.setLayout((new GridLayout(3, 2)));
                    jPanel4.add(Start_Time_Label);
                    jPanel4.add(Start_Time_Text);

                    JLabel End_Time_Label = new JLabel("End Time");
                    JFormattedTextField End_Time_Text = new JFormattedTextField();
                    JPanel jPanel5 = new JPanel();
                    jPanel5.setLayout((new GridLayout(3, 2)));
                    jPanel5.add(End_Time_Label);
                    jPanel5.add(End_Time_Text);

                    JButton addTimeSlotButton = new JButton("Add Time Slot");
                    JPanel jPanel6 = new JPanel();
                    jPanel6.add(addTimeSlotButton);

                    jFrame.add(jPanel1);
                    jFrame.add(jPanel2);
                    jFrame.add(jPanel3);
                    jFrame.add(jPanel4);
                    jFrame.add(jPanel5);
                    jFrame.add(jPanel6);

                    addTimeSlotButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            time_slot time_slot = new time_slot(
                                    Integer.parseInt(IDText.getText()),
                                    Integer.parseInt(DoctorIDText.getText()),
                                    (Date) DayText.getValue(),
                                    (Time) Start_Time_Text.getValue(),
                                    (Time) End_Time_Text.getValue()
                            );

                            time_slot.addAvailableTime();

                        }
                    });

                    break;


            }
            // add time slot

            // book appointment
            // cancel appointment
            // modify appointment

            // view profile
            // edit profile

        } else if (object instanceof Patient) {
            switch (operationType) {
                case "view profile":
                    JLabel jLabel = new JLabel(String.valueOf(object));
                    jFrame.add(jLabel);
                    break;

                case "make appointment":
                    customerMakeAppointment(object);
                    break;
                case "view appointment":
                    viewAppointment(object);
                    break;

                case "edit profile":
                    editProfile(object);

                    break;
                case "make enquiry":
                    JPanel jPanel1 = new JPanel();
                    jPanel1.setLayout((new GridLayout(3, 2, 3, 3)));
                    jPanel1.add(new JLabel("Title: "));
                    JTextField titleText = new JTextField();
                    jPanel1.add(titleText);

                    JPanel jPanel2 = new JPanel();
                    jPanel2.setLayout((new GridLayout(3, 2, 3, 3)));
                    jPanel2.add(new JLabel("Name: "));
                    JTextField nameText = new JTextField();
                    jPanel2.add(nameText);

                    JPanel jPanel3 = new JPanel();
                    jPanel3.setLayout((new GridLayout(3, 2, 3, 3)));
                    jPanel3.add(new JLabel("Email: "));
                    JTextField emailText = new JTextField();
                    jPanel3.add(emailText);

                    JPanel jPanel4 = new JPanel();
                    jPanel4.setLayout((new GridLayout(3, 2, 3, 3)));
                    jPanel4.add(new JLabel("Mobile: "));
                    JTextField mobileText = new JTextField();
                    jPanel4.add(mobileText);

                    JPanel jPanel5 = new JPanel();
                    jPanel5.setLayout((new GridLayout(3, 2, 3, 3)));
                    jPanel5.add(new JLabel("Message: "));
                    JTextField messageText = new JTextField();
                    jPanel5.add(messageText);

                    JPanel jPanel6 = new JPanel();
                    JButton makeEnquiryButton = new JButton("makeEnquiryButton");
                    jPanel6.add(makeEnquiryButton);


                    jFrame.add(jPanel1);
                    jFrame.add(jPanel2);
                    jFrame.add(jPanel3);
                    jFrame.add(jPanel4);
                    jFrame.add(jPanel5);
                    jFrame.add(jPanel6);

                    makeEnquiryButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Enquiries enquiry = new Enquiries(titleText.getText(),
                                    nameText.getText(),
                                    emailText.getText(),
                                    Integer.parseInt(mobileText.getText()),
                                    messageText.getText()
                            );

                            enquiry.makeEnquiries();

                            JOptionPane.showMessageDialog(null, "Enquiry has been sent successfully", "Success", JOptionPane.YES_OPTION);

                        }
                    });
                    break;
            }

        } else {
            String[][] data = {};
            String[] columnNames = {"ID", "Name", "Surname", "Email", "Password", "Mobile", "Country", "Birth Date"};
            table = new JTable(data, columnNames);
            table.setVisible(true);
            table.setLayout(new FlowLayout());

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(500, 500));

            tableStrArr = FileOperations.readAndPopulateTable("patient.txt");
            DefaultTableModel model = new DefaultTableModel(tableStrArr, columnNames);
            table.setModel(model);

            switch (operationType) {
                case "delete user":
                    System.out.println("delete user");

                    // Panel to hold the buttons
                    JPanel buttonPanel = new JPanel();
                    JButton deleteUserButton = new JButton("Delete User");
                    buttonPanel.add(deleteUserButton);

                    // Create a JPanel to hold the JScrollPane
                    JPanel jPanel2 = new JPanel();
                    jPanel2.add(scrollPane);

                    // Add the panels to the JFrame
                    jFrame.add(jPanel2, BorderLayout.CENTER);
                    jFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the JFrame properties and make it visible
                    jFrame.setTitle("Admin Screen");
                    jFrame.setSize(500, 300);
                    jFrame.setLocationRelativeTo(null);
                    jFrame.setVisible(true);

                    // Populate the JTable with data from the "patient.txt" file
                    String[][] tableStrArr = FileOperations.readAndPopulateTable("patient.txt");
                    model = new DefaultTableModel(tableStrArr, columnNames);
                    table.setModel(model);

                    // Add an ActionListener to the Delete User button
                    deleteUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DefaultTableModel model2 = (DefaultTableModel) table.getModel();
                            int selectedRow = table.getSelectedRow();
                            //String reply = JOptionPane.showInputDialog(jFrame, "Enter your reply:");
                            if (selectedRow != -1) {
                                model2.removeRow(selectedRow);

                                // Save the changes to the "enquiry.txt" file
                                Vector<Vector> data = model2.getDataVector();
                                try {
                                    PrintWriter writer = new PrintWriter(new File("patient.txt"));
                                    for (Vector rowVector : data) {
                                        String[] rowData = (String[]) rowVector.toArray(new String[0]);
                                        String rowString = String.join("|", rowData);
                                        writer.println(rowString);
                                    }
                                    writer.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });


                    table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JTable target = (JTable) e.getSource();
                        int selectedRow = target.getSelectedRow();
                    }
                });

                    break;

                case "edit user":
                    // Create the UI components
                    System.out.println("edit user");
                    JPanel buttonPanel2 = new JPanel();
                    JButton editUserButton = new JButton("Edit User");
                    buttonPanel2.add(editUserButton);
                    JPanel jPanel3 = new JPanel();
                    jPanel3.add(scrollPane);
                    buttonPanel2.setVisible(true);
                    jFrame.add(jPanel3);
                    jFrame.add(buttonPanel2);
                    jFrame.setTitle("Admin Screen");
                    jFrame.setVisible(true);

                    // Attach a listener to the "Edit User" button
                    editUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get the selected row and column
                            int selectedRow = table.getSelectedRow();
                            int selectedColumn = table.getSelectedColumn();


                            // Display a dialog box to get the updated value
                            String updatedValue = JOptionPane.showInputDialog(this, "Enter updated value:");

                            // Update the cell in the JTable
                            table.getModel().setValueAt(updatedValue, selectedRow, selectedColumn);

                            // Save the changes to the "patient.txt" file
                            String[][] data = new String[table.getRowCount()][table.getColumnCount()];
                            for (int i = 0; i < table.getRowCount(); i++) {
                                for (int j = 0; j < table.getColumnCount(); j++) {
                                    data[i][j] = (String) table.getValueAt(i, j);
                                }
                            }
                            FileOperations.saveTableToFile("patient.txt", data);
                        }
                    });
                    break;

                case "reply enquiry":
                    System.out.println("reply enquiry");

                    String[] columnNames2 = new String[]{"Title", "Name", "Email", "Mobile", "Message"};

                    String[][] data2 = {};

                    DefaultTableModel tableModel2 = new DefaultTableModel(data2, columnNames2);

                    JTable table2 = new JTable(tableModel2);

// Add the JTable to a JScrollPane
                    JScrollPane scrollPane2 = new JScrollPane(table2);

// Create a JPanel to hold the JScrollPane
                    JPanel panel = new JPanel();
                    panel.add(scrollPane2);

                    JPanel panel2 = new JPanel();
                    JButton replyEnquiryButton = new JButton("Reply Enquiry");
                    replyEnquiryButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
                            int selectedRow = table2.getSelectedRow();
                            String reply = JOptionPane.showInputDialog(jFrame, "Enter your reply:");
                            if (selectedRow != -1) {
                                model2.removeRow(selectedRow);

                                // Save the changes to the "enquiry.txt" file
                                Vector<Vector> data = model2.getDataVector();
                                try {
                                    PrintWriter writer = new PrintWriter(new File("enquiry.txt"));
                                    for (Vector rowVector : data) {
                                        String[] rowData = (String[]) rowVector.toArray(new String[0]);
                                        String rowString = String.join("|", rowData);
                                        writer.println(rowString);
                                    }
                                    writer.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                    panel2.add(replyEnquiryButton);

// Create a JFrame to hold the JPanel and set its properties
                    JFrame frame2 = new JFrame("JTable Example");
                    frame2.setSize(500, 300);
                    frame2.setLocationRelativeTo(null);
                    frame2.add(panel);
                    frame2.add(panel2, BorderLayout.SOUTH);
                    frame2.setVisible(true);

// Populate the JTable with data from the "enquiry.txt" file
                    String[][] tableStrArr2 = FileOperations.readAndPopulateTable("enquiry.txt");
                    DefaultTableModel model2 = new DefaultTableModel(tableStrArr2, columnNames2);
                    table2.setModel(model2);

// Add a MouseListener to the JTable
                    table2.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            JTable target = (JTable) e.getSource();
                            int selectedRow = target.getSelectedRow();
                        }
                    });



                    break;

                case "delete past appointment":
                    String[] columnNames3 = new String[]{"ID", "Doctor ID", "Patient ID", "Day", "Start Time", "End Time"};

                    System.out.println("delete past appointment");

                    String[][] data3 = {};

                    DefaultTableModel tableModel3 = new DefaultTableModel(data3, columnNames3);

                    JTable table3 = new JTable(tableModel3);

// Add the JTable to a JScrollPane
                    JScrollPane scrollPane3 = new JScrollPane(table3);

// Create a JPanel to hold the JScrollPane
                    JPanel panel3 = new JPanel();
                    panel3.add(scrollPane3);

                    JPanel panel4 = new JPanel();
                    JButton deletePastAppointmentButton = new JButton("Delete Past Appointment");
                    deletePastAppointmentButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DefaultTableModel model2 = (DefaultTableModel) table3.getModel();
                            int selectedRow = table3.getSelectedRow();
                            //String reply = JOptionPane.showInputDialog(jFrame, "Enter your reply:");
                            if (selectedRow != -1) {
                                model2.removeRow(selectedRow);

                                // Save the changes to the "enquiry.txt" file
                                Vector<Vector> data = model2.getDataVector();
                                try {
                                    PrintWriter writer = new PrintWriter(new File("appointment.txt"));
                                    for (Vector rowVector : data) {
                                        String[] rowData = (String[]) rowVector.toArray(new String[0]);
                                        String rowString = String.join("|", rowData);
                                        writer.println(rowString);
                                    }
                                    writer.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                    panel4.add(deletePastAppointmentButton);

// Create a JFrame to hold the JPanel and set its properties
                    JFrame frame3 = new JFrame("JTable Example");
                    frame3.setSize(500, 300);
                    frame3.setLocationRelativeTo(null);
                    frame3.add(panel3);
                    frame3.add(panel4, BorderLayout.SOUTH);
                    frame3.setVisible(true);

// Populate the JTable with data from the "enquiry.txt" file
                    String[][] tableStrArr3 = FileOperations.readAndPopulateTable("appointment.txt");
                    DefaultTableModel model3 = new DefaultTableModel(tableStrArr3, columnNames3);
                    table3.setModel(model3);

// Add a MouseListener to the JTable
                    table3.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            JTable target = (JTable) e.getSource();
                            int selectedRow = target.getSelectedRow();
                        }
                    });


                    break;
            }

            System.out.println("hataaa... ne doctor ne de patient/customer tipinde değil veri!!!");
        }

    }

    public void viewAppointment(Object object){
        String[] columnNames3 = new String[]{"ID", "Doctor ID", "Patient ID", "Day", "Start Time", "End Time"};


        String[][] data3 = {};


        DefaultTableModel tableModel3 = new DefaultTableModel(data3, columnNames3);


        JTable table_filtered = new JTable(tableModel3);
        JTable table_unfiltered = new JTable(tableModel3);

// Add the JTable to a JScrollPane
        JScrollPane scrollPane3 = new JScrollPane(table_filtered);

// Create a JPanel to hold the JScrollPane
        JPanel panel3 = new JPanel();
        panel3.add(scrollPane3);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(3, 2));
        JButton modify_appointmentButton = new JButton("Modify Appointment");
        JButton cancel_appointmentButton = new JButton("Cancel Appointment");
        panel4.add(modify_appointmentButton);
        panel4.add(cancel_appointmentButton);

// Create a JFrame to hold the JPanel and set its properties
        JFrame frame3 = new JFrame("JTable Example");
        frame3.setSize(500, 300);
        frame3.setLocationRelativeTo(null);
        frame3.add(panel3);
        frame3.add(panel4, BorderLayout.SOUTH);
        frame3.setVisible(true);

// Populate the JTable with data from the "enquiry.txt" file
        final String[][][] tableStrArr3_unfiltered = {FileOperations.readAndPopulateTable("appointment.txt")};

        for (int i = 0; i < tableStrArr3_unfiltered.length; i++) {
            System.out.println("unfiltered array after fileoperations: " );
            for (int j = 0; j < tableStrArr3_unfiltered[0].length; j++) {
                System.out.print(Arrays.toString(tableStrArr3_unfiltered[i][j]) + " ");
            }
        }
        String[][] tableStrArr3_filtered = null;

        try {
            if(object instanceof Patient)
                tableStrArr3_filtered = FileOperations.readAndFilterData(tableStrArr3_unfiltered[0], "appointment.txt", String.valueOf(((Patient) object).ID), 2);
            if (object instanceof Doctor)
                tableStrArr3_filtered = FileOperations.readAndFilterData(tableStrArr3_unfiltered[0], "appointment.txt", String.valueOf(((Doctor) object).ID), 1);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < tableStrArr3_filtered.length; i++) {
            System.out.println("filtered array after fileoperations: " );
            for (int j = 0; j < tableStrArr3_filtered[0].length; j++) {
                System.out.print(tableStrArr3_filtered[i][j] + " ");
            }
        }
        DefaultTableModel model3 = new DefaultTableModel(tableStrArr3_filtered, columnNames3);
        table_filtered.setModel(model3);

        String[][] finalTableStrArr3_filtered = tableStrArr3_filtered;
        table_filtered.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                row = target.getSelectedRow();
                column = target.getSelectedColumn();
            }
        });




        cancel_appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model2 = (DefaultTableModel) table_filtered.getModel();
                int selectedRow = table_filtered.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the data from the selected row
                    String[] rowData = new String[columnNames3.length];
                    for (int i = 0; i < columnNames3.length; i++) {
                        rowData[i] = (String) model2.getValueAt(selectedRow, i);
                    }

                    // Remove the row from the filtered table
                    model2.removeRow(selectedRow);

                    // Find the matching row in the unfiltered table and remove it
                    for (int i = 0; i < tableStrArr3_unfiltered[0].length; i++) {
                        if (Arrays.equals(rowData, tableStrArr3_unfiltered[0][i])) {
                            tableStrArr3_unfiltered[0] = removeRow(tableStrArr3_unfiltered[0], i);
                            break;
                        }
                    }
                    // Save the changes to the "appointment.txt" file
                    try {
                        PrintWriter writer = new PrintWriter(new File("appointment.txt"));
                        for (String[] row : tableStrArr3_unfiltered[0]) {
                            System.out.println(Arrays.toString(row));
                            String rowStr = String.join("|", row);
                            writer.println(rowStr.replace(",", "|"));
                        }
                        writer.close();


                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        time_slot time_slot = new time_slot(
                                Integer.parseInt(finalTableStrArr3_filtered[selectedRow][0]),
                                Integer.parseInt(finalTableStrArr3_filtered[selectedRow][1]),
                                (Date) dateFormat.parse("2012-05-03"),
                                Time.valueOf("22:13:12"),
                                Time.valueOf("23:13:12"));

                        time_slot.addAvailableTime();
                    } catch (FileNotFoundException ex) {
                        System.out.println("Error: appointment.txt file not found.");
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }


                });

                table_filtered.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JTable target = (JTable) e.getSource();
                        int selectedRow = target.getSelectedRow();
                    }
                });
                modify_appointmentButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the selected row and column
                        int selectedRow = table_filtered.getSelectedRow();
                        int selectedColumn = table_filtered.getSelectedColumn();


                        // Display a dialog box to get the updated value
                        String updatedValue = JOptionPane.showInputDialog(this, "Enter updated value:");

                        // Update the cell in the JTable
                        table_filtered.getModel().setValueAt(updatedValue, selectedRow, selectedColumn);

                        // Save the changes to the "patient.txt" file
                        String[][] data = new String[table_filtered.getRowCount()][table_filtered.getColumnCount()];
                        for (int i = 0; i < table_filtered.getRowCount(); i++) {
                            for (int j = 0; j < table_filtered.getColumnCount(); j++) {
                                data[i][j] = (String) table_filtered.getValueAt(i, j);
                            }
                        }
                        FileOperations.saveTableToFile("appointment.txt", data);
                    }

                });
    }

    public void customerMakeAppointment(Object object){
        System.out.println("make appointment");

        String[] columnNames2 = new String[]{"ID", "Doctor ID", "Day", "Start Time", "End Time"};
        String[][] data2 = {};
        DefaultTableModel tableModel2 = new DefaultTableModel(data2, columnNames2);
        JTable table2 = new JTable(tableModel2);

// Add the JTable to a JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(table2);

// Create a JPanel to hold the JScrollPane
        JPanel panel = new JPanel();
        panel.add(scrollPane2);

        JPanel panel2 = new JPanel();
        JButton make_appointmentButton = new JButton("Make Appointment");
        panel2.add(make_appointmentButton);

// Create a JFrame to hold the JPanel and set its properties
        JFrame frame2 = new JFrame("JTable Example");
        frame2.setSize(500, 300);
        frame2.setLocationRelativeTo(null);
        frame2.add(panel);
        frame2.add(panel2, BorderLayout.SOUTH);
        frame2.setVisible(true);

// Populate the JTable with data from the "enquiry.txt" file
        String[][] tableStrArr2 = FileOperations.readAndPopulateTable("time_slot.txt");
        DefaultTableModel model2 = new DefaultTableModel(tableStrArr2, columnNames2);
        table2.setModel(model2);

        table2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                row = target.getSelectedRow();
                column = target.getSelectedColumn();

                // Get the selected data from the table model
                DefaultTableModel model = (DefaultTableModel) target.getModel();
                Vector rowVector = (Vector) model.getDataVector().elementAt(row);
                String[] rowData = (String[]) rowVector.toArray(new String[0]);

                System.out.println("Selected row data: " + Arrays.toString(rowData));
            }
        });

        make_appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
                int selectedRow = table2.getSelectedRow();
                //String reply = JOptionPane.showInputDialog(jFrame, "Enter your reply:");
                if (selectedRow != -1) {
                    model2.removeRow(selectedRow);

                    // Save the changes to the "enquiry.txt" file
                    Vector<Vector> data = model2.getDataVector();
                    try {
                        PrintWriter writer = new PrintWriter(new File("time_slot.txt"));
                        System.out.println("row data: ");
                        for (Vector rowVector : data) {
                            String[] rowData = (String[]) rowVector.toArray(new String[0]);
                            System.out.println(rowData + " ");
                            String rowString = String.join("|", rowData);
                            writer.println(rowString);
                        }
                        writer.close();

                        Appointment appointment = new Appointment(
                                Integer.parseInt(tableStrArr2[selectedRow][0]),
                                Integer.parseInt(tableStrArr2[selectedRow][1]),
                                Integer.parseInt(String.valueOf(((Patient) object).ID)),
                                DayOfWeek.MONDAY,
                                Time.valueOf("22:13:16"),
                                Time.valueOf("21:19:18")
                        );

                        appointment.bookAppointment();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                }
            }
        });
    }

    public void editProfile(Object object){
        JFrame jFrame = new JFrame();
        JFormattedTextField DateOfBirthCustomerText = null;
        JTextField EmailCustomerText = new JTextField();
        JPasswordField PasswordCustomerText = new JPasswordField();
        JButton edit_profile_Button = new JButton("Edit Profile");

        JLabel EmailCustomerLabel = new JLabel("Email");

        JLabel PasswordCustomerLabel = new JLabel("Password");
        JLabel DateOfBirthCustomerLabel = null;
        JLabel DepartmentLabel = null;
        JTextField DepartmentText = null;
        JLabel QualificationsLabel = null;
        JTextField QualificationsText = null;

        if (object instanceof Patient){
            DateOfBirthCustomerLabel = new JLabel("Date of Birth");
            DateOfBirthCustomerText = new JFormattedTextField();
        }


        if (object instanceof Doctor) {
            DepartmentLabel = new JLabel("Department");
            DepartmentText = new JTextField();
            QualificationsLabel = new JLabel("Qualifications: ");
            QualificationsText = new JTextField();
        }


        JTextField IDText = new JTextField();
        JTextField NameText = new JTextField();
        JTextField SurnameText = new JTextField();
        JTextField MobileText = new JTextField();
        JTextField CountryText = new JTextField();
        JLabel GeneralInfoLabel = new JLabel("General Info");
        JLabel IDLabel = new JLabel("ID");
        JLabel NameLabel = new JLabel("Name");
        JLabel SurnameLabel = new JLabel("Surname");
        JLabel MobileLabel = new JLabel("Mobile");
        JLabel CountryLabel = new JLabel("Country");


        jFrame.setLayout(new GridLayout(6, 3));


        JPanel jPanel20 = new JPanel();

        jPanel20.add(GeneralInfoLabel);

        JPanel jPanel21 = new JPanel();
        jPanel21.setLayout((new GridLayout(3, 2)));
        jPanel21.add(IDLabel);
        jPanel21.add(IDText);

        JPanel jPanel23 = new JPanel();
        jPanel23.setLayout((new GridLayout(3, 2)));
        jPanel23.add(NameLabel);
        jPanel23.add(NameText);

        JPanel jPanel24 = new JPanel();
        jPanel24.setLayout((new GridLayout(3, 2)));
        jPanel24.add(SurnameLabel);
        jPanel24.add(SurnameText);

        JPanel jPanel25 = new JPanel();
        jPanel25.setLayout((new GridLayout(3, 2)));
        jPanel25.add(MobileLabel);
        jPanel25.add(MobileText);

        JPanel jPanel26 = new JPanel();
        jPanel26.setLayout((new GridLayout(3, 2)));
        jPanel26.add(CountryLabel);
        jPanel26.add(CountryText);
        JPanel jPanel14 = new JPanel();
        jPanel14.setLayout((new GridLayout(3, 2)));
        jPanel14.add(EmailCustomerLabel);
        jPanel14.add(EmailCustomerText);

        JPanel jPanel15 = null;
        if (object instanceof Patient) {
            jPanel15 = new JPanel();
            jPanel15.setLayout((new GridLayout(3, 2)));
            jPanel15.add(DateOfBirthCustomerLabel);
            jPanel15.add(DateOfBirthCustomerText);
        }

        JPanel jPanel18 = null;
        JPanel jPanel19 = null;
        if(object instanceof Doctor){
            jPanel18 = new JPanel();
            jPanel18.setLayout((new GridLayout(3, 2)));
            jPanel18.add(DepartmentLabel);
            jPanel18.add(DepartmentText);

            jPanel19 = new JPanel();
            jPanel19.setLayout((new GridLayout(3, 2)));
            jPanel19.add(QualificationsLabel);
            jPanel19.add(QualificationsText);
        }



        JPanel jPanel16 = new JPanel();
        jPanel16.setLayout((new GridLayout(3, 2)));
        jPanel16.add(PasswordCustomerLabel);
        jPanel16.add(PasswordCustomerText);

        JPanel jPanel17 = new JPanel();
        jPanel17.add(edit_profile_Button);

        jFrame.add(jPanel20);
        jFrame.add(jPanel21);
        jFrame.add(jPanel23);
        jFrame.add(jPanel24);
        jFrame.add(jPanel25);
        jFrame.add(jPanel26);
        jFrame.add(jPanel14);
        jFrame.setVisible(true);
        if (object instanceof Patient)
            jFrame.add(jPanel15);
        if(object instanceof Doctor){
            jFrame.add(jPanel18);
            jFrame.add(jPanel19);
        }
        jFrame.add(jPanel16);
        jFrame.add(jPanel17);

        JFormattedTextField finalDateOfBirthCustomerText = DateOfBirthCustomerText;
        JTextField finalQualificationsText = QualificationsText;
        JTextField finalDepartmentText = DepartmentText;
        edit_profile_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (object instanceof Patient) {
                    String lineToRemove = ((Patient) object).ID + "|" +
                            ((Patient) object).name + "|" +
                            ((Patient) object).surname + "|" +
                            ((Patient) object).email + "|" +
                            (Arrays.toString(((Patient) object).password)) + "|" +
                            ((Patient) object).mobile + "|" +
                            ((Patient) object).country + "|" +
                            ((Patient) object).date_of_birth;
                    lineToRemove = lineToRemove.replace("[", "");
                    lineToRemove = lineToRemove.replace("]", "");


                    FileOperations.deleteLineFromFile("patient.txt", FileOperations.getLineNumber(lineToRemove, "patient.txt"));

                    ((Patient) object).ID = Integer.parseInt(IDText.getText());
                    ((Patient) object).name = NameText.getText();
                    ((Patient) object).surname = SurnameText.getText();
                    ((Patient) object).email = EmailCustomerText.getText();
                    ((Patient) object).password = PasswordCustomerText.getPassword();
                    ((Patient) object).mobile = Integer.parseInt(MobileText.getText());
                    ((Patient) object).country = CountryText.getText();
                    ((Patient) object).date_of_birth = (Date) finalDateOfBirthCustomerText.getValue();

                    ((Patient) object).addPatient();
                }
                if(object instanceof Doctor){
                    String lineToRemove = ((Doctor) object).ID + "|" +
                            ((Doctor) object).name + "|" +
                            ((Doctor) object).surname + "|" +
                            ((Doctor) object).email + "|" +
                            (Arrays.toString(((Doctor) object).password)) + "|" +
                            ((Doctor) object).mobile + "|" +
                            ((Doctor) object).country + "|" +
                            ((Doctor) object).department + "|" +
                            ((Doctor) object).qualifications;
                    lineToRemove = lineToRemove.replace("[", "");
                    lineToRemove = lineToRemove.replace("]", "");


                    FileOperations.deleteLineFromFile("doctor.txt", FileOperations.getLineNumber(lineToRemove, "doctor.txt"));

                    ((Doctor) object).ID = Integer.parseInt(IDText.getText());
                    ((Doctor) object).name = NameText.getText();
                    ((Doctor) object).surname = SurnameText.getText();
                    ((Doctor) object).email = EmailCustomerText.getText();
                    ((Doctor) object).password = PasswordCustomerText.getPassword();
                    ((Doctor) object).mobile = Integer.parseInt(MobileText.getText());
                    ((Doctor) object).country = CountryText.getText();
                    ((Doctor) object).department = finalDepartmentText.getText();
                    ((Doctor) object).qualifications = finalQualificationsText.getText();

                    ((Doctor) object).addDoctor();
                }

                JOptionPane.showMessageDialog(null, "Profile has been updated successfully", "Success", JOptionPane.YES_OPTION);

            }
        });
    }

    /*
    public void updateTableToCurrent(String filePath, String[] column){
        tableStrArr= FileOperations.readAndPopulateTable(filePath);

        String[] str_deleted = tableStrArr[row];

        tableStrArr = filterTable(tableStrArr, str_deleted);

        String fileName = "patient.txt";
        FileOperations.deleteLineFromFile(fileName, str_deleted[0]);

        DefaultTableModel model = new DefaultTableModel(tableStrArr, column);
        table.setModel(model);
    }*/


    /*
    public String[][] filterTable(String[][] tableStrArr, String[] str_deleted) {


        String[][] filtered_tableStrArr = new String[tableStrArr.length - 1][];



        for (int i = 0, k=0; i < filtered_tableStrArr.length; i++, k++) {
            if(Arrays.equals(filtered_tableStrArr[k], str_deleted)){
                continue;
            }
            filtered_tableStrArr[k] = new String[tableStrArr[i].length];
            for (int j = 0; j < filtered_tableStrArr[i].length; j++) {
                filtered_tableStrArr[k][j] = tableStrArr[i][j];
            }
        }
        return filtered_tableStrArr;
    }*/

    private static String[][] removeRow(String[][] array, int index) {
        String[][] newArray = new String[array.length - 1][array[0].length];
        int newIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                newArray[newIndex++] = array[i];
            }
        }
        return newArray;
    }



}
