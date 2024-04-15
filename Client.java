import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client
{
    public static void main(String args[])
    {
        userOptions();
    }

    public static void userOptions()
    {
        Scanner input = new Scanner(System.in);
        boolean proceed = true;
        String userInput = "";

        String filename;
        System.out.print("Please input a file name (ending in .txt) to create/edit (leave blank for \"entries.txt\"): ");
        filename = input.nextLine().trim();
        while (filename.length() < 4 || !filename.substring(filename.length() - 4).equals(".txt")) {
            if (filename.length() == 0) {
                filename = "entries.txt";
            } else {
                System.out.print("File name must end in \".txt\", please try again: ");
                filename = input.nextLine().trim();
            }
        }

        EntryList entryList = new EntryList(filename);

        while(proceed)
        {
            System.out.println("\nSelect an option:");
            System.out.println("Type \"1\" To make a new entry.");
            System.out.println("Type \"2\" To display an entry or entries.");
            System.out.println("Type \"3\" To get a .csv (spreadsheet) file of all entries.");
            System.out.println("Type \"4\" To remove an entry or entries.");
            System.out.println("Type \"Q\" Quit the program");

            userInput = input.nextLine();

            if(userInput.equals("1")){
                addEntry(input, entryList);
            }
            else if(userInput.equals("2")){
                displayEntries(input, entryList);
            }
            else if(userInput.equals("3")){
                getCSV(input, entryList);
            }
            else if(userInput.equals("4")){
                removeEntry(input, entryList);
            }
            else if (userInput.equals("Q")){
                proceed = false;
            }
            else{
                System.out.println("Please enter a valid option.");
            }
        }
        input.close();    
    }

    public static void addEntry(Scanner input, EntryList entryList) {
        LocalDate date = null;
        System.out.print("\nEnter a Date (MM/DD/YYYY), leave blank for today: "); //add a barrier so it makes sure that date is valid.
        do {
            String dateInput = input.nextLine().trim();
            if (dateInput.length() == 0) {
                date = LocalDate.now();
            } else {
                String[] monthDayYear = dateInput.split("/");
                if (monthDayYear.length != 3) {
                    System.out.print("Please input the date in the format MM/DD/YYYY: ");
                } else {
                    try {
                        date = LocalDate.of(Integer.parseInt(monthDayYear[0]), 
                                            Integer.parseInt(monthDayYear[1]), 
                                            Integer.parseInt(monthDayYear[2]));
                    } catch (NumberFormatException e) {
                        System.out.print("Please use numbers to input the date in the format MM/DD/YYYY: ");
                    }
                }
            }
        } while (date == null);

        Integer numVisitors = null;
        System.out.print("Enter the number of visitors in this group (leave blank for 1): ");
        do {
            String numVisitorsInput = input.nextLine().trim();
            if (numVisitorsInput.length() == 0) {
                numVisitors = 1;
            } else {
                try {
                    numVisitors = Integer.parseInt(numVisitorsInput);
                } catch (NumberFormatException e) {
                    System.out.print("Please input only a number, no other characters: ");
                }
            }
        } while (numVisitors == null);

        System.out.print("Enter the donations from the visitor(s), separated by commas (leave blank for none): ");
        String donationsInput = input.nextLine();
        String[] allDonations = donationsInput.split(",");
        ResizableArrayBag<String> donationsBag = new ResizableArrayBag<>(allDonations.length + 5);
        for (String donation : allDonations)
            donationsBag.add(donation.trim());

        entryList.addEntry(date, numVisitors, donationsBag);
        boolean savedSuccessfully = entryList.saveToFile();

        System.out.println("\nEntry added " + (savedSuccessfully ?  "and saved successfully!" : "but was unable to save."));
    }

    public static void displayEntries(Scanner input, EntryList entryList) {

    }

    public static void getCSV(Scanner input, EntryList entryList) {
        
    }

    public static void removeEntry(Scanner input, EntryList entryList) {
        
    }
}