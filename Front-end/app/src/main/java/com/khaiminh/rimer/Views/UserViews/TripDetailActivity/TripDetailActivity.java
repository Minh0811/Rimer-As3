package com.khaiminh.rimer.Views.UserViews.TripDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.khaiminh.rimer.R;

public class TripDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView driverPicture;
    private TextView driverName, tripDestination, licensePlateNumber, bikeDescription;

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
        driverPicture = findViewById(R.id.driverPicture);
        driverName = findViewById(R.id.driverName);
        tripDestination = findViewById(R.id.tripDestination);
        licensePlateNumber = findViewById(R.id.licensePlateNumber);
        bikeDescription = findViewById(R.id.bikeDescription);

        // TODO: Load data into views
        // For example:
        // driverName.setText("John Doe");
        // tripDestination.setText("Central Park");
        // licensePlateNumber.setText("XYZ 1234");
        // bikeDescription.setText("Black Yamaha R3");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO: Customize the map
        // For example, setting the map type, adding markers, moving the camera, etc.
    }
}
