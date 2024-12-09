package Model;

public class AccountMember {
    private int RunMemid;
    private String recodeDateMem;
    private String type;
    private Double dividend;
    private Double totaldiv;
    private String member_ID;
    private String empID;

    public AccountMember(int runMemid,String recodeDateMem, String type, Double dividend, Double totaldiv, String member_ID, String empID) {
        RunMemid = runMemid;
        this.recodeDateMem = recodeDateMem;
        this.type = type;
        this.dividend = dividend;
        this.totaldiv = totaldiv;
        this.member_ID = member_ID;
        this.empID = empID;
    }
    public AccountMember(){
        super();
    }

    public int getRunMemid() {
        return RunMemid;
    }

    public void setRunMemid(int runMemid) {
        RunMemid = runMemid;
    }

    public String getRecodeDateMem() {
        return recodeDateMem;
    }

    public void setRecodeDateMem(String recodeDateMem) {
        this.recodeDateMem = recodeDateMem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDividend() {
        return dividend;
    }

    public void setDividend(Double dividend) {
        this.dividend = dividend;
    }

    public Double getTotaldiv() {
        return totaldiv;
    }

    public void setTotaldiv(Double totaldiv) {
        this.totaldiv = totaldiv;
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
