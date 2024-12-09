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
import java.nio.file.Path;
import java.sql.SQLException;

public class ProductController {
    @FXML
    protected Pane panemenu;
    @FXML
    private Label nameemp;
    //    scene in member menu page
    @FXML
    public void initialize() throws IOException {
        nameemp.setText(LoginController.employee.getEmpname());
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("prod_data.fxml"));
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
    @FXML
    public void toAccountMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
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
    protected void changepage(String path) throws IOException {
        panemenu.getChildren().clear();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
        panemenu.getChildren().add(newLoadedPane);
    }

//    change page in product menu
    @FXML
    public void productdata(ActionEvent event) throws IOException {
        changepage("prod_data.fxml");
    }
    @FXML
    public void productadd(ActionEvent event) throws IOException {
        changepage("prod_add.fxml");
    }
    @FXML
    public void productdetail(ActionEvent event) throws IOException {
        changepage("prod_detail.fxml");
    }
    @FXML
    public void productexp(ActionEvent event) throws IOException {
        changepage("prod_exp.fxml");
    }
    @FXML
    public void checkstock(ActionEvent event) throws IOException {
        changepage("check_stock.fxml");
    }

}
