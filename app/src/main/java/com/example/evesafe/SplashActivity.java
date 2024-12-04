package com.example.evesafe;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;

import android.os.Handler;



public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for the splash screen
        new Handler().postDelayed(() -> {
            // Redirect to HomeActivity
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close the splash screen activity
        }, SPLASH_DELAY);
    }
}
