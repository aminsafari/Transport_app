package app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import objects.ProfileObject;

public class ProfileBroadCast {

    public static final int ACTION_EDIT = 1;


    public static void send(int action , ProfileObject profile) {

        Intent intent = new Intent(app.main.TAG);
        intent.putExtra("action" , action);
        intent.putExtra("object" , profile);
        LocalBroadcastManager.getInstance(Application.getContext()).sendBroadcast(intent);

        app.l("BroadCast send");




    }
}
