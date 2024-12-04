package com.example.evesafe;





import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        // Set welcome message
        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        welcomeMessage.setText("Welcome to EveSafe Dashboard!");
    }

    // Open Profile Activity
    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    // Open SOS Activity
    public void openSOS(View view) {
        Intent intent = new Intent(this, SosActivity.class);
        startActivity(intent);
    }

    // Open Location Tracking Activity
    public void openLocationTracking(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    // Open Health Monitoring Activity
    public void openHealthMonitoring(View view) {
        Intent intent = new Intent(this, HealthMonitoringActivity.class);
        startActivity(intent);
    }

    // Open Safe Area Indication Activity
    public void openSafeAreaIndication(View view) {
        Intent intent = new Intent(this, SafeAreaActivity.class);
        startActivity(intent);
    }

    // Open Sound Analysis Activity
    public void openSoundAnalysis(View view) {
        Intent intent = new Intent(this, SoundAnalysisActivity.class);
        startActivity(intent);
    }
}
