package com.example.assignment.Model;

public class User {
    private String name;
    private String pass;
    private String phone;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String name, String pass, String phone, String address) {
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.address = address;
    }
}
