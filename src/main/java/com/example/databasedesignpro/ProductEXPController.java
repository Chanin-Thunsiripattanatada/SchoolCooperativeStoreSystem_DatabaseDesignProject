package com.example.databasedesignpro;

import Model.ListProduct;
import Model.Product;
import Model.ProductIn;
import Model.TCooperativeAccount;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import static com.example.databasedesignpro.LoginController.conn;

public class ProductEXPController {
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


    @FXML
    private TextField miniamount;

    @FXML
    private DatePicker firstdate;
    @FXML
    private DatePicker lastdate;
    @FXML
    private ComboBox<String> typeComboBox;
    private static ListProduct listproduct = new ListProduct();
    private static int typeindex;

    @FXML
    public void initialize(){

        setProductTable(listproduct);
        typeComboBox.setItems(FXCollections.observableArrayList("นำเข้าสินค้า","สินค้าสูญหาย"));
        typeComboBox.getSelectionModel().selectFirst();
    }
    @FXML
    public void quaryproduct(ActionEvent event) throws SQLException, IOException {
        String query ;
        query = "select * from product where amount < ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, Integer.parseInt(miniamount.getText().trim()));
        ResultSet rec = ps.executeQuery();
        Product ptmp;
        listproduct = new ListProduct();
        while((rec!=null) && (rec.next())) {
            ptmp = new Product(rec.getString("PID"),rec.getString("PName"),Double.parseDouble(rec.getString("Cost_per_unit")),
                    Double.parseDouble(rec.getString("Price")),rec.getString("Product_detail"), Integer.parseInt(rec.getString("Amount")),
                    Double.parseDouble(rec.getString("Weight")),rec.getString("CategoryID"));
            listproduct.addItem(ptmp);
        }
        ps.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProductController productController = loader.getController();
        productController.changepage("prod_exp.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    }
    @FXML
    public void queryProductin() throws SQLException {
        String query = null;
        typeindex = typeComboBox.getSelectionModel().getSelectedIndex();
        if(typeindex == 0) {
            query = "select product_in_import.ImportID as id,pid as ProductID,quantity,Totalcost from record_import,product_in_import" +
                    "    where importdate between date(?) and  date_add(date(?), INTERVAL 1 DAY)" +
                    "    group by product_in_import.ImportID,pid,quantity,Totalcost;";
        }
        if(typeindex == 1){
            query = "select product_in_damagedlost.lostID as id,pid as ProductID,quantity,Totalcost from record_damagedlost_product,product_in_damagedlost"+
            "   where lostdate between date(?) and  date_add(date(?), INTERVAL 1 DAY)"+
            "   group by product_in_damagedlost.lostID,pid,quantity,Totalcost";
        }
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, String.valueOf((firstdate.getValue()==null) ? LocalDate.now():firstdate.getValue() ));
        ps.setString(2, String.valueOf((lastdate.getValue()==null) ? LocalDate.now():lastdate.getValue() ));
        ResultSet rec = ps.executeQuery();
        Product ptmp;
        ListProduct lstproduct = new ListProduct();
        while((rec!=null) && (rec.next())) {
            ptmp = new Product(rec.getString("id"),rec.getString("ProductID"),0.0,
                    Double.parseDouble(rec.getString("totalcost")),null, Integer.parseInt(rec.getString("quantity")),
                    0.0,null);
            lstproduct.addItem(ptmp);
            System.out.println(ptmp.getPid() +" "+ ptmp.getPname()+" " +(ptmp.getAmount())+" " +(ptmp.getWeight()));
        }
        ps.close();
        setProductInTable(lstproduct);
    }
    public void setProductInTable(ListProduct listproduct) {
        pidColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPid());
            }
        });
        pidColumn.setText("หมายเลข/รหัส");
        pnameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPname());
            }
        });
        pnameColumn.setText("รหัสสินค้า");

        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Product, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getPrice());
            }
        });
        priceColumn.setText("ราคารวม");
        amountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Product, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getAmount ());
            }
        });
        amountColumn.setText("จำนวน");
        productTable.setItems(listproduct.getItems());
        productTable.getColumns().remove(cost_per_unitColumn);
        productTable.getColumns().remove(typeColumn);
        productTable.autosize();
    }


}
