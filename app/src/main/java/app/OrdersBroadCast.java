package app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import objects.OrdersObject;

public class OrdersBroadCast {


    public static final int ACTION_ADD = 1;



    public static void send(int action , OrdersObject orders) {

        Intent intent = new Intent(app.main.TAG);
        intent.putExtra("action" , action);
        intent.putExtra("object" , orders);
        LocalBroadcastManager.getInstance(Application.getContext()).sendBroadcast(intent);

        app.l("BroadCast send");



    }
}
