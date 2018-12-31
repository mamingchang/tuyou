package com.example.administrator.card;

public class User {
    public String ID = "";
    public String password = "";
    public String name = "";

    public User() {
    }

    public User(String name, String password, String ID) {
        this.name = name;
        this.password = password;
        this.ID = ID;
    }
}
