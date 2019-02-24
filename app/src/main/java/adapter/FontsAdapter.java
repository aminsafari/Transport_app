package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.amin08.transport.R;

import java.util.List;

import app.Application;
import objects.FontsSpinnerObject;

public class FontsAdapter extends ArrayAdapter<FontsSpinnerObject> {

    List<FontsSpinnerObject> list;


    public FontsAdapter( @NonNull Context context, int resource, @NonNull List<FontsSpinnerObject> objects) {
        super(context, resource, objects);
        this.list = objects;
    }

     @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {


         LayoutInflater inflater = (LayoutInflater) Application.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view = inflater.inflate(R.layout.layout_fonts_row , parent , false);




         AppCompatTextView fonts_spinner = view.findViewById(R.id.fonts_spinner);

         fonts_spinner.setText(list.get(position).getFonts_spinner());



        return view;
    }

    @Override
    public View getDropDownView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) Application.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_fonts_row , parent , false);

        AppCompatTextView fonts_spinner = view.findViewById(R.id.fonts_spinner);
        fonts_spinner.setText(list.get(position).getFonts_spinner());

        return view;
    }
}
