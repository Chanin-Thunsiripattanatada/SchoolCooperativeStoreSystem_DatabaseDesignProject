package com.example.databasedesignpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AccountController {
    @FXML
    private Pane panemenu;
    @FXML
    private Label nameemp;
    //    scene in member menu page
    @FXML
    public void initialize() throws IOException, SQLException {
        App.cal_daily();
        nameemp.setText(LoginController.employee.getEmpname());
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("accountdata.fxml"));
        panemenu.getChildren().add(newLoadedPane);
    }

//    change Menu
    @FXML
    public void toProductMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toCounterMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toMemberMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("member.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void toStockmarketMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stockmarketmenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void changepage(String path) throws IOException {
        panemenu.getChildren().clear();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
        panemenu.getChildren().add(newLoadedPane);
    }
    @FXML
    public void logout(ActionEvent event) throws SQLException {
        App.cal_daily();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("กำลังออกจากโปรแกรม !");
        alert.setContentText("ต้องการออกจากระบบหรือไม่ ");
        if(ButtonType.OK == alert.showAndWait().get()){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            System.out.println("You successfully logged out");
            stage.close();
        }
    }

    //    change pane in product menu
    @FXML
    public void accountdata(ActionEvent event) throws IOException {
        changepage("accountdata.fxml");
    }
    @FXML
    public void income_expense(ActionEvent event) throws IOException {
        changepage("income_expense.fxml");
    }
    @FXML
    public void performance(ActionEvent event) throws IOException {
        changepage("performance.fxml");
    }

}
