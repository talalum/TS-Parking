package com.example.tsparking.classes;

import java.util.ArrayList;
import java.util.List;

public class listParking {
    private static listParking Instance = new listParking();
    private static ArrayList<Parking> list_Parking = new ArrayList<Parking>(); // create a list of workers

    listParking() {
    }

    public static listParking getMySingelton() { // create singelton
        return Instance;
    }

    public static List<Parking> getList() { // create singelton
        return list_Parking;
    }
    public static void clear() { // create singelton
        list_Parking.clear();
    }


    public static void addParking(Parking new_parking) { // add a new worker to the list
        list_Parking.add(new_parking);
    }

    public static int getSize() {
        return list_Parking.size();
    }
}
