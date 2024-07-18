package week4;

// FileOperation.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperation {
    public static void readCities(String filename, ArrayList<String> departure, ArrayList<String> destination) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isDeparture = true;
            while ((line = br.readLine()) != null) {
                if (line.equals("Departure")) {
                    isDeparture = true;
                } else if (line.equals("Destination")) {
                    isDeparture = false;
                } else {
                    if (isDeparture) {
                        departure.add(line);
                    } else {
                        destination.add(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
