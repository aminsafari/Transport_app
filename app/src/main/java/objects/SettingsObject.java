package objects;

import android.graphics.Color;

import com.example.amin08.transport.R;

public class SettingsObject {





    private String font;
    private static String background_color;
    private String text_color;
    private int text_size;

    private AvatarObject AvatarObject;



    public String getFont() {
        return font;
    }

    public static int getBackground_color() {
        try {
            if (!background_color.contains("#"))
               background_color = "#" + background_color;
            return Integer.parseInt((background_color));

        } catch (Exception e) {
            return R.color.colorPrimary2;
        }

    }

    public int getText_color() {
        try {
            if (!text_color.contains("#"))
                text_color = "#"+text_color;
            return Integer.parseInt((text_color));
        } catch (Exception e) {
            return  Color.WHITE;
        }
    }

    public int getText_size() {
        return text_size;
    }

    public objects.AvatarObject getAvatarObject() {
        return AvatarObject;
    }
}