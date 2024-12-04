package com.example.evesafe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateProfileActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // Find views
        EditText currentPasswordField = findViewById(R.id.currentPasswordField);  // Define the current password field
        EditText newEmailField = findViewById(R.id.newEmailField);
        EditText newPasswordField = findViewById(R.id.newPasswordField);
        Button updateEmailButton = findViewById(R.id.updateEmailButton);
        Button updatePasswordButton = findViewById(R.id.updatePasswordButton);

        // Update Email
        updateEmailButton.setOnClickListener(v -> {
            String newEmail = newEmailField.getText().toString().trim();
            String currentPassword = currentPasswordField.getText().toString().trim(); // Current password input

            if (!newEmail.isEmpty() && !currentPassword.isEmpty() && user != null) {
                // Re-authenticate the user
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);
                user.reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Proceed to update email after successful re-authentication
                                user.updateEmail(newEmail)
                                        .addOnCompleteListener(emailUpdateTask -> {
                                            if (emailUpdateTask.isSuccessful()) {
                                                Toast.makeText(this, "Email updated successfully!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(this, "Failed to update email: " + emailUpdateTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                            } else {
                                Toast.makeText(this, "Re-authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            } else {
                Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
            }
        });

        // Update Password
        updatePasswordButton.setOnClickListener(v -> {
            String newPassword = newPasswordField.getText().toString().trim();
            String currentPassword = currentPasswordField.getText().toString().trim(); // Current password input

            if (!newPassword.isEmpty() && !currentPassword.isEmpty() && user != null) {
                // Re-authenticate the user
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);
                user.reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Proceed to update password after successful re-authentication
                                user.updatePassword(newPassword)
                                        .addOnCompleteListener(passwordUpdateTask -> {
                                            if (passwordUpdateTask.isSuccessful()) {
                                                Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(this, "Failed to update password: " + passwordUpdateTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                            } else {
                                Toast.makeText(this, "Re-authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            } else {
                Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
