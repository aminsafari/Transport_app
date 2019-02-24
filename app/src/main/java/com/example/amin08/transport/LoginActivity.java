package com.example.amin08.transport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import app.*;
import java.sql.ParameterMetaData;
import cz.msebera.android.httpclient.Header;
import objects.ErrorObject;
import objects.LoginObject;
import app.spref;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatEditText email , password ;
    AppCompatTextView login , register ;
    SpinKitView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }




    private void init() {

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar);

        login.setOnClickListener(this);
        register.setOnClickListener(this);




    }


    @Override
    public void onClick(View view) {

        if (view.getId()== login.getId())
        {
            login();

        }
        else {
            startActivity(new Intent(this , RegistrationActivity.class));
        }

    }
    private void login() {

        if (email.getText().toString().length()<7) {

            app.t( "لطفا ایمیل خود را وارد کنید" , app.ToastType.ERROR);
            app.animateError(email);
            return;

        }
        if (password.getText().toString().length()<3) {
            app.t("لطفا حداقل 3 کاراکتر وارد کنید" , app.ToastType.ERROR);
            app.animateError(password);
            return;
        }

        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_LOGIN);
        params.put(ROUTER.ACTION , ROUTER.ACTION_LOGIN);
        params.put(ROUTER.INPUT_EMAIL , email.getText().toString());
        params.put(ROUTER.INPUT_PASSWORD , password.getText().toString());

        OrderClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("error")) {
                        app.t(jsonObject.getString("error") , app.ToastType.ERROR);
                    }
                    else {
                        LoginObject loginObject = new Gson().fromJson(response , LoginObject.class);
                        if (loginObject.getState().equals(ROUTER.SUCCESS)) {
                            String name = loginObject.getUserObject().getFname() + " " + loginObject.getUserObject().getLname();
                            app.t("Wellcome " + name, app.ToastType.SUCCESS);


                            spref.get().edit()
                                    .putString("font" , loginObject.getSettingsObject().getFont())
                                    .putInt("background_color" , loginObject.getSettingsObject().getBackground_color())
                                    .putInt("text_color" , loginObject.getSettingsObject().getText_color())
                                    .putInt("text_size" , loginObject.getSettingsObject().getText_size())
                                    .putInt("id" , loginObject.getSettingsObject().getAvatarObject().getId())
                                    .putString("name" , loginObject.getSettingsObject().getAvatarObject().getName())
                                    .putString("image" , loginObject.getSettingsObject().getAvatarObject().getImage())
                                    .apply();

                            spref.get().edit()
                                    .putString(ROUTER.SESSION , loginObject.getUserObject().getSession())
                                    .putString(ROUTER.INPUT_EMAIL , email.getText().toString())
                                    .putInt(ROUTER.USER_ID , loginObject.getUserObject().getId())
                                    .putString(ROUTER.INPUT_FNAME , loginObject.getUserObject().getFname())
                                    .putString(ROUTER.INPUT_LNAME , loginObject.getUserObject().getLname())
                                    .putInt(ROUTER.INPUT_SEX , loginObject.getUserObject().getSex())
                                    .apply();


                            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                        else {
                            app.t("خطایی رخ داده" , app.ToastType.ERROR);


                        }
                    }



                } catch (JSONException e) {
                    app.t("خطایی رخ داده1" , app.ToastType.ERROR);
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {
                progressBar.setVisibility(View.VISIBLE);
                super.onStart();
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.INVISIBLE);
                super.onFinish();
            }
        });

    }


    @Override
    protected void onResume() {
        email.setText("");
        password.setText("");
        super.onResume();
    }
}
