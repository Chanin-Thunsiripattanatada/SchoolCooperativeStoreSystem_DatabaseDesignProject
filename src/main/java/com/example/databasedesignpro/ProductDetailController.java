package com.example.databasedesignpro;

import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ProductDetailController {
    @FXML
    private Label pid;
    @FXML
    private Label pname;
    @FXML
    private Label cost_per_unit;
    @FXML
    private Label price;
    @FXML
    private Label product_detail;
    @FXML
    private Label amount;
    @FXML
    private Label weight;
    @FXML
    private Label categoryid;
    public void setLabelProduct(Product product){
        this.pid.setText(product.getPid());
        this.pname.setText(product.getPname());
        this.cost_per_unit.setText(String.valueOf(product.getCost_per_unit()));
        this.price.setText(String.valueOf(product.getPrice()));
        this.product_detail.setText(product.getProduct_detail());
        this.amount.setText(String.valueOf(product.getAmount()));
        this.weight.setText(String.valueOf(product.getWeight()));
        this.categoryid.setText(product.getCategoryid());
    }
    @FXML
    public void initialize() throws IOException {
        setLabelProduct(ProductDataController.productdetail);
    }
}
