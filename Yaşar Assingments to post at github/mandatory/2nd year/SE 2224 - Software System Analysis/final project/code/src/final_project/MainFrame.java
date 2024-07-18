package final_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MainFrame {



    private JButton insertTaskButton;
    private JButton deleteTaskButton;
    private JButton editAndUpdateTaskButton;
    private JButton showAllTasksButton;
    private JButton sortByPriorityButton;
    private JButton filterTasksButton;
    private JTable table1;
    private JPanel panel1;
    private JButton viewImageButton;

    public static final String OPERATION_EDIT = "Edit Row";
    public static final String OPERATION_ADD = "Add Row";
    public static final String OPERATION_SORT_BY_PRIORITY = "Sort Row (Task) by Priority";
    public static final String OPERATION_FILTER_BY_DEADLINE = "Filter Row (Task) by Deadline";
    public static final String OPERATION_IMAGE = "View Image of Row";


    MysqlOperations mysqlConnect = new MysqlOperations();
    Connection connection = mysqlConnect.connect();

    public MainFrame() {
        initScreen();

        String url = "jdbc:mysql://localhost:3306/se2224_proje";
        String username = "java";
        String password = "password";


    }


    public void initScreen() {

        JFrame jFrame = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jFrame.setVisible(true);


        String[] columnNames2 = new String[]{};

        String[][] data2 = {{}};

        DefaultTableModel tableModel2 = new DefaultTableModel(data2, columnNames2);


        table1 = new JTable(tableModel2);


        jPanel.add(        new JLabel("Date of today (now) this moment:\n" + String.valueOf(Timestamp.valueOf(LocalDateTime.now()))) );

        jPanel.add(table1);

        jPanel.add(insertTaskButton);
        jPanel.add(deleteTaskButton);
        jPanel.add(editAndUpdateTaskButton);
        jPanel.add(showAllTasksButton);
        jPanel.add(sortByPriorityButton);
        jPanel.add(filterTasksButton);
        jPanel.add(viewImageButton);


        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setBounds(36, 37, 407, 79);
        jFrame.getContentPane().add(scrollPane);

        jFrame.add(jPanel);

        clearTable();
        initTable();





        insertTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddOrEditOrFilterInputScreen addInputScreen = new AddOrEditOrFilterInputScreen(OPERATION_ADD);
                if(addInputScreen.name.getText().equals("") ||
                        addInputScreen.description.getText().equals("") ||
                        addInputScreen.deadline.getText().equals("") ||
                        addInputScreen.priority.getText().equals("") ||
                        addInputScreen.reminderImage.getText().equals("")
                ){
                    JOptionPane.showMessageDialog(null, "Boş/veri girilmeyen yer ya da yerler var!");
                    return;
                }



                int id = idNumberGenerator();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();


                try {
                    mysqlConnect.insertRowAtSQLTable(
                            id,
                            addInputScreen.name.getText(),
                            addInputScreen.description.getText(),
                            Timestamp.valueOf(addInputScreen.deadline.getText()),
                            Integer.parseInt(addInputScreen.priority.getText()),
                            Boolean.parseBoolean(addInputScreen.reminderImage.getText()),
                            Timestamp.valueOf(now.format(dtf))
                    );
                }
                catch (Exception e1){
                    e1.getMessage();
                    JOptionPane.showMessageDialog(null, "Deadline olarak girilen '" + addInputScreen.deadline.getText() + "' , TimeStamp (tarih) cinsinde veri değil!");
                    return;
                }


                String[] data = {String.valueOf(id), addInputScreen.name.getText(), addInputScreen.description.getText(), addInputScreen.deadline.getText(), addInputScreen.priority.getText(), addInputScreen.reminderImage.getText(), now.format(dtf).toString() };
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
                tblModel.addRow(data);


                JOptionPane.showMessageDialog(null, "Data added successfully");

            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                if(table1.getSelectedRowCount() != 1){
                    JOptionPane.showMessageDialog(null, "0'dan az veya 1'den fazla task (row) seçilemez. 1 tane row seçili olmalı");
                    return;
                }

                try {
                    mysqlConnect.deleteRowAtSQLTable(Integer.parseInt(tblModel.getValueAt(table1.getSelectedRow(), 0).toString()));
                }catch (Exception e2){
                    e2.getMessage();
                    JOptionPane.showMessageDialog(null, "silmek istenilen task (row) bulunamadı");

                }

                tblModel.removeRow(table1.getSelectedRow());
            }
        });
        editAndUpdateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table1.getSelectedRowCount() != 1){
                    JOptionPane.showMessageDialog(null, "0'dan az veya 1'den fazla task (row) seçilemez. 1 tane task (row) seçili olmalı");
                    return;
                }

                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                int ID = Integer.parseInt(tblModel.getValueAt(table1.getSelectedRow(), 0).toString());
                String name = String.valueOf(tblModel.getValueAt(table1.getSelectedRow(), 1));
                String desc = String.valueOf(tblModel.getValueAt(table1.getSelectedRow(), 2));
                Timestamp deadline = Timestamp.valueOf(tblModel.getValueAt(table1.getSelectedRow(), 3).toString());
                int priority = Integer.parseInt(tblModel.getValueAt(table1.getSelectedRow(), 4).toString());
                boolean reminderImage = Boolean.parseBoolean(tblModel.getValueAt(table1.getSelectedRow(), 5).toString());
                Timestamp entryDate = Timestamp.valueOf(tblModel.getValueAt(table1.getSelectedRow(), 6).toString());

                AddOrEditOrFilterInputScreen editInputScreen = new AddOrEditOrFilterInputScreen(
                        OPERATION_EDIT, ID, name, desc, deadline, priority, reminderImage, entryDate
                );

                try {
                    Timestamp.valueOf(editInputScreen.deadline.getText());

                    mysqlConnect.editAndUpdateRowAtSQLTable(
                            Integer.parseInt(editInputScreen.id.getText()),
                            editInputScreen.name.getText(),
                            editInputScreen.description.getText(),
                            Timestamp.valueOf(editInputScreen.deadline.getText()),
                            Integer.parseInt(editInputScreen.priority.getText()),
                            Boolean.parseBoolean(editInputScreen.reminderImage.getText())
                    );
                } catch (Exception ex) {
                    ex.getMessage();
                    JOptionPane.showMessageDialog(null, "Deadline olarak girilen '" + editInputScreen.deadline.getText() + "' , TimeStamp (tarih) cinsinde veri değil!");
                    return;
                }

                //tblModel.setValueAt(editInputScreen.id.getText(), table1.getSelectedRow(), 0);
                tblModel.setValueAt(editInputScreen.name.getText(), table1.getSelectedRow(), 1);
                tblModel.setValueAt(editInputScreen.description.getText(), table1.getSelectedRow(), 2);
                tblModel.setValueAt(editInputScreen.deadline.getText(), table1.getSelectedRow(), 3);
                tblModel.setValueAt(editInputScreen.priority.getText(), table1.getSelectedRow(), 4);
                tblModel.setValueAt(editInputScreen.reminderImage.getText(), table1.getSelectedRow(), 5);
                //tblModel.setValueAt(editInputScreen.entryDate.getText(), table1.getSelectedRow(), 6);

                JOptionPane.showMessageDialog(null, "Update succesful");

            }
        });
        showAllTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                initTable();
            }
        });
        sortByPriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrEditOrFilterInputScreen sortByPriorityScreen = new AddOrEditOrFilterInputScreen(OPERATION_SORT_BY_PRIORITY);
                sortByPriority(sortByPriorityScreen.deadLineForSorting.getText());
            }
        });
        filterTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddOrEditOrFilterInputScreen sortByPriorityScreen = new AddOrEditOrFilterInputScreen(OPERATION_FILTER_BY_DEADLINE);
                filterByDeadline(sortByPriorityScreen.deadlineStartingForFiltering.getText(), sortByPriorityScreen.deadlineEndingForFiltering.getText());

            }
        });

        viewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRowCount() != 1){
                    JOptionPane.showMessageDialog(null, "0'dan az veya 1'den fazla task (row) seçilemez. 1 tane row seçili olmalı");
                    return;
                }

                DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

                AddOrEditOrFilterInputScreen viewImageScreen = new AddOrEditOrFilterInputScreen(OPERATION_IMAGE, Integer.parseInt(tblModel.getValueAt(table1.getSelectedRow(), 0).toString()));
            }
        });

        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setSize(400, 400);

        operationBeforeOpeningAndClosingMainFramePage(jFrame);
    }


    public void oneDayLeftTasksNotification() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime noww = LocalDateTime.now();
        LocalDateTime tomorroww = noww.plusDays(1);

        Timestamp now = Timestamp.valueOf(noww);
        Timestamp tomorrow = Timestamp.valueOf(tomorroww);

        DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();

        ArrayList<String[]> selectedTasks = new ArrayList<>();

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            Timestamp deadlineOfCurrentLine = Timestamp.valueOf(String.valueOf(tblModel.getValueAt(i, 3)));

            if( (deadlineOfCurrentLine.after(now) || deadlineOfCurrentLine.equals(now) )
                    &&
                    (deadlineOfCurrentLine.before(tomorrow) || deadlineOfCurrentLine.equals(tomorrow) ) ){


                String[] row = new String[2];

                row[0] = String.valueOf(tblModel.getValueAt(i, 0));
                row[1] = String.valueOf(tblModel.getValueAt(i, 1));
                selectedTasks.add(row);

                System.out.println(deadlineOfCurrentLine + " - " + now + " - " + tomorrow);


            }
        }

        String notificationText = ("Aşağıdaki task'ların bitmesine tam 1 gün ya da 1 günden daha az süre kalmıştır: \n");

        for (int i = 0; i < selectedTasks.size(); i++) {
            notificationText += "   - >   Task name :  " + selectedTasks.get(i)[0] + "  -   Task ID : " + selectedTasks.get(0)[1] + "\n";
        }

        JOptionPane.showMessageDialog(null, notificationText);

    }

    public void filterByDeadline(String startingDeadline, String endingDeadline) {


        ArrayList<String[]> new_table = setTableByGivenDeadlines(startingDeadline, endingDeadline);


        clearTable();
        DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
        //tblModel.setRowCount(0);

        int i = 0;

        while (i < new_table.size()){
            tblModel.addRow(new_table.get(i));
            i++;
        }



    }

    public ArrayList<String[]> setTableByGivenDeadlines(String startingDeadline, String endingDeadline) {
        clearTable();
        initTable();

        DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
        ArrayList<String[]> new_table = new ArrayList<>();

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            Timestamp deadlineOfCurrentLine = Timestamp.valueOf(String.valueOf(tblModel.getValueAt(i, 3)));
            Timestamp startingDeadLine = Timestamp.valueOf(startingDeadline);
            Timestamp endingDeadLine = Timestamp.valueOf(endingDeadline);

            if( (deadlineOfCurrentLine.after(startingDeadLine) || deadlineOfCurrentLine.equals(startingDeadLine) )
                &&
                (deadlineOfCurrentLine.before(endingDeadLine) || deadlineOfCurrentLine.before(endingDeadLine) ) ){

                String[] row = new String[ tblModel.getColumnCount() ];

                for (int j = 0; j < tblModel.getColumnCount(); j++) {
                    row[j] = String.valueOf(tblModel.getValueAt(i, j));
                }

                new_table.add(row);
            }
        }

        for (int i = 0; i < new_table.size(); i++) {
            System.out.println(Arrays.toString(new_table.get(i)));
        }


        return new_table;
    }

    public int idNumberGenerator() {

        boolean idNoIsUsed;

        int i;
        for (i = 1; i <= table1.getRowCount(); i++) {
            idNoIsUsed = false;

            for (int j = 0; j < table1.getRowCount(); j++) {

                System.out.println("i - j: " + i + " " + j);
                System.out.println("i - value at table1: " + i + " " + table1.getValueAt(j, 0));

                if (table1.getValueAt(j, 0) != null)
                    if (i == Integer.parseInt(String.valueOf(table1.getValueAt(j, 0)))) {
                        idNoIsUsed = true;
                        break;
                    }
            }

                if(!idNoIsUsed)
                    return i;

            }

        return i;
    }


    public void initTable(){

        String sql = "SELECT * FROM tasks";
        try {
            //PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            int cols = rsmd.getColumnCount();

            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i+1);
            model.setColumnIdentifiers(colName);

            String ID, name, description,  deadline, priority, reminderImage, entryDate;

            while (rs.next()){
                ID = rs.getString(1);
                name = rs.getString(2);
                description = rs.getString(3);
                deadline = rs.getString(4);
                priority = rs.getString(5);
                reminderImage = rs.getString(6);
                entryDate = rs.getString(7);
                String[] row = {ID, name, description, deadline, priority, reminderImage, entryDate};
                model.addRow(row);
            }

            statement.close();
//           connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
    }

    public ArrayList<String[]> setTableByGivenDeadline(String deadline){

        clearTable();
        initTable();

        DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
        ArrayList<String[]> new_table = new ArrayList<>();

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            String deadlineOfCurrentLine = String.valueOf(tblModel.getValueAt(i, 3));

            if(deadline.contains(deadlineOfCurrentLine) || deadlineOfCurrentLine.contains(deadline)){
                String[] row = new String[ tblModel.getColumnCount() ];

                for (int j = 0; j < tblModel.getColumnCount(); j++) {
                    row[j] = String.valueOf(tblModel.getValueAt(i, j));
                }

                new_table.add(row);
            }
        }

        for (int i = 0; i < new_table.size(); i++) {
            System.out.println(Arrays.toString(new_table.get(i)));
        }


        return new_table;


    }

    public void sortByPriority(String deadline){

        ArrayList<String[]> new_table = setTableByGivenDeadline(deadline);

        System.out.println("\n\nsort by priority:");

        for (int i = 0; i < new_table.size(); i++) {

            // 1 is the highest priority

            String[] currentHighestPriorityRow = new_table.get(i);
            int selectedIndex = i;

            for (int j = i+1; j < new_table.size(); j++) {

                if(Integer.parseInt(new_table.get(j)[4]) < Integer.parseInt(currentHighestPriorityRow[4]) ){
                    currentHighestPriorityRow = new_table.get(j);
                    selectedIndex = j;
                }

            }

            String[] tmp_arr = new_table.get(i);
            new_table.set(i, new_table.get(selectedIndex));
            new_table.set(selectedIndex, tmp_arr);
        }


        for (int i = 0; i < new_table.size(); i++) {
            System.out.println(Arrays.toString(new_table.get(i)));
        }

        clearTable();
        DefaultTableModel tblModel = (DefaultTableModel) table1.getModel();
        //tblModel.setRowCount(0);

        int i = 0;

        while (i < new_table.size()){
            tblModel.addRow(new_table.get(i));
            i++;
        }

    }

    public void operationBeforeOpeningAndClosingMainFramePage(JFrame jFrame){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane.showConfirmDialog(null,"Are sure to close!");
                mysqlConnect.disconnect();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                oneDayLeftTasksNotification();
            }

        });
    }

    /*
    public static void main(String[] args) {
        new MainFrame();
    }
     */


}



