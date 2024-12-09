package com.example.databasedesignpro;

import Model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;


public class LoginController {
    public static Connection conn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label check;

    protected static Employee employee;
    protected static Boolean logincomplete = false;

    @FXML
    public void setLabel(String text) {
        check.setText(text);
    }

    @FXML
    public void connect(ActionEvent event) throws IOException {
//      String url = "jdbc:mysql://{ip,localhost Database}:{port}/{Database Name}";
        String url = "jdbc:mysql://localhost/projectdatabase2";
        String user = this.username.getText().trim();
        String password = this.password.getText().trim();
        logincomplete = false;
        try {
            conn = DriverManager.getConnection(url, user, password);
            setEmployee();
            logincomplete = true;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setLabel("กรุณาป้อนชื่อและรหัสผ่านให้ถูกต้อง");
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    public void setEmployee() throws SQLException {
        String query = "SELECT EmpID,member_name FROM member,employee where username = ? and employee.Member_ID = member.member_id;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,username.getText().trim());
        ResultSet rec = ps.executeQuery();
        if (rec.next()){
            employee = new Employee(rec.getString("EmpID"),rec.getString("member_name"));
        }
        CallableStatement cs = conn.prepareCall("{call newcoobook()}");
        cs.executeUpdate();
        cs.close();

    }




}
