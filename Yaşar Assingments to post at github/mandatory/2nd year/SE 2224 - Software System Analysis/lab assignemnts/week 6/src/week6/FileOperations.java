package week6;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
    public static <T> void writeToFile(T data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data.toString());
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void reWriteToFile(String fileName) {

            try {
                // Read the file
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();

                // Remove data elements from last line
                String[] parts = sb.toString().split("\n");
                String lastLine = parts[parts.length - 1];
                String[] lastLineParts = lastLine.split("\\|"); // split using "|" as delimiter
                lastLineParts[2] = "";
                lastLineParts[3] = "";
                lastLineParts[6] = "";
                lastLineParts[7] = "";
                StringBuilder updatedLastLine = new StringBuilder();
                for (String part : lastLineParts) {
                    if (!part.isEmpty()) {
                        updatedLastLine.append(part).append("|"); // separate using "|" as delimiter
                    }
                }
                parts[parts.length - 1] = updatedLastLine.toString().trim();
                sb = new StringBuilder();
                for (String part : parts) {
                    sb.append(part).append("\n");
                }

                // Write the updated file
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(sb.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    public static boolean isDataInFile(String data, String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
                return false;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields using the '|' character and check if the 0th field matches the desired data
                String[] fields = line.split("\\|");
                if (fields.length > 0 && fields[0].equals(data)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isDataInFileForAddingBookAlreadyExists(String data1, String data2, String data3, String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
                return false;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields using the '|' character and check if the 0th field matches the desired data
                String[] fields = line.split("\\|");
                if (fields.length > 0 && fields[0].equals(data1) && fields[1].equals(data2) && fields[3].equals(data3)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void booksIncreaseAvailabilityReWriteBooksFile(String desiredData1, String desiredData2, String desiredData3, String filepath, String availableCopyAmount) {
        try {
            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(desiredData1) && parts[1].equals(desiredData2) && parts[3].equals(desiredData3)) {
                    int availability = Integer.parseInt(parts[5]);
                    if (availability > 1) {
                        parts[5] = String.valueOf(availability + Integer.parseInt(availableCopyAmount));
                        line = String.join("|", parts);
                    } else {
                        continue;
                    }
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



    public static void addBookToLine(String lineToCheck, Book newBook, String filePath) {
        try {
            File file = new File(filePath);
            boolean fileExists = file.exists();

            if (!fileExists) {
                file.createNewFile();
            }

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (line.startsWith(lineToCheck)) {
                    // lineToCheck exists in the file, so add newBook to the existing line
                    int index = line.lastIndexOf("]");
                    line = line.substring(0, index) + ", " + newBook.toString() + line.substring(index);
                }
                sb.append(line).append("\n");
            }

            br.close();
            fr.close();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(sb.toString());

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static ArrayList<Book> borrowSelectedBooks(String title, String filepath) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into fields using the '|' character
                String[] fields = line.split("\\|");
                if (fields.length > 0 && fields[0].equals(title)) {
                    // Create a Book object from the fields
                    String bookTitle = fields[0];
                    Author author = new Author(fields[1]);
                    String isbn = fields[2];
                    String publicationDate = fields[3];
                    Publisher publisher = new Publisher(fields[4]);
                    int availableCopies = Integer.parseInt(fields[5]);
                    Book book = new Book(bookTitle, author, isbn, publicationDate, publisher, availableCopies);
                    books.add(book);
                }
            }
            reader.close();
        } catch (IOException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }

        return books;
    }


    public static void booksReduceAvailabilityReWriteBooksFile(String desiredData, String filepath) {
        try {
            // Read the file
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(desiredData)) {
                    int availability = Integer.parseInt(parts[5]);
                    if (availability > 1) {
                        parts[5] = String.valueOf(availability - 1);
                        line = String.join("|", parts);
                    } else {
                        continue;
                    }
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




    public static String[] readFirstData(String filename) {
        ArrayList<String> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length > 0) {
                    dataList.add(data[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList.toArray(new String[0]);
    }

    public static String[] findDataFromFile(String desiredData, String filename) {
        ArrayList<String[]> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                System.out.println("bu line'daki datalar: \n");
                for (String s: data) {
                    System.out.print(s + ' ');
                }
                if (data.length > 0 && data[0].equals(desiredData)) {
                    return data;
                    //dataList.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        String[] returnedData = new String[dataList.size()];

        for (int i = 0; i < dataList.size(); i++) {
            returnedData[i] = String.join(" ", dataList.get(i));
        }

        return returnedData;

         */
        return new String[0];
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



}
