package com.example.databasedesignpro;

import Model.*;
import Model.Member;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.MemberdetailController.listmemberpurchasebook;

public class MemberinfoController {

    @FXML
    private TextField searchbyid;
    @FXML
    private TextField searchbyname;


    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> member_idColumn;
    @FXML
    private TableColumn<Member, String> id_card_numberColumn;
    @FXML
    private TableColumn<Member, String> member_nameColumn;
    @FXML
    private TableColumn<Member,String> addressColumn;
    @FXML
    private TableColumn<Member,String> phoneColumn;
    private static ListMember listmember = new ListMember();
    protected static Member member = new Member();



    @FXML
    public void initialize() throws IOException {
        setMemberTable(listmember);
    }
    @FXML
    public void quaryMember(ActionEvent event) throws SQLException, IOException {
        String midsearch =  (this.searchbyid.getText().trim().isEmpty()) ? "" : this.searchbyid.getText().trim();
        String mnamesearch = (this.searchbyname.getText().trim().isEmpty()) ? "" : this.searchbyname.getText().trim();
        String query ;
        Statement st = conn.createStatement();

        query = "select * from member\n" +
                "where member_id like \"%"+midsearch+"%\" and member_name like \"%"+mnamesearch +"%\" ;";

        ResultSet rec = st.executeQuery(query);
        Member mtmp;
        listmember = new ListMember();
        while((rec!=null) && (rec.next())) {
            mtmp = new Member(rec.getString("Member_ID"),rec.getString("ID_Card_Number"),rec.getString("Member_Name"),
                    rec.getString("Member_Address"),rec.getString("PhoneNumber"), rec.getString("DateToRegis"),
                    rec.getString("Email"),rec.getString("Member_Career"));
            listmember.addItem(mtmp);
        }
        st.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MemberController memberController = loader.getController();
        memberController.changepage("member_info.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    
    public void setMemberTable(ListMember listmember) {
        this.listmember = listmember;
        member_idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMember_id());
            }
        });
        id_card_numberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getId_card_number());
            }
        });
        member_nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMember_name());
            }
        });
        addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getMember_address());
            }
        });
        phoneColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPhonenumber());
            }
        });
        memberTable.setItems(listmember.getItems());
        addButtonToMemberTable();


    }
    private void addButtonToMemberTable() {
        TableColumn<Member, Void> colBtn = new TableColumn("ปุ่มเลือกสมาชิก");

        Callback<TableColumn<Member, Void>, TableCell<Member, Void>> cellFactory = new Callback<TableColumn<Member, Void>, TableCell<Member, Void>>() {
            @Override
            public TableCell<Member, Void> call(final TableColumn<Member, Void> param) {
                final TableCell<Member, Void> cell = new TableCell<Member, Void>() {

                    private final Button btn = new Button("เลือก");

                    {
                        btn.setOnAction((ActionEvent event)  -> {
                            Member data = getTableView().getItems().get(getIndex());
                            member = data;
                            System.out.println("selectedData: " + data);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);}
                            MemberController memberController = loader.getController();
                            try {
                                memberController.changepage("member_detail.fxml");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();

                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        memberTable.getColumns().add(colBtn);
    }


}
