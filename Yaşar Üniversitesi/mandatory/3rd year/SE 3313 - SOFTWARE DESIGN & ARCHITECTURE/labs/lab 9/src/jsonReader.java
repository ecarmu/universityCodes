import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class jsonReader implements Service{
    @Override
    public void readDataFile(String path) {

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            System.out.println("Value from JSON: " + jsonNode.toPrettyString());
        }catch (IOException e) {
            System.err.println("Error reading the JSON file: " + e.getMessage());
        }

    }
}
