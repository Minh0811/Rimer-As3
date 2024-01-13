package com.khaiminh.rimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;

import com.khaiminh.rimer.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class OngoingScreenActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    // Other UI components like TextViews, ImageViews, etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoingscreen);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize TextViews, ImageViews, and other UI components

        Button btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle cancel order logic
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // Configure your map here
    }

    // Additional methods to set up the UI and handle user interaction
}
