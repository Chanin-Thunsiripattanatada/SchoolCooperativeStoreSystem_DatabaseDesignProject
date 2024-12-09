package Model;

public class TCooperativeAccount {
    private int Runbookid;
    private String recodeDate;
    private double income;
    private double outcome;
    private double TotalDaily;
    private String EmpID;

    public TCooperativeAccount(int runbookid, String recodeDate, double income, double outcome, double totalDaily, String empID) {
        Runbookid = runbookid;
        this.recodeDate = recodeDate;
        this.income = income;
        this.outcome = outcome;
        TotalDaily = totalDaily;
        EmpID = empID;
    }
    public TCooperativeAccount() {
        super();
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = outcome;
    }

    public double getTotalDaily() {
        return TotalDaily;
    }

    public void setTotalDaily(double totalDaily) {
        TotalDaily = totalDaily;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }
}
