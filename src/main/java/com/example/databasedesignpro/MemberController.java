package com.example.databasedesignpro;

import Model.Member;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class MemberController {
    //    head main page
    @FXML
    protected Label nameemp;
    //    member menu page
    @FXML
    private Pane panemenu;

    @FXML
    public void initialize() throws IOException {
        nameemp.setText(LoginController.employee.getEmpname());
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("regis_member.fxml"));
        panemenu.getChildren().add(newLoadedPane);
    }


    //    scene in menu page
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
    public void toProductMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
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
    //    scene in member menu page
    protected void changepage(String path) throws IOException {
        panemenu.getChildren().clear();
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource(path));
        panemenu.getChildren().add(newLoadedPane);
    }
    @FXML
    public void testswap(ActionEvent event) throws IOException {
        changepage("member_info.fxml");
    }
    @FXML
    public void regispage(ActionEvent event) throws IOException {
        changepage("regis_member.fxml");
    }
    @FXML
    public void memberdetail_page(ActionEvent event) throws IOException {
        changepage("member_detail.fxml");
    }



}
