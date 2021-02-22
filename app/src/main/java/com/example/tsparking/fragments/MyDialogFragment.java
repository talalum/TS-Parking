package com.example.tsparking.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;
import com.example.tsparking.classes.Parking;
import com.example.tsparking.classes.Slot;

import org.w3c.dom.Text;

public class MyDialogFragment extends DialogFragment {
    private static final String TAG ="SAPIR" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.dialogslot,container,false);

        Button yesB = (Button) view.findViewById(R.id.yesB);
        Button noB = (Button) view.findViewById(R.id.noB);

        MainActivity mainActivity = (MainActivity) getActivity();
        noB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.ReleaseSlot();
                mainActivity.closeDialogFunc();


            }
        });

        yesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.closeDialogFunc();

            }
        });

        TextView area = (TextView) view.findViewById(R.id.areaDialog);

        SharedPreferences sharedPreferences=mainActivity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        Slot slot= mainActivity.getSlotByNum(Integer.parseInt(sharedPreferences.getString("SaveSlot",null)));
        Parking parking=mainActivity.getParkingByNum(slot.getParkingNum());
        area.setText(parking.getArea()+"?");

        return view;
    }
}
