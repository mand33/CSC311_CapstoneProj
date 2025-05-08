package org.example.csc311_capstoneproj.models;

public class User {

    private String username;
    private String email;
    private String password;
    private String dob;

    public User(String username, String email, String password, String dob) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
