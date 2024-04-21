package project0semisterlongproject.ideas.idea2;

import project0semisterlongproject.LinkedList;
import project0semisterlongproject.Node;
import project0semisterlongproject.listinterface;
import project0semisterlongproject.ideas.idea1.donation;
import java.time.LocalDate;

public class donationlist<T> extends LinkedList<donation>
{
    private static int totalVisitors;
    private static double totalMoney;
    private static int totalClothes;
    private static int totalSeeds;
    private static int totalOther;
    private static int numberOfEntries ;
    private static String listdate;

    

    public donationlist()
    {
        totalVisitors = 0;
        totalMoney = 0;
        totalClothes = 0;
        totalSeeds = 0;
        totalOther = 0;
        numberOfEntries = 0;
        listdate = LocalDate.now().toString();
    }

    public void clearDonations() 
    {
        totalVisitors = 0;
        totalMoney = 0;
        totalClothes = 0;
        totalSeeds = 0;
        totalOther = 0;
        numberOfEntries = 0;
        super.clear();
        System.out.println("Donations have been cleared");
    }
    
    
    @Override
    public void add(donation newDonation) 
    {
        super.add(newDonation);
        addToTotal(newDonation);
    }

    private void addToTotal(donation donation) 
    {
        totalVisitors += donation.getGroupsize();
        totalMoney += donation.getMoney();
        totalClothes += donation.getClothes();
        totalSeeds += donation.getSeeds();
        totalOther += donation.getOther();
        numberOfEntries++;
    }

    @Override
    public donation remove(int givenPosition)
    {
       
        if (this.isEmpty() || numberOfEntries <= 0)
        {
            System.out.println("there is nothing to remove");
            return null;
        }
        else
        {
            donation result = super.remove(givenPosition);
            totalVisitors -= result.getGroupsize();
            totalMoney -= result.getMoney();
            totalClothes -= result.getClothes();
            totalSeeds -= result.getSeeds();
            totalOther -= result.getOther();


            System.out.println("Donation # "+ numberOfEntries + " has been removed. " + result.toString());

            numberOfEntries--;
            return result;
        }
    }


    public static void printtotal()
    {
        System.out.print("TotalVisitors " + totalVisitors +" ,");
        System.out.print("totalMoney " + totalMoney +" ,");
        System.out.print("totalClothes " + totalClothes +" ,");
        System.out.print("totalSeeds " + totalSeeds +" ,");
        System.out.println("totalOther " + totalOther);
    }

    public static void printat (LinkedList<donation> donations, int index) 
    {
        donation entry = donations.getEntry(index);
        if (entry != null) 
        {
            System.out.print(index + ": ");
            entry.printString();
        } else 
        {
            System.out.println("Entry at position " + index + " does not exist.");
        }
    }

    public void showsummary()
    {
        printListall();
        printtotal();
        print("This donation box was created on: "+listdate);
        print("the total number of entries are: "+ numberOfEntries);
    }
    private void print(String a)
    {
        System.out.println(a);
    }

    public static int getTotalVisitors() {
        return totalVisitors;
    }

    public static double getTotalMoney() {
        return totalMoney;
    }

    public static int getTotalClothes() {
        return totalClothes;
    }

    public static int getTotalSeeds() {
        return totalSeeds;
    }

    public static int getTotalOther() {
        return totalOther;
    }

    public static int getNumberOfEntries() {
        return numberOfEntries;
    }

    public static String getListdate() {
        return listdate;
    }
}
    

    
   


