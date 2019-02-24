package objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileObject implements Serializable {


    @SerializedName("ID")
    private int id;
    private String fname;
    private String lname;

    private String email;



    private SettingsObject SettingsObject ;
    private UserObject UserObject ;



    public ProfileObject(int id , String fname , String lname  , String email) {

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



    public objects.SettingsObject getSettingsObject() {
        return SettingsObject;
    }

    public objects.UserObject getUserObject() {
        return UserObject;
    }
}
