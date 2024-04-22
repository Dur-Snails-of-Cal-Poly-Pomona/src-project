import java.time.LocalDate;  

public class donation
{
    private double money;
    private int clothes;
    private int seeds;
    private int other;
    private int groupsize;
    private String date;


    public donation()
    {
        this(0,0,0,0,0);
    }

    public donation(double money, int clothes, int seeds, int other,int groupsize)
    {
        this.money = money;
        this.clothes = clothes;
        this.seeds = seeds;
        this.other = other;
        this.groupsize = groupsize;
        date = LocalDate.now().toString();
    }

    public void setall(double money, int clothes, int seeds, int other, int groupsize)
    {
        this.money = money;
        this.clothes = clothes;
        this.seeds = seeds;
        this.other = other;
        this.groupsize = groupsize;
        date = LocalDate.now().toString();
    }
    public double getMoney() 
    {
        return money;
    }
    public void setMoney(double money) 
    {
        this.money = money;
    }
    public int getClothes() 
    {
        return clothes;
    }
    public void setClothes(int clothes) 
    {
        this.clothes = clothes;
    }
    public int getSeeds() 
    {
        return seeds;
    }
    public void setSeeds(int seeds) 
    {
        this.seeds = seeds;
    }
    public int getOther() 
    {
        return other;
    }
    public void setOther(int other) 
    {
        this.other = other;
    }



    public int getGroupsize() 
    {
        return groupsize;
    }

    public void setGroupsize(int groupsize) 
    {
        this.groupsize = groupsize;
    }

    @Override
    public String toString() 
    {
        return "[money=" + money + ", clothes=" + clothes + ", seeds=" + seeds + ", other=" + other + ", groupsize=" + groupsize + "]\t" + date;
    }

    public void printString() 
    {
        System.out.println("[money=" + money + ", clothes=" + clothes + ", seeds=" + seeds + ", other=" + other+ ", groupsize=" + groupsize  + "]\t"+ date);
    }

}