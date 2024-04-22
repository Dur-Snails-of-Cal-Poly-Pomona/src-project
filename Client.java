import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Client
{
    final static String DEFAULT_FILE_NAME = "entries.txt";

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
        System.out.print("Please input a file name (ending in .txt) to create/edit (leave blank for \"" + DEFAULT_FILE_NAME + "\"): ");
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
                addOrEditEntry(input, entryList);
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
            else if (userInput.toUpperCase().equals("Q")){
                proceed = false;
            }
            else{
                System.out.println("Please enter a valid option.");
            }
        }
        input.close();    
    }

    public static void addNewEntry(LocalDate date, Scanner input, EntryList entryList) {
        Integer numVisitors = null;
        System.out.print("Enter the number of visitors this day (leave blank for 1): ");
        do {
            String numVisitorsInput = input.nextLine().trim();
            if (numVisitorsInput.length() == 0) {
                numVisitors = 1;
            } else {
                try {
                    numVisitors = Integer.parseInt(numVisitorsInput);
                    if (numVisitors < 0) {
                        System.out.print("Please input a non-negative number: ");
                        numVisitors = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Please input only a number, no other characters: ");
                }
            }
        } while (numVisitors == null);

        System.out.print("Enter the donations from the day, separated by commas (leave blank for none): ");
        String donationsInput = input.nextLine();
        String[] allDonations = donationsInput.split(",");
        ResizableArrayBag<String> donationsBag = new ResizableArrayBag<>(allDonations.length + 5);
        for (String donation : allDonations)
            donationsBag.add(donation.trim());

        entryList.addEntry(date, numVisitors, donationsBag);
        boolean savedSuccessfully = entryList.saveToFile();

        System.out.println("\nEntry added " + (savedSuccessfully ?  "and saved successfully to " + entryList.filename + "!"  : "but was unable to save."));
    }

    public static void editEntry(LocalDate date, Entry entry, int entryIndex, Scanner input, EntryList entryList) {
        boolean proceed = true;

        while (proceed) {
            System.out.println(
                "\nOptions:\n" +
                "1: Add or subtract number to visitors\n" +
                "2: Set visitor count\n" +
                "3: Add donation(s)\n" +
                "4: Clear all donations\n" +
                "5: Change date\n" +
                "6: Remove entry\n" +
                "Q or leave blank: Back to main menu"
            );

            String userInput = input.nextLine();

            if (userInput.equals("1")) {
                Integer numVisitorsToAdd = null;
                System.out.print("\nEnter the number of visitors this day (leave blank for 1): ");

                do {
                    String numVisitorsInput = input.nextLine().trim();
                    if (numVisitorsInput.length() == 0)
                        numVisitorsToAdd = 1;
                    else try {
                        numVisitorsToAdd = Integer.parseInt(numVisitorsInput);
                        if (entry.getNumVisitors() + numVisitorsToAdd < 0) {
                            System.out.print("Unable to set visitors to negative (" + (entry.getNumVisitors() + numVisitorsToAdd) + ") (currently " + entry.getNumVisitors() + "): ");
                            numVisitorsToAdd = null;
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Please input only a number, no other characters: ");
                    }
                } while (numVisitorsToAdd == null);

                entry.setNumVisitors(entry.getNumVisitors() + numVisitorsToAdd);
                System.out.println("\nSuccessfully added " + numVisitorsToAdd + " visitors (now " + entry.getNumVisitors() + ").");

            } else if (userInput.equals("2")) {
                Integer numVisitors = null;
                System.out.print("Enter the number of visitors this day (leave blank for 1): ");
                do {
                    String numVisitorsInput = input.nextLine().trim();
                    if (numVisitorsInput.length() == 0)
                        numVisitors = 1;
                    else try {
                        numVisitors = Integer.parseInt(numVisitorsInput);
                        if (numVisitors < 0) {
                            System.out.print("Please input a non-negative number: ");
                            numVisitors = null;
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Please input only a number, no other characters: ");
                    }
                } while (numVisitors == null);

                entry.setNumVisitors(numVisitors);
                System.out.println("\nSuccessfully set the number of visitors to " + numVisitors + ".");

            } else if (userInput.equals("3")) {
                // TODO: Add donation(s) (leave blank to cancel, divide with commas)
            } else if (userInput.equals("4")) {
                System.out.println("Are you sure you want to clear all donations? (Y or Yes for yes, anything else for no)");
                String userChoice = input.nextLine();
                if (userChoice.toUpperCase().equals("YES") || userChoice.toUpperCase().equals("Y")) {
                    entry.getDonations().clear();
                    System.out.println("Successfully cleared all donations for this entry.");
                } else {
                    System.out.println("Donations were not cleared for this entry.");
                }
            } else if (userInput.equals("5")) {
                System.out.print("Enter a new date in the format MM/DD/YYYY, leave blank for today: ");

                LocalDate newDate = promptDate(input, true);
                
                Entry entryAtNewDate = null;
                Integer newEntryIndex = null;
                // TODO: Make this loop backwards (& above)
                for (int i = 1; i <= entryList.allEntries.getCurrentSize(); i++) {
                    Entry newEntry = entryList.allEntries.getEntry(i);
                    if (entry.getDate().equals(date)) {
                        entryAtNewDate = newEntry;
                        newEntryIndex = i;
                        break;
                    }
                }

                if (newEntryIndex == null) {
                    entry.setDate(newDate);
                    System.out.println("Successfully changed date of entry.");
                } else {
                    System.out.println("There is already an entry at that date that has the following data:");

                    System.out.println("\nWould you like to:\n1: Merge these entries\n2: Replace the entry at the new date\n3 or leave blank: Cancel");
                    
                }
                
                // TODO: Change date (if new date already has an entry: prompt to merge, replace, or cancel)
            } else if (userInput.equals("6")) {
                System.out.println("Are you sure you want to remove this element? (Y or Yes for yes, anything else for no)");
                String userChoice = input.nextLine();
                if (userChoice.toUpperCase().equals("YES") || userChoice.toUpperCase().equals("Y")) {
                    entryList.allEntries.remove(entryIndex);
                    System.out.println("Successfully deleted entry.");
                    proceed = false;
                } else {
                    System.out.println("Entry was not deleted.");
                }
            } else if (userInput.toUpperCase().equals("Q")) {
                proceed = false;
            } else {
                System.out.println("Please enter a valid option.");
            }
        }
    }

    public static LocalDate promptDate(Scanner input, boolean leaveBlankForToday) {
        LocalDate date = null;
        do {
            String dateInput = input.nextLine().trim();
            if (dateInput.length() == 0 && leaveBlankForToday) {
                date = LocalDate.now();
            } else {
                String[] monthDayYear = dateInput.split("/");
                if (monthDayYear.length != 3) {
                    System.out.print("Please input the date in the format MM/DD/YYYY: ");
                } else {
                    try {
                        date = LocalDate.of(Integer.parseInt(monthDayYear[2]), 
                                            Integer.parseInt(monthDayYear[0]), 
                                            Integer.parseInt(monthDayYear[1]));
                    } catch (NumberFormatException e) {
                        System.out.print("Please use numbers to input the date in the format MM/DD/YYYY: ");
                    }
                }
            }
        } while (date == null);

        return date;
    }

    public static void addOrEditEntry(Scanner input, EntryList entryList) {
        System.out.print("\nEnter a Date (MM/DD/YYYY), leave blank for today: "); //add a barrier so it makes sure that date is valid.

        LocalDate date = promptDate(input, true);
        Entry entryAtDate = null;
        Integer entryIndex = null;
        for (int i = 1; i <= entryList.allEntries.getCurrentSize(); i++) {
            Entry entry = entryList.allEntries.getEntry(i);
            if (entry.getDate().equals(date)) {
                entryAtDate = entry;
                entryIndex = i;
                break;
            }
        }
        if (entryAtDate != null) {
            System.out.println("\nThat entry already exists, and has the following data:");

            // TODO: Display that entry
            
            editEntry(date, entryAtDate, entryIndex, input, entryList);
        } else {
            addNewEntry(date, input, entryList);
        }
    }

    public static void displayEntries(Scanner input, EntryList entryList) {
        System.out.print("\nEnter a Date (MM/DD/YYYY), leave blank for today: "); 
        LocalDate date = promptDate(input, true);

        System.out.println("All Entries with the Date: " + date);

        for(int i = 0; i < entryList.allEntries.getCurrentSize()+1; i++)
        {
            Entry entry = entryList.allEntries.getEntry(i);
            if(entryList.allEntries.getEntry(i) == null)
            {

            }
            else if(entryList.allEntries.getEntry(i).getDate().equals(date))
            {
                System.out.println(entryList.allEntries.getEntry(i));   
            }
        }
    }

    public static void getCSV(Scanner input, EntryList entryList) {
        File csvFile = new File("entries.csv");
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);

            fileWriter.append("Date,Number of Visitors,Donations\n");
            for (int i = 1; i <= entryList.allEntries.getCurrentSize(); i++) {
                Entry currentEntry = entryList.allEntries.getEntry(i);
                fileWriter.append(currentEntry.getDate() + "," + 
                                  currentEntry.getNumVisitors());
                ResizableArrayBag<String> donations = currentEntry.getDonations();
                ResizableArrayBag<String> tempBag = new ResizableArrayBag<>();
                while (!donations.isEmpty()) {
                    String donation = donations.remove();
                    fileWriter.append("," + donation);
                    tempBag.add(donation);
                }
                while (!tempBag.isEmpty())
                    donations.add(tempBag.remove());
                fileWriter.append("\n");
            }

            if (!csvFile.exists())
                csvFile.createNewFile();

            fileWriter.close();

            System.out.println("Successfully saved to entries.csv!");
        } catch (IOException e) { 
            System.out.println("Error in saving file to csv");
        }
    }

    public static void removeEntry(Scanner input, EntryList entryList) {
        
    }
}