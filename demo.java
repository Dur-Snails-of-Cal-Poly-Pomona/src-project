package project0semisterlongproject.ideas;
import java.util.Scanner;

import project0semisterlongproject.BagInterface;
import project0semisterlongproject.LinkedBag;
import project0semisterlongproject.LinkedList;

import java.util.*;

public class demo 
{
    private static int totalVisitors = 0;
    private static double totalMoney = 0;
    private static int totalClothes = 0;
    private static int totalSeeds = 0;
    private static int totalOther = 0;
    public static void main(String[] args) 
    {
        /*donationperperson hi = new donationperperson();
        hi.setdonation(1,2,3,4,5);
        hi.printString();System.out.println();*/

        LinkedList<donationperperson> donations = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);
        String yesno = "yes";

        int entrynum =1;

        System.out.println("yes to enter another entry " + entrynum);
        yesno = scanner.nextLine();

        while (yesno.equalsIgnoreCase("yes")) 
        {
            int numppl, clothes, seeds,other;
            double money;

            donationperperson entry = new donationperperson();

            System.out.println("how many people?");
            numppl= scanner.nextInt();
            totalVisitors += numppl;
            //entry.setGroupsize(numppl);
            
            System.out.println("how much money");
            money = scanner.nextDouble();
            totalMoney += money;
            //entry.setMoney(money);

            System.out.println("how many clothes?");
            clothes= scanner.nextInt();
            totalClothes += clothes;
            //entry.setClothes(clothes);

            System.out.println("how many seeds?");
            seeds= scanner.nextInt();
            totalSeeds += seeds;
            //entry.setSeeds(seeds);

            System.out.println("how many other?");
            other= scanner.nextInt();
            scanner.nextLine();
            totalOther =+ other;
            //entry.setOther(other);


            entry.setall(money, clothes, seeds, other, numppl);
            entry.printString();

            donations.add(entry);

            entrynum++;

            System.out.println("yes to enter another entry " + entrynum);
            yesno = scanner.nextLine();
        }

        //donations.printList();

        for (int i=1; i <entrynum;i++)
        {
            printoutlistat(donations, i);
        }
   
        printtotal();

        scanner.close();
    }

    public static void printtotal()
    {
        System.out.print("TotalVisitors " + totalVisitors +" ,");
        System.out.print("totalMoney " + totalMoney +" ,");
        System.out.print("totalClothes " + totalClothes +" ,");
        System.out.print("totalSeeds " + totalSeeds +" ,");
        System.out.println("totalOther " + totalOther +" ,");
    }


    public static void printoutlistat (LinkedList<donationperperson> donations, int index) 
    {
        donationperperson entry = donations.getEntry(index);
        if (entry != null) 
        {
            System.out.print(index + ": ");
            entry.printString();
        } else 
        {
            System.out.println("Entry at position " + index + " does not exist.");
        }
    }
    
}
