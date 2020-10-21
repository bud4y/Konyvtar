package com.company;

public class User {
    private int userID;
    private String name;
    private String email;
    private String address;

    public User(int userID, String name, String email, String address) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return
                "\n\nuserID=" + userID +
                "\nname='" + name +
                "\nemail='" + email +
                "\naddress='" + address ;
    }
}


