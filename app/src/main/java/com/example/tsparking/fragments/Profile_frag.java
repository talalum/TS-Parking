package com.example.tsparking.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_frag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView t1;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static String firstName;
    private String mParam2;

    public Profile_frag() {
        // Required empty public constructor
    }

    public Profile_frag(String firstName) {
        // Required empty public constructor
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
    public static Profile_frag newInstance(String param1, String param2) {
        Profile_frag fragment = new Profile_frag(firstName);
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
            firstName = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_frag, container, false);
        t1=(TextView)view.findViewById(R.id.helloTP);
        t1.setText(firstName);
        Button search_user_button = (Button) view.findViewById(R.id.SearchingUserB);
        search_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadSearchingUser();
            }
        });

        Button SearchingSlotB = (Button) view.findViewById(R.id.SearchingSlotB);
        SearchingSlotB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadSearchingSlot();
            }
        });
        return view;
    }

}