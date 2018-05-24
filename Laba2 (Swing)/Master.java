package Laba;

public class Master {
    private DogFood dogFood=new DogFood();
    private Dog dog;

    public Master(){
    }

    public String giveCommand(String command){
       return dog.executeCommand(command);
    }

    public int feedDog(){
        dogFood.onePorcion();
        return dogFood.getAmountOfFeed();
    }

    public int howMuchFood(){
        return dogFood.getAmountOfFeed();
    }

    public void setDog (Dog dog){
        this.dog = dog;
    }
}
