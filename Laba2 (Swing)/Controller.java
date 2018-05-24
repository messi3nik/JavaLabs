package Laba;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class Controller {
    private Button buttonForCreate, buttonForDelete, buttonForFeed, buttonForCommand, buttonIWannaEat, buttonGetInfo;
    private Label labelForFeed, labelForCommand, labelForFood, commandInfo, feedInfo, foodInfo, warnings;
    private RadioButton RBTerrier, RBSheepDog, RBPoodle;
    private TextField name, age, command;
    private Master master;
    private Terrier terrier;
    private Poodle poodle;
    private SheepDog sheepDog;
    private GridPane gridPane;


    public Controller(){
        initialization();
        setPositions();
        customization();
        filters();
        actionsForButtons();
    }

    private void initialization(){
        buttonForCommand = new Button();
        buttonForCreate= new Button();
        buttonForFeed = new Button();
        buttonIWannaEat = new Button();
        buttonForDelete = new Button();
        buttonGetInfo = new Button();
        labelForCommand = new Label();
        labelForFeed = new Label();
        labelForFood = new Label();
        commandInfo = new Label();
        feedInfo = new Label();
        foodInfo = new Label();
        warnings = new Label();
        name = new TextField();
        age = new TextField();
        command = new TextField();
        group();
        master = new Master();
        gridPane = new GridPane();
    }

    private void setPositions(){
        gridPane.setMinSize(500, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.add(name,0,0);
        gridPane.add(age,1,0);
        gridPane.add(command,2,0);
        gridPane.add(RBSheepDog,0,1);
        gridPane.add(RBPoodle,0,2);
        gridPane.add(RBTerrier, 0,3);
        gridPane.add(buttonForCreate,1,1);
        gridPane.add(buttonForDelete,1,2);
        gridPane.add(buttonForCommand,2,1);
        gridPane.add(buttonForFeed,2,2);
        gridPane.add(buttonIWannaEat,2,3);
        gridPane.add(buttonGetInfo, 1,3);
        gridPane.add(labelForFood, 0,4);
        gridPane.add(labelForFeed, 1,4);
        gridPane.add(labelForCommand, 2,4);
        gridPane.add(foodInfo, 0,5);
        gridPane.add(feedInfo, 1,5);
        gridPane.add(commandInfo, 2,5);
        gridPane.add(warnings, 0, 6);
    }

    public GridPane getScene(){
        return gridPane;
    }

    private void group(){
        RBTerrier = new RadioButton();
        RBSheepDog = new RadioButton();
        RBPoodle = new RadioButton();
        final ToggleGroup group = new ToggleGroup();
        RBTerrier.setToggleGroup(group);
        RBTerrier.setSelected(true);
        RBSheepDog.setToggleGroup(group);
        RBPoodle.setToggleGroup(group);
    }

    private void customization(){
        name.setPromptText("Enter name");
        age.setPromptText("Enter age");
        command.setPromptText("Enter command");
        RBTerrier.setText("Terrier");
        RBPoodle.setText("Poodle");
        RBSheepDog.setText("Sheepdog");
        buttonForDelete.setText("Delete");
        buttonIWannaEat.setText("Request food");
        buttonForCommand.setText("Command");
        buttonForCreate.setText("Create");
        buttonForFeed.setText("Feed");
        buttonGetInfo.setText("Info");
        labelForCommand.setText("Command result:");
        labelForFeed.setText("Feed result:");
        labelForFood.setText("Amount of food:");
        foodInfo.setText(Integer.toString(master.howMuchFood()));
        warnings.setTextFill(Paint.valueOf("Red"));
    }

    private void createNewDog(){
        int dogId;

        if(RBTerrier.isSelected()){
            if(terrier == null)
            terrier = new Terrier();
            else{
                warnings.setText("This pet already created");
                return;
            }
            dogId = 0;
        }
        else if(RBPoodle.isSelected()){
            if(poodle == null)
            poodle = new Poodle();
            else{
                warnings.setText("This pet already created");
                return;
            }
            dogId = 1;
        }else {
            if(poodle == null)
                sheepDog = new SheepDog();
            else{
                warnings.setText("This pet already created");
                return;
            }
            dogId = 2;
        }

        try {
            switch (dogId) {
                case 0: {
                    terrier.setName(name.getText());
                    terrier.setAge(Integer.parseInt(age.getText()));
                }
                break;
                case 1: {
                    poodle.setName(name.getText());
                    poodle.setAge(Integer.parseInt(age.getText()));
                }
                break;
                case 2: {
                    sheepDog.setName(name.getText());
                    sheepDog.setAge(Integer.parseInt(age.getText()));
                }
            }
        }
        catch (NumberFormatException e){
            warnings.setText("Incorrect input");
            switch (dogId) {
                case 0: {
                    terrier = null;
                }
                break;
                case 1: {
                    poodle = null;
                }
                break;
                case 2: {
                   sheepDog = null;
                }
            }
        }
    }

    private void info(){
        int dogId = whatDog();

        switch (dogId) {
            case 0: {
                if(terrier == null){
                    warnings.setText("This pet is not existed");
                    return;
                }
                name.setText(terrier.getName());
                age.setText(Integer.toString(terrier.getAge()));
            }
            break;
            case 1: {
                if(poodle == null){
                    warnings.setText("This pet is not existed");
                    return;
                }
                name.setText(poodle.getName());
                age.setText(Integer.toString(poodle.getAge()));
            }
            break;
            case 2: {
                if(sheepDog == null){
                    warnings.setText("This pet is not existed");
                    return;
                }
                name.setText(sheepDog.getName());
                age.setText(Integer.toString(sheepDog.getAge()));
            }
            break;
        }
    }

    private void delete(){
        int dogId = whatDog();

        switch (dogId) {
            case 0: {
                terrier = null;
            }
            break;
            case 1: {
                poodle = null;
            }
            break;
            case 2: {
                sheepDog = null;
            }
            break;
        }
    }

    private void actionsForButtons (){
        buttonForCreate.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                createNewDog();
            }
        });

        buttonGetInfo.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                info();
            }
        });

        buttonForDelete.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                delete();
            }
        });

        buttonForCommand.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                command();
            }
        });

        buttonForFeed.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                feed();
                foodInfo.setText(Integer.toString(master.howMuchFood()));
            }
        });

        buttonIWannaEat.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                warnings.setText("");
                feedInfo.setText(iWannaEat());
                foodInfo.setText(Integer.toString(master.howMuchFood()));
            }
        });
    }

    private int whatDog(){
        int dogId;
        if(RBTerrier.isSelected()){
            dogId = 0;
        }
        else if(RBPoodle.isSelected()){
            dogId = 1;
        }else {
            dogId = 2;
        }
        return dogId;
    }

    private void command(){
        int dogId = whatDog();

        switch (dogId) {
            case 0: {
                if(terrier == null){
                    warnings.setText("This pet is not existed");
                    return;
                }
                master.setDog(terrier);
                commandInfo.setText(master.giveCommand(command.getText()));
            }
            break;
            case 1: {
                if(poodle == null){
                    warnings.setText("This pet is not existed");
                    return;
                }
                master.setDog(poodle);
                commandInfo.setText(master.giveCommand(command.getText()));
            }
            break;
            case 2: {
                if(sheepDog == null) {
                    warnings.setText("This pet is not existed");
                    return;
                }
                master.setDog(sheepDog);
                commandInfo.setText(master.giveCommand(command.getText()));
            }
            break;
        }
    }

    private void feed(){
        int dogId = whatDog();
        switch (dogId) {
            case 0: {
                if(terrier == null) {
                    warnings.setText("This pet is not existed");
                    return;
                }
            }
            break;
            case 1: {
                if(poodle == null) {
                    warnings.setText("This pet is not existed");
                    return;
                }
            }
            break;
            case 2: {
                if(sheepDog == null) {
                    warnings.setText("This pet is not existed");
                    return;
                }
            }
            break;
        }
        master.feedDog();
        if(master.howMuchFood()==0)
            warnings.setText("Not enough food");
    }

    private void filters(){
        age.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    age.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private String iWannaEat() {
        if (master.howMuchFood() == 0) {
            warnings.setText("Not enough food");
            return "";
        }
        int dogId = whatDog();
        switch (dogId) {
            case 0: {
                if (terrier == null) {
                    warnings.setText("This pet is not existed");
                    return null;
                }
                terrier.setMaster(master);
                return terrier.iWannaEat();
            }
            case 1: {
                if (poodle == null) {
                    warnings.setText("This pet is not existed");
                    return null;
                }
                poodle.setMaster(master);
                return poodle.iWannaEat();
            }
            case 2: {
                if (sheepDog == null) {
                    warnings.setText("This pet is not existed");
                    return null;
                }
                sheepDog.setMaster(master);
               return sheepDog.iWannaEat();
            }
        }
        return null;
    }
}
