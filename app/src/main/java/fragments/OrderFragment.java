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
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.amin08.transport.AddOrdersActivity;
import com.example.amin08.transport.LoginActivity;
import com.example.amin08.transport.MainActivity;
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

import adapter.OrdersAdapter;
import app.*;
import cz.msebera.android.httpclient.Header;
import objects.LoginObject;
import app.spref;
import objects.OrdersObject;

public class OrderFragment extends Fragment  {

    FloatingActionButton fab;
    int start = 0 ;
    ShimmerRecyclerView recyclerView;
    List<OrdersObject> list;
    OrdersAdapter adapter;
    AppCompatTextView no_orders;


    public OrderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        
        view = init(view);
        return view ;
    }

    private View init(View view) {


        fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.shimmer_recycler_view);
        no_orders = view.findViewById(R.id.no_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new OrdersAdapter(getActivity() , list);
        recyclerView.setAdapter(adapter);


        getData();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , AddOrdersActivity.class));

            }
        });


        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver , new IntentFilter(app.main.TAG));



        return view ;
    }


    @Override
    public void onDestroy() {

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void getData() {

        RequestParams params = app.getRequestParams();
        params.put(ROUTER.ROUTE , ROUTER.ROUTE_ORDERS);
        params.put(ROUTER.ACTION , ROUTER.ACTION_READ);
        params.put(ROUTER.INPUT_START , start);

        app.l("session :" + spref.get().getString(ROUTER.SESSION , ""));
        app.l("USER_ID :" +  spref.get().getInt(ROUTER.USER_ID , -1) + "");

        OrderClient.post(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

               String response = new String(responseBody);


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("error")) {
                        app.t(jsonObject.getString("error") , app.ToastType.ERROR);
                        if (jsonObject.has("action") && jsonObject.getString("action").equals("logout")) {

                            spref.get().edit().clear();
                            getActivity().finish();
                            getActivity().startActivity(new Intent(getActivity() , LoginActivity.class));
                        }
                    }
                    else {


                     }



                } catch (JSONException e) {




                }


                OrdersObject [] objects = new Gson().fromJson(response , OrdersObject[].class);
                list.addAll(Arrays.asList(objects));

                if (list.size() == 0) {
                    no_orders.setVisibility(View.VISIBLE);

                }
                else {
                    no_orders.setVisibility(View.INVISIBLE);
                }

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
    OrdersObject orders = (OrdersObject) intent.getSerializableExtra("object");

                int action = intent.getIntExtra("action" , -1);
                if (action == OrdersBroadCast.ACTION_ADD) {

                    List <OrdersObject> objects = new ArrayList<>();
                objects.add(orders);
                objects.addAll(list);
                list.clear();
                list.addAll(objects);
                adapter.notifyDataSetChanged();


                if (list.size() == 0) {
                    no_orders.setVisibility(View.VISIBLE);

                }
                else {
                    no_orders.setVisibility(View.INVISIBLE);
                }

            }

            app.l("i got BroadCast in OrdersFragment");
        }
    };


}
