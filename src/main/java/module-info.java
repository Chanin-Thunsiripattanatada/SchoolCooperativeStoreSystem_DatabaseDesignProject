module com.example.databasedesignpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databasedesignpro to javafx.fxml;
    exports com.example.databasedesignpro;
}