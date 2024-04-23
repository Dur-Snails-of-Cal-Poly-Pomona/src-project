import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry implements Serializable {

    private LocalDate date;
    private int numVisitors;
    private ResizableArrayBag<String> donations;

    /** 
     * Creates empty log entry with today's date, 0 visitors, and an empty bag of donations
     */
    public Entry() {
        this.date = LocalDate.now();
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }
    /** 
     * Creates empty log entry with specified date, 0 visitors, and an empty bag of donations
     * 
     * @param date the date for the new entry
     */
    public Entry(LocalDate date) {
        this.date = date;
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }

    /** 
     * Creates empty log entry with specified date, specified number of visitors, and specified bag of donations
     * 
     * @param date the date for the new entry
     * @param numVisitors the number of visitors for the new entry
     * @param donations the donations for the new entry
     */
    public Entry(LocalDate date, int numVisitors, ResizableArrayBag<String> donations) {
        this.date = date;
        this.numVisitors = numVisitors;
        this.donations = donations;
    }
    /** 
     * Sets the date of the entry
     * 
     * @param date the date to change in the entry
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /** 
     * Sets the number of visitors in the entry
     * 
     * @param numVisitors the number of visitors to change in the entry
     */
    public void setNumVisitors(int numVisitors) {
        this.numVisitors = numVisitors;
    }

    
    /** 
     * Sets the donations in the entry
     * 
     * @param donations the donations to change in the entry
     */
    public void setDonations(ResizableArrayBag<String> donations) {
        this.donations = donations;
    }

    
    /** 
     * Returns the date of the entry
     * 
     * @return the date of the entry
     */
    public LocalDate getDate() {
        return date;
    }

    
    /** 
     * Returns the number of visitors of the entry
     * 
     * @return the number of visitors of the entry
     */
    public int getNumVisitors() {
        return numVisitors;
    }

    
    /** 
     * Returns the donations of the entry
     * 
     * @return the donations of the entry
     */
    public ResizableArrayBag<String> getDonations() {
        return donations;
    }

    
    /** 
     * Returns the contents of the entry in a String format
     * 
     * @return the entry in String format
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
