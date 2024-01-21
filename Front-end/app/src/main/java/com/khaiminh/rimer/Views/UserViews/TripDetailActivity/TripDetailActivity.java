package com.khaiminh.rimer.Views.UserViews.TripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.khaiminh.rimer.R;

public class TripDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tripPickupPoint, tripDestination, driverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        // Initialize the map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize views
        tripPickupPoint = findViewById(R.id.tripPickupPoint);
        tripDestination = findViewById(R.id.tripDestination);
        driverName = findViewById(R.id.driverName);

        // TODO: Load data into views
        // For example:
        // tripPickupPoint.setText("123 Main St");
        // tripDestination.setText("Central Park");
        // driverName.setText("John Doe");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO: Customize the map
        // For example, setting the map type, adding markers, moving the camera, etc.
    }
}
