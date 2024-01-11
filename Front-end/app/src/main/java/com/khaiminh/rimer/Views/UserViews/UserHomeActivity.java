package com.khaiminh.rimer.Views.UserViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.R;
import com.khaiminh.rimer.Views.LoginView.LoginActivity;

public class UserHomeActivity extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private UserControllers userControllers = new UserControllers();
    private DrawerLayout drawerLayout;

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
            if (id == R.id.nav_item1) {
                // Handle action for item 1
            } else if (id == R.id.nav_item2) {
                // Handle action for item 2
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
        }

        // Check for Google account and set the username
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            userControllers.signup(acct.getDisplayName(), acct.getEmail(), "123", this);
            String personName = acct.getDisplayName();
            navUsername.setText(personName);
        }

        // Set sign out button listener
        signOutBtn.setOnClickListener(v -> signOut());
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
}
