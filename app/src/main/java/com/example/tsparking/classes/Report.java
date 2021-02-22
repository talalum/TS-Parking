package com.example.tsparking.classes;



public class Report {
    private String TheReport;
    private	String WriteBy;
    private int SlotNum;
    private String DateOfCreate;




    public Report(String theReport, String writeBy, int slotNum) {
        TheReport = theReport;
        WriteBy = writeBy;
        SlotNum = slotNum;
    }
    public Report(){

    }


    public String getTheReport() {
        return TheReport;
    }

    public void setTheReport(String theReport) {
        TheReport = theReport;
    }

    public String getWriteBy() {
        return WriteBy;
    }

    public void setWriteBy(String writeBy) {
        WriteBy = writeBy;
    }

    public int getSlotNum() {
        return SlotNum;
    }

    public void setSlotNum(int slotNum) {
        SlotNum = slotNum;
    }

    public String getDateOfCreate() {
        return DateOfCreate;
    }

    public void setDateOfCreate(String dateOfCreate) {
        DateOfCreate = dateOfCreate;
    }
}
