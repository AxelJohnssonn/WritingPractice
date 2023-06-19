import java.io.File;
import java.util.ArrayList;

public class WordHandler {

    private FileHandler fh;
    private ArrayList<String> list;
    private int startIndex;

    public WordHandler(FileHandler fh) {
        this.fh = fh;
        fh.addWords();
        this.list = fh.getWords();
        startIndex = 0;
    }

    public ArrayList<String> getTenWords() {
        ArrayList<String> listOfTen = new ArrayList<>();
        for(int i = startIndex; i < startIndex + 10 && i < list.size(); i++) {
            listOfTen.add(list.get(i));
            
        }
        startIndex = startIndex + 10;
        return listOfTen;
    }

    public void updateList() {
        list = fh.getWords();
    }
    
    public String getStringTenWords() {
        StringBuilder sb = new StringBuilder();
        int currentSize = 0;
        for(String s : getTenWords()) {
            if(currentSize > 18 && currentSize < 33){
                sb.append("\n");
                currentSize = 0;
            }
                sb.append(s);
                sb.append("\u2423");
                currentSize = currentSize + s.length() + 1;
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
