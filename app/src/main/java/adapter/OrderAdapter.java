package adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.amin08.transport.R;
import com.example.amin08.transport.ShowOrderActivity;

import java.util.List;

import objects.OrderObject;
import app.*;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Activity activity;
    List<OrderObject> list;

    public OrderAdapter(Activity activity , List<OrderObject> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_order_row , parent , false );

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        holder.serial_number.setText(String.valueOf(list.get(position).getSerial_number()));
        holder.total.setText(String.valueOf(list.get(position).getTotal()));
        holder.end_address.setImageDrawable(app.getImage(list.get(position).getEnd_address() , list.get(position).getId_prov()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatImageView end_address;
        AppCompatTextView serial_number , total ;
        RelativeLayout parentse;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            end_address = itemView.findViewById(R.id.end_address);
            serial_number = itemView.findViewById(R.id.serial_number);
            total = itemView.findViewById(R.id.total);
            parentse = itemView.findViewById(R.id.parentse);
            parentse.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view == parentse) {

                Intent intent = new Intent(activity , ShowOrderActivity.class);
                intent.putExtra("object" , list.get(getAdapterPosition()));
                activity.startActivity(intent);
            }
        }
    }
}
