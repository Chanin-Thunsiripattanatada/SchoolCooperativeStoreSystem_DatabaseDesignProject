package Model;

public class Tmemberpurchase {
    private int Runbookid;
    private String recodeDate;
    private String OrderID;
    private double Totalprice;

    public Tmemberpurchase(int runbookid, String recodeDate, String orderID, double totalprice) {
        Runbookid = runbookid;
        this.recodeDate = recodeDate;
        OrderID = orderID;
        Totalprice = totalprice;
    }

    public int getRunbookid() {
        return Runbookid;
    }

    public void setRunbookid(int runbookid) {
        Runbookid = runbookid;
    }

    public String getRecodeDate() {
        return recodeDate;
    }

    public void setRecodeDate(String recodeDate) {
        this.recodeDate = recodeDate;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public double getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(double totalprice) {
        Totalprice = totalprice;
    }
}
