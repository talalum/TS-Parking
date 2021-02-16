package com.example.tsparking.classes;


import java.sql.Array;
import java.util.List;

public class Parking {


    private int ParkingNum;
    private double price;
    private String area;
    private String address;
    private boolean paved;

    public Parking() {
    }

    public Parking(double price, String area, String address, boolean paved) {
        super();
        this.price = price;
        this.area = area;
        this.address = address;
        this.paved = paved;
    }
    public Parking(int num) {
    }


    public int getParkingNum() {
        return ParkingNum;
    }

    public void setParkingNum(int parkingNum) {
        ParkingNum = parkingNum;
    }

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
