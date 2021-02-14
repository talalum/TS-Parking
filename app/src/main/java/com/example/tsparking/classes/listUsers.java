package com.example.tsparking.classes;

import java.util.ArrayList;
import java.util.List;

public class listUsers {

    private static listUsers Instance = new listUsers();
    private static ArrayList<User> list_Users = new ArrayList<User>(); // create a list of workers

    private listUsers() {
    }

    public static listUsers getMySingelton() { // create singelton
        return Instance;
    }

    public static List<User> getList() { // create singelton
        return list_Users;
    }
    public static void clear() { // create singelton
        list_Users.clear();
    }


    public static void addUser(User new_user) { // add a new worker to the list
        list_Users.add(new_user);
    }

    public static int getSize() {
        return list_Users.size();
    }
}
