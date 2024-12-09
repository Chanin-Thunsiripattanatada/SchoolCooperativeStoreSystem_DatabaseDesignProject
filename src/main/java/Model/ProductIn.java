package Model;

public class ProductIn {
    private String id;
    private String pid;
    private int quantity;
    private double totalcost;
    public ProductIn(){
        super();
    }
    public ProductIn(String id, String pid, int quantity, double totalcost) {
        this.id = id;
        this.pid = pid;
        this.quantity = quantity;
        this.totalcost = totalcost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }
}
