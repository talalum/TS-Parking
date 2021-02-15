package com.example.tsparking.classes;

import java.util.ArrayList;
import java.util.List;

public class listSlot {

    private static listSlot Instance = new listSlot();
    private static ArrayList<Slot> list_Slot = new ArrayList<Slot>(); // create a list of workers

    private listSlot() {
    }

    public static listSlot getMySingelton() { // create singelton
        return Instance;
    }

    public static List<Slot> getList() { // create singelton
        return list_Slot;
    }
    public static void clear() { // create singelton
        list_Slot.clear();
    }


    public static void addSlot(Slot new_slot) {
        list_Slot.add(new_slot);
    }

    public static int getSize() {
        return list_Slot.size();
    }
}