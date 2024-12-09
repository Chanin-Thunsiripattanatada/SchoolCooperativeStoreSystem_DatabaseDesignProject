package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListMember {
    private final ObservableList<Member> items = FXCollections.observableArrayList();

    public void addItem(Member item){
        items.add(item);
    }

    public void removeItem(Member item){
        items.remove(item);
    }

    public ObservableList<Member> getItems(){
        return items;
    }

}
