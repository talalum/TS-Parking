package com.example.tsparking.classes;


public class Slot extends Parking{
    private String IdSlot;
    private boolean Disable;
    private boolean Indoor;
    private boolean free;

    public Slot(String idParking, double price, String area, String address, boolean paved, String idSlot,
                boolean disable, boolean indoor) {
        super(price, area, address, paved);
        IdSlot = idSlot;
        Disable = disable;
        Indoor = indoor;
        free = true;
    }

    public boolean isDisable() {
        return Disable;
    }

    public void setDisable(boolean disable) {
        Disable = disable;
    }

    public boolean isIndoor() {
        return Indoor;
    }

    public void setIndoor(boolean indoor) {
        Indoor = indoor;
    }

    public String getIdSlot() {
        return IdSlot;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}

