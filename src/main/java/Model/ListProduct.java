package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListProduct {
    private final ObservableList<Product> items = FXCollections.observableArrayList();

    public void addItem(Product item){
        items.add(item);
    }

    public void removeItem(Product item){
        items.remove(item);
    }

    public ObservableList<Product> getItems(){
        return items;
    }

}
