package com.example.evesafe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstName, lastName, email, dob, password, confirmPassword, phone, city, address, pinCode;
    private RadioGroup genderGroup;
    private Spinner stateSpinner;
    private Button registerButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Authentication and Database
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize fields
        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        email = findViewById(R.id.register_email);
        dob = findViewById(R.id.register_dob);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.editText_register_confirm_password);
        phone = findViewById(R.id.register_phone);
        city = findViewById(R.id.register_city);
        address = findViewById(R.id.register_address);
        pinCode = findViewById(R.id.register_pin);
        genderGroup = findViewById(R.id.radioGroup_gender);
        stateSpinner = findViewById(R.id.spinner_state);
        registerButton = findViewById(R.id.btn_register);

        // Handle Register Button Click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String dobText = dob.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String confPass = confirmPassword.getText().toString().trim();
        String phoneNum = phone.getText().toString().trim();
        String cityText = city.getText().toString().trim();
        String addressText = address.getText().toString().trim();
        String pin = pinCode.getText().toString().trim();

        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(fname)) {
            Toast.makeText(RegisterActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(confPass)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register user with Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(emailText, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Send verification email
                            firebaseUser.sendEmailVerification()
                                    .addOnCompleteListener(verificationTask -> {
                                        if (verificationTask.isSuccessful()) {
                                            saveUserDataToDatabase(fname, lname, emailText, dobText, phoneNum, cityText, addressText, pin);
                                            Toast.makeText(RegisterActivity.this, "Registration successful! Please verify your email.", Toast.LENGTH_LONG).show();
                                            firebaseAuth.signOut(); // Logout user after registration
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserDataToDatabase(String fname, String lname, String email, String dob, String phone, String city, String address, String pin) {
        String userId = firebaseAuth.getCurrentUser().getUid();

        // Prepare user data
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("firstName", fname);
        userMap.put("lastName", lname);
        userMap.put("email", email);
        userMap.put("dob", dob);
        userMap.put("phone", phone);
        userMap.put("city", city);
        userMap.put("address", address);
        userMap.put("pinCode", pin);

        // Save data to Firebase Database
        databaseReference.child(userId).setValue(userMap);
    }
}
