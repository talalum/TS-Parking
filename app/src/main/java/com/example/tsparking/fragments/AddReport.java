package com.example.tsparking.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReport extends Fragment {
    private TextView TexViewtEmail;
    private TextView SlotNumText;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddReport.
     */
    // TODO: Rename and change types and number of parameters
    public static AddReport newInstance(String param1, String param2) {
        AddReport fragment = new AddReport();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_report, container, false);

        Button save_reportB = (Button) view.findViewById(R.id.SaveReportButton);
        save_reportB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.SaveReport();
            }
        });

        TexViewtEmail = view.findViewById(R.id.EmailUserTP);

        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences=mainActivity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        TexViewtEmail.setText(sharedPreferences.getString("email",null));

        SlotNumText = view.findViewById(R.id.SlotNumTP);
        SlotNumText.setText(sharedPreferences.getString("numSlot",null));
        return view;
    }
}