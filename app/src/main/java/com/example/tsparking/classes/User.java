package com.example.tsparking.classes;


import java.util.ArrayList;


public class User {
    private String Email;
    private String FirstName;
    private String LastName;
    private int slotNum;

    public User() {
    }
    public User( String email, String firstName, String lastName) {
        super();
        Email = email;
        FirstName = firstName;
        LastName = lastName;
        slotNum=0;
    }
    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public int getSlotNum() {
        return slotNum;
    }

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

