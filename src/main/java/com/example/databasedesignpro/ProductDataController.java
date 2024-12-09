package com.example.databasedesignpro;

import Model.Cart;
import Model.Product;
import Model.ListProduct;
import Model.Product;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.RegisController.leftPadding;

public class ProductDataController {
    @FXML
    private TextField searchbypid;
    @FXML
    private TextField searchbypname;
    @FXML
    private ComboBox<String> catagoryCombobox;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> pidColumn;
    @FXML
    private TableColumn<Product, String> pnameColumn;
    @FXML
    private TableColumn<Product,Double> cost_per_unitColumn;
    @FXML
    private TableColumn<Product,Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> amountColumn;
    @FXML
    private TableColumn<Product, String> typeColumn;
    private static ListProduct listproduct = new ListProduct();
    public static Product productdetail = new Product();
    private static int typeindex;

    @FXML
    public void initialize(){

        setProductTable(listproduct);
        this.catagoryCombobox.setItems(FXCollections.observableArrayList("ทั้งหมด","อาหาร","เครื่องดื่ม"));
        this.catagoryCombobox.getSelectionModel().selectFirst();
        this.catagoryCombobox.getSelectionModel().select(typeindex);
    }
    @FXML
    public void quaryproduct(ActionEvent event) throws SQLException {
        int i = this.catagoryCombobox.getSelectionModel().getSelectedIndex();
        String pidsearch =  (this.searchbypid.getText().trim().isEmpty()) ? "" : this.searchbypid.getText().trim();
        String pnamesearch = (this.searchbypname.getText().trim().isEmpty()) ? "" : this.searchbypname.getText().trim();
        String catesearch = (i == 0) ? "" : 'C' + leftPadding(String.valueOf(this.catagoryCombobox.getSelectionModel().getSelectedIndex()), '0', 3);
        String query ;
        Statement st = conn.createStatement();

        query = "select * from product\n" +
                "where pid like \"%"+pidsearch+"%\" and pname like \"%"+pnamesearch +"%\" and categoryid like \"%"+catesearch+"%\" ;";
        ResultSet rec = st.executeQuery(query);
        Product ptmp;
        listproduct = new ListProduct();
        while((rec!=null) && (rec.next())) {
            ptmp = new Product(rec.getString("PID"),rec.getString("PName"),Double.parseDouble(rec.getString("Cost_per_unit")),
                    Double.parseDouble(rec.getString("Price")),rec.getString("Product_detail"), Integer.parseInt(rec.getString("Amount")),
                    Double.parseDouble(rec.getString("Weight")),rec.getString("CategoryID"));
            listproduct.addItem(ptmp);
            System.out.println(ptmp.getPid());

        }
        st.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void setDescProduct(Product product){
        productdetail = product;
    }
    public void setProductTable(ListProduct listproduct) {
        this.listproduct = listproduct;
        pidColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPid());
            }
        });
        pnameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPname());
            }
        });
        cost_per_unitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Product, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCost_per_unit());
            }
        });
        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Product, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrice());
            }
        });
        amountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Product, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAmount ());
            }
        });
        typeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new ReadOnlyObjectWrapper(("C001".equals(p.getValue().getCategoryid())) ? "อาหาร": "เครื่องดื่ม");
            }
        });
        productTable.setItems(listproduct.getItems());
        addButtonToProductTable();
    }
    private void addButtonToProductTable() {
        TableColumn<Product, Void> colBtn = new TableColumn("ปุ่มเลือกสินค้า");

        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<Product, Void>() {

                    private final Button btn = new Button("เลือก");

                    {
                        btn.setOnAction((ActionEvent event)  -> {
                            Product data = getTableView().getItems().get(getIndex());
                            setDescProduct(data);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            ProductController productController = loader.getController();
                            try {
                                productController.changepage("prod_detail.fxml");

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        productTable.getColumns().add(colBtn);
    }
}
