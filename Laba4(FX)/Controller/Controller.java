package Controller;

import Threads.MainThread;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Label label1, label2, label3;
    private TextField field1, field2, field3;
    private Button button;
    private ListView<Double> listOfAnswers;
    private GridPane mainPane;
    private GridPane controlPane;

    private Thread thread;
    private MainThread mainThread;

    private double x,y;
    private int degree;
    private List<Double> result;

    public Controller(){
        Init();
        SetPosition();
        Listener();
    }

    public void Init(){
        mainPane = new GridPane();
        mainPane.setHgap(2);
        mainPane.setVgap(2);
        mainPane.setMinSize(600, 500);
        mainPane.setAlignment(Pos.TOP_LEFT);
        controlPane = new GridPane();
        controlPane.setVgap(10);
        controlPane.setMinSize(300, 300);
        controlPane.setAlignment(Pos.TOP_LEFT);
        controlPane.setHgap(10);

        label1 = new Label("X");
        label2 = new Label("i*Y");
        label3 = new Label("Degree");
        field1 = new TextField();
        field2 = new TextField();
        field3 = new TextField();
        button = new Button("GO");

        result = new ArrayList<Double>();

        listOfAnswers = new ListView<Double>();
        listOfAnswers.setItems(FXCollections.observableArrayList(result));
    }

    private void SetPosition() {
        controlPane.add(label1, 0,0);
        controlPane.add(field1, 1,0);
        controlPane.add(label2, 0, 1);
        controlPane.add(field2, 1,1);
        controlPane.add(label3, 0,2);
        controlPane.add(field3, 1,2);
        //controlPane.add(listOfAnswers, 4,4);
        controlPane.add(button, 0, 3);

        mainPane.add(controlPane, 0, 0);
        mainPane.add(listOfAnswers, 1,0);
    }

    public void Calculate() {
        x = Double.parseDouble(field1.getText());
        y = Double.parseDouble(field2.getText());
        degree = Integer.parseInt(field3.getText());

        for (int k = 0; k < degree; k++) {
            mainThread = new MainThread(x, y, degree, k);
            thread = new Thread(mainThread);
            thread.run();
            result.add(mainThread.getResult());
        }
    }

    public void Listener(){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                result.clear();
                Calculate();
                UpdateListView();
            }
        });
    }

    public GridPane getPane()
    {
        return mainPane;
    }

    private void UpdateListView(){
        listOfAnswers.getItems().clear();
        listOfAnswers.setItems(FXCollections.observableArrayList(result));
    }
}
