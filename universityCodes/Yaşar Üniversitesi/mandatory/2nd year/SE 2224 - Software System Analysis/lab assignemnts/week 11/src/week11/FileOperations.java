package week11;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {

    public static String isInCustomerFile(String text, char[] password){
        String passwordString = new String(password);

        File file = new File("patient.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader("patient.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4 && data[3].equals(text) && data[4].equals(passwordString)) {
                    System.out.println("Found match: " + line);
                    return line;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String isInDoctorFile(String text, char[] password){
        String passwordString = new String(password);

        File file = new File("doctor.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader("doctor.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 4 && data[3].equals(text) && data[4].equals(passwordString)) {
                    System.out.println("Found match: " + line);
                    return line;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isInAdminFile(){
        return true;
    }

    public static Patient createPatientFromFile() {
        return new Patient();
    }

    public static Doctor createDoctorFromFile() {
        return new Doctor();
    }

    public static int getLineNumber(String data, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                return -1;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 1;
            System.out.println("data: " + data);
            while ((line = reader.readLine()) != null) {
                System.out.println("line string: " + line);
                if (line.equals(data)) {
                    reader.close();
                    return lineNumber;
                }
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void deleteLineFromFile(String fileName, int lineNumber) {
        try {
            // Read the file
            System.out.println("line: " + lineNumber);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine != lineNumber) {
                    sb.append(line).append("\n");
                }
                currentLine++;
            }
            reader.close();

            // Write the updated file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static String[][] readAndPopulateTable(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            ArrayList<String[]> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\\|");
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

    public static void booksReduceAvailabilityReWriteBooksFile(String[] desiredData, String filepath, int length) {
        try {
            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(String.valueOf(desiredData[0])) &&
                        parts[1].equals(desiredData[1]) &&
                        parts[2].equals(desiredData[2]) &&
                        parts[3].equals(desiredData[3]) &&
                        parts[4].equals(desiredData[4]) &&
                        parts[5].equals(String.valueOf(desiredData[5])) &&
                        parts[6].equals(desiredData[6]) &&
                        parts[7].equals(desiredData[7])
                ) {
                    System.out.println("buraya girdi");
                    continue;
                }
                sb.append(line).append("\n");
            }
            reader.close();

            // Write the updated file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(sb.toString());
            writer.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }



    public static void saveTableToFile(String filePath, String[][] tableData) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            for (String[] row : tableData) {
                String rowString = String.join("|", row);
                writer.println(rowString);
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String[][] readAndFilterData(String[][] givenArr, String filePath, String wantedData, int desiredIndex) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Declare a 2D array to store the filtered data
        ArrayList<ArrayList<String>> filteredData = new ArrayList<>();

        // Read each line from the file and process it
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into individual data fields
            String[] dataFields = line.split("\\|");

            // Check if the desired data matches the value in the desired index
            if (dataFields[desiredIndex].equals(wantedData)) {
                // Store the data fields in the filteredData ArrayList
                ArrayList<String> filteredDataLine = new ArrayList<>();
                for (int i = 0; i < dataFields.length; i++) {
                    filteredDataLine.add(dataFields[i]);
                }
                filteredData.add(filteredDataLine);
            }
        }

        // Close the file reader
        reader.close();

        // Convert the ArrayList of ArrayLists to a 2D String array
        String[][] filteredDataFinal = new String[filteredData.size()][];
        for (int i = 0; i < filteredData.size(); i++) {
            ArrayList<String> filteredDataLine = filteredData.get(i);
            filteredDataFinal[i] = filteredDataLine.toArray(new String[filteredDataLine.size()]);
        }

        // Return the filtered data array
        return filteredDataFinal;
    }



}
