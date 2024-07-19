package final_project;

import java.io.*;

public class FileOperations {

    public static String isInUserFile(String text, char[] password){
        String passwordString = new String(password);
        String filePath = "account.txt";
        String account = "a|b";

        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(account);
                writer.close();
            }

            BufferedReader reader = new BufferedReader(new FileReader("account.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 2 && data[0].equals(text) && data[1].equals(passwordString)) {
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
}
