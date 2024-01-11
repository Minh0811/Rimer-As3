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
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    UserControllers userControllers = new UserControllers();
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Set up the navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_item1) {
                // Handle action for item 1
            } else if (id == R.id.nav_item2) {
                // Handle action for item 2
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        TextView name = findViewById(R.id.name);
        Intent intent = getIntent();
        if (intent.getExtras() != null){
            String username = (String) intent.getExtras().get("username");
            name.setText(username);
        }

//        google account
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            userControllers.signup(acct.getDisplayName(), acct.getEmail(), "123", UserHomeActivity.this);
            String personName = acct.getDisplayName();
            name.setText(personName);
        }

        Button signOutBtn = (Button) findViewById(R.id.signout);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(UserHomeActivity.this, LoginActivity.class));
            }
        });
    }
}