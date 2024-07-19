package final_project;

import java.awt.*;
import java.sql.Timestamp;
import java.util.Objects;
import javax.swing.*;

public class AddOrEditOrFilterInputScreen {

    public static final String OPERATION_EDIT = "Edit Row";
    public static final String OPERATION_ADD = "Add Row";
    public static final String OPERATION_SORT_BY_PRIORITY = "Sort Row (Task) by Priority";
    public static final String OPERATION_FILTER_BY_DEADLINE = "Filter Row (Task) by Deadline";
    public static final String OPERATION_IMAGE = "View Image of Row";


    // for adding or editing row
    JTextField id;
    JTextField name;
    JTextField description;
    JTextField deadline;
    JTextField priority;
    JTextField reminderImage;
    JTextField entryDate;

    // for sorting by priority
    JTextField deadLineForSorting;

    JTextField deadlineStartingForFiltering;
    JTextField deadlineEndingForFiltering;


    public AddOrEditOrFilterInputScreen(String operationName, int Id, String Name, String Description, Timestamp Deadline, int Priority, boolean ReminderImage, Timestamp EntryDate) {
        JFrame f = new JFrame(operationName);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.setResizable(false);
        f.setSize(400, 300);
        f.setLocationByPlatform(true);
        f.setVisible(false);

        if(operationName.equals(OPERATION_EDIT))
            editRow(f, Id, Name, Description, Deadline, Priority, ReminderImage, EntryDate);
    }

    public AddOrEditOrFilterInputScreen(String operationName) {
        JFrame f = new JFrame(operationName);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.setResizable(false);
        f.setSize(400, 300);
        f.setLocationByPlatform(true);
        f.setVisible(false);

        if(operationName.equals(OPERATION_ADD))
            addRow(f);
        if(operationName.equals(OPERATION_SORT_BY_PRIORITY))
            sortRowByPriority(f);
        if(operationName.equals(OPERATION_FILTER_BY_DEADLINE))
            filterRowByDeadline(f);
    }

    public AddOrEditOrFilterInputScreen(String operationName, int ID) {
        JFrame f = new JFrame(operationName);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.setResizable(false);
        f.setSize(400, 300);
        f.setLocationByPlatform(true);
        f.setVisible(false);

        if(operationName.equals(OPERATION_IMAGE))
            viewImageOfRow(f, ID);
    }

    public void addRow(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));

        labels.add(new JLabel("Name: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Description: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Deadline: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Priority: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Reminder Image: ", SwingConstants.TRAILING));

        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));


        name = new JTextField();
        controls.add(name);

        description = new JTextField();
        controls.add(description);


        deadline = new JTextField();
        controls.add(deadline);

        priority = new JTextField();
        controls.add(priority);

        reminderImage = new JTextField();
        controls.add(reminderImage);

        p.add(controls, BorderLayout.CENTER);

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        JOptionPane.showMessageDialog(frame, p, OPERATION_ADD + "(Task)", JOptionPane.QUESTION_MESSAGE);
    }

    public void editRow(JFrame frame, int Id, String Name, String Description, Timestamp Deadline, int Priority, boolean ReminderImage, Timestamp EntryDate)    {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("ID: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Name: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Description: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Deadline: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Priority: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Reminder Image: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Entry Date: ", SwingConstants.TRAILING));

        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        id = new JTextField(String.valueOf(Id));
        id.setEditable(false);
        controls.add(id);

        name = new JTextField(Name);
        controls.add(name);

        description = new JTextField(Description);
        controls.add(description);


        deadline = new JTextField(String.valueOf(Deadline));
        controls.add(deadline);

        priority = new JTextField(String.valueOf(Priority));
        controls.add(priority);

        reminderImage = new JTextField(String.valueOf(ReminderImage));
        controls.add(reminderImage);

        entryDate = new JTextField(String.valueOf(EntryDate));
        entryDate.setEditable(false);
        controls.add(entryDate);


        p.add(controls, BorderLayout.CENTER);

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        JOptionPane.showMessageDialog(frame, p, OPERATION_EDIT + "(Task)", JOptionPane.QUESTION_MESSAGE);

    }

    public void sortRowByPriority(JFrame frame) {
        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));
        labels.add(new JLabel("Deadline: ", SwingConstants.TRAILING));

        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));



        deadLineForSorting = new JTextField();
        controls.add(deadLineForSorting);

        p.add(controls, BorderLayout.CENTER);

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        JOptionPane.showMessageDialog(frame, p, OPERATION_SORT_BY_PRIORITY, JOptionPane.QUESTION_MESSAGE);
    }

    public void filterRowByDeadline(JFrame frame) {

        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel labels = new JPanel(new GridLayout(0,1,2,2));

        labels.add(new JLabel("Starting Deadline: ", SwingConstants.TRAILING));
        labels.add(new JLabel("Ending Deadline: ", SwingConstants.TRAILING));

        p.add(labels, BorderLayout.LINE_START);

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));


        deadlineStartingForFiltering = new JTextField();
        controls.add(deadlineStartingForFiltering);

        deadlineEndingForFiltering = new JTextField();

        controls.add(deadlineEndingForFiltering);

        p.add(controls, BorderLayout.CENTER);

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        JOptionPane.showMessageDialog(frame, p, OPERATION_FILTER_BY_DEADLINE, JOptionPane.QUESTION_MESSAGE);

    }

    public void viewImageOfRow(JFrame frame, int ID) {

        JPanel p = new JPanel(new BorderLayout(5,5));

        JPanel controls = new JPanel(new GridLayout(0,1,2,2));

        try{
            ImageIcon image1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/final_project/task_images/task" + ID + ".jpg")));
            JLabel label1 = new JLabel(image1);
            controls.add(label1);

            p.add(controls, BorderLayout.CENTER);

            //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
            JOptionPane.showMessageDialog(frame, p, OPERATION_IMAGE + "(Task)", JOptionPane.QUESTION_MESSAGE);

        }catch (Exception e){
            e.getMessage();
            //UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
            JOptionPane.showMessageDialog(null, "No image of this task (row) have found for ID: " + ID);
        }




    }

/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //new AddOrEditOrFilterInputScreen(OPERATION_ADD, 3, "a", "b", Timestamp.valueOf( "2023-05-05 10:00:00"), 2, false, Timestamp.valueOf( "2023-05-05 10:00:00"));
            new AddOrEditOrFilterInputScreen(OPERATION_IMAGE, 1);
        });
    }
 */




}
