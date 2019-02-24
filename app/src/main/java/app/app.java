package app;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.amin08.transport.MainActivity;
import com.loopj.android.http.RequestParams;

import es.dmoral.toasty.Toasty;

public class app {


    public enum ToastType {

        ERROR , WARNING , SUCCESS , NORMAL , INFO
    }



    public static class main{

        public static final String TAG = "Transport";
        public static final String TAGS = "eTransport";
        public static final String URl = "http://majidzolfaghari.ir/amin/";

    }

    public static void l(String message) {

        Log.e(main.TAG , message);
    }

    public static void t(String message , ToastType type) {

        switch (type) {

            case ERROR:   Toasty.error(Application.getContext() , message , Toast.LENGTH_LONG).show();break;
            case WARNING: Toasty.warning(Application.getContext() , message , Toast.LENGTH_LONG).show();break;
            case SUCCESS: Toasty.success(Application.getContext() , message , Toast.LENGTH_LONG).show();break;
            case NORMAL:  Toasty.normal(Application.getContext() , message , Toast.LENGTH_LONG).show();break;
            case INFO:    Toasty.info(Application.getContext() , message , Toast.LENGTH_LONG).show();break;





        }


    }


    public static Drawable getImage(String text , int id) {

        if (text.length()>1)

            text = text.substring(0 , 1).toUpperCase();

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color2 = generator.getColor("id"+id);
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig().useFont(MainActivity.font)
                .endConfig()
                .buildRound(text , color2);


        return drawable ;

    }

    public static RequestParams getRequestParams() {

        RequestParams params = new RequestParams();

        params.put(ROUTER.SESSION , spref.get().getString(ROUTER.SESSION , ""));
        params.put(ROUTER.USER_ID , spref.get().getInt(ROUTER.USER_ID , -1) + "");

        return params;
    }

    public static RequestParams egetRequestParams() {
        RequestParams params = new RequestParams();

        params.put(ROUTER.INPUT_COMPANY , espref.get().getString(ROUTER.INPUT_COMPANY , ""));
        params.put(ROUTER.ID , espref.get().getInt(ROUTER.ID , -1) + "");
        return params;
    }

    public static void animateError(View view) {
        YoYo.with(Techniques.Bounce).duration(1500).playOn(view);
    }
















}
