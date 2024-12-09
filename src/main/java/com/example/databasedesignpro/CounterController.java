package com.example.databasedesignpro;

import Model.Cart;
import Model.CartItem;
import Model.ListProduct;
import Model.Product;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.databasedesignpro.App.InputErrorListener;
import static com.example.databasedesignpro.LoginController.conn;

public class CounterController   {
//  input in scene
    @FXML
    private TextField searchbypid;
    @FXML
    private TextField searchbypname;
    @FXML
    private TextField member_id_input;
    @FXML
    private TextField quantity;


//  Product table
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
    private static ListProduct listproduct = new ListProduct();

//  name Employee
    @FXML
    private Label nameemp;

//  Choose Product
    @FXML
    private Label pidLabel;
    @FXML
    private Label pnameLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label totalPrice;

    private static Product product = new Product() ;
    public void setProduct(Product product){
        CounterController.product = product;
    }


//  Cart table
    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem, String> piditemColumn;
    @FXML
    private TableColumn<CartItem, String> pnameitemColumn;
    @FXML
    private TableColumn<CartItem,Integer> quantityitemColumn;
    @FXML
    private TableColumn<CartItem,Double> priceitemColumn;
    @FXML
    private TableColumn<CartItem,Double> totalsubpriceitemColumn;
    private static Cart cart = new Cart();


    @FXML
    public void initialize(){
        setProductTable(listproduct);
        setCartTable(cart);
        nameemp.setText(" ");
        setLabel();
        addButtonToCartTable();
        InputErrorListener(quantity);
    }

    private void setLabel() {
        this.pidLabel.setText(product.getPid());
        this.pnameLabel.setText(product.getPname());
        this.amountLabel.setText(String.valueOf(product.getAmount()));
        this.totalPrice.setText(String.valueOf(cart.getTotalPrice()));
    }

