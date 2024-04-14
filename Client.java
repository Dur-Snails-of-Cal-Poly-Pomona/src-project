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

        while(proceed)
        {
            System.out.println("Select an option:");
            System.out.println("Type \"1\" Make a new file.");
            System.out.println("Type \"2\" To add to existing file.");
            System.out.println("Type \"3\" List the donations in a file.");
            System.out.println("Type \"4\" Remove an item from a file.");
            System.out.println("Type \"Q\" Quit the program");

            userInput = input.next();

            if(userInput.equals("1")){
                makeNewFile();
            }
            else if(userInput.equals("2")){
                addToFile();
            }
            else if(userInput.equals("3")){
                listDonations();
            }
            else if(userInput.equals("4")){
                removeItemFromFile();
            }
            else if (userInput.equals("Q")){
                proceed = false;
            }
            else{
                System.out.println("Please enter a valid option\n");
            }
        }
        input.close();    
    }

    public static void makeNewFile(){
        
    }

    public static void addToFile(){

    }

    public static void listDonations(){

    }

    public static void removeItemFromFile(){
        
    }
}