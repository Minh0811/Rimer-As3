package com.khaiminh.rimer.Views.UserViews.BookingView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khaiminh.rimer.Controllers.BookingControllers.BookingControllers;
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
    private String userId;
    double price;
    double distance;
    String status;
    String startPoint;
    String endPoint;

    public void setData(Context context, String userId, List<User> drivers, int layout, String status, double distance, double price, String startPoint, String endPoint) {
        this.context = context;
        this.drivers = drivers;
        this.layout = layout;
        this.userId = userId;
        this.price = price;
        this.distance = distance;
        this.status = status;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
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
        holder.priceView.setText(String.valueOf(Math.floor(price)));
        holder.driver_id = drivers.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class DriversListHolder extends RecyclerView.ViewHolder {
        TextView name, priceView;
        String driver_id;
        public DriversListHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.drivername);
            priceView = itemView.findViewById(R.id.farerideinfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(itemView.getContext(), TripWaitingConfirmationActivity.class);
                    newIntent.putExtra("driverName", name.getText());
                    newIntent.putExtra("driver_id", driver_id);
                    newIntent.putExtra("currUserId", userId);
                    itemView.getContext().startActivity(newIntent);
                    BookingControllers bookingControllers = new BookingControllers();
                    bookingControllers.createNewBooking(userId, driver_id, status, distance, price, startPoint, endPoint);
                }
            });
        }
    }
}

