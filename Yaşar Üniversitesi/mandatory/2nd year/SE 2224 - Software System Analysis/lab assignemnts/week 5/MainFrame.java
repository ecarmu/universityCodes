    package week5;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class MainFrame extends JFrame{
        private JPanel MainFramePanel;
        private JRadioButton comissionEmployeeRadioButton;
        private JRadioButton pieceWorkerRadioButton;
        private JRadioButton hourlyEmployeeRadioButton;
        private JRadioButton allRadioButton;

        private JTextField searchByIDNo;
        private JButton searchButton;
        private JButton ADDEMPLOYEEButton;
        private JButton EXITButton;
        private JLabel filter;
        private JScrollPane tablePanel;


        public static ArrayList<Employee> allEmployeesList = new ArrayList<>();
        public static ArrayList<PieceWorker> pieceWorkerArrayList = new ArrayList<>();
        public static ArrayList<HourlyEmployee> hourlyEmployeeArrayList = new ArrayList<>();
        public static ArrayList<ComissionEmployee> comissionEmployeeArrayList = new ArrayList<>();

        public String[][] tableStrArr;
        private JTable table;
        JFrame f;

        public MainFrame() {

            setContentPane(MainFramePanel);
            setTitle("Main");
            setSize(1000,500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            updateTableToCurrent("allEmployees.txt");

            ADDEMPLOYEEButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame();
                }
            });
            allRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    allRadioButton.setSelected(true);
                    pieceWorkerRadioButton.setSelected(false);
                    hourlyEmployeeRadioButton.setSelected(false);
                    comissionEmployeeRadioButton.setSelected(false);
                    updateTableToCurrent("allEmployees.txt");

                }
            });
            pieceWorkerRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    allRadioButton.setSelected(false);
                    pieceWorkerRadioButton.setSelected(true);
                    hourlyEmployeeRadioButton.setSelected(false);
                    comissionEmployeeRadioButton.setSelected(false);

                    updateTableToCurrent("pieceWorkers.txt");
                }
            });
            hourlyEmployeeRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    allRadioButton.setSelected(false);
                    pieceWorkerRadioButton.setSelected(false);
                    hourlyEmployeeRadioButton.setSelected(true);
                    comissionEmployeeRadioButton.setSelected(false);

                    updateTableToCurrent("hourlyWorkers.txt");
                    //readAndPopulateTable("C:\\Users\\ardah\\IdeaProjects\\SE-2224_week 3\\src\\week5\\hourlyWorkers.txt");
                }
            });
            comissionEmployeeRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    allRadioButton.setSelected(false);
                    pieceWorkerRadioButton.setSelected(false);
                    hourlyEmployeeRadioButton.setSelected(false);
                    comissionEmployeeRadioButton.setSelected(true);

                    updateTableToCurrent("comissionEmployees.txt");
                    //readAndPopulateTable("C:\\Users\\ardah\\IdeaProjects\\SE-2224_week 3\\src\\week5\\comissionEmployees.txt");
                }
            });

            setVisible(true);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = "";

                    for (int i = 0; i < tableStrArr.length; i++) {
                            id = tableStrArr[i][3];
                            if(searchByIDNo.getText().equals(id) ){
                                SearchResultFrame searchResultFrame = new SearchResultFrame(tableStrArr[i][3], tableStrArr[i][0], tableStrArr[i][1], tableStrArr[i][5], tableStrArr[i][6], tableStrArr[i][7], tableStrArr[i][4], tableStrArr[i][2] );
                                break;
                            }

                    }


                }
            });
            EXITButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }


        public static void writeToFile(ArrayList<?> list, String fileName) {
            try (FileWriter writer = new FileWriter(fileName, true)) {

                    writer.write(list.get(list.size() - 1).toString() + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateTableToCurrent(String filePath){
            tableStrArr= readAndPopulateTable(filePath);
            String column[]={"FIRST NAME","LAST NAME","SALARY"};
            JTable jt=new JTable(tableStrArr,column);



            jt.setSize(500, 500);
            jt.setVisible(true);

            jt.setLayout(new FlowLayout());

            tablePanel.setViewportView(jt);
        }


        public String[][] readAndPopulateTable(String filename) {
            try {
                Scanner scanner = new Scanner(new File(filename));
                ArrayList<String[]> lines = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(" ");
                    lines.add(data);
                }
                scanner.close();
                if (lines.isEmpty()) {
                    return new String[0][0];
                }
                String[][] result = new String[lines.size()][];
                for (int i = 0; i < lines.size(); i++) {
                    result[i] = lines.get(i);
                }
                return result;
            } catch (FileNotFoundException ex) {
                try {
                    File file = new File(filename);
                    file.createNewFile();
                    return new String[0][0];
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }


        public static void main(String[] args) {
            MainFrame mainFrame = new MainFrame();
        }
    }
