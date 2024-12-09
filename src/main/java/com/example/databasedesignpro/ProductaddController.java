package com.example.databasedesignpro;

import Model.ListProduct;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static com.example.databasedesignpro.App.InputErrorListener;
import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.RegisController.leftPadding;
import static java.lang.Double.parseDouble;

public class ProductaddController {
    @FXML
    private TextField pid;
    @FXML
    private TextField pname;
    @FXML
    private TextField cost_per_unit;
    @FXML
    private TextField price;
    @FXML
    private TextField product_detail;
    @FXML
    private TextField amount;
    @FXML
    private TextField weight;
    @FXML
    private ComboBox<String> categoryid;
    @FXML
    private ComboBox<String> status;

    @FXML
    private Label check;
    private static  Product productupdate ;


    @FXML
    public void initialize(){
        status.setItems(FXCollections.observableArrayList("เพิ่ม","แก้ไข"));
        categoryid.setItems(FXCollections.observableArrayList("อาหาร","เครื่องดื่ม"));
        status.getSelectionModel().selectFirst();
        categoryid.getSelectionModel().selectFirst();
        //listener
        InputErrorListener(price);
        InputErrorListener(cost_per_unit);
        InputErrorListener(weight);
        InputErrorListener(amount);
    }

    public void setLabel(String text){
        check.setText(text);
    }

    @FXML
    public void insertProduct(ActionEvent event) throws IOException, SQLException {

        int i = status.getSelectionModel().getSelectedIndex();
        if (i == 0) {
            if (isNumber()) {
                try {
                    String query = "insert into product(PID,PName,Cost_per_unit,Price,Product_detail,Amount,Weight,CategoryID)" +
                            "values(? ,? ,? ,? ,? ,? ,? ,?);";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, pid.getText().trim());
                    preparedStmt.setString(2, pname.getText().trim());
                    preparedStmt.setDouble(3, parseDouble(cost_per_unit.getText().trim()));
                    preparedStmt.setDouble(4, parseDouble(price.getText().trim()));
                    preparedStmt.setString(5, product_detail.getText().trim());
                    preparedStmt.setInt(6,Integer.parseInt(amount.getText().trim()));
                    preparedStmt.setDouble(7, parseDouble(weight.getText().trim()));
                    preparedStmt.setString(8, 'C' + leftPadding(String.valueOf(categoryid.getSelectionModel().getSelectedIndex() + 1), '0', 3));
                    preparedStmt.execute();
                    check.setTextFill(Color.GREEN);
                    setLabel("ลงข้อมูลสำเร็จ");

                } catch (SQLException e) {
                    check.setTextFill(Color.RED);
                    setLabel("ลงข้อมูลไม่สำเร็จ");
                    System.out.println(e.getMessage());
                }
            }
            else {
                check.setTextFill(Color.RED);
                setLabel("ลงข้อมูลไม่สำเร็จ");
            }

        } else if (i == 1) {
            String sql = "select * from product where pid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,pid.getText().trim());
            ResultSet rec = ps.executeQuery();

            productupdate = new Product();
            boolean c = false;
            if(rec.next()) {
                productupdate = new Product(rec.getString("PID"),rec.getString("PName"),Double.parseDouble(rec.getString("Cost_per_unit")),
                        Double.parseDouble(rec.getString("Price")),rec.getString("Product_detail"), Integer.parseInt(rec.getString("Amount")),
                        Double.parseDouble(rec.getString("Weight")),rec.getString("CategoryID"));
                c = true;
            }
            ps.close();
            if (c) {
                sql = "update product set pname = ?,cost_per_unit = ?,price = ?,product_detail = ?,weight = ?,CategoryID = ? where pid = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, (pname.getText().trim().isEmpty()) ? productupdate.getPname() : pname.getText().trim());
                ps.setDouble(2, (cost_per_unit.getText().trim().isEmpty()) ? productupdate.getCost_per_unit() : parseDouble(cost_per_unit.getText().trim()));
                ps.setDouble(3, (price.getText().trim().isEmpty()) ? productupdate.getPrice() : parseDouble(price.getText().trim()));
                ps.setString(4, (product_detail.getText().trim().isEmpty()) ? productupdate.getProduct_detail() : product_detail.getText().trim());
                ps.setDouble(5, (weight.getText().trim().isEmpty()) ? productupdate.getWeight() : parseDouble(weight.getText().trim()));
                ps.setString(6,  'C' + leftPadding(String.valueOf(categoryid.getSelectionModel().getSelectedIndex() + 1), '0', 3) );
                ps.setString(7, pid.getText().trim());
                ps.executeUpdate();
                check.setTextFill(Color.GREEN);
                setLabel("ลงข้อมูลสำเร็จ");
            }else {
                check.setTextFill(Color.RED);
                setLabel("ลงข้อมูลไม่สำเร็จ");
            }


        }
        else{
            check.setTextFill(Color.RED);
            setLabel("กรุณากรอกข้อมูลให้ถูกต้อง");
        }

    }
    private boolean isNumber(){
        try{
            double costpu = Double.parseDouble(cost_per_unit.getText().trim());
            double pric = Double.parseDouble(price.getText().trim());
            double weig = Double.parseDouble(weight.getText().trim());
            if (costpu < 0 || pric < 0 || weig < 0){
                return  false;
            }
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }



}
