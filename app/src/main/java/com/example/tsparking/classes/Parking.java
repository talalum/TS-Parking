package com.example.tsparking.classes;


import java.sql.Array;
import java.util.List;

public class Parking {
//    private int ParkingNum;
    private double price;
    private String area;
    private String address;
    private boolean paved;

//    private static int counter_of_parking = 0;

    public Parking() {
//        ParkingNum = ++counter_of_parking; // blank constructor
    }

    public Parking(double price, String area, String address, boolean paved) {
        super();
        this.price = price;
        this.area = area;
        this.address = address;
        this.paved = paved;
//        ParkingNum = ++counter_of_parking; // blank constructor
    }


//    public int getParkingNum() {
//        return ParkingNum;
//    }
//


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPaved() {
        return paved;
    }

    public void setPaved(boolean paved) {
        this.paved = paved;
    }
}
