package com.example.tsparking.classes;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Slot {
    private boolean IsDisable;
    private boolean IsIndoor;
    private boolean IsFree;
    private int ParkingNum;

    public Slot() {
    }

    public Slot( boolean disable, boolean indoor, boolean free, int parkingNum) {
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

    public String getArea(){
       int parkingnum=this.ParkingNum;
        final String[] area = {null};
        Query query2= FirebaseDatabase.getInstance().getReference("Parking").orderByChild("area");
        query2.addValueEventListener(new ValueEventListener() {
          //  private int ParkingNum;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot adSnapshot: snapshot.getChildren()) {
                    if(adSnapshot.getValue(Parking.class).getParkingNum()==parkingnum)
                        area[0] =adSnapshot.getValue(Parking.class).getArea();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return area[0];


    }
}