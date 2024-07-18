package week10;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
    public static void writeToLogin(String fileName, String username, String password) {

        File file = new File(fileName);

        try {
            // Create the file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open a FileWriter to write to the file
            FileWriter writer = new FileWriter(file, true); // true appends to the file instead of overwriting it

            // Write the entered data to a line in the file
            writer.write(username + "," + password + "\n");

            // Close the FileWriter to release resources
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkLogin(String fileName, String username, String password) {

        File file = new File(fileName);

        try {
            // Open a BufferedReader to read from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into its username and password components
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    // Skip this line if it doesn't have at least two parts
                    continue;
                }
                String storedUsername = parts[0];
                String storedPassword = parts[1];

                // Check if the given username and password match the stored username and password
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    // Close the BufferedReader to release resources
                    reader.close();
                    return true;
                }
            }

            // Close the BufferedReader to release resources
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean checkAccountExists(String fileName, String username, String password) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            // Open a BufferedReader to read from the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into its username and password components
                String[] parts = line.split(",");

                // Check if the line contains both the username and password
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];

                    // Check if the given username and password match the stored username and password
                    if (username.equals(storedUsername)) {
                        // Close the BufferedReader to release resources
                        reader.close();
                        return true;
                    }
                }
            }

            // Close the BufferedReader to release resources
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void writeToFile(String fileName, String message) {

        File file = new File(fileName);

        try {
            // Create the file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Open a FileWriter to write to the file
            FileWriter writer = new FileWriter(file, true); // true appends to the file instead of overwriting it

            // Write the entered data to a line in the file
            writer.write(message + "\n");

            // Close the FileWriter to release resources
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String replaceNewlines(String input) {
        String[] lines = input.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i > 0 && i < lines.length - 1) {
                sb.append(" ");
            }
            sb.append(lines[i]);
        }
        return sb.toString();
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


    public static void deleteLineFromFile(String fileName, String lineToRemove) {
        try {
            // Open the input file
            File inputFile = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            // Open the temporary file
            File tempFile = new File("temp.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);

            String line;
            int lineNumber = 0;
            boolean lineFound = false;

            // Read through the input file line by line
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                // If the line contains the input string, set a flag
                if (line.contains(lineToRemove)) {
                    lineFound = true;
                } else {
                    // Otherwise, write the line to the temporary file
                    printWriter.println(line);
                }
            }

            // Close the input and temporary files
            bufferedReader.close();
            printWriter.close();
            fileInputStream.close();
            fileOutputStream.close();

            if (!lineFound) {
                // If the input string was not found, delete the temporary file
                tempFile.delete();
            } else {
                // Otherwise, rename the temporary file to the input file
                inputFile.delete();
                tempFile.renameTo(inputFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

