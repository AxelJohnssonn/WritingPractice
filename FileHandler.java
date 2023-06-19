import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private ArrayList<String> list;
    private File textFile;

    public FileHandler(File textFile) {
        list = new ArrayList<String>();
        this.textFile = textFile;
    }

    public boolean addWords() {

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
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

    public ArrayList<String> getWords() {
        if(list.isEmpty()) {
            System.out.println("List of words is empty, Please select another textfile");
        }
        return list;
    }
}
