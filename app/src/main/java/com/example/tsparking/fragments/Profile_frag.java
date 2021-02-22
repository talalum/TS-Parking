package com.example.tsparking.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;
import com.example.tsparking.classes.Parking;
import com.example.tsparking.classes.Slot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView firstNameHeT;
    TextView firstNameT;
    TextView lastNameT;
    TextView emailT;
    TextView slotNumT;
    TextView AreaT;
    TextView freeT;
    TextView disabledT;

    private static final String TAG = "MyActivity";



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";



    // TODO: Rename and change types of parameters
    private static String firstName;
    private static String lastName;
    private static String Email;
    private static int slotNum;


    public Profile_frag() {
        // Required empty public constructor
    }


    public Profile_frag(String firstName, String lastName, String email, int slotNum) {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_frag newInstance(String param1, String param2,String param3,String param4) {
        Profile_frag fragment = new Profile_frag(firstName,lastName,Email,slotNum);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstName = getArguments().getString(ARG_PARAM1);
            lastName = getArguments().getString(ARG_PARAM2);
            Email = getArguments().getString(ARG_PARAM3);
            slotNum = Integer.parseInt(getArguments().getString(ARG_PARAM4));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_frag, container, false);
        firstNameHeT=(TextView)view.findViewById(R.id.helloTP);
        firstNameHeT.setText(firstName);
        firstNameT=(TextView)view.findViewById(R.id.FirstNameTPr);
        firstNameT.setText(firstName);
        lastNameT=(TextView)view.findViewById(R.id.LastNameTPr);
        lastNameT.setText(lastName);
        emailT=(TextView)view.findViewById(R.id.EmailTPr);
        emailT.setText(Email);

        MainActivity mainActivity = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(slotNum!=0) {
            LinearLayout saved=view.findViewById(R.id.slotSaved);
            saved.setVisibility(0);
            Slot slot = mainActivity.getSlotByNum(slotNum);
            Parking parking = mainActivity.getParkingByNum(slot.getParkingNum());

            AreaT = (TextView) view.findViewById(R.id.areaSlotSaved);
            AreaT.setText(parking.getArea());

            freeT= (TextView) view.findViewById(R.id.freeSlotSaved);
            freeT.setText(String.valueOf(parking.getPrice()));

            disabledT= (TextView) view.findViewById(R.id.disableSlotSaved);
            if(slot.isDisable()==true)
                disabledT.setText("X");

        }

        Button SearchingSlotB = (Button) view.findViewById(R.id.SearchingSlotB);
        SearchingSlotB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadSearchingSlot();
//                mainActivity.GoToAddReportPage();

            }
        });
        Button logout = (Button) view.findViewById(R.id.LogOutB);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LogOutFunc();
            }
        });

        Button release = (Button) view.findViewById(R.id.releaseSlot);
        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.ReleaseSlot();
            }
        });
        return view;
    }

}