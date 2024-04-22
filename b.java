import java.util.Scanner;
import java.time.LocalDate;

public class b
{
    public static int readValidInteger(Scanner scanner) 
    {
        while (!scanner.hasNextInt()) 
        {
            System.out.println("Please enter a solid number! Thanks! Type X to quit!");
            if (scanner.next().equalsIgnoreCase("X")) 
            {
                return 0; // Assume 0 donations if the user chooses to quit
            }
            else if (scanner.hasNextInt())
            {
                return scanner.nextInt();
            }
        }
        return scanner.nextInt();
    }

    public static double readValidNumber(Scanner scanner) 
    {
        while (!scanner.hasNextDouble()) 
        {
            System.out.println("Please enter a valid number! Thanks! Type X to quit!");
            if (scanner.next().equalsIgnoreCase("X")) 
            {
                return 0; // Assume 0 donations if the user chooses to quit
            }
            else if (scanner.hasNextDouble())
            {
                return scanner.nextDouble();
            }
        }
        return scanner.nextDouble();
    }



    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        int a; double b; char c; String d;

        a = readValidInteger(scanner);
        b = readValidNumber(scanner);
        System.out.println(a +" "+b);
    }
}
