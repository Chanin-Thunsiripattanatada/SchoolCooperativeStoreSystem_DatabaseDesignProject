package Model;

public class TIncome_Expenses {
    private String recordDate;
    private String type;
    private double total;

    public TIncome_Expenses(String recordDate, String type, double total) {
        this.recordDate = recordDate;
        this.type = type;
        this.total = total;
    }
    public TIncome_Expenses(){
        super();
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
