package com.khaiminh.rimer.Views.UserViews.BookingView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitControllers;
import com.khaiminh.rimer.Controllers.Retrofit.RetrofitInterface;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.UserViews.TripWaitingConfirmationActivity.TripWaitingConfirmationActivity;

import java.util.List;

public class ListDriverAdapter extends RecyclerView.Adapter<ListDriverAdapter.DriversListHolder> {
    private RetrofitInterface retrofitInterface;
    private RetrofitControllers retrofitControllers = new RetrofitControllers();
    private Context context;
    private List<User> drivers;
    private int layout;
//    private User curUser;
    double price;

    public void setData(Context context, List<User> drivers, int layout, double price) {
        this.context = context;
        this.drivers = drivers;
        this.layout = layout;
//        this.curUser = curUser;
        this.price = price;
    }
    public void retrofitHandle(){
        retrofitControllers.retrofitController();
        this.retrofitInterface = retrofitControllers.getRetrofitInterface();
    }
    @NonNull
    @Override
    public DriversListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        retrofitHandle();

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new DriversListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriversListHolder holder, int position) {
        holder.name.setText(drivers.get(position).getName());
        holder.price.setText(String.valueOf(price));
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriversListHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        public DriversListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.drivername);
            price = itemView.findViewById(R.id.farerideinfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(itemView.getContext(), TripWaitingConfirmationActivity.class);
                    newIntent.putExtra("driverName", name.getText());
                    itemView.getContext().startActivity(newIntent);
                }
            });
        }
    }
}

