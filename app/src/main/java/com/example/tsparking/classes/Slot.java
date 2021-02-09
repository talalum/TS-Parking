package com.example.tsparking.classes;


public class Slot {
//    private int IdSlot;
    private boolean IsDisable;
    private boolean IsIndoor;
    private boolean IsFree;
    private int ParkingNum;


    public Slot() {
    }

    public Slot(/*int idSlot,*/ boolean disable, boolean indoor, boolean free, int parkingNum) {
        //IdSlot = idSlot;
        IsDisable = disable;
        IsIndoor = indoor;
        IsFree = free;
        ParkingNum = parkingNum;
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