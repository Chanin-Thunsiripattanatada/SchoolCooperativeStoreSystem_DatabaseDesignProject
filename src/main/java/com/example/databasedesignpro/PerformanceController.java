package com.example.databasedesignpro;

import Model.TCooperativeAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.databasedesignpro.LoginController.conn;

public class PerformanceController {
    @FXML
    private Label firstdateLabel;
    @FXML
    private Label lastdateLabel;
    @FXML
    private Label firstvalueLabel;
    @FXML
    private Label lastvalueLabel;
    @FXML
    private Label difvalueLabel;
    @FXML
    private DatePicker firstdate;
    @FXML
    private DatePicker lastdate;
    @FXML
    private VBox linechartVBox;
    @FXML
    private VBox piechartVBox;


    private static ObservableList<TCooperativeAccount> listtransection = FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        setlinechart();
        setpiechart();
        setLabel();
    }

    private void setLabel() {
        if(!listtransection.isEmpty()){
            TCooperativeAccount first = listtransection.get(0);
            TCooperativeAccount last = listtransection.get(listtransection.size() - 1);
            firstdateLabel.setText(first.getRecodeDate());
            lastdateLabel.setText(last.getRecodeDate());
            firstvalueLabel.setText(String.valueOf(first.getTotalDaily()));
            lastvalueLabel.setText(String.valueOf(last.getTotalDaily()));
        }
    }

    @FXML
    public void quaryaccountdata(ActionEvent event) throws SQLException, IOException {
        System.out.println(Date.valueOf((firstdate.getValue()==null) ? LocalDate.now():firstdate.getValue() ));
        String query ;
        query = "select * from cooperativebook where recordDate between date(?) and date(?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, String.valueOf((firstdate.getValue()==null) ? LocalDate.now():firstdate.getValue() ));
        ps.setString(2, String.valueOf((lastdate.getValue()==null) ? LocalDate.now():lastdate.getValue() ));
        ResultSet rec = ps.executeQuery();
        TCooperativeAccount Tcooper;
        listtransection = FXCollections.observableArrayList();
        while((rec!=null) && (rec.next())) {
            Tcooper = new TCooperativeAccount(Integer.parseInt(rec.getString("Runbookid")), rec.getString("recordDate"),
                    Double.parseDouble(rec.getString("income")),
                    Double.parseDouble(rec.getString("outcome")),
                    Double.parseDouble(rec.getString("TotalDaily")),
                    null);
            listtransection.add(Tcooper);
        }
        ps.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AccountController accountController = loader.getController();
        accountController.changepage("Performance.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setlinechart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("จำนวนรายได้");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("ข้อมูลในแต่ละวัน");
        for(int i = 0; i < listtransection.size(); i++) {
            dataSeries1.getData().add(new XYChart.Data(i+1, listtransection.get(i).getIncome()));
        }
        lineChart.getData().add(dataSeries1);
        linechartVBox.getChildren().clear();
        linechartVBox.getChildren().add(lineChart);

    }
    public void setpiechart() {
        double totalin = 0;
        double totalout = 0;
        double total = 0;

        for(TCooperativeAccount i :  listtransection) {
            totalin += i.getIncome();
            totalout += i.getOutcome();
            total += i.getTotalDaily();

        }
        difvalueLabel.setText(String.valueOf(total));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("เงินเข้า", totalin),
                new PieChart.Data("เงินออก",totalout));

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("แผนภูมิวงกลมแสดงสัดส่วนของเงินเข้าออกทั้งหมด");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
        piechartVBox.getChildren().clear();
        piechartVBox.getChildren().add(pieChart);



    }
}
