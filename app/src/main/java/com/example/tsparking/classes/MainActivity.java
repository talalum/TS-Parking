package com.example.tsparking.classes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tsparking.R;
import com.example.tsparking.fragments.AddParking;
import com.example.tsparking.fragments.AddSlot;
import com.example.tsparking.fragments.Login_frag;
import com.example.tsparking.fragments.ManagerPage;
import com.example.tsparking.fragments.Profile_frag;
import com.example.tsparking.fragments.Register_frag;
import com.example.tsparking.fragments.SearchingSlot;
import com.example.tsparking.fragments.SearchingUser;
import com.example.tsparking.fragments.searchingUserR;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private static String firstName=null;
    private static String lastName=null;
    private static String Email=null;
    private FirebaseAuth mAuth;
    private static listUsers userList ;
    private static listSlot slotList ;

    EditText Tprice;
    EditText Tarea;
    EditText Taddress;
    EditText Tpaved;
    DatabaseReference refB;
    Parking parking;

    EditText Tdisable;
    EditText Tindoor;
    EditText Tfree;
    EditText TparkingNum;
    DatabaseReference refBS;
    Slot slot;
//    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            mAuth = FirebaseAuth.getInstance();
            setContentView(R.layout.activity_main);
            fragmentManager = getSupportFragmentManager();
            Login_frag login_frag = new Login_frag();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentCont, login_frag).commit();
//            spinner = findViewById(R.id.spinner1);
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout)
    }

    public static listUsers getMySingeltonM() { // create singelton
        return userList.getMySingelton();
    }

    public static listSlot getMySingeltonMSlot() { // create singelton
        return slotList.getMySingelton();
    }

    public void LoadPageReg() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Register_frag r1=new Register_frag();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void SignUpFunc() {
        EditText emailText = findViewById(R.id.EmailRText);
        String email = emailText.getText().toString();

        EditText passwordText = findViewById(R.id.PasswordRText);
        String password = passwordText.getText().toString();

        EditText firstNameText = findViewById(R.id.FirstNameText);
        String first_name = firstNameText.getText().toString();

        EditText lastNameText = findViewById(R.id.LastNameText);
        String last_name = lastNameText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(MainActivity.this, "Register succeeded.",
                            Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Users").child(uid);

                    User u = new User(email, first_name, last_name);
                    firstName=first_name;
                    lastName=last_name;
                    Email=email;
                    myRef.setValue(u);

                    SharedPreferences sharedPreferences=getSharedPreferences("myPref",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();

                    LoadPageProf();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "Register failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SignInFunc(View view) {
        EditText emailText = findViewById(R.id.EmailText);
        String email = emailText.getText().toString();

        EditText passwordText = findViewById(R.id.PasswordText);
        String password = passwordText.getText().toString();
        if((email.equals("talalum@gmail.com") && password.equals("123456")) ||
                (email.equals("sapirani@gmail.com") && password.equals("123456")) ||
                (email.equals("effi.profus@gmail.com") && password.equals("123456")))
            LoadManagerPage();
        else {
            mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(MainActivity.this, "Login succeeded",
                                Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Users").child(uid);

                        SharedPreferences sharedPreferences=getSharedPreferences("myPref",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();

                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                User value = dataSnapshot.getValue(User.class);
                                firstName = value.getFirstName();
                                lastName = value.getLastName();
                                Email = value.getEmail();

                                LoadPageProf();
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                            }
                        });
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Login failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void LoadPageProf() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Profile_frag r1= Profile_frag.newInstance(firstName,lastName,Email);
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }
    public void LoadManagerPage() {
        fragmentTransaction = fragmentManager.beginTransaction();
        ManagerPage r1= new ManagerPage();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void LoadSearchingUser() {
        fragmentTransaction = fragmentManager.beginTransaction();
        SearchingUser r1=new SearchingUser();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void LoadSearchingSlot() {
        fragmentTransaction = fragmentManager.beginTransaction();
        SearchingSlot r1=new SearchingSlot();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void BackToProfile() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Profile_frag r1=new Profile_frag();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }
    public void BackToLogin() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Login_frag r1=new Login_frag();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void LogOutFunc() {
        firstName=null;
        lastName=null;
        Email=null;
        SharedPreferences sharedPreferences=getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email", null);
        editor.putString("password", null);
        editor.apply();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentCont, new Login_frag()).addToBackStack(null).commit();
    }

    public void SaveParking() {
        Tprice = (EditText) findViewById(R.id.priceText);
        Tarea = (EditText) findViewById(R.id.areaText);
        Taddress = (EditText) findViewById(R.id.addressText);
        Tpaved = (EditText) findViewById(R.id.pavedText);

        parking = new Parking();
        refB = FirebaseDatabase.getInstance().getReference().child("Parking");

        double price = Double.parseDouble(Tprice.getText().toString());
        String area = Tarea.getText().toString();
        String address = Taddress.getText().toString();
        String paved = Tpaved.getText().toString();

        Boolean b = false;
        if (paved.equals("1"))
            b = true;

        parking.setPrice(price);
        parking.setArea(area);
        parking.setAddress(address);
        parking.setPaved(b);

        refB.push().setValue(parking);
        Toast.makeText(MainActivity.this, "data insert successfully", Toast.LENGTH_LONG).show();
        GoToRegisterParking();
    }

    public void GoToRegisterParking() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddParking r1=new AddParking();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

    public void SaveSlot() {

        Tdisable = (EditText) findViewById(R.id.DisableText);
        Tindoor = (EditText) findViewById(R.id.IndoorText);
        Tfree = (EditText) findViewById(R.id.FreeText);
        TparkingNum = (EditText) findViewById(R.id.ParkingNumText);

        slot = new Slot();
        refBS = FirebaseDatabase.getInstance().getReference().child("Slot");

        String disable = Tdisable.getText().toString();
        String indoor = Tindoor.getText().toString();
        String free = Tfree.getText().toString();
        String ParkingNum = TparkingNum.getText().toString();

        Boolean a = false;
        if (disable.equals("1"))
            a = true;

        Boolean b = false;
        if (indoor.equals("1"))
            b = true;

        Boolean c = false;
        if (free.equals("1"))
            c = true;

        slot.setDisable(a);
        slot.setIndoor(b);
        slot.setFree(c);
        slot.setParkingNum(Integer.parseInt(ParkingNum));

        refBS.push().setValue(slot);
        Toast.makeText(MainActivity.this, "data insert successfully", Toast.LENGTH_LONG).show();
        GoToRegisterSlot();
    }

    public void searchingUserFunc() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentCont, new searchingUserR()).addToBackStack(null).commit();
    }

    public void GoToRegisterSlot() {
        fragmentTransaction = fragmentManager.beginTransaction();
        AddSlot r1=new AddSlot();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }

}