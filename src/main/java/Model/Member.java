package Model;

public class Member {
    private String member_id;
    private String id_card_number;
    private String member_name;
    private String member_address;
    private String phonenumber;
    private String datetoregis;
    private String email;
    private String member_career;

    public Member(String member_id, String id_card_number, String member_name, String member_address, String phonenumber, String datetoregis, String email, String member_career) {
        this.member_id = member_id;
        this.id_card_number = id_card_number;
        this.member_name = member_name;
        this.member_address = member_address;
        this.phonenumber = phonenumber;
        this.datetoregis = datetoregis;
        this.email = email;
        this.member_career = member_career;
    }

    public Member() {
        super();
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_address() {
        return member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDatetoregis() {
        return datetoregis;
    }

    public void setDatetoregis(String datetoregis) {
        this.datetoregis = datetoregis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMember_career() {
        return member_career;
    }

    public void setMember_career(String member_career) {
        this.member_career = member_career;
    }
}
