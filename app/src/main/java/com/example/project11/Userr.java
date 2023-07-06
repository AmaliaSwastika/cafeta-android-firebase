package com.example.project11;

public class Userr {

    private String name;
    private String email;
    private String password;

    public Userr() {
        // Required empty constructor for Firebase
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Userr(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
}
}