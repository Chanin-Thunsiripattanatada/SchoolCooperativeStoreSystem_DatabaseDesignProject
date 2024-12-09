package com.example.databasedesignpro;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.example.databasedesignpro.App.InputErrorListener;
import static com.example.databasedesignpro.LoginController.conn;

public class StockBuySellController {
    @FXML
    private TextField member_id;
    @FXML
    private ComboBox<String> status;
    @FXML
    private TextField quantity;
    @FXML
    private Label check;


    @FXML
    public void insertBuySellStock(ActionEvent event) {
        try {
            int i = status.getSelectionModel().getSelectedIndex();
            int quantity;
            double totalsh = -1;
            double totaldiv = -1;
            CallableStatement cs = conn.prepareCall("{call checkshareandtotaldiv(?)}");
            cs.setString(1, member_id.getText().trim());
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                totalsh = rs.getDouble("totalsh");
                totaldiv = rs.getDouble("totalacc");
            }
            cs.close();

            if (!(this.quantity.getText().trim().isEmpty()) && !(this.member_id.getText().trim().isEmpty())
                    &&  isNumber()) {
                if (i == 0 | i == 1) {
                    quantity = (i == 0) ? Integer.parseInt(this.quantity.getText().trim()) : Integer.parseInt("-" + this.quantity.getText().trim());
                    boolean checktotalsh = totalsh + quantity >= 0;
                    if (checktotalsh) {
//                        ซื้อขายหุ้น
                        cs = conn.prepareCall("{call share_cal(?,?,?)}");
                        cs.setString(1, member_id.getText().trim());
                        cs.setString(2, LoginController.employee.getEmpid());
                        cs.setInt(3, quantity);
                        cs.executeUpdate();
                        cs.close();
                        cs = conn.prepareCall("{call calculate_daily()}");
                        cs.executeUpdate();
                        cs.close();
                        check.setTextFill(Color.GREEN);
                        setLabel("ลงข้อมูลสำเร็จ");
                    }
                    else {
                        check.setTextFill(Color.RED);
                        setLabel("จำนวนหุ้นไม่พอ");
                    }
                }
                else {
//                    เลือกถอน
                    Double quantity2 =  Double.parseDouble("-"+this.quantity.getText().trim());
                    System.out.println(quantity2 + 1);
                    boolean checktotaldiv = (totaldiv + quantity2) >= 0;
                    System.out.println(checktotaldiv);
                    if(checktotaldiv){

                        String query = "insert into accountmember(recorddate,dividend,total,member_id,empid,accounttype)" +
                                "values(now() ,? ,? ,? ,? ,?);";
                        PreparedStatement preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setDouble(1, quantity2);
                        preparedStmt.setDouble(2, totaldiv + quantity2);
                        preparedStmt.setString(3, member_id.getText().trim());
                        preparedStmt.setString(4, LoginController.employee.getEmpid());
                        preparedStmt.setString(5, "ถอน");
                        preparedStmt.executeUpdate();
                        preparedStmt.close();
                        cs = conn.prepareCall("{call calculate_daily()}");
                        cs.executeUpdate();
                        cs.close();
                        check.setTextFill(Color.GREEN);
                        setLabel("ลงข้อมูลสำเร็จ");

                    }
                    else{
                        check.setTextFill(Color.RED);
                        setLabel("ยอดเงินในบัญชีไม่พอ");
                    }
                }
            }
            else{
                check.setTextFill(Color.RED);
                setLabel("กรุณากรอกข้อมูลให้ถูกต้อง");
            }
        }
        catch (SQLException e){
            check.setTextFill(Color.RED);
            setLabel("ลงข้อมูลไม่สำเร็จ");
            System.out.println(e.getMessage());
        }
        member_id.setText("");
        quantity.setText("");

    }

    private boolean isNumber(){
        try{
            int qinput1 = Integer.parseInt(quantity.getText().trim());
            double qinput2 = Double.parseDouble(quantity.getText().trim());
            if (qinput1 <= 0 || qinput2 <= 0){
                return  false;
            }
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public void setLabel(String text){
        check.setText(text);
    }
    @FXML
    public void initialize(){
        status.setItems(FXCollections.observableArrayList("ซื้อหุ้น","ขายหุ้น","ถอนปันผล"));
        status.getSelectionModel().selectFirst();
        InputErrorListener(quantity);
    }


}
