package com.example.project11;

public class User {

    private String name;
    private String email;
    private String phone;
    private String outlet;
    private String person;
    private String time;
    private String date;


    public User() {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User(String name, String email, String phone, String outlet, String person, String time, String date) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.outlet = outlet;
        this.person = person;
        this.time = time;
        this.date = date;
}

}