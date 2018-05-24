package Laba;

public class DogFood {
    private int amountOfFeed;


    public DogFood(){
        amountOfFeed=10;
    }

    public void setAmountOfFeed(int amountOfFeed) {
        this.amountOfFeed=amountOfFeed;
    }

    public int getAmountOfFeed(){
        return amountOfFeed;
    }

    public boolean onePorcion()
    {
        if(amountOfFeed<=0)
            return false;
        amountOfFeed-=1;
        return true;
    }
}
