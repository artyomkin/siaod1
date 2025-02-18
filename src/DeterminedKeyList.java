import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeterminedKeyList implements IDeterminedIntegerKeyList {

    public List<Integer> keys;

    public DeterminedKeyList(){}

    public DeterminedKeyList(String filePath) throws FileNotFoundException {
        this.keys = loadFromFile(filePath);
    }

    public List<Integer> loadFromFile(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<Integer> keys = new ArrayList<>();
        try {
            String line = br.readLine();

            while (line != null) {
                keys.add(Integer.parseInt(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Expected Integer, but String found in file " + path);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return keys;
    }
}
