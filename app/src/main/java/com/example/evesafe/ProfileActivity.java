package com.example.evesafe;



import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView, emailTextView, phoneTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth and Database reference
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Bind the TextViews
        nameTextView = findViewById(R.id.nameView);
        emailTextView = findViewById(R.id.emailView);
        phoneTextView = findViewById(R.id.phoneView);

        // Load user data from Firebase
        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = mAuth.getCurrentUser().getUid();

        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);

                    // Display data in TextViews
                    nameTextView.setText("Name: " + name);
                    emailTextView.setText("Email: " + email);
                    phoneTextView.setText("Phone: " + phone);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
