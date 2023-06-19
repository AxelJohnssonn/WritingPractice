import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private ArrayList<String> list;

    public FileHandler() {
        list = new ArrayList<String>();

    }

    public boolean addWords(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
