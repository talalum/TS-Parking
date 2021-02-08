package com.example.tsparking.fragments;

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
 * Use the {@link Register_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register_frag extends Fragment {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextFirstname;
    private EditText editTextLastname;
    private Button buttonRegister;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Register_frag newInstance(String param1, String param2) {
        Register_frag fragment = new Register_frag();
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
        View view = inflater.inflate(R.layout.fragment_register_frag, container, false);

        Button register_button = (Button) view.findViewById(R.id.RegisterButtonPARKING);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.SignUpFunc();
            }
        });

        Button back_to_login = (Button) view.findViewById(R.id.BackB);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.BackToLogin();
            }
        });

        editTextEmail = view.findViewById(R.id.EmailRText);
        editTextPassword = view.findViewById(R.id.PasswordRText);
        editTextFirstname = view.findViewById(R.id.FirstNameText);
        editTextLastname = view.findViewById(R.id.LastNameText);
        buttonRegister = view.findViewById(R.id.RegisterButtonPARKING);

        editTextEmail.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        editTextFirstname.addTextChangedListener(loginTextWatcher);
        editTextLastname.addTextChangedListener(loginTextWatcher);
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
            String firstNameInput = editTextFirstname.getText().toString();
            String lastNameInput = editTextLastname.getText().toString();

            buttonRegister.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !firstNameInput.isEmpty() && !lastNameInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}