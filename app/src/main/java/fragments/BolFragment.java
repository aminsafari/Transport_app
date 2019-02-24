package fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.amin08.transport.R;
import com.google.gson.Gson;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Documented;

import app.*;
import cz.msebera.android.httpclient.Header;
import objects.BolObject;


public class BolFragment extends Fragment  implements View.OnClickListener{


    AppCompatImageView  save , sender_signature;
    AppCompatEditText serial;
    AppCompatTextView send , start_address , id_prov , end_address , number , packages , contents, dimensions
            , weight , insurance , deliver , pickup , breakable , phone_number , total , date ,
            serial_number , sender , receiver , receiver_phone , send_date ;

    int id_pro;


    private File pdfFile;
    public static final int REQUEST_CODE = 111;

    public BolFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bol, container, false);

        view = init(view);
        return view;
    }

    private View init(View view) {

        save                  = view.findViewById(R.id.save);
        sender_signature      = view.findViewById(R.id.sender_signature);
        serial                = view.findViewById(R.id.serial);
        send                  = view.findViewById(R.id.send);
        start_address         = view.findViewById(R.id.start_address);
        id_prov               = view.findViewById(R.id.id_prov);
        end_address           = view.findViewById(R.id.end_address);
        number                = view.findViewById(R.id.number);
        packages              = view.findViewById(R.id.packages);
        contents              = view.findViewById(R.id.contents);
        dimensions            = view.findViewById(R.id.dimensions);
        weight                = view.findViewById(R.id.weight);
        insurance             = view.findViewById(R.id.insurance);
        deliver               = view.findViewById(R.id.deliver);
        pickup                = view.findViewById(R.id.pickup);
        breakable             = view.findViewById(R.id.breakable);
        phone_number          = view.findViewById(R.id.phone_number);
        total                 = view.findViewById(R.id.total);
        date                  = view.findViewById(R.id.date);
        serial_number         = view.findViewById(R.id.serial_number);
        sender                = view.findViewById(R.id.sender);
        receiver              = view.findViewById(R.id.receiver);
        receiver_phone        = view.findViewById(R.id.receiver_phone);
        send_date             = view.findViewById(R.id.send_date);




        save.setOnClickListener(this);
        send.setOnClickListener(this);

        return view;


    }


    @Override
    public void onClick(View view) {

           if (view == send) {
            send();
        }

       else if (view == save) {


                if (checkPermission()) {

               try {
                   CreatePdf();
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (DocumentException e) {
                   e.printStackTrace();
               }
           }
           else {
               app.t("نیاز به مجوز می باشد" , app.ToastType.ERROR);
                }
           }

    }

    private boolean checkPermission() {

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(Application.getContext() , Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            else {

                ActivityCompat.requestPermissions(getActivity() , new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE} , REQUEST_CODE);
             }
        }
        else {
            return true;
        }
        return false;

    }




    private void CreatePdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }

        String myFile = serial_number.getText().toString();
        pdfFile = new File(docsFolder.getAbsolutePath() , myFile+".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document , output);
        document.open();

        document.add(new Paragraph("start address : "  + start_address.getText().toString()));
        document.add(new Paragraph("id_prov : "        + id_prov.getText().toString()));
        document.add(new Paragraph("end_address : "    + end_address.getText().toString()));
        document.add(new Paragraph("number : "         + number.getText().toString()));
        document.add(new Paragraph("packages : "       + packages.getText().toString()));
        document.add(new Paragraph("contents : "       + contents.getText().toString()));
        document.add(new Paragraph("dimensions : "     + dimensions.getText().toString()));
        document.add(new Paragraph("weight : "         + weight.getText().toString()));
        document.add(new Paragraph("insurance : "      + insurance.getText().toString()));
        document.add(new Paragraph("deliver : "        + deliver.getText().toString()));
        document.add(new Paragraph("pickup : "         + pickup.getText().toString()));
        document.add(new Paragraph("breakable : "      + breakable.getText().toString()));
        document.add(new Paragraph("phone_number : "   + phone_number.getText().toString()));
        document.add(new Paragraph("total : "          + total.getText().toString()));
        document.add(new Paragraph("date : "           + date.getText().toString()));
        document.add(new Paragraph("serial_number : "  + serial_number.getText().toString()));
        document.add(new Paragraph("sender : "         + sender.getText().toString()));
        document.add(new Paragraph("receiver : "       + receiver.getText().toString()));
        document.add(new Paragraph("receiver_phone : " + receiver_phone.getText().toString()));
        document.add(new Paragraph("send_date : "      + send_date.getText().toString()));




        document.close();

        app.t("فایل با موفقیت ذخیره شد" , app.ToastType.INFO);



    }


    private void send() {


        if (serial.getText().toString().length()< 5) {

            app.t("شماره سریال وارد شده صحیح نمی باشد" , app.ToastType.ERROR);
            app.animateError(serial);
            return;

        }

        RequestParams params = app.egetRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_BOL);
        params.put(ROUTER.SERIAL_NUMBER , serial.getText().toString());

        EmployeeClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                app.l("response :" + response);

                try {
                    JSONObject object = new JSONObject(response);

                    if (object.has("error")) {
                        app.t(object.getString("error"), app.ToastType.ERROR);
                    }

                    else {


                        BolObject bolObject = new Gson().fromJson(response , BolObject.class);


                        start_address.setText(bolObject.getStart_address());

                         id_pro = bolObject.getId_prov();


                        end_address.setText(bolObject.getEnd_address());
                        number.setText(bolObject.getNumber());
                        if (bolObject.getPackages() == 1) {
                            packages.setText("بله");

                        }else {
                            packages.setText("خیر");
                        }
                        contents.setText(bolObject.getContents());
                        dimensions.setText(bolObject.getDimensions());
                        weight.setText(bolObject.getWeight());
                        if (bolObject.getInsurance() == 1) {
                            insurance.setText("بله");
                        }
                        else {
                            insurance.setText("خیر");
                        }
                        if (bolObject.getPickup() == 1) {
                            pickup.setText("بله");
                        }
                        else {
                            pickup.setText("خیر");
                        }
                        if (bolObject.getDeliver() == 1) {
                            deliver.setText("بله");
                        }
                        else {
                            deliver.setText("خیر");
                        }
                        if (bolObject.getBreakable() == 1) {
                            breakable.setText("بله");
                        }
                        else {
                            breakable.setText("خیر");
                        }
                        phone_number.setText(bolObject.getPhone_number());
                        total.setText(bolObject.getTotal());
                        date.setText(bolObject.getDate());
                        serial_number.setText(bolObject.getSerial_number());
                        sender.setText(bolObject.getSender());
                        receiver.setText(bolObject.getReceiver());
                        receiver_phone.setText(bolObject.getReceiver_phone());
                        Picasso.get().load(bolObject.getSender_signature()).into(sender_signature);

                        send_date.setText(bolObject.getSend_date());



                        switch (id_pro) {
                            case 0:
                                id_prov.setText("آدربایجان شرقی");
                                break;
                            case 1:
                                id_prov.setText("آدربایجان غربی");
                                break;
                            case 2:
                                id_prov.setText("اردبیل");
                                break;
                            case 3:
                                id_prov.setText("اصفهان");
                                break;
                            case 4:
                                id_prov.setText("البرز");
                                break;
                            case 5:
                                id_prov.setText("ایلام");
                                break;
                            case 6:
                                id_prov.setText("بوشهر");
                                break;
                            case 7:
                                id_prov.setText("تهران");
                                break;
                            case 8:
                                id_prov.setText("چهارمحال و بختیاری");
                                break;
                            case 9:
                                id_prov.setText("خراسان جنوبی");
                                break;
                            case 10:
                                id_prov.setText("خراسان رضوی");
                                break;
                            case 11:
                                id_prov.setText("خراسان شمالی");
                                break;
                            case 12:
                                id_prov.setText("خوزستان");
                                break;
                            case 13:
                                id_prov.setText("زنجان");
                                break;
                            case 14:
                                id_prov.setText("سمنان");
                                break;
                            case 15:
                                id_prov.setText("سیستان و بلوچستان");
                                break;
                            case 16:
                                id_prov.setText("فارس");
                                break;
                            case 17:
                                id_prov.setText("قزوین");
                                break;
                            case 18:
                                id_prov.setText("قم");
                                break;
                            case 19:
                                id_prov.setText("کردستان");
                                break;
                            case 20:
                                id_prov.setText("کرمان");
                                break;
                            case 21:
                                id_prov.setText("کرمانشاه");
                                break;
                            case 22:
                                id_prov.setText("کهگیلویه و بویراحمد");
                                break;
                            case 23:
                                id_prov.setText("گلستان");
                                break;
                            case 24:
                                id_prov.setText("گیلان");
                                break;
                            case 25:
                                id_prov.setText("لرستان");
                                break;
                            case 26:
                                id_prov.setText("مازندران");
                                break;
                            case 27:
                                id_prov.setText("مرکزی");
                                break;
                            case 28:
                                id_prov.setText("هرمزگان");
                                break;
                            case 29:
                                id_prov.setText("همدان");
                                break;
                            case 30:
                                id_prov.setText("یزد");
                                break;
                            case 31:
                                id_prov.setText("تهران - شهریار");
                                break;
                            case 32:
                                id_prov.setText("تهران - اسلامشهر");
                                break;
                            case 33:
                                id_prov.setText("تهران - بهارستان");
                                break;
                            case 34:
                                id_prov.setText("تهران - ملارد");
                                break;
                            case 35:
                                id_prov.setText("تهران - پاکدشت");
                                break;
                            case 36:
                                id_prov.setText("تهران - شهر قدس");
                                break;
                            case 37:
                                id_prov.setText("تهران - رباط کریم");
                                break;
                            case 38:
                                id_prov.setText("تهران - ورامین");
                                break;
                            case 39:
                                id_prov.setText("تهران - قرچک");
                                break;
                            case 40:
                                id_prov.setText("تهران - پردیس");
                                break;
                            case 41:
                                id_prov.setText("تهران - دماوند");
                                break;
                            case 42:
                                id_prov.setText("تهران - پیشوا");
                                break;
                            case 43:
                                id_prov.setText("تهران - فیروزکوه");
                                break;

                        }
/*
                        Glide
                                .with(Application.getContext())
                                .load(app.main.URl)
                                .into(sender_signature);
                        send_date.setText(bolObject.getSend_date());

*/

                    }


                }catch (Exception e) {

                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                app.l("onFailure");
            }
        });











    }


}
