package com.example.tsparking.classes;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ObserverToReport extends Observable {

    public ObserverToReport() {
    }

    public void show(String num)
    {
        setChanged();
        List<String> l=new ArrayList<>();
        l.add(num);
        notifyObservers(l);
    }
}
