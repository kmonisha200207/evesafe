package com.example.evesafe;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOtpActivity extends AppCompatActivity {

    private EditText otpEditText;
    private Button verifyOtpButton;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        otpEditText = findViewById(R.id.otpEditText);
        verifyOtpButton = findViewById(R.id.verifyOtpButton);

        // Get the verification ID passed from PhoneLoginActivity
        verificationId = getIntent().getStringExtra("verificationId");

        verifyOtpButton.setOnClickListener(view -> {
            String otp = otpEditText.getText().toString().trim();
            if (!otp.isEmpty()) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // User successfully logged in
                                startActivity(new Intent(VerifyOtpActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                // OTP verification failed
                                Toast.makeText(VerifyOtpActivity.this, "Verification Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
