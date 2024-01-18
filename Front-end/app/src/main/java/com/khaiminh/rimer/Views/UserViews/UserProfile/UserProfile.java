package com.khaiminh.rimer.Views.UserViews.UserProfile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.khaiminh.rimer.Model.User;
import com.khaiminh.rimer.R;

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

        Button editNameBtn = (Button) findViewById(R.id.nameEditBtn);
        Button editEmailBtn = (Button) findViewById(R.id.emailEditBtn);
        Button editPassBtn = (Button) findViewById(R.id.passwordEditBtn);
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
                String newName = newNameValue.getText().toString();

                Button submitBtn = view.findViewById(R.id.submitEditBtn);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // handle submit edit name
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