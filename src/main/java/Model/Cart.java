package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    private final ObservableList<CartItem> items = FXCollections.observableArrayList();

    public void addItem(Product product,int quantity){
        for (CartItem item : items){
            if (item.getproduct().equals(product)){
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product , quantity));
    }

    public void removeItem(CartItem item){
        items.remove(item);
    }

    public ObservableList<CartItem> getItems(){
        return items;
    }

    public double getTotalPrice(){
        double totalprice = 0.0;
        for (CartItem item : items){
            totalprice += item.getSubTotal();
        }
        return totalprice;
    }
    public double getIncome(){
        double totalprice = 0.0;
        for (CartItem item : items){
            totalprice += (item.getproduct().getPrice() - item.getproduct().getCost_per_unit()) * item.getQuantity();
        }
        return totalprice;
    }
}
