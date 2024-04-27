import java.io.File;
import java.time.LocalDate;

public class EntryListTest {
    /**
     * Test code for EntryList (and subsequently, Entry) class
     * @param args N/A
     */
    public static void main(String args[]) {

        // Test create new entrylist from no file
        EntryList newList = new EntryList("testfile.txt");
        System.out.println("Able to successfully create new list from no file: " + (newList.getAllEntries().getCurrentSize() == 0));

        // Test getFilename
        System.out.println("Able to successfully get filename: " + (newList.getFilename() == "testfile.txt"));

        // Test add entry to list, and read from .getAllEntries()
        newList.addEntry(LocalDate.now(), 5, new ResizableArrayBag<>());
        System.out.println("Able to successfully add new entry to list: " + (newList.getAllEntries().getEntry(1).getNumVisitors() == 5));
        
        // Test save to & load from file
        newList.saveToFile();
        EntryList newListFromFile = new EntryList("testfile.txt");
        System.out.println("Able to successfully save to and load from file: " + (newListFromFile.getAllEntries().getEntry(1).getNumVisitors() == 5));

        // Clean up created file
        File createdFile = new File("testfile.txt");
        createdFile.delete();
    }
}
