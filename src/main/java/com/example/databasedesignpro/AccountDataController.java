package com.example.databasedesignpro;

import Model.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static com.example.databasedesignpro.LoginController.conn;

public class AccountDataController {
//    Input to query
    @FXML
    private DatePicker firstdate;
    @FXML
    private DatePicker lastdate;

//    account table
    @FXML
    private TableView<TCooperativeAccount> accountTable;
    @FXML
    private TableColumn<TCooperativeAccount, Integer> runbookidColumn;
    @FXML
    private TableColumn<TCooperativeAccount, String> recodeDateColumn;
    @FXML
    private TableColumn<TCooperativeAccount, Double> incomeColumn;
    @FXML
    private TableColumn<TCooperativeAccount, Double> outcomeColumn;
    @FXML
    private TableColumn<TCooperativeAccount, Double> totalDailyColumn;

    private static ObservableList<TCooperativeAccount> listtransection = FXCollections.observableArrayList();


    @FXML
    public void initialize() {setAccountdataTable(listtransection);}
    @FXML
    public void quaryaccountdata(ActionEvent event) throws SQLException, IOException {
        CallableStatement cs = conn.prepareCall("{call calculate_daily()}");
        cs.executeUpdate();
        cs.close();
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
                Double.parseDouble(rec.getString("TotalDaily")),null);
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
        accountController.changepage("accountdata.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setAccountdataTable(ObservableList<TCooperativeAccount> listtransection) {
        this.listtransection = listtransection;
        runbookidColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TCooperativeAccount, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<TCooperativeAccount, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRunbookid());
            }
        });
        recodeDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TCooperativeAccount, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TCooperativeAccount, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRecodeDate());
            }
        });
        incomeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TCooperativeAccount, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TCooperativeAccount, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getIncome());
            }
        });
        outcomeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TCooperativeAccount, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TCooperativeAccount, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getOutcome());
            }
        });
        totalDailyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TCooperativeAccount, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TCooperativeAccount, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotalDaily());
            }
        });

        accountTable.setItems(listtransection);


    }
}

