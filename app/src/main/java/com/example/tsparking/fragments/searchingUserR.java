package com.example.tsparking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;
import com.example.tsparking.classes.UserAdapter;
import com.example.tsparking.classes.listUsers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchingUserR#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchingUserR extends Fragment {

    RecyclerView recyclerViewUser;
    UserAdapter adapterUser;

    private static final String TAG = "MyActivityccc";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public searchingUserR() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchingUserR.
     */
    // TODO: Rename and change types and number of parameters
    public static searchingUserR newInstance(String param1, String param2) {
        searchingUserR fragment = new searchingUserR();
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
        View view = inflater.inflate(R.layout.fragment_searching_user_r, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();

        recyclerViewUser=(RecyclerView)view.findViewById(R.id.recycleViewUser);
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listUsers listUser=mainActivity.getMySingeltonM();

//       User u=new User("d@gmail.com","sapir","ir");
//       mainActivity.addUserList(u);
//       Log.i(TAG, "vvv" + list.get(0));



        adapterUser=new UserAdapter(view.getContext(),listUser.getList());
        recyclerViewUser.setAdapter(adapterUser);


        return view;
    }

    }
