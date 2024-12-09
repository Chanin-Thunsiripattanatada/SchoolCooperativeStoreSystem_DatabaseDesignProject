package com.example.databasedesignpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.SQLException;

import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.LoginController.logincomplete;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("สหกรณ์โรงเรียน");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("logo.png")));
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                logout(stage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public static void cal_daily() throws SQLException {
        CallableStatement cs = conn.prepareCall("{call calculate_daily()}");
        cs.executeUpdate();
        cs.close();
    }

    public void logout(Stage stage) throws SQLException {
        if(logincomplete){
            cal_daily();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("กำลังออกจากโปรแกรม !");
        alert.setContentText("ต้องการออกจากระบบหรือไม่ ");
        if(ButtonType.OK == alert.showAndWait().get()){
            System.out.println("You successfully logged out");
            stage.close();
        }
    }
    public static void InputErrorListener(TextField textfield) {
        textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^?[0-9]\\d*(\\.\\d+)?$")) {
                String replacedVal = newValue.replaceAll("[^\\d.]", "");
                if (replacedVal.startsWith(".")) {
                    replacedVal = replacedVal.replaceFirst("\\.", "");
                }
                if (replacedVal.indexOf(".") != replacedVal.lastIndexOf(".")) {
                    replacedVal = replacedVal.substring(0, replacedVal.lastIndexOf("."));
                }
                textfield.setText(replacedVal);
            }
        });
    }


}