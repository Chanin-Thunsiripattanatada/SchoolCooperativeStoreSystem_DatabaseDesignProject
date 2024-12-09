package com.example.databasedesignpro;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static com.example.databasedesignpro.App.InputErrorListener;
import static com.example.databasedesignpro.LoginController.conn;

public class RegisController {
    //    regis page
    @FXML
    private TextField idpersonal;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<String> role;
    @FXML
    private TextField email;


    @FXML
    private Label check;
    private static String checkString = "";
    @FXML
    public void initialize() throws IOException {
        role.setItems(FXCollections.observableArrayList("นักเรียน","ครู","พนักงานโรงเรียน"));
        role.getSelectionModel().selectFirst();
        InputErrorListener(idpersonal);
        InputErrorListener(phone);
    }
    private void  setLabel(String text){
        check.setText(text);
    }

    @FXML
    public void getregisData(ActionEvent event) throws SQLException, IOException, InterruptedException {
        String sql = "select count(member_id) from member";
        Statement st = conn.createStatement();
        ResultSet rec = st.executeQuery(sql);
        int newrunid  = 0;
        if (rec.next()) {
            newrunid = rec.getInt(1) + 1;
        }
        String newmember_id = "M"+leftPadding(String.valueOf(newrunid), '0', 3);
        st.close();
        if(isnotnull()) {
            sql = "insert into member(Member_ID,ID_Card_Number,Member_Name,Member_Address,PhoneNumber,DateToRegis,Email,Member_Career)" +
                    "values(? ,? ,? ,? ,? ,now() ,? ,?);";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newmember_id);
            preparedStmt.setString(2, idpersonal.getText().trim());
            preparedStmt.setString(3, name.getText().trim());
            preparedStmt.setString(4, address.getText());
            preparedStmt.setString(5, phone.getText().trim());
            preparedStmt.setString(6, email.getText().trim());
            preparedStmt.setString(7, role.getSelectionModel().getSelectedItem());
            preparedStmt.execute();
            check.setTextFill(Color.GREEN);
            setLabel("ลงข้อมูลสำเร็จ");
        }
        else{
            check.setTextFill(Color.RED);
            setLabel("ลงข้อมูลไม่สำเร็จ");
        }
    }
    private boolean isnotnull(){
        if(!idpersonal.getText().trim().isEmpty() && !name.getText().trim().isEmpty() && !address.getText().trim().isEmpty()
                && !phone.getText().trim().isEmpty() && !email.getText().trim().isEmpty()
        ){
            return true;
        }
        return false;
    }
    public static String leftPadding(String input, char ch, int L) {
        String result = String.format("%" + L + "s", input).replace(' ', ch);
        return result;
    }



}
