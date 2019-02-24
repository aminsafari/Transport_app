package com.example.amin08.transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;

import objects.OrdersObject;

public class ShowOrdersActivity extends AppCompatActivity implements View.OnClickListener{

    int id_pro;
    AppCompatImageView back ;
    AppCompatTextView ordershistory  , start_addressl , start_address , end_province , id_prov , end_addressl , end_address
            , numberl , number , packagesl , contentsl , contents
            , dimensionsl , dimensions ,weightl , weight , insurancel , pickupl , deliverl
            , breakablel , phone_numberl ,zero , phone_number , total , totall
            , date , datel , serial_number , serial_numberl
            , packages ,insurance , pickup , deliver , breakable;



    SpinKitView progressBar;
    OrdersObject ordersObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);

        initViews();
        init();
    }

    private void initViews() {

        ordersObject   = (OrdersObject) getIntent().getSerializableExtra("object");

        back           = findViewById(R.id.back);
        ordershistory  = findViewById(R.id.ordershistory);
        start_addressl = findViewById(R.id.start_addressl);
        start_address  = findViewById(R.id.start_address);
        end_province   = findViewById(R.id.end_province);
        id_prov        = findViewById(R.id.id_prov);
        end_addressl   = findViewById(R.id.end_addressl);
        end_address    = findViewById(R.id.end_address);
        numberl        = findViewById(R.id.numberl);
        number         = findViewById(R.id.number);
        packagesl      = findViewById(R.id.packagesl);
        contentsl      = findViewById(R.id.contentsl);
        contents       = findViewById(R.id.contents);
        dimensionsl    = findViewById(R.id.dimensionsl);
        dimensions     = findViewById(R.id.dimensions);
        weightl        = findViewById(R.id.weightl);
        weight         = findViewById(R.id.weight);
        insurancel     = findViewById(R.id.insurancel);
        pickupl        = findViewById(R.id.pickupl);
        deliverl       = findViewById(R.id.deliverl);
        breakablel     = findViewById(R.id.breakablel);
        phone_numberl  = findViewById(R.id.phone_numberl);
        zero           = findViewById(R.id.zero);
        phone_number   = findViewById(R.id.phone_number);
        total          = findViewById(R.id.total);
        totall         = findViewById(R.id.totall);
        date           = findViewById(R.id.date);
        datel          = findViewById(R.id.datel);
        serial_number  = findViewById(R.id.serial_number);
        serial_numberl = findViewById(R.id.serial_numberl);
        packages       = findViewById(R.id.packages);
        insurance      = findViewById(R.id.insurance);
        pickup         = findViewById(R.id.pickup);
        deliver        = findViewById(R.id.deliver);
        breakable      = findViewById(R.id.breakable);
        progressBar    = findViewById(R.id.progressBar);

        ordershistory.setTypeface(MainActivity.font);
        start_addressl.setTypeface(MainActivity.font);
        start_address.setTypeface(MainActivity.font);
        end_province.setTypeface(MainActivity.font);
        end_addressl.setTypeface(MainActivity.font);
        end_address.setTypeface(MainActivity.font);
        numberl.setTypeface(MainActivity.font);
        packagesl.setTypeface(MainActivity.font);
        contentsl.setTypeface(MainActivity.font);
        contents.setTypeface(MainActivity.font);
        dimensionsl.setTypeface(MainActivity.font);
        dimensions.setTypeface(MainActivity.font);
        weightl.setTypeface(MainActivity.font);
        insurancel.setTypeface(MainActivity.font);
        pickupl.setTypeface(MainActivity.font);
        deliverl.setTypeface(MainActivity.font);
        breakablel.setTypeface(MainActivity.font);
        totall.setTypeface(MainActivity.font);
        datel.setTypeface(MainActivity.font);
        serial_numberl.setTypeface(MainActivity.font);

        back.setOnClickListener(this);
    }

    private void init() {


        start_address.setText(ordersObject.getStart_address());
        id_pro = ordersObject.getId_prov();
        end_address.setText(ordersObject.getEnd_address());
        number.setText(Integer.toString(ordersObject.getNumber()));
        if (ordersObject.getPackages() == 1) {
            packages.setText("بله");

        }else {
            packages.setText("خیر");
        }

        contents.setText(ordersObject.getContents());
        dimensions.setText(ordersObject.getDimensions());
        weight.setText(Integer.toString(ordersObject.getWeight()));
        if (ordersObject.getInsurance() == 1) {
            insurance.setText("بله");
        }
        else {
            insurance.setText("خیر");
        }
        if (ordersObject.getPickup() == 1) {
            pickup.setText("بله");
        }
        else {
            pickup.setText("خیر");
        }
          if (ordersObject.getDeliver() == 1) {
              deliver.setText("بله");
        }
        else {
              deliver.setText("خیر");
        }
             if (ordersObject.getBreakable() == 1) {
                 breakable.setText("بله");
        }
        else {
                 breakable.setText("خیر");
        }

        phone_number.setText(Integer.toString(ordersObject.getPhone_number()));
        total.setText(ordersObject.getTotal());
        date.setText(ordersObject.getDate());
        serial_number.setText(ordersObject.getSerial_number());

        switch (id_pro) {
            case 0 :
                id_prov.setText("آدربایجان شرقی");
            break;
              case 1 :
                id_prov.setText("آدربایجان غربی");
            break;
              case 2 :
                id_prov.setText("اردبیل");
            break;
              case 3 :
                id_prov.setText("اصفهان");
            break;
              case 4 :
                id_prov.setText("البرز");
            break;
              case 5 :
                id_prov.setText("ایلام");
            break;
              case 6 :
                id_prov.setText("بوشهر");
            break;
              case 7 :
                id_prov.setText("تهران");
            break;
              case 8 :
                id_prov.setText("چهارمحال و بختیاری");
            break;
              case 9 :
                id_prov.setText("خراسان جنوبی");
            break;
              case 10 :
                id_prov.setText("خراسان رضوی");
            break;
              case 11 :
                id_prov.setText("خراسان شمالی");
            break;
              case 12 :
                id_prov.setText("خوزستان");
            break;
              case 13 :
                id_prov.setText("زنجان");
            break;
              case 14 :
                id_prov.setText("سمنان");
            break;
              case 15 :
                id_prov.setText("سیستان و بلوچستان");
            break;
              case 16 :
                id_prov.setText("فارس");
            break;
              case 17 :
                id_prov.setText("قزوین");
            break;
              case 18 :
                id_prov.setText("قم");
            break;
              case 19 :
                id_prov.setText("کردستان");
            break;
              case 20 :
                id_prov.setText("کرمان");
            break;
              case 21 :
                id_prov.setText("کرمانشاه");
            break;
              case 22 :
                id_prov.setText("کهگیلویه و بویراحمد");
            break;
              case 23 :
                id_prov.setText("گلستان");
            break;
              case 24 :
                id_prov.setText("گیلان");
            break;
              case 25 :
                id_prov.setText("لرستان");
            break;
              case 26 :
                id_prov.setText("مازندران");
            break;
              case 27 :
                id_prov.setText("مرکزی");
            break;
              case 28 :
                id_prov.setText("هرمزگان");
            break;
              case 29 :
                id_prov.setText("همدان");
            break;
              case 30 :
                id_prov.setText("یزد");
            break;
              case 31 :
                id_prov.setText("تهران - شهریار");
            break;
              case 32 :
                id_prov.setText("تهران - اسلامشهر");
            break;
              case 33 :
                id_prov.setText("تهران - بهارستان");
            break;
              case 34 :
                id_prov.setText("تهران - ملارد");
            break;
              case 35 :
                id_prov.setText("تهران - پاکدشت");
            break;
              case 36 :
                id_prov.setText("تهران - شهر قدس");
            break;
              case 37 :
                id_prov.setText("تهران - رباط کریم");
            break;
              case 38 :
                id_prov.setText("تهران - ورامین");
            break;
              case 39 :
                id_prov.setText("تهران - قرچک");
            break;
              case 40 :
                id_prov.setText("تهران - پردیس");
            break;
              case 41 :
                id_prov.setText("تهران - دماوند");
            break;
              case 42 :
                id_prov.setText("تهران - پیشوا");
            break;
              case 43 :
                id_prov.setText("تهران - فیروزکوه");
            break;

        }


    }

    @Override
    public void onClick(View view) {

        if (view == back) {

            finish();
        }




    }
}
