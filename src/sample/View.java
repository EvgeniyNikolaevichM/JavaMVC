package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class View {
    private TableView<TableValue> table = new TableView<TableValue>();
    private XYChart.Series series = new XYChart.Series();
    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
    private Button btAdd = new Button("Добавить");
    private Button btDel = new Button("Удалить");
    private Button btUpd = new Button("Редактировать");
    private Label label = new Label("My Label");
    private TextField textFieldX = new TextField();
    private BorderPane pane;
    public View(BorderPane pane) {
        yAxis.setLabel("Шкала Y");
        xAxis.setLabel("Шкала X");
        this.pane = pane;
        initControlFields();
        initTable();
        initSeriesChartLabels();
        this.pane.setRight(table);
    }
    private void initControlFields() {
        textFieldX.setPromptText("X");
        textFieldX.setPrefWidth(80);
        Tooltip tooltipAdd = new Tooltip("Добавить точку");
        Tooltip tooltipDel = new Tooltip("Удалить точку");
        Tooltip tooltipUpd = new Tooltip("Редактировать точку");
        btAdd.setPrefWidth(80);
        btDel.setPrefWidth(80);
        btUpd.setPrefWidth(100);
        btAdd.setTooltip(tooltipAdd);
        btDel.setTooltip(tooltipDel);
        btUpd.setTooltip(tooltipUpd);
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.getChildren().addAll(textFieldX, btAdd, btDel, btUpd, label);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        pane.setBottom(hBox);
    }
    private void initSeriesChartLabels() {
        label.setVisible(false);
        series.setName("Координата на графике");
        lineChart.setTitle("y = 2x²+3");
    }

    private void initTable() {
        TableColumn<TableValue,Double> x = new TableColumn<TableValue,Double>("X");
        x.setEditable(true);
        x.setPrefWidth(125);
        x.setMaxWidth(125);
        x.setCellValueFactory(new PropertyValueFactory<TableValue,Double>("x"));
        table.getColumns().add(x);
        TableColumn<TableValue,Double> y = new TableColumn<TableValue,Double>("Y");
        y.setEditable(true);
        y.setPrefWidth(125);
        y.setMaxWidth(125);
        y.setCellValueFactory(new PropertyValueFactory<TableValue,Double>("y"));
        table.getColumns().add(y);
    }


    public Button getBtAdd() {
        return btAdd;
    }
    public TextField getTextFieldX() {
        return textFieldX;
    }
    public TableView<TableValue> getTable() {
        return table;
    }
    public Button getBtDel() {
        return btDel;
    }
    public XYChart.Series getSeries() {
        return series;
    }
    public LineChart<Number, Number> getLineChart() {
        return lineChart;
    }
    public Button getBtUpd() {
        return btUpd;
    }
    public Label getLabel() {
        return label;
    }
}

    