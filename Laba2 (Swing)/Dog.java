package Laba;

public class Dog {
    private int age;
    private String name;
    private Master master;


    public Dog(){
        age=0;
        name="Sharik";
    }

    public String executeCommand(String command){
        String execution;
        switch (command){
            case "Voice": execution= "Bark! Bark!";
                break;
            case "Sit": execution="I'm siting, so?";
                break;
            case "Die": execution="...";
                break;
            default: execution="I don't understand";
                break;
        }
        return execution;
    }

    public String iWannaEat(){
        master.feedDog();
        return "I'm full";
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age=age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setMaster(Master master){
        this.master=master;
    }
}
