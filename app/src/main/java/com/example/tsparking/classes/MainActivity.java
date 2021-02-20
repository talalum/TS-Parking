package com.example.tsparking.classes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private static String firstName=null;
    private static String lastName=null;
    private static String Email=null;
    private FirebaseAuth mAuth;
    private static String UID=null;
    private static int oldNumSlot;
    private static int slotNum;



    private static listUsers listUsers ;
    private static listSlot ListSlot ;
    private static listParking listparking;

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

    private static int parkingNum=0;
    private static int numParkingMax=1;
    private static int numSlotMax=1;
    private static Parking ParkingByNum;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            mAuth = FirebaseAuth.getInstance();
            setContentView(R.layout.activity_main);
            fragmentManager = getSupportFragmentManager();
            Login_frag login_frag = new Login_frag();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentCont, login_frag).commit();
            ParkingByNum=new Parking();

            UpdateLists();
          DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parking");
          ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot parkings : snapshot.getChildren()) {
                    if(parkings.getValue(Parking.class).getParkingNum()>=numParkingMax) {
                        numParkingMax=parkings.getValue(Parking.class).getParkingNum();
                        numParkingMax++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

        DatabaseReference ref_2 = FirebaseDatabase.getInstance().getReference("Slot");
        ref_2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot slots : snapshot.getChildren()) {
                    if(slots.getValue(Slot.class).getSlotNum()>=numSlotMax) {
                        numSlotMax=slots.getValue(Slot.class).getSlotNum();
                        numSlotMax++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public static listUsers getMySingeltonM() { // create singelton
        return listUsers.getMySingelton();
    }
    public static listParking getMySingeltonParking() { // create singelton
        return listparking.getMySingelton();
    }

    public static listSlot getMySingeltonMSlot() { return ListSlot.getMySingelton();
    }

    public void LoadPageReg() {
        fragmentTransaction = fragmentManager.beginTransaction();
        Register_frag r1=new Register_frag();
        fragmentTransaction.replace(R.id.fragmentCont, r1).addToBackStack(null).commit();
    }


    public void UpdateLists()
    {
        listparking=listParking.getMySingelton();
        List<Parking> listp=listparking.getList();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parking");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot parkings : snapshot.getChildren()) {
                    if(!listp.contains(parkings.getValue(Parking.class))) {
                        listp.add(parkings.getValue(Parking.class));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        listUsers=listUsers.getMySingelton();
        List<User> listu=listUsers.getList();
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Users");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot users : snapshot.getChildren()) {
                    if(!listu.contains(users.getValue(User.class))) {
                        listu.add(users.getValue(User.class));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        ListSlot=listSlot.getMySingelton();
        List<Slot> lists=ListSlot.getList();
        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Slot");
        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot slots : snapshot.getChildren()) {
                    if(!lists.contains(slots.getValue(Slot.class))) {
                        lists.add(slots.getValue(Slot.class));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

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
                    UID=user.getUid();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Users").child(uid);

                    User u = new User(email, first_name, last_name);
                    firstName=first_name;
                    lastName=last_name;
                    Email=email;
                    slotNum=0;
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
                        UID=user.getUid();
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
                                slotNum=value.getSlotNum();
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
        Profile_frag r1= Profile_frag.newInstance(firstName,lastName,Email, String.valueOf(slotNum));
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
        getMaxParkingNum();
        parking.setParkingNum(numParkingMax);
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
        getMaxSlotNum();
        slot = new Slot(a,b,c,Integer.parseInt(ParkingNum),numSlotMax);
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

    public void getMaxParkingNum() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Parking");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot parkings : snapshot.getChildren()) {
                    if(parkings.getValue(Parking.class).getParkingNum()>=numParkingMax) {
                        numParkingMax=parkings.getValue(Parking.class).getParkingNum();
                        numParkingMax++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }
    public void getMaxSlotNum() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Slot");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot slots : snapshot.getChildren()) {
                    if(slots.getValue(Slot.class).getSlotNum()>=numSlotMax) {
                        numSlotMax=slots.getValue(Slot.class).getSlotNum();
                        numSlotMax++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

    }

    public String getAreaByNum(int num) {
        for (int i = 0; i < listparking.getMySingelton().getList().size(); i++)
            if (listparking.getMySingelton().getList().get(i).getParkingNum() == num)
                return listparking.getMySingelton().getList().get(i).getArea();
            return null;

    }

    public Parking getParkingByNum(int num) {
        for (int i = 0; i < listparking.getMySingelton().getList().size(); i++)
            if (listparking.getMySingelton().getList().get(i).getParkingNum() == num)
                return listparking.getMySingelton().getList().get(i);
        return null;

    }

    public void chooseParking(int numSlot) {
        slotNum=numSlot;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();

        Query query= FirebaseDatabase.getInstance().getReference("Users").orderByChild("FirstName");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // mainActivity.clearUserList();
                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                    if(adSnapshot.getValue(User.class).getEmail().equals(Email)) {
                        oldNumSlot = adSnapshot.getValue(User.class).getSlotNum();
                        mDatabaseRef.child("Users").child(UID).child("slotNum").setValue(numSlot);
                    }

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Slot");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot slots : snapshot.getChildren()) {
                    if(slots.getValue(Slot.class).getSlotNum()==numSlot) {
                        mDatabaseRef.child("Slot").child(slots.getKey()).child("free").setValue(false);
                    }
                    if(slots.getValue(Slot.class).getSlotNum()==oldNumSlot)
                        mDatabaseRef.child("Slot").child(slots.getKey()).child("free").setValue(true);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        }

    public void ReleaseSlot() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = database.getReference();

        Query query= FirebaseDatabase.getInstance().getReference("Users").orderByChild("FirstName");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // mainActivity.clearUserList();
                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                    if(adSnapshot.getValue(User.class).getEmail().equals(Email)) {
                        oldNumSlot = adSnapshot.getValue(User.class).getSlotNum();
                        mDatabaseRef.child("Users").child(UID).child("slotNum").setValue(0);
                    }

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Slot");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot slots : snapshot.getChildren()) {
                    if(slots.getValue(Slot.class).getSlotNum()==oldNumSlot)
                        mDatabaseRef.child("Slot").child(slots.getKey()).child("free").setValue(true);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    public Slot getSlotByNum(int num) {
        for (int i = 0; i < listSlot.getMySingelton().getList().size(); i++)
            if (listSlot.getMySingelton().getList().get(i).getSlotNum() == num)
                return listSlot.getMySingelton().getList().get(i);
        return null;

    }

}