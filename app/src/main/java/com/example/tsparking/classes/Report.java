package com.example.tsparking.classes;



public class Report {
    private String idReport;
    private	User user;
    private Parking parking;
    private Slot slot;

    public Report(String idReport, User user, Parking parking, Slot slot) {
        super();
        this.idReport = idReport;
        this.user = user;
        this.parking = parking;
        this.slot = slot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getIdReport() {
        return idReport;
    }


}
