package com.example.evesafe;



import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
    }

    public void sendSOS(View view) {
        // Mock SOS functionality
        Toast.makeText(this, "SOS Alert Sent!", Toast.LENGTH_SHORT).show();
    }
}
