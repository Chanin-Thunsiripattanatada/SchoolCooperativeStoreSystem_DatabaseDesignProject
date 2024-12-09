package Model;

public class Sharebookmember {
    private int RunSharedid;
    private String recordDateShared;
    private int SHARE;
    private int totalshare;
    private String member_ID;
    private String empID;


    public Sharebookmember(int runSharedid, String recordDateShared, int SHARE, int totalshare, String member_ID, String empID) {
        RunSharedid = runSharedid;
        this.recordDateShared = recordDateShared;
        this.SHARE = SHARE;
        this.totalshare = totalshare;
        this.member_ID = member_ID;
        this.empID = empID;
    }

    public int getRunSharedid() {
        return RunSharedid;
    }

    public void setRunSharedid(int runSharedid) {
        RunSharedid = runSharedid;
    }

    public String getRecordDateShared() {
        return recordDateShared;
    }

    public void setRecordDateShared(String recordDateShared) {
        this.recordDateShared = recordDateShared;
    }

    public int getSHARE() {
        return SHARE;
    }

    public void setSHARE(int SHARE) {
        this.SHARE = SHARE;
    }

    public int getTotalshare() {
        return totalshare;
    }

    public void setTotalshare(int totalshare) {
        this.totalshare = totalshare;
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
