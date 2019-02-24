package app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import objects.OrderObject;
import objects.OrdersObject;

public class OrderBroadCast {



    public static final int ACTION_ADD = 1;


    public static void send(int action , OrderObject order) {

        Intent intent = new Intent(app.main.TAGS);
        intent.putExtra("action" , action);
        intent.putExtra("object" , order);
        LocalBroadcastManager.getInstance(Application.getContext()).sendBroadcast(intent);

        app.l("BroadCast send");



    }
}
