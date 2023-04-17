package sample;
//Широко используется в web приложениях, графический где есть интрефейс отрисовываемый
//и в десктопых также
//Суть в том, что есть 3 составляющих:
//Модель-работа с данными и БД
//Представление-отображение данных для пользователя
//Контроллер-связующее звено, из представления может забирать данные и реагировать на действия пользователя
//            И производить какие-либо действия с моделью
//Интерфейс также обновляется в зависимости от действий совершенных с моделью

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