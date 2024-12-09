package com.example.databasedesignpro;

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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

import static com.example.databasedesignpro.App.InputErrorListener;
import static com.example.databasedesignpro.LoginController.conn;
import static com.example.databasedesignpro.RegisController.leftPadding;


public class ProductCheckstockController {
//    input
    @FXML
    private TextField searchbypid;
    @FXML
    private TextField searchbypname;
    @FXML
    private TextField quantityinput;
    @FXML
    private TextField idinput;
    @FXML
    private ComboBox<String> edittype;


    @FXML
    private Label pidLabel;
    @FXML
    private Label pnameLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label timetoday;


    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> pidColumn;
    @FXML
    private TableColumn<Product, String> pnameColumn;
    @FXML
    private TableColumn<Product, String> typeColumn;
    @FXML
    private TableColumn<Product, Integer> amountColumn;
    private static ListProduct listproduct = new ListProduct();
    private static Product product = new Product();
    private static int edittypeindex = 0;

    @FXML
    public void initialize(){

        setProductTable(listproduct);
        setLabelProduct();
        edittype.setItems(FXCollections.observableArrayList("นำเข้าสินค้า","ระบุสินค้าสูญหาย"));
        edittype.getSelectionModel().selectFirst();
        edittype.getSelectionModel().select(edittypeindex);
        InputErrorListener(quantityinput);

    }

