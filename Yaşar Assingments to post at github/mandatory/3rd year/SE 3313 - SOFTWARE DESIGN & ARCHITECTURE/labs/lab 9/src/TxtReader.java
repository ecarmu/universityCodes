import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class TxtReader implements FileReaderr{



    @Override
    public void readFile(String path) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }

            System.out.println("File content:\n" + content.toString());
        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


    }

}
