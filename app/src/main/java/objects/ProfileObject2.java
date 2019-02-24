package objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileObject2 implements Serializable {


    @SerializedName("ID")
    private int id;
    private String fname;
    private String lname;
    private String email;

    public ProfileObject2( int id , String fname , String lname , String email) {

        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }
}
