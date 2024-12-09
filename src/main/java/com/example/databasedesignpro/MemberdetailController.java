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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;

import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.MemberinfoController.member;

public class MemberdetailController {
    @FXML
    private Label member_idLabel;
    @FXML
    private Label id_card_numberLabel;
    @FXML
    private Label member_nameLabel;
    @FXML
    private Label member_addressLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label datetoregisLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label member_careerLabel;

    @FXML
    private TableView<Memberpurchasebook> memberpurchaseTable;
    @FXML
    private TableColumn<Memberpurchasebook, String> runmemColumn;
    @FXML
    private TableColumn<Memberpurchasebook, String> datememColumn;
    @FXML
    private TableColumn<Memberpurchasebook, Double> orderidColumn;
    @FXML
    private TableColumn<Memberpurchasebook, Double> totalpriceColumn;
    public static ObservableList<Memberpurchasebook> listmemberpurchasebook = FXCollections.observableArrayList();

    @FXML
    private TableView<Sharebookmember> sharebookTable;
    @FXML
    private TableColumn<Sharebookmember, String> runshareColumn;
    @FXML
    private TableColumn<Sharebookmember, String> dateshareColumn;
    @FXML
    private TableColumn<Sharebookmember, Double> shareColumn;
    @FXML
    private TableColumn<Sharebookmember, Double> totalshareColumn;
    public static ObservableList<Sharebookmember> listsharebook = FXCollections.observableArrayList();

    @FXML
    private TableView<AccountMember> memberbookTable;
    @FXML
    private TableColumn<AccountMember, String> runaccColumn;
    @FXML
    private TableColumn<AccountMember, String> dateaccColumn;
    @FXML
    private TableColumn<AccountMember, String> typeaccColumn;
    @FXML
    private TableColumn<AccountMember, Double> divColumn;
    @FXML
    private TableColumn<AccountMember, Double> totaldivColumn;
    public static ObservableList<AccountMember> listaccmember = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException, IOException {
        quarymemberpurchase();
        quaryaccountmember();
        quarysharebook();
        quaryaccountmember();
        setLabelMember();
        setMemberpurchaseTable();
        setmemberbookTable();
        setsharebookTable();
    }

    public void setLabelMember() {
        member_idLabel.setText(member.getMember_id());
        id_card_numberLabel.setText(member.getId_card_number());
        member_nameLabel.setText(member.getMember_name());
        member_addressLabel.setText(member.getMember_address());
        phonenumberLabel.setText(member.getPhonenumber());
        datetoregisLabel.setText(member.getDatetoregis());
        emailLabel.setText(member.getEmail());
        member_careerLabel.setText(member.getMember_career());
    }

    public void quarymemberpurchase() throws SQLException {
        String query;
        Statement st = conn.createStatement();
        query = "select * from memberpurchasebook where member_id = \"" + member.getMember_id()+"\"" ;
        ResultSet rec = st.executeQuery(query);
        Memberpurchasebook mtmp;
        listmemberpurchasebook.clear();
        while ((rec != null) && (rec.next())) {
            mtmp = new Memberpurchasebook(Integer.parseInt(rec.getString("Runbookid")), rec.getString("recordDate"), rec.getString("OrderID"),
                    Double.parseDouble(rec.getString("Totalprice")), rec.getString("Member_ID"), rec.getString("EmpID"));
            listmemberpurchasebook.add(mtmp);
        }
        st.close();

    }

    public void setMemberpurchaseTable() {
        runmemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Memberpurchasebook, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Memberpurchasebook, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRunbookid());
            }
        });
        datememColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Memberpurchasebook, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Memberpurchasebook, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRecordDate());
            }
        });
        orderidColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Memberpurchasebook, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Memberpurchasebook, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getOrderID());
            }
        });

        totalpriceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Memberpurchasebook, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Memberpurchasebook, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotalPrice());
            }
        });
        memberpurchaseTable.setItems(listmemberpurchasebook);


    }

    public static void quarysharebook() throws SQLException {
        String query;
        Statement st = conn.createStatement();
        query = "select * from sharebookmember\n" +
                "where member_id = \"" + member.getMember_id() + "\" ;";
        ResultSet rec = st.executeQuery(query);
        Sharebookmember mtsb;
        listsharebook.clear();
        while ((rec != null) && (rec.next())) {
            mtsb = new Sharebookmember(Integer.parseInt(rec.getString("Runbookid")), rec.getString("recordDate"), Integer.parseInt(rec.getString("SHARE")),
                    Integer.parseInt(rec.getString("totalshare")), rec.getString("Member_ID"), rec.getString("EmpID"));
            listsharebook.add(mtsb);
        }
        st.close();
    }
    public void setsharebookTable() {
        runshareColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sharebookmember, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sharebookmember, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRunSharedid());
            }
        });
        dateshareColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sharebookmember, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sharebookmember, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRecordDateShared());
            }
        });
        shareColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sharebookmember, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Sharebookmember, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getSHARE());
            }
        });

        totalshareColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sharebookmember, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Sharebookmember, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotalshare());
            }
        });

        sharebookTable.setItems(listsharebook);


    }


    public static void quaryaccountmember() throws SQLException {
        String query;
        Statement st = conn.createStatement();
        query = "select * from accountmember\n" +
                "where member_id = \"" + member.getMember_id() + "\" ;";
        ResultSet rec = st.executeQuery(query);
        AccountMember mtacc;
        listaccmember.clear();
        double temp = 0;
        while ((rec != null) && (rec.next())) {
            System.out.println(temp);
            mtacc = new AccountMember(Integer.parseInt(rec.getString("Runbookid")), rec.getString("recordDate"), rec.getString("accountType"),
                    (rec.getString("dividend") == null) ? 0 : Double.parseDouble(rec.getString("dividend")),
                    (rec.getString("total") == null) ? 0 :Double.parseDouble(rec.getString("total")), rec.getString("Member_ID"), rec.getString("EmpID"));
            listaccmember.add(mtacc);
        }
        st.close();
    }

    public void setmemberbookTable() {
        runaccColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AccountMember, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AccountMember, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRunMemid());
            }
        });
        dateaccColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AccountMember, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AccountMember, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRecodeDateMem());
            }
        });
        typeaccColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AccountMember, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AccountMember, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getType());
            }
        });

        divColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AccountMember, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<AccountMember, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getDividend());
            }
        });
        totaldivColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AccountMember, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<AccountMember, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotaldiv());
            }
        });

        memberbookTable.setItems(listaccmember);
    }
    @FXML
    public void cal_moneyback(ActionEvent event) throws SQLException, IOException {
        CallableStatement cs = conn.prepareCall("{call share_div(?,?)}");
        cs.setString(1, member.getMember_id());
        cs.setString(2, LoginController.employee.getEmpid());
//        cs.setInt(3, 0);
        cs.executeUpdate();
        cs.close();
        cs = conn.prepareCall("{call income_div(?,?)}");
        cs.setString(1,member.getMember_id());
        cs.setString(2,LoginController.employee.getEmpid());
        cs.executeUpdate();
        cs.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
        Parent root = null;
        root = loader.load();
        MemberController memberController = loader.getController();
        memberController.changepage("member_detail.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



}
