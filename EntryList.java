import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class EntryList {
    private LinkedList<Entry> allEntries;
    private String filename;

    /**
     * Returns the name of the file this EntryList was loaded from/saves to
     * @return the name of the file this EntryList was loaded from/saves to
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns the list of all entries in this EntryList
     * @return the list of all entries in this EntryList
     */
    public LinkedList<Entry> getAllEntries() {
        return allEntries;
    }

    /**
     * Creates a new EntryList loaded from the specified file, or empty if no file is found
     * @param filenameToLoad the name of the file to load the EntryList from
     */
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

    /**
     * Loads the EntryList from the specified file
     * @param filenameToLoad the name of the file to load the EntryList from
     * @return true if the file successfully loaded, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean loadFromFile(String filenameToLoad) {
        try {
            FileInputStream fin = new FileInputStream(filenameToLoad);
            ObjectInputStream ois = new ObjectInputStream(fin);
            allEntries = (LinkedList<Entry>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            // System.out.println("Error in loading file: " + e);
            return false;
        } catch (ClassCastException e) {
            // System.out.println("Error in file content: " + e);
            return false;
        } catch (ClassNotFoundException e) {
            // System.out.println("Error in file content: " + e);
            return false;
        }

        return true;
    }

    /**
     * Saves this EntryList to the/a file specified by filename
     * @return true if the file was successfully saved, false otherwise
     */
    public boolean saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allEntries);
            oos.close();
        } catch (IOException e) {
            // System.err.println("Error in saving: " + e);
            return false;
        }

        return true;
    }

    /**
     * Adds a new entry to this EntryList's list of entries
     * @param date the date of the new entry
     * @param numVisitors the number of visitors in the new entry
     * @param donations the donations in the new entry
     */
    public void addEntry(LocalDate date, int numVisitors, ResizableArrayBag<String> donations) {
        allEntries.add(new Entry(date, numVisitors, donations));
    }
}
