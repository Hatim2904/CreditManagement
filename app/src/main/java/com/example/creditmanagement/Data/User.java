package com.example.creditmanagement.Data;

public class User {
    private int id;
    private String name;
    private int currCredit;
    private String email;

    public User() {
    }

    public User(int id, String name, int currCredit, String email) {
        this.id = id;
        this.name = name;
        this.currCredit = currCredit;
        this.email = email;
    }

    public String toString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrCredit() {
        return currCredit;
    }

    public void setCurrCredit(int currCredit) {
        this.currCredit = currCredit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