    @FXML
    public void quaryproduct(ActionEvent event) throws SQLException {
        String pidsearch =  (this.searchbypid.getText().trim().isEmpty()) ? "" : this.searchbypid.getText().trim();
        String pnamesearch = (this.searchbypname.getText().trim().isEmpty()) ? "" : this.searchbypname.getText().trim();
        String query ;
        Statement st = conn.createStatement();
        query = "select * from product\n" +
                "where pid like \"%"+pidsearch+"%\" and pname like \"%"+pnamesearch +"%\";";
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CounterController counterController = loader.getController();
        counterController.searchbypid.setText(searchbypid.getText());
        counterController.searchbypname.setText(searchbypname.getText());
        counterController.member_id_input.setText(member_id_input.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void setProductTable(ListProduct listproduct) {
        CounterController.listproduct = listproduct;
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
                            System.out.println("selectedData: " + data);
                            setProduct(data);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            CounterController counterController = loader.getController();
                            counterController.setProduct(data);
                            counterController.pidLabel.setText(data.getPid());
                            counterController.searchbypid.setText(searchbypid.getText());
                            counterController.searchbypname.setText(searchbypname.getText());
                            counterController.member_id_input.setText(member_id_input.getText());
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
    public void setCartTable(Cart cart) {
        CounterController.cart = cart;
        piditemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CartItem, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CartItem, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getproduct().getPid());
            }
        });
        pnameitemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CartItem, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CartItem, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getproduct().getPname());
            }
        });
        priceitemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CartItem, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<CartItem, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getproduct().getPrice());
            }
        });
        quantityitemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CartItem, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<CartItem, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getQuantity());
            }
        });
        totalsubpriceitemColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CartItem, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<CartItem, Double> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getSubTotal());
            }
        });
        cartTable.setItems(cart.getItems());



    }
    private void addButtonToCartTable() {
        TableColumn<CartItem, Void> colBtn = new TableColumn("ปุ่มเลือกสินค้า");

        Callback<TableColumn<CartItem, Void>, TableCell<CartItem, Void>> cellFactory = new Callback<TableColumn<CartItem, Void>, TableCell<CartItem, Void>>() {
            @Override
            public TableCell<CartItem, Void> call(final TableColumn<CartItem, Void> param) {
                final TableCell<CartItem, Void> cell = new TableCell<CartItem, Void>() {

                    private final Button btn = new Button("ลบ");

                    {
                        btn.setOnAction((ActionEvent event)  -> {
                            CartItem data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            cart.removeItem(data);
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            CounterController counterController = loader.getController();
                            counterController.pidLabel.setText(product.getPid());
                            counterController.searchbypid.setText(searchbypid.getText());
                            counterController.searchbypname.setText(searchbypname.getText());
                            counterController.member_id_input.setText(member_id_input.getText());
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

        cartTable.getColumns().add(colBtn);
    }

    @FXML
    public void addtocart(ActionEvent event){
        if (!(this.quantity.getText().trim().isEmpty() ) && NumberandcheckAmount()) {
            cart.addItem(product, Integer.parseInt(this.quantity.getText()));
            product.setAmount(product.getAmount() - Integer.parseInt(this.quantity.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CounterController counterController = loader.getController();
            counterController.pidLabel.setText(product.getPid());
            counterController.searchbypid.setText(searchbypid.getText());
            counterController.searchbypname.setText(searchbypname.getText());
            counterController.member_id_input.setText(member_id_input.getText());
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else{
             
        }
    }

//    insert order
    @FXML
    public void insertOrder(ActionEvent event) throws SQLException {
        String sql;
        sql = "INSERT INTO recordorder(OrderDate,Totalprice,income,Member_ID,EmpID)"
                + "VALUES (now() ,? ,? ,? ,? )";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setDouble(1,cart.getTotalPrice());
        preparedStmt.setDouble(2,cart.getIncome());
        preparedStmt.setString(3,(member_id_input.getText().trim().isEmpty()) ? null : member_id_input.getText().trim());
        preparedStmt.setString(4, LoginController.employee.getEmpid());
        preparedStmt.executeUpdate();
        preparedStmt.close();
        Statement st = conn.createStatement();
        sql = "select max(orderid) as id from recordorder";
        ResultSet rec = st.executeQuery(sql);
        String orderid = null;
        if (rec.next()) {
            orderid = String.valueOf(rec.getObject(1));

        }
        st.close();

        try {
            String query = " insert into product_in_order (OrderID,PID,quantity,Totalprice)"
                    + " values (?, ?, ?, ?)";
            for (CartItem cartItem : cart.getItems()) {
                preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, orderid);
                preparedStmt.setString(2, cartItem.getproduct().getPid());
                preparedStmt.setInt(3, cartItem.getQuantity());
                preparedStmt.setDouble(4, cartItem.getSubTotal());
                preparedStmt.executeUpdate();

            }
            preparedStmt.close();
        }
        catch (Exception e)
        {

        }
        if(!(member_id_input.getText().trim().isEmpty())) {
            sql = " insert into memberpurchasebook (RecordDate,OrderID,Totalprice,Member_ID,EmpID)"
                    + " values (now(),?, ?, ?, ?)";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, orderid);
            preparedStmt.setDouble(2, cart.getTotalPrice());
            preparedStmt.setString(3, member_id_input.getText().trim());
            preparedStmt.setString(4, LoginController.employee.getEmpid());
            preparedStmt.executeUpdate();
        }
        preparedStmt.close();
        CallableStatement cs = conn.prepareCall("{call update_stock(?)}");
        cs.setInt(1,Integer.valueOf(orderid));
        cs.executeUpdate();
        cs.close();
        cs = conn.prepareCall("{call calculate_daily()}");
        cs.executeUpdate();
        cs.close();
        writeOrder();
        cart = new Cart();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("counter.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CounterController counterController = loader.getController();
        counterController.pidLabel.setText(product.getPid());
        counterController.searchbypid.setText(searchbypid.getText());
        counterController.searchbypname.setText(searchbypname.getText());
        counterController.member_id_input.setText(member_id_input.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public boolean NumberandcheckAmount(){
        try {
            int q = Integer.parseInt(quantity.getText().trim());
            if( product.getAmount() - q >=0) {
                return true;
            }
            return false;
        }
        catch (NumberFormatException e){
            return false;
        }

    }
    @FXML
    public void toAccountMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("account.fxml"));
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
    @FXML
    public void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout !");
        alert.setContentText("Do you want to save before exiting? : ");
        if(ButtonType.OK == alert.showAndWait().get()){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            System.out.println("You successfully logged out");
            stage.close();
        }
    }
    private void writeOrder() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String formattedFileName = String.format("Order"+formattedDate+".txt");
        File myObj = new File(formattedFileName);
        try {
            FileWriter myWriter = new FileWriter(formattedFileName);

            myWriter.write("Order : " + formattedDate + "\n");
            myWriter.write("Member : " + member_id_input.getText().trim() + "\t Employee : " + LoginController.employee.getEmpname() + "\n");
            myWriter.write("Product"  + "\t\t\t" + "Price" + "\t\t\t" + "Quantity" + "\n");
            for(var e : cart.getItems()){
                myWriter.write(e.getproduct().getPname() + "\t\t\t" + e.getproduct().getPrice() + "\t\t\t"
                        + e.getQuantity() + "\n");
            }
            myWriter.write("Total : " + cart.getTotalPrice());

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
