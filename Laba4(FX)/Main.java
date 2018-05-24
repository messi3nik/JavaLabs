import Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        GridPane mainPane = controller.getPane();
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }
}
