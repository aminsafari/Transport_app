package com.example.amin08.transport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PreemptiveAuthorizationHttpRequestInterceptor;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.SpinnerAdapter;
import cz.msebera.android.httpclient.Header;
import fragments.OrderFragment;
import objects.OrdersObject;
import objects.SpinnerObject;
import app.*;
import app.spref;

import static cz.msebera.android.httpclient.client.methods.RequestBuilder.delete;

public class AddOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , View.OnClickListener {

    List<SpinnerObject> list = new ArrayList<>();
    SpinnerAdapter adapter;
    AppCompatSpinner spinner;
    Object value;
    int id_prov;


    AppCompatImageView back , delete , save;
    LinearLayout parent;
    AppCompatEditText start_address , end_address , number , contents , dimensions , weight , phone_number;
    AppCompatCheckBox packages;
    AppCompatCheckBox insurance;
    AppCompatCheckBox pickup;
    AppCompatCheckBox deliver;
    AppCompatCheckBox breakable;
    AppCompatTextView  add_orders , start_addressl , id_provl , end_addressl , numberl ,packagesl ,contentsl, dimensionsl , weightl , insurancel , pickupl , deliverl , breakablel , phone_numberl , totall , datel , serial_numberl , total , date , serial_number;

    SpinKitView progressbar;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_orders);
        init();
    }

    private void init() {

        spinner             = findViewById(R.id.spinner);
        back                =findViewById(R.id.back);
        delete              =findViewById(R.id.delete);
        save                =findViewById(R.id.save);
        //parent              =findViewById(R.id.parent);
        start_address       =findViewById(R.id.start_address);
        end_address         =findViewById(R.id.end_address);
        number              =findViewById(R.id.number);
        contents            =findViewById(R.id.contents);
        dimensions          =findViewById(R.id.dimensions);
        weight              =findViewById(R.id.weight);
        phone_number        =findViewById(R.id.phone_number);
        packages            =findViewById(R.id.packages);
        insurance           =findViewById(R.id.insurance);
        pickup              =findViewById(R.id.pickup);
        deliver             =findViewById(R.id.deliver);
        breakable           =findViewById(R.id.breakable);
        total               =findViewById(R.id.total);
        date                =findViewById(R.id.date);
        serial_number       =findViewById(R.id.serial_number);
        progressbar         = findViewById(R.id.progressBar);
        add_orders          = findViewById(R.id.add_orders);
        start_addressl      = findViewById(R.id.start_addressl);
        id_provl            = findViewById(R.id.id_provl);
        end_addressl        = findViewById(R.id.end_addressl);
        numberl             = findViewById(R.id.numberl);
        packagesl           = findViewById(R.id.packagesl);
        contentsl           = findViewById(R.id.contentsl);
        dimensionsl         = findViewById(R.id.dimensionsl);
        weightl             = findViewById(R.id.weightl);
        insurancel          = findViewById(R.id.insurancel);
        pickupl             = findViewById(R.id.pickupl);
        deliverl            = findViewById(R.id.deliverl);
        breakablel          = findViewById(R.id.breakablel);
        phone_numberl       = findViewById(R.id.phone_numberl);
        totall              = findViewById(R.id.totall);
        datel               = findViewById(R.id.datel);
        serial_numberl      = findViewById(R.id.serial_numberl);

        start_address.setTypeface(MainActivity.font);
        end_address.setTypeface(MainActivity.font);
        contents.setTypeface(MainActivity.font);
        dimensions.setTypeface(MainActivity.font);
        add_orders.setTypeface(MainActivity.font);
        start_addressl.setTypeface(MainActivity.font);
        id_provl.setTypeface(MainActivity.font);
        end_addressl.setTypeface(MainActivity.font);
        numberl.setTypeface(MainActivity.font);
        packagesl.setTypeface(MainActivity.font);
        contentsl.setTypeface(MainActivity.font);
        dimensionsl.setTypeface(MainActivity.font);
        weightl.setTypeface(MainActivity.font);
        insurancel.setTypeface(MainActivity.font);
        pickupl.setTypeface(MainActivity.font);
        deliverl.setTypeface(MainActivity.font);
        breakablel.setTypeface(MainActivity.font);
        phone_numberl.setTypeface(MainActivity.font);
        totall.setTypeface(MainActivity.font);
        datel.setTypeface(MainActivity.font);
        serial_numberl.setTypeface(MainActivity.font);



        start_address.setText(spref.get().getString(ROUTER.INPUT_START_ADDRESS , ""));
       // start_address.setTypeface(MainActivity.font);

        list.add(new SpinnerObject("آذربایجان شرقی"));
        list.add(new SpinnerObject("آذربایجان غربی"));
        list.add(new SpinnerObject("اردبیل"));
        list.add(new SpinnerObject("اصفهان"));
        list.add(new SpinnerObject("البرز"));
        list.add(new SpinnerObject("ایلام"));
        list.add(new SpinnerObject("بوشهر"));
        list.add(new SpinnerObject("تهران"));
        list.add(new SpinnerObject("چهارمحال و بختیاری"));
        list.add(new SpinnerObject("خراسان جنوبی"));
        list.add(new SpinnerObject("خراسان رضوی"));
        list.add(new SpinnerObject("خراسان شمالی"));
        list.add(new SpinnerObject("خوزستان"));
        list.add(new SpinnerObject("زنجان"));
        list.add(new SpinnerObject("سمنان"));
        list.add(new SpinnerObject("سیستان و بلوچستان"));
        list.add(new SpinnerObject("فارس"));
        list.add(new SpinnerObject("قزوین"));
        list.add(new SpinnerObject("قم"));
        list.add(new SpinnerObject("کردستان"));
        list.add(new SpinnerObject("کرمان"));
        list.add(new SpinnerObject("کرمانشاه"));
        list.add(new SpinnerObject("کهگیلویه و بویراحمد"));
        list.add(new SpinnerObject("گلستان"));
        list.add(new SpinnerObject("گیلان"));
        list.add(new SpinnerObject("لرستان"));
        list.add(new SpinnerObject("مازندران"));
        list.add(new SpinnerObject("مرکزی"));
        list.add(new SpinnerObject("هرمزگان"));
        list.add(new SpinnerObject("همدان"));
        list.add(new SpinnerObject("یزد"));
        list.add(new SpinnerObject("تهران - شهریار"));
        list.add(new SpinnerObject("تهران - اسلامشهر"));
        list.add(new SpinnerObject("تهران - بهارستان"));
        list.add(new SpinnerObject("تهران - ملارد"));
        list.add(new SpinnerObject("تهران - پاکدشت"));
        list.add(new SpinnerObject("تهران - شهر قدس"));
        list.add(new SpinnerObject("تهران - رباط کریم"));
        list.add(new SpinnerObject("تهران - ورامین"));
        list.add(new SpinnerObject("تهران - قرچک"));
        list.add(new SpinnerObject("تهران - پردیس"));
        list.add(new SpinnerObject("تهران - دماوند"));
        list.add(new SpinnerObject("تهران - پیشوا"));
        list.add(new SpinnerObject("تهران - فیروزکوه"));


        end_address.setText(spref.get().getString(ROUTER.INPUT_END_ADDRESS , ""));
        number.setText(spref.get().getString(ROUTER.INPUT_NUMBER , ""));
        packages.setChecked(spref.get().getBoolean(ROUTER.INPUT_PACKAGES , true));

        contents.setText(spref.get().getString(ROUTER.INPUT_CONTENTS , ""));
        //contents.setTypeface(MainActivity.font);
        dimensions.setText(spref.get().getString(ROUTER.INPUT_DIMENSIONS ,""));
        weight.setText(spref.get().getString(ROUTER.INPUT_WEIGHT , ""));
        insurance.setChecked(spref.get().getBoolean(ROUTER.INPUT_INSURANCE , true));
        pickup.setChecked(spref.get().getBoolean(ROUTER.INPUT_PICKUP , true));
        deliver.setChecked(spref.get().getBoolean(ROUTER.INPUT_DELIVER , true));
        breakable.setChecked(spref.get().getBoolean(ROUTER.INPUT_BREAKABLE , true));
        phone_number.setText(spref.get().getString(ROUTER.INPUT_PHONE_NUMBER , ""));
        total.setText(spref.get().getString(ROUTER.INPUT_total , ""));
        date.setText(spref.get().getString(ROUTER.INPUT_date , ""));
        serial_number.setText(spref.get().getString(ROUTER.INPUT_serial_number, ""));




        back.setOnClickListener(this);
       // delete.setOnClickListener(this);
        save.setOnClickListener(this);



        spinner.setOnItemSelectedListener(this);
        adapter = new SpinnerAdapter(this , R.layout.layout_spinner_row , list);
        spinner.setAdapter(adapter);


     }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        value = parent.getItemAtPosition(position);
        final Intent intent;

        switch (position) {

            case 1 :
                id_prov = 1;
                break;

            case 2 :
                id_prov = 2;
                break;

            case 3 :
                id_prov = 3;
                break;
            case 4 :
                id_prov = 4;
                break;
            case 5 :
                id_prov = 5;
                break;
            case 6 :
                id_prov = 6;
                break;
            case 7 :
                id_prov = 7;
                break;
            case 8 :
                id_prov = 8;
                break;
            case 9 :
                id_prov = 9;
                break;
            case 10 :
                id_prov = 10;
                break;
            case 11 :
                id_prov = 11;
                break;
            case 12 :
                id_prov = 12;
                break;
            case 13 :
                id_prov = 13;
                break;
            case 14 :
                id_prov = 14;
                break;
            case 15 :
                id_prov = 15;
                break;
            case 16 :
                id_prov = 16;
                break;
            case 17 :
                id_prov = 17;
                break;
            case 18 :
                id_prov = 18;
                break;
            case 19 :
                id_prov = 19;
                break;
            case 20 :
                id_prov = 20;
                break;
            case 21 :
                id_prov = 21;
                break;
            case 22 :
                id_prov = 22;
                break;
            case 23 :
                id_prov = 23;
                break;
            case 24 :
                id_prov = 24;
                break;
            case 25 :
                id_prov = 25;
                break;
            case 26 :
                id_prov = 26;
                break;
            case 27 :
                id_prov = 27;
                break;
            case 28 :
                id_prov = 28;
                break;
            case 29 :
                id_prov = 29;
                break;
            case 30 :
                id_prov = 30;
                break;
            case 31 :
                id_prov = 31;
                break;
            case 32 :
                id_prov = 32;
                break;
            case 33 :
                id_prov = 33;
                break;
            case 34 :
                id_prov = 34;
                break;
            case 35 :
                id_prov = 35;
                break;
            case 36 :
                id_prov = 36;
                break;
            case 37 :
                id_prov = 37;
                break;
            case 38 :
                id_prov = 38;
                break;
            case 39 :
                id_prov = 39;
                break;
            case 40 :
                id_prov = 40;
                break;
            case 41 :
                id_prov = 41;
                break;
            case 42 :
                id_prov = 42;
                break;
            case 43 :
                id_prov = 43;
                break;
            case 44 :
                id_prov = 44;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {



        if (view == back) {
/*
            spref.get().edit()
                    .putInt(ROUTER.ID_PROV , id_prov)
                    .putString(ROUTER.INPUT_START_ADDRESS , start_address.getText().toString())
                    .putString(ROUTER.INPUT_END_ADDRESS , end_address.getText().toString())
                    .putInt(ROUTER.INPUT_NUMBER , Integer.parseInt(number.getText().toString()))
                    .putString(ROUTER.INPUT_CONTENTS , contents.getText().toString())
                    .putInt(ROUTER.INPUT_DIMENSIONS , Integer.parseInt(dimensions.getText().toString()))
                    .putInt(ROUTER.INPUT_WEIGHT , Integer.parseInt(weight.getText().toString()))
                    .putInt(ROUTER.INPUT_PHONE_NUMBER , Integer.parseInt(phone_number.getText().toString()))
                    .putBoolean(ROUTER.INPUT_PACKAGES , Boolean.parseBoolean(packages.getText().toString()))
                    .putBoolean(ROUTER.INPUT_INSURANCE , Boolean.parseBoolean(insurance.getText().toString()))
                    .putBoolean(ROUTER.INPUT_PICKUP , Boolean.parseBoolean(pickup.getText().toString()))
                    .putBoolean(ROUTER.INPUT_DELIVER , Boolean.parseBoolean(deliver.getText().toString()))
                    .putBoolean(ROUTER.INPUT_BREAKABLE , Boolean.parseBoolean(breakable.getText().toString()))
                    .putInt(ROUTER.INPUT_total , Integer.parseInt(total.getText().toString()))
                    .putInt(ROUTER.INPUT_date , Integer.parseInt(date.getText().toString()))
                    .putInt(ROUTER.INPUT_serial_number , Integer.parseInt(serial_number.getText().toString()))
                    .apply();
*/
            finish();







        }

        else if (view == save)  {


            saveOrders();

        }

    }

    private void saveOrders() {


        if (start_address.getText().toString().length() <20) {

            app.t("لطفا آدرس مبدأ را وارد کنید" , app.ToastType.ERROR);
            app.animateError(start_address);
            return;

        }

        if (id_prov == 0) {

            app.t("لطفا استان مقصد را انتخاب کنید" , app.ToastType.ERROR);
            app.animateError(spinner);
            return;
        }

        if (end_address.getText().toString().length() <20) {

            app.t("لطفا آدرس مقصد را وارد کنید" , app.ToastType.ERROR);
            app.animateError(end_address);
            return;

        }
        if (number.getText().toString().length() ==0) {
            app.t("لطفا تعداد را وارد کنید" , app.ToastType.ERROR);
            app.animateError(number);
            return;
        }

        if (contents.getText().toString().length() <2) {
            app.t("لطفا محتوای ارسالی را وارد کنید" , app.ToastType.ERROR);
            app.animateError(contents);
            return;
        }
        if (dimensions.getText().toString().length() <5) {
            app.t("لطفا ابعاد محتوای ارسالی را وارد کنید" , app.ToastType.ERROR);
            app.animateError(dimensions);
            return;
        }
        if (weight.getText().toString().length() ==0) {
            app.t("لطفا وزن محتوای ارسالی را وارد کنید" , app.ToastType.ERROR);
            app.animateError(weight);
            return;
        }
        if (phone_number.getText().toString().length() != 9) {
            app.t("لطفا شماره همراه خود را وارد کنید" , app.ToastType.ERROR);
            app.animateError(phone_number);
            return;
        }


        int v = packages.isChecked() ? 1 : 0;
        int i = insurance.isChecked() ? 1 : 0;
        int p = pickup.isChecked() ? 1 : 0;
        int d = deliver.isChecked() ? 1 : 0;
        int b = breakable.isChecked() ? 1 : 0;




        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_ORDERS);
        params.put(ROUTER.ACTION , ROUTER.ACTION_ADD);
        params.put(ROUTER.INPUT_START_ADDRESS , start_address.getText().toString());
        params.put(ROUTER.ID_PROV , id_prov);
        params.put(ROUTER.INPUT_END_ADDRESS , end_address.getText().toString());
        params.put(ROUTER.INPUT_NUMBER , number.getText().toString());
        params.put(ROUTER.INPUT_PACKAGES , v);
        params.put(ROUTER.INPUT_CONTENTS, contents.getText().toString());
        params.put(ROUTER.INPUT_DIMENSIONS , dimensions.getText().toString());
        params.put(ROUTER.INPUT_WEIGHT , weight.getText().toString());
        params.put(ROUTER.INPUT_INSURANCE , i);
        params.put(ROUTER.INPUT_PICKUP , p);
        params.put(ROUTER.INPUT_DELIVER , d);
        params.put(ROUTER.INPUT_BREAKABLE , b);
        params.put(ROUTER.INPUT_PHONE_NUMBER , phone_number.getText().toString());

        OrderClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                app.l("response :" + response);

                try {
                     JSONObject object = new JSONObject(response);

                    if (object.has("error")) {
                        app.t(object.getString("error") , app.ToastType.ERROR);
                    }
                    else if (object.has("serial_number") ) {
                        //int id = object.getInt("ID");

                        //OrdersObject ordersObject = new OrdersObject( spref.get().getInt(ROUTER.USER_ID , -1)  , end_address.getText().toString() , total.getText().toString() , serial_number.getText().toString() , "");


                         OrdersObject ordersObject = new Gson().fromJson(response , OrdersObject.class);


                        final int serial = Integer.parseInt(ordersObject.getSerial_number());
                          final int ID = ordersObject.getId();

                         app.l("serial_number :" + serial);


                        app.l("idddddddddddd :" + ID);

                        delete.setVisibility(View.VISIBLE);
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                RequestParams params1 = app.getRequestParams();
                                params1.put(ROUTER.ROUTE, ROUTER.ROUTE_ORDERS);
                                params1.put(ROUTER.ACTION, ROUTER.ACTION_DELETE);
                                params1.put(ROUTER.ID, ID);

                                OrderClient.post(params1, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        app.l("delete is completed");

                                        spref.get().edit().remove(ROUTER.INPUT_START_ADDRESS).remove(ROUTER.ID_PROV).remove(ROUTER.INPUT_END_ADDRESS)
                                                .remove(ROUTER.INPUT_NUMBER).remove(ROUTER.INPUT_PACKAGES).remove(ROUTER.INPUT_CONTENTS).remove(ROUTER.INPUT_DIMENSIONS)
                                                .remove(ROUTER.INPUT_WEIGHT).remove(ROUTER.INPUT_INSURANCE).remove(ROUTER.INPUT_PICKUP)
                                                .remove(ROUTER.INPUT_DELIVER).remove(ROUTER.INPUT_BREAKABLE).remove(ROUTER.INPUT_PHONE_NUMBER).remove(ROUTER.INPUT_total).remove(ROUTER.INPUT_serial_number).remove(ROUTER.INPUT_date).apply();

                                       // OrdersObject ordersObject = new OrdersObject( spref.get().getInt(ROUTER.USER_ID , -1)  , end_address.getText().toString() , total.getText().toString() , serial_number.getText().toString() , "");
                                       // OrdersBroadCast.send(OrdersBroadCast.ACTION_ADD , ordersObject);
                                        app.t("سفارش با موفقیت حذف شد" , app.ToastType.SUCCESS);
                                        finish();


                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                    }
                                });
                            }

                        });


                        app.t("سفارش شما با موفقیت ثبت شد" , app.ToastType.SUCCESS);


                        new Handler().postDelayed(new Runnable() {

                            @Override

                            public void run() {

                               // startActivity(new Intent(AddOrdersActivity.this , OrderFragment.class));





                                spref.get().edit().remove(ROUTER.INPUT_START_ADDRESS).remove(ROUTER.ID_PROV).remove(ROUTER.INPUT_END_ADDRESS)
                                        .remove(ROUTER.INPUT_NUMBER).remove(ROUTER.INPUT_PACKAGES).remove(ROUTER.INPUT_CONTENTS).remove(ROUTER.INPUT_DIMENSIONS)
                                        .remove(ROUTER.INPUT_WEIGHT).remove(ROUTER.INPUT_INSURANCE).remove(ROUTER.INPUT_PICKUP)
                                        .remove(ROUTER.INPUT_DELIVER).remove(ROUTER.INPUT_BREAKABLE).remove(ROUTER.INPUT_PHONE_NUMBER).remove(ROUTER.INPUT_total).remove(ROUTER.INPUT_serial_number).remove(ROUTER.INPUT_date).apply();

                                OrdersObject ordersObject = new OrdersObject(ID , spref.get().getInt(ROUTER.USER_ID , -1)  , end_address.getText().toString() , total.getText().toString() , serial_number.getText().toString() , "");
                                OrdersBroadCast.send(OrdersBroadCast.ACTION_ADD , ordersObject);
                                finish();



                            }
                        }, 240000);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                app.l("response :" + response);


                OrdersObject ordersObject = new Gson().fromJson(response , OrdersObject.class);

              total.setText(ordersObject.getTotal());
              serial_number.setText(ordersObject.getSerial_number());
              date.setText(ordersObject.getDate());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {

                progressbar.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onFinish() {
                progressbar.setVisibility(View.INVISIBLE);
                super.onFinish();
            }
        });



    }















}
