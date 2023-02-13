import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    private int selectedFunk = 0;
    private int selectedMethod = 0;
    private int i = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Лаба 2");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        ObservableList<String> funcs = FXCollections.observableArrayList("1,63x^3 - 8,15*x^2 + 4.39x + 4.29", "sin(x)", "3x^5+4");
        ComboBox<String> funcComboBox = new ComboBox<String>(funcs);
        funcComboBox.setValue("1,63x^3 - 8,15*x^2 + 4.39x + 4.29");

        ObservableList<String> method = FXCollections.observableArrayList("Половинного деления", "Ньютона", "Простой итерации");
        ComboBox<String> methodComboBox = new ComboBox<String>(method);
        methodComboBox.setValue("Половинного деления");

        TextField leftBorder = new TextField("-5");
        TextField rightBorder = new TextField("5");
        FlowPane drawBorders = new FlowPane(new Label("Область отрисовки "), leftBorder, rightBorder);

        TextField aBorder = new TextField("-2");
        TextField bBorder = new TextField("5");
        FlowPane borders = new FlowPane(new Label("Область поиска ответов "), aBorder, bBorder);

        TextField field = new TextField("0.001");
        FlowPane text = new FlowPane(new Label("Точность "), field);

        Button selectBtn = new Button("Вычислить");

        ScatterChart<Number, Number> numberScatterChart = new ScatterChart<Number, Number>(x,y);

        Label ans = new Label("Ответ");
        GridPane pane = new GridPane();
        //GridPane pane = new GridPane(numberScatterChart, funk1, funk2, borders, text, selectBtn, ans);

        ColumnConstraints column1 = new ColumnConstraints(300,300,Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints(200,200,Double.MAX_VALUE);
        column2.setHgrow(Priority.ALWAYS);
        ColumnConstraints column3 = new ColumnConstraints(400,400,Double.MAX_VALUE);
        column3.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().add(column1);
        pane.getColumnConstraints().add(column2);
        pane.getColumnConstraints().add(column3);
        //pane.setGridLinesVisible(true);

        pane.add(numberScatterChart, 0, 0);
        GridPane.setColumnSpan(numberScatterChart, 3);
        pane.add(funcComboBox, 0,1);
        pane.add(methodComboBox, 0,2);
        //pane.add(funk2, 0,2);
        pane.add(borders, 1, 1);
        GridPane.setColumnSpan(borders, 2);
        pane.add(drawBorders, 1, 2);
        GridPane.setColumnSpan(drawBorders, 2);
        pane.add(text, 0, 3);
        pane.add(selectBtn, 1,3);
        pane.add(ans, 2,3);
        numberScatterChart.setTitle("График");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series2.setName("Проверяемые точки");
        series1.setName("Функция");
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        selectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                datas.clear();
                datas2.clear();
                double tochnost = 0.01;
                try {
                    tochnost = Double.parseDouble(field.getText());
                }catch (Exception e){}
                //selectedLbl.setText("Selected: " + selection.getText());
                if(methodComboBox.getValue().equals("Ньютона")){
                    selectedMethod = 1;
                }else if(methodComboBox.getValue().equals("Простой итерации")) {
                    selectedMethod = 2;
                }else{
                    selectedMethod = 0;
                }
                if(funcComboBox.getValue().equals("sin(x)")){
                    selectedFunk = 1;
                }else if(funcComboBox.getValue().equals("3x^5+4")) {
                    selectedFunk = 2;
                }else{
                    selectedFunk = 0;
                }
                double n = -5;
                double m = 5;
                try{
                    n = Double.parseDouble(leftBorder.getText());
                    m = Double.parseDouble(rightBorder.getText());
                    if(m < n){
                        double temp1 = m;
                        m = n;
                        n = temp1;
                    }
                }catch (Exception e){}
                for(double i = n; i<m; i += (m-n)/1000){
                    datas.add(new XYChart.Data(i,function(i)));

                    //datas2.add(new XYChart.Data(i,Math.cos(i)));
                }
                double a = -2;
                double b = 5;
                try{
                    a = Double.parseDouble(aBorder.getText());
                    b = Double.parseDouble(bBorder.getText());
                    if(b < a){
                        double temp = a;
                        a = b;
                        b = temp;
                    }
                }catch (Exception e){}
                double p = 0;
                if(selectedMethod == 0) {
                    try {
                        p = halfs(a, b, datas2, tochnost, m, n);
                        ans.setText("x: " + p + "\t y: " + function(p) + "\tИтераций: " + i);
                    } catch (Exception e) {
                        datas2.add(new XYChart.Data(a, function(a)));
                        ans.setText("Уточните границы поиска");
                    }
                }else if(selectedMethod == 1){
                    try {
                        p = nuton(a, b, datas2, tochnost, m, n);
                        ans.setText("x: " + p + "\t y: " + function(p) + "\tИтераций: " + i);
                    } catch (Exception e) {
                        ans.setText("Уточните границы поиска");
                    }
                }else{
                    try {
                        p = simple(a, b, datas2, tochnost, m, n);
                        ans.setText("x: " + p + "\t y: " + function(p) + "\tИтераций: " + i);
                    } catch (Exception e) {
                        ans.setText("Уточните границы поиска");
                    }
                }
                //System.out.println(p + " "  + function(p));

                series1.setData(datas);
                series2.setData(datas2);
            }
        });

        datas.add(new XYChart.Data(0,0));
        datas2.add(new XYChart.Data(0,0));
        series1.setData(datas);
        series2.setData(datas2);
        Scene scene = new Scene(pane, 1200,800);
        scene.getStylesheets().add("Succ.css");
        numberScatterChart.getData().add(series1);
        numberScatterChart.getData().add(series2);
        primaryStage.setScene(scene);

        primaryStage.show();


    }

    private double function(double p) {
        if(selectedFunk == 0){
            return func1(p);
        }else{
            if(selectedFunk == 1) {
                return func2(p);
            }else{
                return func3(p);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static double mod(double x){
        if(x >= 0){
            return x;
        }else{
            return -1*x;
        }
    }

    public double halfs(double a, double b, ObservableList<XYChart.Data> datas2, double l, double m, double n) throws Exception {
        double x = (a+b)/2;
        if((x > n)&&(x < m)) {
            datas2.add(new XYChart.Data(x, function(x)));
        }
        i = 1;
        //System.out.println(a + "\t" + b + "\t" + x + "\t" + function(a) + "\t" + function(b) + "\t" + function(x) + "\t" + mod(a - b));
        for(;mod(function(x)) > l;){
            i++;
            if(function(a)*function(x) > 0){
                if(x == a)throw new Exception();
                a = x;
            }else{
                if(x == b)throw new Exception();
                b = x;
            }
            x = (a+b)/2;
            if((x > n)&&(x < m)){
                datas2.add(new XYChart.Data(x, function(x)));
            }
            //System.out.println(a + "\t" + b + "\t" + x + "\t" + function(a) + "\t" + function(b) + "\t" + function(x) + "\t" + mod(a - b));
        }
        return x;
    }

    public double nuton(double a, double b, ObservableList<XYChart.Data> datas2, double l, double m, double n) throws Exception {
        double x;
        if(function(a)/functionA(a) > 0){
            x = b - function(b)/functionA(b);
        }else {
            x = a - function(a) / functionA(a);
        }
        if((x > n)&&(x < m)) {
            datas2.add(new XYChart.Data(x, function(x)));
        }
        i = 1;
        //System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f", x, function(x), functionA(x), (x - function(x)/functionA(x)), mod(x - x - function(x)/functionA(x)));
        //System.out.println();
        for(;mod(function(x)/(functionA(x))) > l;){
            i++;
            x = x - function(x)/functionA(x);
            if((x > n)&&(x < m)){
                datas2.add(new XYChart.Data(x, function(x)));
            }
            //System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f", x, function(x), functionA(x), (x - function(x)/functionA(x)), mod(x - x - function(x)/functionA(x)));
            //System.out.println();
        }
        if(x < a || x > b){
            throw new Exception();
        }
        return x;
    }

    public double simple(double a, double b, ObservableList<XYChart.Data> datas2, double l, double m, double n) throws Exception {
        double r = -1/max(functionA(a), functionA(b));
        double x;
        if(max(functionA(a), functionA(b)) == functionA(a)) {
            x = a + r * function(a);
        }else{
            x = b + r * function(b);
        }
        if((x > n)&&(x < m)) {
            datas2.add(new XYChart.Data(x, function(x)));
        }
        i = 1;
        //System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f", x, function(x), (x + r*function(x)), functionA(x), mod(x - x + r*function(x)));
        //System.out.println();
        for(;((mod(mod(x - mod(x + r*function(x)))) > l/20) && (mod(r*function(x)) > l/20) && (x != (x + r*function(x) + r*function(x + r*function(x)))));){
            i++;
            x = x + r*function(x);
            if((x > n)&&(x < m)){
                datas2.add(new XYChart.Data(x, function(x)));
            }
            //System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f", x, function(x), (x + r*function(x)), functionA(x), mod(x - x + r*function(x)));
            //System.out.println();
        }
        if(mod(function(x)) > l*40 || x > b || x < a){
            throw new Exception();}
        return x;
    }
    private double max(double a, double b){
        if(a > b){
            return a;
        }else{
            return b;
        }
    }

    private double functionA(double p) {
        if(selectedFunk == 0){
            return funcA1(p);
        }else{
            if(selectedFunk == 1) {
                return funcA2(p);
            }else{
                return funcA3(p);
            }
        }
    }

    private double funcA1(double p) {
        return Math.pow(p, 2)*4.86 - 16.3*p + 4.39;
    }

    private double funcA2(double p) {
        return Math.cos(p);
    }

    private double funcA3(double p) {
        return Math.pow(p, 4)*15;
    }

    public static double func1(double x){
        return Math.pow(x, 3)*1.62 - 8.15*Math.pow(x, 2) + 4.39*x + 4.29;
    }

    public static double func2(double p) {
        return Math.sin(p);
    }

    public static double func3(double p) {
        return Math.pow(p, 5)*3 + 4;
    }
}
