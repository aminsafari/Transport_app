package objects;


import com.google.gson.annotations.SerializedName;

public class EmployeeObject {


@SerializedName("ID")
    private int id ;
    @SerializedName("FNAME")
    private String fname ;
    @SerializedName("LNAME")
    private String lname ;
    @SerializedName("FATHER_NAME")
    private String father_name ;
    @SerializedName("SEX")
    private int sex ;
    @SerializedName("COMPANY")
    private String company ;
    @SerializedName("USERNAME")
    private int username ;
    @SerializedName("EMAIL")
    private String email;

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getFather_name() {
        return father_name;
    }

    public int getSex() {
        return sex;
    }

    public String getCompany() {
        return company;
    }

    public int getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
