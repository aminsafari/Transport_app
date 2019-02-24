package com.example.amin08.transport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import app.*;
import app.spref;
import cz.msebera.android.httpclient.Header;
import objects.LoginObject;
import objects.OrdersObject;
import objects.ProfileObject;

public class Edit_ProfileActivity extends AppCompatActivity {

    AppCompatImageView back ;
    AppCompatEditText fname , lname , email ;
    AppCompatTextView   save_info ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);

        init();
    }

    private void init() {

        back        = findViewById(R.id.back);
        fname       = findViewById(R.id.fname);
        lname       = findViewById(R.id.lname);
        email       = findViewById(R.id.email);
        save_info   = findViewById(R.id.save_info);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fname.getText().toString().length() < 3) {
                    app.t("لطفا نام خود را وارد کنید" , app.ToastType.ERROR);
                    app.animateError(fname);
                    return;
                }
                if (lname.getText().toString().length() < 3) {
                    app.t("لطفا نام خانوادگی خود را وارد کنید" , app.ToastType.ERROR);
                    app.animateError(lname);
                    return;
                }
                if (email.getText().toString().length() < 7) {
                    app.t("لطفا ایمیل خود را وارد کنید" , app.ToastType.ERROR);
                    app.animateError(email);
                    return;
                }

                RequestParams params = app.getRequestParams();
                params.put(ROUTER.ROUTE , ROUTER.ROUTE_PROFILE);
                params.put(ROUTER.ACTION , ROUTER.ACTION_EDIT_PROFILE);
                params.put(ROUTER.INPUT_FNAME , fname.getText().toString());
                params.put(ROUTER.INPUT_LNAME ,lname.getText().toString());
                params.put(ROUTER.INPUT_EMAIL , email.getText().toString());

                OrderClient.post(params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        String response = new String(responseBody);

                        try {
                            JSONObject object = new JSONObject(response);


                            if (object.has("error")) {
                                app.t(object.getString("error"), app.ToastType.ERROR);

                            }
                             else if (object.has("status") && object.getString("status").equals("SUCCESS")) {



                                 int id = object.getInt("id");
                                 spref.get().edit().remove(ROUTER.INPUT_FNAME).remove(ROUTER.INPUT_LNAME).remove(ROUTER.INPUT_EMAIL).apply();
                                ProfileObject profileObject = new ProfileObject(id , fname.getText().toString()  , lname.getText().toString() , email.getText().toString());
                                ProfileBroadCast.send(ProfileBroadCast.ACTION_EDIT , profileObject);
                                finish();






                            }

                           else {

                            }
                            }catch (JSONException e) {
                            app.l(e.toString());
                        }
                        app.l("response : " + response);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        app.l("onFailure");

                    }
                });


            }
        });
    }
}
