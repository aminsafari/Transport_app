package objects;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderObject implements Serializable {


    //[{"id":"301","user_id":"139","start_address":"tehransar","id_prov":"34","end_address":"akbarcity","number":"7","package":"1","contents"
    // :"wood","dimensions":"120*60","weight":"60000","insurance":"1","pickup":"1","deliver":"1","breakable":"1","phone_number":"6430559",
    // "carriage_fares":"75000","rate_to_gr":"3000","total":"96300","date":"2018-12-29 23:47:54","serial_number":"10301","seen":"0"}

    @SerializedName("ID")
    private int id ;
    private String sender;
    private String receiver;
    private String reciver_phone_number;
    private String sender_signature;
    private String send_date;

    private String receiver_signature;
    private String receive_date;


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






    public OrderObject( int id , String sender  , String receiver  , String reciver_phone_number , String sender_signature , String send_date) {


        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.reciver_phone_number = reciver_phone_number;
        this.sender_signature = sender_signature;
        this.send_date = send_date;
    }

    public OrderObject( int id , String receiver_signature , String receive_date) {

        this.id = id;
        this.receiver_signature = receiver_signature;
        this.receive_date = receive_date;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReciver_phone_number() {
        return reciver_phone_number;
    }

    public String getSender_signature() {
        return sender_signature;
    }

    public String getSend_date() {
        return send_date;
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

    public String getReceiver_signature() {
        return receiver_signature;
    }

    public String getReceive_date() {
        return receive_date;
    }
}
