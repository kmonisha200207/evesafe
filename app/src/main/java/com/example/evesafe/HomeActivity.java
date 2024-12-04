package com.example.evesafe;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Ensure your home layout is named correctly

        // Initialize Firebase instances
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Find views
        Button loginWithEmailButton = findViewById(R.id.loginWithEmailButton);
        Button loginWithPhoneButton = findViewById(R.id.loginWithPhoneButton);
        TextView registerLink = findViewById(R.id.registerLink);

        // Navigate to Email Login
        loginWithEmailButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Navigate to Phone Login
        loginWithPhoneButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PhoneLoginActivity.class);
            startActivity(intent);
        });

        // Navigate to Registration Page
        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

}
