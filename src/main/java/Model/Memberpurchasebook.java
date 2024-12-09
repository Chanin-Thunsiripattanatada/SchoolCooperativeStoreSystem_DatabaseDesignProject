package Model;

public class Memberpurchasebook {
    private int runbookid;
    private String recordDate;
    private String orderID;
    private Double totalPrice;
    private String member_ID;
    private String empID;

    public Memberpurchasebook(int runbookid, String recordDate, String orderID, Double totalPrice, String member_ID, String empID) {
        this.runbookid = runbookid;
        this.recordDate = recordDate;
        this.orderID = orderID;
        this.totalPrice = totalPrice;
        this.member_ID = member_ID;
        this.empID = empID;
    }

    public int getRunbookid() {
        return runbookid;
    }

    public void setRunbookid(int runbookid) {
        this.runbookid = runbookid;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMember_ID() {
        return member_ID;
    }

    public void setMember_ID(String member_ID) {
        this.member_ID = member_ID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
}
