import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            System.out.println("\n----------------\n");
            System.out.println("Select an option:");
            System.out.println("Type \"1\" To create, edit, or remove an entry.");
            System.out.println("Type \"2\" To display an entry or entries.");
            System.out.println("Type \"3\" To get a .csv (spreadsheet) file of all entries.");
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
        for (String donation : allDonations) {
            donation = donation.trim();
            if (donation != "") donationsBag.add(donation);
        }

        entryList.addEntry(date, numVisitors, donationsBag);
        boolean savedSuccessfully = entryList.saveToFile();

        System.out.println("\nEntry added " + (savedSuccessfully ?  "and saved successfully to " + entryList.getFilename() + "."  : "but was unable to save."));
    }

    public static void editEntry(LocalDate date, Entry entry, int entryIndex, Scanner input, EntryList entryList) {
        boolean proceed = true;

        while (proceed) {
            boolean saveAfter = true;
            System.out.println("\n------------------------------\n\nCurrently editing:\n");
            System.out.println(entry);
            System.out.println(
                "\nEditing Options:\n" +
                "Type \"1\" To add or subtract number to visitors\n" +
                "Type \"2\" To set visitor count\n" +
                "Type \"3\" To add donation(s)\n" +
                "Type \"4\" To clear all donations\n" +
                "Type \"5\" To change date\n" +
                "Type \"6\" To remove entry\n" +
                "Type \"Q\" To go back to main menu"
            );

            String userInput = input.nextLine();

            if (userInput.equals("1")) {
                Integer numVisitorsToAdd = null;
                System.out.print("\nEnter the number of visitors to add (negative to subtract, leave blank for 1): ");

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
                System.out.print("\nAdded " + numVisitorsToAdd + " visitors (now " + entry.getNumVisitors() + ")");

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
                System.out.print("\nSet the number of visitors to " + numVisitors);

            } else if (userInput.equals("3")) {
                System.out.print("\nEnter the donations to add, separated by commas (leave blank for none): ");
                String donationsInput = input.nextLine();
                String[] allDonations = donationsInput.split(",");
                
                for (String donation : allDonations) {
                    donation = donation.trim();
                    if (donation != "") entry.getDonations().add(donation);
                }

                System.out.print("\nAdded donations to entry");
            } else if (userInput.equals("4")) {
                System.out.println("\nAre you sure you want to clear all donations? (Y or Yes for yes, anything else for no)");
                String userChoice = input.nextLine();
                if (userChoice.toUpperCase().equals("YES") || userChoice.toUpperCase().equals("Y")) {
                    entry.getDonations().clear();
                    System.out.print("\nCleared all donations for this entry ");
                } else {
                    System.out.println("\nDonations were not cleared for this entry.");
                    saveAfter = false;
                }
            } else if (userInput.equals("5")) {
                System.out.print("\nEnter a new date in the format MM/DD/YYYY, leave blank for today: ");

                LocalDate newDate = promptDate(input, true);
                
                Entry entryAtNewDate = null;
                Integer newEntryIndex = null;
                for (int i = entryList.getAllEntries().getCurrentSize(); i > 0; i--) {
                    Entry newEntry = entryList.getAllEntries().getEntry(i);
                    if (newEntry != null && newEntry.getDate().equals(newDate)) {
                        entryAtNewDate = newEntry;
                        newEntryIndex = i;
                        break;
                    }
                }


                if (newEntryIndex == null) {
                    entry.setDate(newDate);
                    System.out.print("\nChanged date of entry");
                    proceed = false;
                } else if (newEntryIndex == entryIndex) {
                    System.out.println("\nThat is the same date.");
                    saveAfter = false;
                } else {
                    System.out.println("" + newEntryIndex + " " + entryIndex);
                    System.out.println("\nThere is already an entry at that date that has the following data:\n");
                    System.out.println(entryAtNewDate);
                    System.out.println("\nWould you like to:\n1: Merge these entries\n2: Replace the entry at the new date\nAnything else: Cancel");
                    
                    String dateChangeUserInput = input.nextLine();

                    if (dateChangeUserInput.trim().equals("1")) {
                        entryAtNewDate.setNumVisitors(entryAtNewDate.getNumVisitors() + entry.getNumVisitors());
                        while (!entry.getDonations().isEmpty())
                            entryAtNewDate.getDonations().add(entry.getDonations().remove());
                        entryList.getAllEntries().remove(entryIndex);

                        System.out.print("\nMerged entries");
                        proceed = false;
                    } else if (dateChangeUserInput.trim().equals("2")) {
                        entryList.getAllEntries().replace(newEntryIndex, entry);
                        entry.setDate(newDate);
                        entryList.getAllEntries().remove(entryIndex);
                        System.out.print("\nOverwrote entry at date with this entry");
                        proceed = false;
                    } else if (dateChangeUserInput.toUpperCase().equals("Q")) {
                        System.out.println("\nDate was not changed.");
                        saveAfter = false;
                    }
                }
                
                // TODO: Change date (if new date already has an entry: prompt to merge, replace, or cancel)
            } else if (userInput.equals("6")) {
                System.out.println("\nAre you sure you want to remove this element? (Y or Yes for yes, anything else for no)");
                String userChoice = input.nextLine();
                if (userChoice.toUpperCase().equals("YES") || userChoice.toUpperCase().equals("Y")) {
                    entryList.getAllEntries().remove(entryIndex);
                    System.out.print("\nDeleted entry");
                    proceed = false;
                } else {
                    System.out.println("\nEntry was not deleted.");
                    saveAfter = false;
                }
            } else if (userInput.toUpperCase().equals("Q")) {
                proceed = false;
                saveAfter = false;
            } else {
                System.out.println("Please enter a valid option.");
                saveAfter = false;
            }

            if (saveAfter) {
                if (entryList.saveToFile()) {
                    System.out.println(" and saved successfully to " + entryList.getFilename() + ".");
                } else {
                    System.out.println(" but was unable to save.");
                }
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
        for (int i = entryList.getAllEntries().getCurrentSize(); i > 0; i--) {
            Entry entry = entryList.getAllEntries().getEntry(i);
            if (entry != null && entry.getDate().equals(date)) {
                entryAtDate = entry;
                entryIndex = i;
                break;
            }
        }
        if (entryAtDate != null) {
            System.out.println("\nThat entry already exists, now editing entry.");
            
            editEntry(date, entryAtDate, entryIndex, input, entryList);
        } else {
            addNewEntry(date, input, entryList);
        }
    }

    public static void displayEntries(Scanner input, EntryList entryList) {
        System.out.print("\nEnter a Date (MM/DD/YYYY), leave blank for today: "); 
        LocalDate date = promptDate(input, true);

        ArrayList<Entry> entriesAtDate = new ArrayList<>();

        for(int i = 0; i < entryList.getAllEntries().getCurrentSize()+1; i++)
        {
            Entry entry = entryList.getAllEntries().getEntry(i);
            if(entry != null && entry.getDate().equals(date))
            {
                entriesAtDate.add(entry);  
            }
        }

        if (entriesAtDate.size() == 0)
            System.out.println("\nCould not find any entries at that date.");
        else if (entriesAtDate.size() == 1)
            System.out.println("\n------------------------\n\nDisplaying the entry for that date:");
        else
            System.out.println("\nDisplaying the " + entriesAtDate.size() + " entries for that date:");
        
        for (Entry entry : entriesAtDate)
            System.out.println("\n" + entry);

        System.out.print("\nHit enter to return to main menu.");
        input.nextLine();
    }

    public static void getCSV(Scanner input, EntryList entryList) {
        File csvFile = new File("entries.csv");
        try {
            FileWriter fileWriter = new FileWriter(csvFile, false);

            fileWriter.append("Date,Number of Visitors,Donations\n");
            for (int i = 1; i <= entryList.getAllEntries().getCurrentSize(); i++) {
                Entry currentEntry = entryList.getAllEntries().getEntry(i);
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

            System.out.println("\nSuccessfully saved to entries.csv!");
        } catch (IOException e) { 
            System.out.println("Error in saving file to csv");
        }
    }
}