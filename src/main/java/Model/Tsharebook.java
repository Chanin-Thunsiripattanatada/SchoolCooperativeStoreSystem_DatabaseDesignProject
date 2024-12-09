package Model;

public class Tsharebook {
    private int Runbookid;
    private String recodeDate;
    private double SHARE;
    private double totalshare;

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

    public double getSHARE() {
        return SHARE;
    }

    public void setSHARE(double SHARE) {
        this.SHARE = SHARE;
    }

    public double getTotalshare() {
        return totalshare;
    }

    public void setTotalshare(double totalshare) {
        this.totalshare = totalshare;
    }

    public Tsharebook(int runbookid, String recodeDate, double SHARE, double totalshare) {
        Runbookid = runbookid;
        this.recodeDate = recodeDate;
        this.SHARE = SHARE;
        this.totalshare = totalshare;
    }
}
