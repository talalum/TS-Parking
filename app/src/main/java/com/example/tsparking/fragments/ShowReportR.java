package com.example.tsparking.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;
import com.example.tsparking.classes.Report;
import com.example.tsparking.classes.ReportAdapter;
import com.example.tsparking.classes.listReport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowReportR#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowReportR extends Fragment {

    private static final String TAG = "MyActivity";
    RecyclerView recyclerViewReport;
    ReportAdapter adapterReport;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static String NumSlot;
    private String mParam1;
    private String mParam2;

    public ShowReportR(String numSlot) {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowReportR.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowReportR newInstance(String param1, String param2) {
        ShowReportR fragment = new ShowReportR(NumSlot);
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
            NumSlot = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_report_r, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        mainActivity.getMySingeltonMReport().clear();
                Query query= FirebaseDatabase.getInstance().getReference("Report");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                            if(adSnapshot.getValue(Report.class).getSlotNum() == Integer.parseInt(NumSlot)) {
                                mainActivity.getMySingeltonMReport().addReport(adSnapshot.getValue(Report.class));
                                Log.i(TAG, adSnapshot.getValue(Report.class).getTheReport());
                            }
                        }
                        recyclerViewReport=(RecyclerView)view.findViewById(R.id.recycleViewReport);
                        recyclerViewReport.setHasFixedSize(true);
                        recyclerViewReport.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        listReport listreport=mainActivity.getMySingeltonMReport();
                        adapterReport=new ReportAdapter(view.getContext(),listreport.getList());
                        recyclerViewReport.setAdapter(adapterReport);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        Button BackB = (Button) view.findViewById(R.id.BackB);
        BackB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadSearchingSlot();
            }
        });

        Button chooseSlot = (Button) view.findViewById(R.id.chooseSlotB);
        chooseSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.chooseParking(Integer.parseInt(NumSlot));
            }
        });



        return view;
    }
}