package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChartGui extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Функция MVC");
        BorderPane pane = new BorderPane();
        View view = new View(pane);

        pane.setCenter(view.getLineChart());

        Controller controller = new Controller(view, view.getSeries());

        Scene scene = new Scene(pane,800,600);
        view.getLineChart().getData().add(view.getSeries());
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
    