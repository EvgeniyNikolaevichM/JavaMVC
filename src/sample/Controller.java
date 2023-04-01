package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Controller {
    private View view;
    private XYChart.Series series;
    private Model model = new Model();
    public Controller(View view, XYChart.Series series) {
        this.view = view;
        this.series = series;
        this.addActionSubscribe();
        this.delActionSubscribe();
        this.updActionSubscribe();
    }
    public void addActionSubscribe() {
        this.view.getBtAdd().setOnAction(e -> {
            try {
                if (view.getTextFieldX().getText().equals("")) {
                    return;
                } else {
                    TableValue tableValue = new TableValue(
                            Double.parseDouble(view.getTextFieldX().getText()),
                            model.calculateFunctionValue(Double.parseDouble(view.getTextFieldX().getText()))
                    );
                    double x = Double.parseDouble(view.getTextFieldX().getText());
                    model.addValueToArray(x);
                    series.getData().add(new XYChart.Data(x, model.calculateFunctionValue(x)));
                    view.getTable().getItems().add(tableValue);
                    view.getTextFieldX().clear();
                }
            } catch (NumberFormatException ex){
                view.getLabel().setVisible(true);
                view.getLabel().setText("Ошибка ввода числа");
            }
        });
    }
    public void delActionSubscribe() {
        ObservableList<TableValue> selectedValues, allValues;
        allValues = view.getTable().getItems();
        selectedValues = view.getTable().getSelectionModel().getSelectedItems();
        this.view.getBtDel().setOnAction(e -> {
            int index = view.getTable().getSelectionModel().getSelectedIndex();
            if(index != -1) {
                series.getData().remove(index);
                model.delValueInArray(index);
            }
            selectedValues.forEach(allValues::remove);
        });
    }
    public void updActionSubscribe() {
        this.view.getBtUpd().setOnAction(e -> {
            int index = view.getTable().getSelectionModel().getSelectedIndex();
            double  selectedValue = 0;
            if (index != -1) {
                Stage newWindow = new Stage();
                FlowPane root = new FlowPane();
                TextField textField = new TextField();
                Button update = new Button("Обновить");
                Button close = new Button("Закрыть");
                selectedValue = model.getValuesX()[index];
                textField.setText(String.valueOf(selectedValue));
                final double previousValue = selectedValue;
                update.setOnAction(event -> {
                    try{
                        double x = Double.parseDouble(textField.getText());
                        updateChart(index, x);
                        view.getLabel().setVisible(true);
                        view.getLabel().setText("Значение " + previousValue + " отредактировано на " + x);
                        newWindow.close();
                    }
                    catch (NumberFormatException ex){
                        textField.setText("Ошибка ввода числа");
                    }
                });
                close.setOnAction(event1 -> {
                    view.getLabel().setVisible(false);
                    newWindow.close();
                });
                root.setPadding(new Insets(10, 10, 10, 10));
                root.setVgap(5);
                root.setHgap(5);
                root.getChildren().addAll(textField, update, close);
                Scene secondScene = new Scene(root, 300, 80);
                newWindow.setTitle("Редактировать");
                newWindow.setScene(secondScene);
                newWindow.setMinWidth(300);
                newWindow.setMaxWidth(300);
                newWindow.setMaxHeight(80);
                newWindow.setMinHeight(80);
                newWindow.show();
            }
        });
    }
    private void updateChart(int index, double x) {
        model.updValueInArray(index, x);
        view.getTable().getItems().clear();
        series.getData().clear();
        for(int i = 0; i < model.getValuesX().length; i++) {
            view.getTable().getItems().add(new TableValue(model.getValuesX()[i], model.getValuesY()[i]));
        }
        for(int i = 0; i < model.getValuesX().length; i++) {
            series.getData().add(new XYChart.Data(model.getValuesX()[i], model.getValuesY()[i]));
        }
    }
}
