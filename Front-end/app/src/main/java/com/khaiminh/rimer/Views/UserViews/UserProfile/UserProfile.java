package com.khaiminh.rimer.Views.UserViews.UserProfile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khaiminh.rimer.Controllers.UserControllers.UserControllers;
import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("curUser");
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        TextView nameView = findViewById(R.id.nameValue);
        TextView emailView = findViewById(R.id.emailValue);

        nameView.setText(name);
        emailView.setText(email);

        Button backButton = findViewById(R.id.backbtn);

        Button editNameBtn =  findViewById(R.id.nameEditBtn);
        Button editEmailBtn = findViewById(R.id.emailEditBtn);
        Button editPassBtn =  findViewById(R.id.passwordEditBtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity
                finish();
            }
        });

        editNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.edit_name_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);

                builder.setView(view).show()
                        .setTitle("Edit name");

                TextView oldName = view.findViewById(R.id.oldUsername);
                oldName.setText(name);

                EditText newNameValue = view.findViewById(R.id.editNameValue);

                Button submitBtn = view.findViewById(R.id.submitEditBtn);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = newNameValue.getText().toString();
                        String userId = user.getId(); // Make sure you have the user's ID

                        // Create an instance of UserControllers and call updateUsername
                        UserControllers userControllers = new UserControllers();
                        userControllers.updateUsername(userId, newName, UserProfile.this);
                    }
                });
            }
        });

        editEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.edit_email_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);

                builder.setView(view).show()
                        .setTitle("Edit email");

                TextView oldEmail = view.findViewById(R.id.oldEmail);
                oldEmail.setText(email);

                EditText newEmailValue = view.findViewById(R.id.editEmailValue);
                String newName = newEmailValue.getText().toString();

                Button submitBtn = view.findViewById(R.id.submitEditBtn);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // handle submit edit email
                    }
                });
            }
        });

        editPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.edit_pass_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);

                builder.setView(view).show()
                        .setTitle("Edit pass");

                TextView newPassLabel = view.findViewById(R.id.newPasswordLabel);
                EditText newPassValue = view.findViewById(R.id.editPasswordValue);
                String newPass = newPassValue.getText().toString();
                Button confirmBtn = view.findViewById(R.id.confirmNewPassBtn);

                EditText oldPassword = view.findViewById(R.id.oldPasswordValue);
                Button submitBtn = view.findViewById(R.id.submitEditBtn);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // handle submit edit old password
                        String oldPass = oldPassword.getText().toString();
                      //  if (oldPass == password) { // add logic compare password
                            newPassLabel.setVisibility(v.VISIBLE);
                            newPassValue.setVisibility(v.VISIBLE);
                            confirmBtn.setVisibility(v.VISIBLE);

                            confirmBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //handle confirm new pass
                                }
                            });
                       // }
                    }
                });
            }
        });
    }

}