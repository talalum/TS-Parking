package com.example.tsparking.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tsparking.R;
import com.example.tsparking.classes.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login_frag extends Fragment {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Login_frag newInstance(String param1, String param2) {
        Login_frag fragment = new Login_frag();
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
        View view = inflater.inflate(R.layout.fragment_login_frag, container, false);
        Button reg_button = (Button) view.findViewById(R.id.RegisterB);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.LoadPageReg();
            }
        });

        Button log_button = (Button) view.findViewById(R.id.LoginB);
        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.SignInFunc(view);
            }
        });


        /*Button registerParking = (Button) view.findViewById(R.id.RegisterParkingB);
        registerParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.GoToRegisterParking();
            }
        });

        Button registerSlot = (Button) view.findViewById(R.id.RegisterSlotB);
        registerSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.GoToRegisterSlot();
            }
        });*/

        editTextEmail = view.findViewById(R.id.EmailText);
        editTextPassword = view.findViewById(R.id.PasswordText);
        buttonLogin = view.findViewById(R.id.LoginB);

        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences=mainActivity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editTextEmail.setText(sharedPreferences.getString("email",null));
        editTextPassword.setText(sharedPreferences.getString("password",null));

        editTextEmail.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        return view;
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = editTextEmail.getText().toString().trim();
            String passwordInput = editTextPassword.getText().toString().trim();

            buttonLogin.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }


    };


}