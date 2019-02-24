package fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.amin08.transport.AddOrderActivity;
import com.example.amin08.transport.EloginActivity;
import com.example.amin08.transport.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.OrderAdapter;
import cz.msebera.android.httpclient.Header;
import objects.OrderObject;
import app.*;
import app.espref;
import objects.OrdersObject;


public class OrderFragment2 extends Fragment {


    int start = 0;

    ShimmerRecyclerView recyclerView;
    List<OrderObject> list;
    OrderAdapter adapter;

    public OrderFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_fragment2, container, false);

        view = init(view);

        return view;
    }

    private View init(View view) {

        recyclerView = view.findViewById(R.id.shimmer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new OrderAdapter(getActivity() , list);
        recyclerView.setAdapter(adapter);

        getData();


        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver , new IntentFilter(app.main.TAGS));

        return view;
    }

    @Override
    public void onDestroy() {

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void getData() {

        RequestParams params = app.egetRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_ORDER);
        params.put(ROUTER.ACTION , ROUTER.ACTION_READ_ORDER);
        params.put(ROUTER.INPUT_START , start);

        app.l("INPUT_COMPANY :" + espref.get().getString(ROUTER.INPUT_COMPANY , ""));
        app.l("ID :" +  espref.get().getInt(ROUTER.ID , -1) + "");

        OrderClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String response = new String(responseBody);

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.has("error")) {
                        app.t(jsonObject.getString("error") , app.ToastType.ERROR);
                        if (jsonObject.has("action") && jsonObject.getString("action").equals("logout")) {

                            espref.get().edit().clear();
                            getActivity().finish();
                            getActivity().startActivity(new Intent(getActivity() , EloginActivity.class));

                        }
                    }
                    else {

                    }

                }catch (JSONException e) {

                }

                app.l("response :" + response);


                OrderObject [] objects = new Gson().fromJson(response , OrderObject[].class);
                list.addAll(Arrays.asList(objects));

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                app.l("onFailure");
            }

            @Override
            public void onStart() {
                recyclerView.showShimmerAdapter();
                super.onStart();
            }

            @Override
            public void onFinish() {
                recyclerView.hideShimmerAdapter();
                super.onFinish();
            }
        });






    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int action = intent.getIntExtra("action" , -1);
            if (action == OrderBroadCast.ACTION_ADD) {
                OrderObject order = (OrderObject) intent.getSerializableExtra("object");
                List<OrderObject> objects = new ArrayList<>();
                objects.add(order);
                objects.addAll(list);
                objects.clear();
                list.addAll(objects);
                adapter.notifyDataSetChanged();

            }

            app.l("i got BrodCast");

        }
    };


}
