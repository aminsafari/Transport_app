package objects;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrdersObject implements Serializable {


    //[{"id":"301","user_id":"139","start_address":"tehransar","id_prov":"34","end_address":"akbarcity","number":"7","package":"1","contents"
    // :"wood","dimensions":"120*60","weight":"60000","insurance":"1","pickup":"1","deliver":"1","breakable":"1","phone_number":"6430559",
    // "carriage_fares":"75000","rate_to_gr":"3000","total":"96300","date":"2018-12-29 23:47:54","serial_number":"10301","seen":"0"}

    @SerializedName("ID")
    private int id ;
    private  int user_id;
    private String start_address;
    private int id_prov;
    private String end_address;
    private int number;
    private int packages;
    private String contents;
    private String dimensions;
    private int weight;
    private int insurance;
    private int pickup;

    private int deliver;
    private int breakable;
    private int phone_number;
    private int carriage_fares;
    private int rate_to_gr;
    private String total;
    private String date;
    private String serial_number;
    private int seen;






    public OrdersObject( int id , int user_id  , String end_address  , String total , String serial_number , String date) {


        this.id = id;
        this.user_id = user_id;
        this.end_address = end_address;
        this.total = total;
        this.serial_number = serial_number;
        this.date = date;
     }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getStart_address() {
        return start_address;
    }

    public int getId_prov() {
        return id_prov;
    }

    public String getEnd_address() {
        return end_address;
    }

    public int getNumber() {
        return number;
    }

    public int getPackages() {
        return packages;
    }

    public String getContents() {
        return contents;
    }

    public String getDimensions() {
        return dimensions;
    }

    public int getWeight() {
        return weight;
    }

    public int getInsurance() {
        return insurance;
    }

    public int getPickup() {
        return pickup;
    }

    public int getDeliver() {
        return deliver;
    }

    public int getBreakable() {
        return breakable;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public int getCarriage_fares() {
        return carriage_fares;
    }

    public int getRate_to_gr() {
        return rate_to_gr;
    }


    public String getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public int getSeen() {
        return seen;
    }









}
