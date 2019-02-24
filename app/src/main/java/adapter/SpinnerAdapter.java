package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.amin08.transport.R;

import java.util.List;

import objects.SpinnerObject;

public class SpinnerAdapter extends ArrayAdapter<SpinnerObject> {
    List<SpinnerObject> list;
    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SpinnerObject> objects) {
        super(context, resource, objects);

        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_spinner_row , parent , false);

        AppCompatTextView name = view.findViewById(R.id.name);
        name.setText(list.get(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      View view = inflater.inflate(R.layout.layout_spinner_row , parent , false);

      AppCompatTextView name = view.findViewById(R.id.name);
      name.setText(list.get(position).getName());

      return view;
    }
}
