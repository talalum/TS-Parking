package com.example.tsparking.classes;


import java.util.ArrayList;


public class User {
//    private int UserNum;
    private String Email;
    private String FirstName;
    private String LastName;

    //    private static int counter_of_users = 0;

    public User() {
//        UserNum = ++counter_of_users; // blank constructor
    }
    public User(String email, String firstName, String lastName) {
        super();
        Email = email;
        FirstName = firstName;
        LastName = lastName;
        //        UserNum = ++counter_of_users; // blank constructor

    }
//    public int getUserNum() {
//        return UserNum;
//    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }


}

