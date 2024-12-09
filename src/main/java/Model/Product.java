package Model;

public class Product {
    private String pid;
    private String pname;
    private double cost_per_unit;
    private double price;
    private String product_detail;
    private int amount;
    private double weight;
    private String categoryid;

    public Product(String pid, String pname, double cost_per_unit, double price, String product_detail, int amount, double weight, String categoryid) {
        this.pid = pid;
        this.pname = pname;
        this.cost_per_unit = cost_per_unit;
        this.price = price;
        this.product_detail = product_detail;
        this.amount = amount;
        this.weight = weight;
        this.categoryid = categoryid;
    }

    public Product() {
        super();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getCost_per_unit() {
        return cost_per_unit;
    }

    public void setCost_per_unit(double cost_per_unit) {
        this.cost_per_unit = cost_per_unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
}

