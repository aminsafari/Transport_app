package app;

        import android.content.Context;
        import android.content.SharedPreferences;

public class espref {

     //private static SharedPreferences prefs = Application.getContext().getSharedPreferences(app.main.TAGS , Context.MODE_PRIVATE);
    private static SharedPreferences Instance = Application.getContext().getSharedPreferences(app.main.TAGS, Context.MODE_PRIVATE);

    public static SharedPreferences get() {

        if (Instance == null) {
            Instance = Application.getContext().getSharedPreferences(app.main.TAGS , Context.MODE_PRIVATE);
        }
        return Instance;
    }
}
