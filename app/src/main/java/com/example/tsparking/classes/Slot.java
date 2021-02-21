package com.example.tsparking.classes;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Slot {
    private boolean IsDisable;
    private boolean IsIndoor;
    private boolean IsFree;
    private int slotNum;
    private int ParkingNum;

    public Slot() {
    }

    public Slot( boolean disable, boolean indoor, boolean free, int parkingNum,int slotnum) {
        IsDisable = disable;
        IsIndoor = indoor;
        IsFree = free;
        slotNum=slotnum;
        ParkingNum = parkingNum;
    }

//    public Slot( boolean disable, boolean indoor, boolean free, int parkingNum,
//                 double price, String area, String address, boolean paved) {
//        IsDisable = disable;
//        IsIndoor = indoor;
//        IsFree = free;
//        ParkingNum = parkingNum;
//    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }


    public int getSlotNum() {
        return slotNum;
    }
    public boolean isDisable() {
        return IsDisable;
    }

    public void setDisable(boolean disable) {
        IsDisable = disable;
    }

    public boolean isIndoor() {
        return IsIndoor;
    }

    public void setIndoor(boolean indoor) {
        IsIndoor = indoor;
    }

    public boolean isFree() {
        return IsFree;
    }

    public void setFree(boolean free) {
        IsFree = free;
    }

    public int getParkingNum() {
        return ParkingNum;
    }

    public void setParkingNum(int parkingNum) {
        ParkingNum = parkingNum;
    }

}