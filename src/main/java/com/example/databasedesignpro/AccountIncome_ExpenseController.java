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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.databasedesignpro.LoginController.conn;

public class AccountIncome_ExpenseController {

    @FXML
    private TableView<TIncome_Expenses> accountTable;
    @FXML
    private TableColumn<TIncome_Expenses, String> recodeDateColumn;
    @FXML
    private TableColumn<TIncome_Expenses, String> typeColumn;
    @FXML
    private TableColumn<TIncome_Expenses, Double> totalColumn;
    private static ObservableList<TIncome_Expenses> listtransection = FXCollections.observableArrayList();

//    input
    @FXML
    private DatePicker firstdate;
    @FXML
    private DatePicker lastdate;
    @FXML
    private ComboBox<String> typeComboBox;
    private static int typeindex = 0;

    @FXML
    private Label totalLabel;
    private static double total = 0;



    @FXML
    public void initialize() {
        typeComboBox.setItems(FXCollections.observableArrayList("เงินขายของ","จ่ายปันผล","ซื้อของเข้า","ของเสียหาย"));
        setincomeoutcomeTable(listtransection);
        typeComboBox.getSelectionModel().selectFirst();
        typeComboBox.getSelectionModel().select(typeindex);
        totalLabel.setText(String.valueOf(total));
    }
    @FXML
    public void quaryIncomeExpense(ActionEvent event) throws SQLException, IOException {

        String query = "select * from recordorder where orderdate between date(?) and date_add(date(?), INTERVAL 1 DAY) ;" ;
//        check type to query
        typeindex = typeComboBox.getSelectionModel().getSelectedIndex();
        if(typeindex == 0) {
            query = "select * from recordorder where orderdate between date(?) and date_add(date(?), INTERVAL 1 DAY) ;";
        }
        else if (typeindex == 1){
            query = "select * from accountmember where recorddate between date(?) and date_add(date(?), INTERVAL 1 DAY) ;";
        }
        else if(typeindex == 2) {
            query = "select * from record_import where importdate between date(?) and date_add(date(?), INTERVAL 1 DAY) ;";
        }
        else if (typeindex == 3) {
            query = "select * from record_damagedlost_product where lostdate between date(?) and date_add(date(?), INTERVAL 1 DAY) ;";
        }
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, String.valueOf((firstdate.getValue()==null) ? LocalDate.now():firstdate.getValue() ));
        ps.setString(2, String.valueOf((lastdate.getValue()==null) ? LocalDate.now():lastdate.getValue() ));
        ResultSet rec = ps.executeQuery();
        TIncome_Expenses tctmp;
        listtransection.clear();
        total = 0;
        if (typeindex == 0){
            while ((rec != null) && (rec.next())) {

                tctmp = new TIncome_Expenses(rec.getString("orderdate"), typeComboBox.getSelectionModel().getSelectedItem() + "    รหัส    " + rec.getString("orderid"), rec.getDouble("income"));
                listtransection.add(tctmp);
                total += rec.getDouble("income");
            }
            ps.close();
        }
        if (typeindex == 1){
            while ((rec != null) && (rec.next())) {

                tctmp = new TIncome_Expenses(rec.getString("recorddate"), typeComboBox.getSelectionModel().getSelectedItem() + "    รหัส    " + rec.getString("runbookid"), rec.getDouble("dividend"));
                listtransection.add(tctmp);
                total += rec.getDouble("dividend");
            }
            ps.close();
        }
        if (typeindex == 2){
            while ((rec != null) && (rec.next())) {

                tctmp = new TIncome_Expenses(rec.getString("importdate"), typeComboBox.getSelectionModel().getSelectedItem() + "    รหัส    " + rec.getString("importID"), rec.getDouble("import_Totalprice"));
                listtransection.add(tctmp);
                total += rec.getDouble("import_Totalprice");
            }
            ps.close();
        }
        if (typeindex == 3){
            while ((rec != null) && (rec.next())) {

                tctmp = new TIncome_Expenses(rec.getString("LostID"), typeComboBox.getSelectionModel().getSelectedItem() + "    รหัส    " + rec.getString("LostID"), rec.getDouble("Lost_Totalprice"));
                listtransection.add(tctmp);
                total += rec.getDouble("Lost_Totalprice");
            }
            ps.close();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AccountController accountController = loader.getController();
        accountController.changepage("income_expense.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setincomeoutcomeTable(ObservableList<TIncome_Expenses> listtransection) {
        this.listtransection = listtransection;
        recodeDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TIncome_Expenses, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TIncome_Expenses, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRecordDate());
            }
        });
        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TIncome_Expenses, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TIncome_Expenses, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getType());
            }
        });
        totalColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TIncome_Expenses, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<TIncome_Expenses, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotal());
            }
        });


        accountTable.setItems(listtransection);


    }

}
