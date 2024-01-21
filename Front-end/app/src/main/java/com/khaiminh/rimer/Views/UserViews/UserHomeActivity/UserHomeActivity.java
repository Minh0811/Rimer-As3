package com.khaiminh.rimer.Views.UserViews.UserHomeActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.AuthenticationViews.LoginView.LoginActivity;
import com.khaiminh.rimer.Views.UserViews.BookingView.BookingActivity;
import com.khaiminh.rimer.Views.UserViews.UserProfile.UserProfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserHomeActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private UserControllers userControllers = new UserControllers();
    private DrawerLayout drawerLayout;
    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment mapFragment;
    LocationRequest locationRequest;
    ArrayList<User> driverList;
    double latitude, longitude, end_latitude, end_longitude, distanceValue, priceValue;
    private final static int LOCATION_REQUEST_CODE = 23;
    private final int FINE_PERMISSION_CODE = 1;
    private boolean isLocationSearched = false;
    User user;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        // Initialize the drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);

        // Set up the navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_header_username);
        Button signOutBtn = headerView.findViewById(R.id.nav_header_signout);

        // Set navigation item selection listener
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bookingHistory) {
                // Handle action for item 1
            } else if (id == R.id.onGoingBooking) {
                // Handle action for item 2
            } else if (id == R.id.profile) {
                // Handle action for item 3
                Intent intent = new Intent(UserHomeActivity.this, UserProfile.class);
                intent.putExtra("curUser", user);
                startActivityForResult(intent, 100);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Set up the menu button to open the navigation drawer
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Configure Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        // Set the username in the navigation drawer and main layout

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String username = intent.getStringExtra("username");
            navUsername.setText(username);
            user = (User) intent.getSerializableExtra("currUser");
            driverList = (ArrayList<User>) intent.getSerializableExtra("driversList");
        }
        // Check for Google account and set the username
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            userControllers.signup(acct, acct.getDisplayName(), acct.getEmail(), "" + acct.getEmail() + acct.getId(), this);
            String personName = acct.getDisplayName();
            navUsername.setText(personName);
        }

        // Set sign out button listener
        signOutBtn.setOnClickListener(v -> signOut());

//        Map logic
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        SearchView mapSearchView = (SearchView) findViewById(R.id.searchField);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;
                isLocationSearched = true;
                if (location != null) {
                    Geocoder geocoder = new Geocoder(UserHomeActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }

                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));

                    end_latitude = address.getLatitude();
                    end_longitude = address.getLongitude();

                    getDistance();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Button confirm onclick
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(UserHomeActivity.this, BookingActivity.class);
                newIntent.putExtra("distance", String.valueOf(distanceValue));
                newIntent.putExtra("price", String.valueOf(priceValue));
                newIntent.putExtra("lat", latitude);
                newIntent.putExtra("long", longitude);
                newIntent.putExtra("endlat", end_latitude);
                newIntent.putExtra("endlong", end_longitude);
                newIntent.putExtra("user_id", user.getId());
                newIntent.putExtra("drivers", driverList);
                newIntent.putExtra("startPoint", getAddress(new LatLng(latitude, longitude) ));
                newIntent.putExtra("endPoint", getAddress(new LatLng(end_latitude, end_longitude) ));
                startActivityForResult(newIntent, 900);
            }
        });
    }


    @Override
    public void onBackPressed() {
        // Close the navigation drawer if it's open, otherwise perform default back action
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Sign out method
    private void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                // Redirect to login activity after sign out
                finish();
                startActivity(new Intent(UserHomeActivity.this, LoginActivity.class));
            }
        });
    }

    private void getLastLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (isGPSEnabled()) {

                LocationServices.getFusedLocationProviderClient(UserHomeActivity.this)
                        .requestLocationUpdates(locationRequest, new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                super.onLocationResult(locationResult);

                                LocationServices.getFusedLocationProviderClient(UserHomeActivity.this)
                                        .removeLocationUpdates(this);

                                if (locationResult != null && locationResult.getLocations().size() >0){
                                    int index = locationResult.getLocations().size() - 1;
                                    latitude = locationResult.getLocations().get(index).getLatitude();
                                    longitude = locationResult.getLocations().get(index).getLongitude();
                                    end_latitude = latitude;
                                    end_longitude = longitude;
                                    getDistance();
                                }
                            }
                        }, Looper.getMainLooper());

            } else {
                turnOnGPS();
            }
        }
    }

    public double calculatePrice(double distance){
        return distance*0.5;
    }

    private void turnOnGPS() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());
        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(UserHomeActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(UserHomeActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });
    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (isGPSEnabled()) {
                    getLastLocation();
                }else {
                    turnOnGPS();
                }
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        end_latitude = latitude;
        end_longitude = longitude;
        getDistance();

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(UserHomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(UserHomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                if (!isLocationSearched) { // Only update to current location if a search hasn't been performed
                    LatLng ltlng = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                            ltlng, 20f);
                    mMap.animateCamera(cameraUpdate);
                    mMap.getUiSettings().setZoomControlsEnabled(true);
                    mMap.getUiSettings().setCompassEnabled(true);
                    latitude = ltlng.latitude;
                    longitude = ltlng.longitude;
                }
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                mMap.clear();
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                        latLng, 20);
                mMap.animateCamera(location);
                mMap.addMarker(markerOptions);

                end_latitude = latLng.latitude;
                end_longitude = latLng.longitude;
                getDistance();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void getDistance(){
        float[] results = new float[10];
//        Location.distanceBetween(10.7789, 106.6983, 10.7290, 106.6985, results);
        Location.distanceBetween(latitude, longitude, end_latitude, end_longitude, results);
        TextView distance = (TextView) findViewById(R.id.distanceValue);
        TextView price = (TextView) findViewById(R.id.priceValue);
        distanceValue = results[0]/1000;
        priceValue = calculatePrice(distanceValue);
        distance.setText(String.format("%.2f", distanceValue));
        price.setText(String.format("%.2f", priceValue));
    }

    private String getAddress(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Get max 1 location result

            if (addresses == null || addresses.isEmpty()) {
                // Handle case where no addresses are found
                return "No Address Found";
            }

            // It's safe to access the first element, as we've checked that the list is not empty
            String address = addresses.get(0).getAddressLine(0);
            // ... you can use other address components as needed ...

            // Your existing code for handling the fragment transaction
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.commit();

            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return "No Address Found";
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        isLocationSearched = false; // Reset the flag when the activity is resumed
        // ...
    }
}
