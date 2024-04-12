import java.time.LocalDate;
import java.util.Arrays;

public class Entry {

    private LocalDate date;
    private int numVisitors;
    private ResizableArrayBag<String> donations;

    public Entry() {
        this.date = LocalDate.now();
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }

    public Entry(LocalDate date) {
        this.date = date;
        this.numVisitors = 0;
        this.donations = new ResizableArrayBag<String>();
    }

    public Entry(LocalDate date, int numVisitors, ResizableArrayBag<String> donations) {
        this.date = date;
        this.numVisitors = numVisitors;
        this.donations = donations;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNumVisitors(int numVisitors) {
        this.numVisitors = numVisitors;
    }

    public void setDonations(ResizableArrayBag<String> donations) {
        this.donations = donations;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumVisitors() {
        return numVisitors;
    }

    public ResizableArrayBag<String> getDonations() {
        return donations;
    }

    public String toString() {
        return "Entry:\n" +
            "------------" +
            "Date: " + date + "\n" +
            "Number of Visitors: " + numVisitors + "\n" +
            "Donations" + Arrays.toString(donations.toArray()); + "\n";
    }
}
