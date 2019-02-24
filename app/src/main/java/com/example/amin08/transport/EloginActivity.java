package com.example.amin08.transport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import app.*;
import cz.msebera.android.httpclient.Header;
import objects.EloginObject;
import app.espref;




public class EloginActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatEditText username , password ;
    AppCompatTextView login ;
    SpinKitView progressbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elogin);

        init();
    }

    private void init() {

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        progressbar = findViewById(R.id.progressbar);

        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == login.getId()) {

            Login();
        }


    }

    private void Login() {


        if (username.getText().toString().length()<6) {
            app.t("شناسه کاربری وارد شده صحیح نمی باشد" , app.ToastType.ERROR);
            app.animateError(username);

            return;
        }

        if (password.getText().toString().length()<6) {
            app.t(" رمز عبور وارد شده صحیح نمی باشد" , app.ToastType.ERROR);
            app.animateError(password);

            return;
        }

        RequestParams params = app.egetRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_ELOGIN);
        params.put(ROUTER.ACTION , ROUTER.ACTION_ELOGIN);
        params.put(ROUTER.USERNAME , username.getText().toString());
        params.put(ROUTER.PASSWORD , password.getText().toString());
        EmployeeClient.post(params, new AsyncHttpResponseHandler() {


            @Override

            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.has("error")) {
                        app.t(jsonObject.getString("error") , app.ToastType.ERROR);
                    }
                    else {
                       EloginObject eloginObject = new Gson().fromJson(response , EloginObject.class);
                        if (eloginObject.getState().equals(ROUTER.SUCCESS)) {

                           String name = eloginObject.getEmployeeObject().getFname() + " " + eloginObject.getEmployeeObject().getLname();
                           app.t("Wellcome " + name  , app.ToastType.SUCCESS );


                            espref.get().edit()
                            .putString(ROUTER.INPUT_COMPANY , eloginObject.getEmployeeObject().getCompany())
                            .putInt(ROUTER.ID , eloginObject.getEmployeeObject().getId())
                            .putInt(ROUTER.USERNAME , eloginObject.getEmployeeObject().getUsername())
                            .putString(ROUTER.INPUT_FNAME , eloginObject.getEmployeeObject().getFname())
                            .putString(ROUTER.INPUT_LNAME ,eloginObject.getEmployeeObject().getLname())
                            .putString(ROUTER.INPUT_EMAIL , eloginObject.getEmployeeObject().getEmail())
                            .putInt(ROUTER.INPUT_SEX , eloginObject.getEmployeeObject().getSex())
                                    .apply();


                            espref.get().edit()
                                    .putString("font" , eloginObject.getSettingsObject().getFont())
                                    .putInt("background_color" , eloginObject.getSettingsObject().getBackground_color())
                                    .putInt("text_color" , eloginObject.getSettingsObject().getText_color())
                                    .putInt("text_size" , eloginObject.getSettingsObject().getText_size())
                                    .putInt("id" , eloginObject.getSettingsObject().getAvatarObject().getId())
                                    .putString("name" , eloginObject.getSettingsObject().getAvatarObject().getName())
                                    .putString("image" , eloginObject.getSettingsObject().getAvatarObject().getImage())
                                    .apply();

                            Intent intent = new Intent(EloginActivity.this , Main2Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }


                } catch (JSONException e) {
                    app.t("خطایی رخ داده" , app.ToastType.ERROR);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });





    }

    @Override
    protected void onResume() {
        username.setText("");
        password.setText("");
        super.onResume();
    }
}
