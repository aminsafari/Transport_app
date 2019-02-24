package fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
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
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.amin08.transport.Edit_ProfileActivity;
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
import objects.SettingsObject;

public class ProfileFragment extends Fragment {



    AppCompatImageView avatar;
    AppCompatEditText email , fname , lname ;
    AppCompatTextView logout;
    FloatingActionButton fab_edit;
    RelativeLayout relative , recyclerView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        return init(view);
    }

    private View init(View view) {


        avatar   = view.findViewById(R.id.avatar);
        email    = view.findViewById(R.id.email);
        fname    = view.findViewById(R.id.fname);
        lname    = view.findViewById(R.id.lname);
        logout   = view.findViewById(R.id.logout);
        fab_edit = view.findViewById(R.id.fab_edit);
        relative = view.findViewById(R.id.relative);
        recyclerView = view.findViewById(R.id.recyclerView);





        email.setText(spref.get().getString(ROUTER.INPUT_EMAIL , ""));
        fname.setText(spref.get().getString(ROUTER.INPUT_FNAME , ""));
        lname.setText(spref.get().getString(ROUTER.INPUT_LNAME , ""));








        Glide
                .with(Application.getContext())
                .load(app.main.URl+spref.get().getString("image" , ""))
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

                        spref.get().edit().clear().apply();
                        startActivity(new Intent(getActivity() , LoginActivity.class));
                        getActivity().finish();

                    }
                });
                alert.setPositiveButton("خیر" , null);
                alert.show();
            }
        });

        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity() , Edit_ProfileActivity.class));
            }
        });

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver , new IntentFilter(app.main.TAG));

        return view;
    }

    @Override
    public void onDestroy() {

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }


    private void getData() {






        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_PROFILE);
        params.put(ROUTER.ACTION , ROUTER.ACTION_READ_PROFILE);


        OrderClient.post(params, new AsyncHttpResponseHandler() {
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

                        ProfileObject  objects = new Gson().fromJson(response , ProfileObject.class);

                        fname.setText(objects.getFname());
                        lname.setText(objects.getLname());
                        email.setText(objects.getEmail());



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

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {



            ProfileObject profile = (ProfileObject) intent.getSerializableExtra("object");
            fname.setText(profile.getFname());
            lname.setText(profile.getLname());
            email.setText(profile.getEmail());







            app.l("i got BroadCast in ProfileFragment");

        }
    };

}


