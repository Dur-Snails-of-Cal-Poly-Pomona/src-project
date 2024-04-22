import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry implements Serializable {

    private LocalDate date;
    private int numVisitors;
    private ResizableArrayBag<String> donations;

    /** 
     * Creates empty log entry
     */
    public Entry() {
        this.date = LocalDate.now();
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }
    /** 
     * @param LocalDate date,
     */
    public Entry(LocalDate date) {
        this.date = date;
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }

    /** 
     * @param LocalDate date,
     * @param int numVisitors
     * @param ResizableArrayBag<String> donations
     */
    public Entry(LocalDate date, int numVisitors, ResizableArrayBag<String> donations) {
        this.date = date;
        this.numVisitors = numVisitors;
        this.donations = donations;
    }
    /** 
     * @param LocalDate date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /** 
     * @param int numVisitors
     */
    public void setNumVisitors(int numVisitors) {
        this.numVisitors = numVisitors;
    }

    
    /** 
     * @param donations
     */
    public void setDonations(ResizableArrayBag<String> donations) {
        this.donations = donations;
    }

    
    /** 
     * @return LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    
    /** 
     * @return int
     */
    public int getNumVisitors() {
        return numVisitors;
    }

    
    /** 
     * @return ResizableArrayBag<String>
     */
    public ResizableArrayBag<String> getDonations() {
        return donations;
    }

    
    /** 
     * @return String
     */
    public String toString() {

        String output = "Entry at " + date + ":\n" +
            " o Number of Visitors: " + numVisitors + "\n" +
            " o Donations:";

        for (Object donation : donations.toArray())
            output += "\n   - " + donation.toString();

        return output;
    }
}
