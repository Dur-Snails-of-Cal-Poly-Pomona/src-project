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
            else if (userInput.toUpperCase().equals("Q")){
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
                        date = LocalDate.of(Integer.parseInt(monthDayYear[2]), 
                                            Integer.parseInt(monthDayYear[0]), 
                                            Integer.parseInt(monthDayYear[1]));
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
        String filename;

        System.out.print("Please input a csv file name (ending in .csv) to create/edit (leave blank for entries.csv): ");
        filename = input.nextLine().trim();
        while (filename.length() < 4 || !filename.substring(filename.length() - 4).equals(".csv")) {
            if (filename.length() == 0) {
                filename = "entries.csv";
            } else {
                System.out.print("File name must end in \".csv\", please try again: ");
                filename = input.nextLine().trim();
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