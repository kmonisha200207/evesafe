package com.example.evesafe;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String password;
    private String confirmPassword;
    private String phone;
    private String city;
    private String address;
    private String pinCode;

    // Default constructor (required for Firebase)
    public User() {}

    // Constructor with all fields
    public User(String firstName, String lastName, String email, String dob, String password,
                String confirmPassword, String phone, String city, String address, String pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.pinCode = pinCode;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
