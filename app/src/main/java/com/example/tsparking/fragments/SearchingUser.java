package com.example.tsparking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;
import com.example.tsparking.classes.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchingUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchingUser extends Fragment {

    private static final String TAG = "MyActivity";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchingUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchingUser.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchingUser newInstance(String param1, String param2) {
        SearchingUser fragment = new SearchingUser();
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
        View view = inflater.inflate(R.layout.fragment_searching_user, container, false);



        Button BackB = (Button) view.findViewById(R.id.BackB);
        BackB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.BackToProfile();
            }
        });



        Button searching = (Button) view.findViewById(R.id.searchB);
        searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                TextView firtsname=(TextView)view.findViewById(R.id.editTextTextPersonName);
                String firstnames=firtsname.getText().toString();
                mainActivity.getMySingeltonM().clear();

                Query query= FirebaseDatabase.getInstance().getReference("Users").orderByChild("FirstName");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       // mainActivity.clearUserList();
                        for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                            if(adSnapshot.getValue(User.class).getFirstName().equals(firstnames))
                            mainActivity.getMySingeltonM().addUser(adSnapshot.getValue(User.class));
                        }
                        mainActivity.searchingUserFunc();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return view;

    }
}