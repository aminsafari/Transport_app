package com.example.amin08.transport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.github.ybq.android.spinkit.SpinKitView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import app.ROUTER;
import app.espref;
import app.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import objects.OrderObject;


public class AddOrderActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatImageView back , delete , save  , save_signature ;
    AppCompatTextView senderl , receiverl , receiver_phone_numberl , sender_signaturel , send_datel , send_date ;
    AppCompatEditText sender , receiver , reciver_phone_number ;
    SignaturePad sender_signature , reciver_signature ;
    SpinKitView progressBar;

    FoldingCell folding_cell;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        init();
    }

    private void init() {



        back                   = findViewById(R.id.back);
        save                   = findViewById(R.id.save);
        senderl                = findViewById(R.id.senderl);
        receiverl              = findViewById(R.id.receiverl);
        receiver_phone_numberl = findViewById(R.id.receiver_phone_numberl);
        sender_signaturel      = findViewById(R.id.sender_signaturel);
        send_datel             = findViewById(R.id.send_datel);
        send_date              = findViewById(R.id.send_date);
        sender                 = findViewById(R.id.sender);
        receiver               = findViewById(R.id.receiver);
        reciver_phone_number   = findViewById(R.id.receiver_phone_number);
        sender_signature       = findViewById(R.id.sender_signature);
        progressBar            = findViewById(R.id.progressBar);
        folding_cell           = findViewById(R.id.folding_cell);
        save_signature         = findViewById(R.id.save_signature);
        reciver_signature      = findViewById(R.id.reciver_signature);

        sender.setText(espref.get().getString(ROUTER.INPUT_SENDER , ""));
        receiver.setText(espref.get().getString(ROUTER.INPUT_RECEIVER , ""));
        reciver_phone_number.setText(espref.get().getString(ROUTER.INPUT_RECEIVER_PHONE , ""));


        back.setOnClickListener(this);
        save.setOnClickListener(this);
        save_signature.setOnClickListener(this);



        sender_signature.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

           }

            @Override
            public void onSigned() {

               }

            @Override
            public void onClear() {

            }
        });


        folding_cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell.toggle(false);
            }
        });

      }

    @Override
    public void onClick(View view) {

        if (view == back) {

            finish();
        }



        else if (view == save ) {


            saveOrder();


        }






        else if (view == save_signature) {

            receiver_info();
        }

    }

    private void saveOrder() {






        if (sender.getText().toString().length()<3) {
            app.t("لطفا مشخصات فرستنده را وارد کنید" , app.ToastType.ERROR);
            app.animateError(sender);
            return;
         }
         if (receiver.getText().toString().length()<3) {
            app.t("لطفا مشخصات گیرنده را وارد کنید" , app.ToastType.ERROR);
            app.animateError(receiver);
            return;
         }
         if (reciver_phone_number.getText().toString().length() !=9) {
            app.t("لطفا شماره همراه گیرنده را وارد کنید" , app.ToastType.ERROR);
            app.animateError(reciver_phone_number);
            return;

         }
         if (sender_signature.isEmpty()) {
            app.t("لطفا امضای خود را وارد کنید" , app.ToastType.ERROR);
            app.animateError(sender_signature);
            return;
         }

         Intent intent = getIntent();
         Bundle bundle = intent.getExtras();
         String serial_number = bundle.getString("serial_number");

         final String seen = String.valueOf(1);




        RequestParams params = app.egetRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_ORDER);
        params.put(ROUTER.ACTION , ROUTER.ACTION_ADD_ORDER);
        params.put(ROUTER.INPUT_SENDER , sender.getText().toString());
        params.put(ROUTER.INPUT_RECEIVER , receiver.getText().toString());
        params.put(ROUTER.INPUT_RECEIVER_PHONE , reciver_phone_number.getText().toString());
        params.put(ROUTER.INPUT_SENDER_SIGNATURE , sender_signature.getSignatureBitmap());
        params.put(ROUTER.INPUT_serial_number , serial_number);
        params.put(ROUTER.INPUT_SEEN  , seen);

        app.l("serial_number_send :"+ serial_number);

        EmployeeClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);




                try {
                    JSONObject object = new JSONObject(response);

                    if (object.has("error")) {
                        app.t(object.getString("error"), app.ToastType.ERROR);
                    }

                    else if (object.has("status") && object.getString("status").equals("SUCCESS") && object.has("seen")  ) {


                        int ID = object.getInt("id");
                        espref.get().edit().remove(ROUTER.INPUT_SENDER).remove(ROUTER.INPUT_RECEIVER).remove(ROUTER.INPUT_RECEIVER_PHONE).remove(ROUTER.INPUT_SENDER_SIGNATURE).remove(ROUTER.INPUT_send_date).apply();
                        OrderObject orderObject = new OrderObject(ID  , sender.getText().toString() , receiver.getText().toString() , reciver_phone_number.getText().toString() , sender_signature.getSignatureBitmap().toString() , "");
                        OrderBroadCast.send(OrderBroadCast.ACTION_ADD , orderObject);
                        app.t("اطلاعات سفارش با موفقیت تکمیل شد" , app.ToastType.SUCCESS);
                        finish();

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


                app.l("response :" + response);





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



    private void receiver_info() {


        if (reciver_signature.isEmpty()) {
            app.t("لطفا امضای خود را وارد کنید" , app.ToastType.ERROR);
            app.animateError(sender_signature);
            return;
 }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String serial_number = bundle.getString("serial_number");

        String seen_receive = String.valueOf(1);


        RequestParams params1 = app.egetRequestParams();
        params1.put(ROUTER.ROUTE , ROUTER.ROUTE_ORDER);
        params1.put(ROUTER.ACTION , ROUTER.ACTION_ADD_RECEIVER_INFO);
        params1.put(ROUTER.INPUT_RECEIVER_SIGNATURE , reciver_signature.getSignatureBitmap().toString());
        params1.put(ROUTER.INPUT_serial_number , serial_number);
        params1.put(ROUTER.INPUT_SEEN_RECEIVE , seen_receive);

        EmployeeClient.post(params1, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                String response = new String(responseBody);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.has("error")) {
                        app.t(object.getString("error"), app.ToastType.ERROR);
                    }
                    else if (object.has("status") && object.getString("status").equals("SUCCESS")) {

                        int ID = object.getInt("id");
                        espref.get().edit().remove(ROUTER.INPUT_RECEIVER_SIGNATURE).remove(ROUTER.INPUT_RECEIVE_DATE).apply();
                        OrderObject OrderObject2 = new OrderObject(ID, reciver_signature.getSignatureBitmap().toString() , "");
                        OrderBroadCast.send(OrderBroadCast.ACTION_ADD , OrderObject2);
                        app.t("اطلاعات با موفقیت تکمیل شد" , app.ToastType.SUCCESS);
                         finish();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


                app.l("response :" + response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