    private void setLabelProduct() {

        pidLabel.setText(product.getPid());
        pnameLabel.setText(product.getPname());
        amountLabel.setText(String.valueOf(product.getAmount()));
        timetoday.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    public void quaryproduct(ActionEvent event) throws SQLException, IOException {
        String pidsearch =  (this.searchbypid.getText().trim().isEmpty()) ? "" : this.searchbypid.getText().trim();
        String pnamesearch = (this.searchbypname.getText().trim().isEmpty()) ? "" : this.searchbypname.getText().trim();
        String query ;
        Statement st = conn.createStatement();
        query = "select * from product\n" +
                "where pid like \"%"+pidsearch+"%\" and pname like \"%"+pnamesearch +"%\" ;";
        ResultSet rec = st.executeQuery(query);
        Product ptmp;
        listproduct = new ListProduct();
        while((rec!=null) && (rec.next())) {
            ptmp = new Product(rec.getString("PID"),rec.getString("PName"),Double.parseDouble(rec.getString("Cost_per_unit")),
                    Double.parseDouble(rec.getString("Price")),rec.getString("Product_detail"), Integer.parseInt(rec.getString("Amount")),
                    Double.parseDouble(rec.getString("Weight")),rec.getString("CategoryID"));
            listproduct.addItem(ptmp);
        }
        st.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProductController productController = loader.getController();
        productController.changepage("check_stock.fxml");
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
                            product = data;
                            edittypeindex = edittype.getSelectionModel().getSelectedIndex();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            ProductController productController = loader.getController();
                            try {
                                productController.changepage("check_stock.fxml");
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
    @FXML
    private void updateStock(ActionEvent event) throws SQLException, IOException {
        edittypeindex = edittype.getSelectionModel().getSelectedIndex();
        String sql = "";
//        update import
        if(edittypeindex == 0 && !idinput.getText().isEmpty())  {
            sql = "select importid from record_import where importid = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, idinput.getText().trim());
            ResultSet rec = preparedStmt.executeQuery();
            boolean checknewimportid = false;
            if (rec.next()) {
                checknewimportid = true;
            }
            preparedStmt.close();
            if (!checknewimportid) {
                sql = "insert into record_import (importid,importDate,import_totalprice,Empid) values (?,now(),0.0,?)";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, idinput.getText().trim());
                preparedStmt.setString(2, LoginController.employee.getEmpid());
                preparedStmt.executeUpdate();
            }
            sql = "select quantity from product_in_import where importid = ? and pid = ?";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, idinput.getText().trim());
            preparedStmt.setString(2, product.getPid());
            rec = preparedStmt.executeQuery();
            int oldquantity = 0;
            if (rec.next()) {
                oldquantity = rec.getInt("quantity");
            }
            if(oldquantity == 0) {
                sql = "insert into product_in_import (importid,pid,quantity,totalcost) values (?,?,?,?);";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, idinput.getText().trim());
                preparedStmt.setString(2, product.getPid());
                preparedStmt.setInt(3, Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setDouble(4, product.getCost_per_unit() * Double.parseDouble(quantityinput.getText().trim()));
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
            else{
                sql = "update product_in_import set quantity = ? + ? ,totalcost = (? + ?)* ? where importid = ? and pid = ?;";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1,oldquantity);
                preparedStmt.setInt(2, Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setInt(3,oldquantity);
                preparedStmt.setInt(4, Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setDouble(5, product.getCost_per_unit());
                preparedStmt.setString(6, idinput.getText().trim());
                preparedStmt.setString(7, product.getPid());
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
            sql = "update record_import set import_totalprice = (select sum(totalcost) from product_in_import where product_in_import.importid = ? ) where record_import.importid = ?";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, idinput.getText().trim());
            preparedStmt.setString(2, idinput.getText().trim());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            sql = "update product set amount = amount +  ? where pid = ?;";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.parseInt(quantityinput.getText().trim()));
            preparedStmt.setString(2, product.getPid());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            product.setAmount(product.getAmount()+Integer.parseInt(quantityinput.getText().trim()));
            System.out.println("update");
        }
//        update lost product
        if(edittypeindex == 1 && Integer.parseInt("-" + quantityinput.getText().trim()) + product.getAmount() >= 0 ) {
            sql = "select lostid,date(lostdate),empid from record_damagedlost_product";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            ResultSet rec = preparedStmt.executeQuery();
            int count = 0;
            String newlostid;
            String oldlostid = "none";
            boolean checkdupli = false;
            while((rec!=null) && (rec.next())) {
                count += 1;
                if(rec.getString("empid").equals(LoginController.employee.getEmpid()) &&
                        rec.getDate("date(lostdate)").toLocalDate().equals(LocalDate.now())){
                    checkdupli = true;
                    oldlostid = rec.getString("lostid");
                    break;
                }
            }
            newlostid = 'L' + leftPadding(String.valueOf(Integer.valueOf(count)+1),'0',3);
            preparedStmt.close();
            if (checkdupli == false) {
                sql = "insert into record_damagedlost_product (lostid,lostDate,lost_totalprice,Empid) values (?,now(),0.0,?)";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, newlostid);
                preparedStmt.setString(2, LoginController.employee.getEmpid());
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }

            String lostid = (oldlostid.equals("none")) ? newlostid : oldlostid;
            System.out.println(lostid);
            sql = "select quantity from product_in_damagedlost where lostid = ? and pid = ?";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1,lostid);
            preparedStmt.setString(2, product.getPid());
            rec = preparedStmt.executeQuery();
            int oldquantity = 0;
            if (rec.next()) {
                oldquantity = rec.getInt("quantity");
            }
            if(oldquantity == 0) {
                sql = "insert into product_in_damagedlost (lostid,pid,quantity,totalcost) values (?,?,?,?);";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, lostid);
                preparedStmt.setString(2, product.getPid());
                preparedStmt.setInt(3, Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setDouble(4, product.getCost_per_unit() * Double.parseDouble(quantityinput.getText().trim()));
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
            else{
                sql = "update product_in_damagedlost set quantity = ? + ?,totalcost = (? + ?) * ?  where lostid = ? and pid = ?;";
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setInt(1,oldquantity);
                preparedStmt.setInt(2,Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setInt(3, oldquantity);
                preparedStmt.setInt(4, Integer.parseInt(quantityinput.getText().trim()));
                preparedStmt.setDouble(5, product.getCost_per_unit());
                preparedStmt.setString(6, lostid);
                preparedStmt.setString(7, product.getPid());
                preparedStmt.executeUpdate();
                preparedStmt.close();
            }
            sql = "update record_damagedlost_product set lost_totalprice = (select sum(totalcost) from product_in_damagedlost where product_in_damagedlost.lostid = ? ) " +
                    "where record_damagedlost_product.lostid = ?";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, lostid);
            preparedStmt.setString(2, lostid);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            sql = "update product set amount = amount -  ? where pid = ?;";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, Integer.parseInt(quantityinput.getText().trim()));
            preparedStmt.setString(2, product.getPid());
            preparedStmt.executeUpdate();
            preparedStmt.close();
            product.setAmount(product.getAmount()+(Integer.parseInt(quantityinput.getText().trim())*-1));

        }
        CallableStatement cs = conn.prepareCall("{call calculate_daily()}");
        cs.executeUpdate();
        cs.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("product.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product = new Product();
        ProductController productController = loader.getController();
        productController.changepage("check_stock.fxml");
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

}
