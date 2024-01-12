package com.example.rimer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListTripAdapter extends RecyclerView.Adapter<ListTripAdapter.ViewHolder> {
    @NonNull
    @Override
    public ListTripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_approval,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTripAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView drivername, carplate, carmodel, pickuppointdetail, destinationdetail, statusdetail;

        Button ongoingbtn, historybtn, viewbtn;

        ImageView driverimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drivername = itemView.findViewById(R.id.drivername);
            carplate = itemView.findViewById(R.id.carplate);
            carmodel = itemView.findViewById(R.id.carmodel);
            pickuppointdetail = itemView.findViewById(R.id.pickuppointdetail);
            destinationdetail = itemView.findViewById((R.id.destinationdetail));
            statusdetail = itemView.findViewById(R.id.statusdetail);
            ongoingbtn = itemView.findViewById(R.id.ongoingbtn);
            historybtn = itemView.findViewById(R.id.historybtn);
            viewbtn = itemView.findViewById(R.id.viewbtn);
            driverimg = itemView.findViewById(R.id.driverimage);
        }
    }
}
