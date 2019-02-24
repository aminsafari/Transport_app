package fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.amin08.transport.Edit_ProfileActivity;
import com.example.amin08.transport.EloginActivity;
import com.example.amin08.transport.LoginActivity;
import com.example.amin08.transport.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import app.Application;
import app.ROUTER;
import app.spref;
import app.*;
import cz.msebera.android.httpclient.Header;
import objects.ProfileObject;
import objects.ProfileObject2;

public class ProfileFragment2 extends Fragment {



    AppCompatImageView avatar;
    AppCompatEditText email , fname , lname ;
    AppCompatTextView logout;

    public ProfileFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fragment2, container, false);

        return init(view);
    }

    private View init(View view) {


        avatar   = view.findViewById(R.id.avatar);
        email    = view.findViewById(R.id.email);
        fname    = view.findViewById(R.id.fname);
        lname    = view.findViewById(R.id.lname);
        logout   = view.findViewById(R.id.logout);





        email.setText(espref.get().getString(ROUTER.INPUT_EMAIL , ""));
        fname.setText(espref.get().getString(ROUTER.INPUT_FNAME , ""));
        lname.setText(espref.get().getString(ROUTER.INPUT_LNAME , ""));

        Glide
                .with(Application.getContext())
                .load(app.main.URl+espref.get().getString("image" , ""))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_person_black_24dp)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(avatar);

        getData();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("خروج");
                alert.setMessage("واقعا میخوای خارج بشی ؟");
                alert.setNegativeButton("بله", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        espref.get().edit().clear().apply();
                        startActivity(new Intent(getActivity() , EloginActivity.class));
                        getActivity().finish();

                    }
                });
                alert.setPositiveButton("خیر" , null);
                alert.show();
            }
        });


        return view;
    }



    private void getData() {

        RequestParams params = app.egetRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_PROFILE2);
        params.put(ROUTER.ACTION , ROUTER.ACTION_READ_PROFILE2);


        EmployeeClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                app.l("response :" + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("error")) {
                        app.t(jsonObject.getString("error") , app.ToastType.ERROR);

                    }
                    else {

                        ProfileObject2  objects = new Gson().fromJson(response , ProfileObject2.class);

                        fname.setText(objects.getFname());
                        lname.setText(objects.getLname());
                        email.setText(objects.getEmail());

                        app.l("emaillllllllllll :" + email);


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


