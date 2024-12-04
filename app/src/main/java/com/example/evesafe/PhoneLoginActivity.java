package com.example.evesafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private Button verifyPhoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login); // Ensure this layout exists

        // Initialize the views
        phoneEditText = findViewById(R.id.phoneEditText);
        verifyPhoneButton = findViewById(R.id.verifyPhoneButton);

        verifyPhoneButton.setOnClickListener(view -> {
            String phoneNumber = phoneEditText.getText().toString().trim();
            if (!phoneNumber.isEmpty()) {
                // Add the country code if necessary
                if (!phoneNumber.startsWith("+")) {
                    phoneNumber = "+1" + phoneNumber; // Replace "+1" with your country's code
                }

                // Use Firebase PhoneAuthProvider to send OTP
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber, // Phone number to verify
                        60,  // Timeout duration in seconds
                        TimeUnit.SECONDS,
                        this,  // Activity for callbacks
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                // Automatically handle OTP when verification is completed
                                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                        .addOnCompleteListener(task -> {
                                            if (task.isSuccessful()) {
                                                // Navigate to home screen or another activity upon successful login
                                                startActivity(new Intent(PhoneLoginActivity.this, HomeActivity.class));
                                                finish();
                                            } else {
                                                Toast.makeText(PhoneLoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                // Handle verification failed
                                Toast.makeText(PhoneLoginActivity.this, "Verification Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                                // Pass the verification ID and token to the next activity to verify the OTP
                                Intent intent = new Intent(PhoneLoginActivity.this, VerifyOtpActivity.class);
                                intent.putExtra("verificationId", verificationId); // Send verificationId to the next activity
                                intent.putExtra("resendToken", token); // Optional, if you want to resend OTP
                                startActivity(intent);
                            }
                        });
            } else {
                Toast.makeText(PhoneLoginActivity.this, "Please enter a valid phone number!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
