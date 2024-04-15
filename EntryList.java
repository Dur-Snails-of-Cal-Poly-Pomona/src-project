import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class EntryList {
    LinkedList<Entry> allEntries;
    String filename;

    EntryList(String filenameToLoad) {
        this.filename = filenameToLoad;
        // Load allEntries from file, make new allEntries if none found
        boolean loadSucceeded = loadFromFile(filenameToLoad);
        if (!loadSucceeded) {
            allEntries = new LinkedList<Entry>();
            File newFile = new File(filenameToLoad);
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                
            }
        }
    }

    @SuppressWarnings("unchecked")
    public boolean loadFromFile(String filenameToLoad) {
        try {
            FileInputStream fin = new FileInputStream(filenameToLoad);
            ObjectInputStream ois = new ObjectInputStream(fin);
            allEntries = (LinkedList<Entry>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            return false;
        } catch (ClassCastException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }

    public boolean saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allEntries);
            oos.close();
        } catch (IOException e) {
            System.err.println(e);
            return false;
        }

        return true;
    }

    public void addEntry(LocalDate date, int numVisitors, ResizableArrayBag<String> donations) {
        allEntries.add(new Entry(date, numVisitors, donations));
    }
}
