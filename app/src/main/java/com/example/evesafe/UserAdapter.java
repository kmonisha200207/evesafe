package com.example.evesafe;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;

    // Constructor to initialize the user list
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate item layout for each user
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind user data to the views
        User user = userList.get(position);

        holder.firstName.setText("First Name: " + user.getFirstName());
        holder.lastName.setText("Last Name: " + user.getLastName());
        holder.email.setText("Email: " + user.getEmail());
        holder.dob.setText("Date of Birth: " + user.getDob());
        holder.password.setText("Password: " + user.getPassword());
        holder.confirmPassword.setText("Confirm Password: " + user.getConfirmPassword());
        holder.phone.setText("Phone: " + user.getPhone());
        holder.city.setText("City: " + user.getCity());
        holder.address.setText("Address: " + user.getAddress());
        holder.pinCode.setText("Pin Code: " + user.getPinCode());
    }

    @Override
    public int getItemCount() {
        // Return total number of items
        return userList.size();
    }

    // ViewHolder class to hold UI components
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName, email, dob, password, confirmPassword, phone, city, address, pinCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.text_first_name);
            lastName = itemView.findViewById(R.id.text_last_name);
            email = itemView.findViewById(R.id.text_email);
            dob = itemView.findViewById(R.id.text_dob);
            password = itemView.findViewById(R.id.text_password);
            confirmPassword = itemView.findViewById(R.id.text_confirm_password);
            phone = itemView.findViewById(R.id.text_phone);
            city = itemView.findViewById(R.id.text_city);
            address = itemView.findViewById(R.id.text_address);
            pinCode = itemView.findViewById(R.id.text_pin_code);
        }
    }
}
