package com.example.tsparking.classes;

import java.util.ArrayList;
import java.util.List;

public class listReport {

    private static listReport Instance = new listReport();
    private static ArrayList<Report> list_Report = new ArrayList<Report>(); // create a list of reports

    listReport() {
    }

    public static listReport getMySingelton() { // create singelton
        return Instance;
    }

    public static List<Report> getList() { // create singelton
        return list_Report;
    }
    public static void clear() { // create singelton
        list_Report.clear();
    }


    public static void addReport(Report new_report) { // add a new worker to the list
        list_Report.add(new_report);
    }

    public static int getSize() {
        return list_Report.size();
    }
    //
}
