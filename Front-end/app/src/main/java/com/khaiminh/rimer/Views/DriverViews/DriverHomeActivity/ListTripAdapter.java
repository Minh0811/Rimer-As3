package com.khaiminh.rimer.Views.DriverViews.DriverHomeActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.khaiminh.rimer.Model.Booking;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.DriverViews.TripConfirmationActivity.TripConfirmationActivity;

import java.util.List;

public class ListTripAdapter extends RecyclerView.Adapter<ListTripAdapter.ViewHolder> {


    private List<Booking> bookingList;

    public ListTripAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }


    @NonNull
    @Override
    public ListTripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_approval,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTripAdapter.ViewHolder holder, int position) {
        Booking currentBooking = bookingList.get(position);
        // Bind booking details to the views
        holder.pickuppointdetail.setText(currentBooking.getStartPoint());
        holder.destinationdetail.setText(currentBooking.getEndPoint());
        holder.statusdetail.setText(currentBooking.getStatus());

        holder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log the current booking ID
                Log.d("ListTripAdapter", "Current Booking ID: " + currentBooking.getId());

                // Ensure that currentBooking.getId() is not null
                if (currentBooking.getId() != null) {
                    Intent intent = new Intent(v.getContext(), TripConfirmationActivity.class);
                    intent.putExtra("BOOKING_ID", currentBooking.getId());
                    v.getContext().startActivity(intent);
                } else {
                    // Handle the case where booking ID is null
                    Log.e("ListTripAdapter", "Booking ID is null, cannot start TripConfirmationActivity");
                    Toast.makeText(v.getContext(), "Error: Booking ID is null.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView drivername, carplate, carmodel, pickuppointdetail, destinationdetail, statusdetail;
        Button ongoingbtn, historybtn, viewbtn;
        ImageView driverimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drivername = itemView.findViewById(R.id.drivername);
            carplate = itemView.findViewById(R.id.carplate);
            carmodel = itemView.findViewById(R.id.carmodel);
            pickuppointdetail = itemView.findViewById(R.id.pickuppointdetail);
            destinationdetail = itemView.findViewById(R.id.destinationdetail);
            statusdetail = itemView.findViewById(R.id.statusdetail);
            ongoingbtn = itemView.findViewById(R.id.ongoingbtn);
            historybtn = itemView.findViewById(R.id.historybtn);
            viewbtn = itemView.findViewById(R.id.viewbtn);
            driverimg = itemView.findViewById(R.id.driverimage);
            // Initialize other views in the item if there are any
        }
    }
}
