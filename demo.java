package project0semisterlongproject.ideas.idea2;

import java.util.Scanner;

import project0semisterlongproject.BagInterface;
import project0semisterlongproject.LinkedBag;
import project0semisterlongproject.LinkedList;
import project0semisterlongproject.ideas.idea1.donation;

import java.util.*;

public class demo 
{

    public static void main(String[] args) 
    {
        donationlist <donation> farm = new donationlist<>();

        Scanner scanner = new Scanner(System.in);
        String again = "y";
        
        while (!again.equalsIgnoreCase("x"))
        {
            int numppl, clothes, seeds,other;
            double money;
            String answer;

            farm.showsummary();
           
            System.out.println("Hello! how do you want to interact with this donationbox?\n(d to donate)(r to remove entry) (c to clear)(e to exit)");
            answer = scanner.nextLine();

            if (answer.equals("d"))
            {
                String moredonations = "yes";

                donation entry = new donation();

                System.out.println("How many people are in your group so far?");
                //numppl = scanner.nextInt();
                //scanner.nextLine();
                numppl = b.readValidInteger(scanner);
                scanner.nextLine();

                entry.setGroupsize(numppl);
                while (!moredonations.equals("no"))
                {
                    entry.printString();
                    System.out.println("okay great! please choose the type of donation!\nA=money\nB=Clothes\nC=seeds/Plants\nD=other items\npress X to complete your donations");
                            
                    answer = scanner.nextLine();
                    if (answer.equalsIgnoreCase("a"))
                    {
                        System.out.print("enter the amount of money you want to donate: ");
                       // money = scanner.nextDouble();scanner.nextLine();
                       money = b.readValidInteger(scanner);scanner.nextLine();
                       entry.setMoney(money);
                    }
                    else if(answer.equalsIgnoreCase("b"))
                    {
                        System.out.print("enter the amount of clothes you want to donate: ");
                        clothes = b.readValidInteger(scanner);scanner.nextLine();
                        entry.setClothes(clothes);
                    }
                    else if(answer.equalsIgnoreCase("c"))
                    {
                        System.out.print("enter the amount of plants/seeds you want to donate: ");
                        seeds = b.readValidInteger(scanner);scanner.nextLine();
                        entry.setSeeds(seeds);
                    }
                    else if(answer.equalsIgnoreCase("d"))
                    {
                        System.out.print("enter the amount of miscileanious items you want to donate: ");
                        other = b.readValidInteger(scanner);scanner.nextLine();
                        entry.setOther(other);
                    }
                    else if(answer.equalsIgnoreCase("x"))
                    {
                        System.out.println("Thanks!");
                        moredonations = "no";
                        farm.add(entry);
                    }
                    else
                    {
                        System.out.println("that wasnt right");
                    }

                }
            }
           else if (answer.equals("r"))
           {
            farm.remove(farm.getCurrentSize());
           }
           else if (answer.equals("c"))
           {
            farm.clearDonations();
           }
           else if (answer.equals("e"))
           {
            System.out.println("Thank You");
            farm.showsummary();
            again = "x";
           }
            //System.out.println("do you want to exit? press anything other than x to continue running");
            //again = scanner.nextLine();

            scanner.nextLine();
        }
        scanner.close();

        



       



    }
    
}

/*
        farm.getcreationDate();
        farm.gettotalEntries();
        donation kyle = new donation();
        kyle.setall(5.0,1,2,3,1);
        farm.add(kyle);
        farm.printListall();
        farm.printtotal();
 */