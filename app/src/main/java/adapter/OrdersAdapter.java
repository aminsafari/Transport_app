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
import com.example.amin08.transport.ShowOrdersActivity;

import java.util.List;

import objects.OrdersObject;
import app.*;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {


    Activity activity;
    List<OrdersObject> list;
    public OrdersAdapter(Activity activity , List<OrdersObject> list) {

        this.activity = activity;
        this.list = list;

    }


    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.layout_orders_row , parent , false);

        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {

        holder.serial_number.setText(String.valueOf(list.get(position).getSerial_number()));
        holder.total.setText(String.valueOf(list.get(position).getTotal()));
        holder.end_address.setImageDrawable(app.getImage(list.get(position).getEnd_address() , list.get(position).getId()));





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView end_address;
        AppCompatTextView serial_number , total;
        RelativeLayout parents;


        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            end_address = itemView.findViewById(R.id.end_address);
            serial_number = itemView.findViewById(R.id.serial_number);
            total = itemView.findViewById(R.id.total);
            parents = itemView.findViewById(R.id.parents);
            parents.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            if (view == parents) {

                Intent intent = new Intent(activity , ShowOrdersActivity.class);
                intent.putExtra("object" , list.get(getAdapterPosition()));
                activity.startActivity(intent);
            }

        }

    }
}
