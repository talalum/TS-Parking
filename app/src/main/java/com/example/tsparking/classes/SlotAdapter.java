package com.example.tsparking.classes;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.print.PrintAttributes;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.tsparking.R;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;
        import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder>{

    private static final String TAG = "mYtrySAPIR";
    private Context mCtx;
    private List<Slot> listSlot;

    public SlotAdapter(Context mCtx, List<Slot> listSlot) {
        this.mCtx = mCtx;
        this.listSlot = listSlot;
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_slot_layout,null);
        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        Slot slot=listSlot.get(position);

        if (slot.isDisable() == true)
            holder.IsDisableTPr.setText("V");
        else
            holder.IsDisableTPr.setText("X");

        if (slot.isIndoor() == true)
            holder.IsIndoorTPr.setText("V");
        else
            holder.IsIndoorTPr.setText("X");

        if (slot.isFree() == true)
            holder.IsFreeTPr.setText("V");
        else
            holder.IsFreeTPr.setText("X");

        holder.ParkingNumTPr.setText(String.valueOf(slot.getParkingNum()));
//        final String Useruid = getRef(position).getKey();
        holder.v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MainActivity mainActivity=new MainActivity();
                Slot slot=listSlot.get(position);
                int numParkingChoose=slot.getSlotNum();
                mainActivity.chooseParking(numParkingChoose);
                Button b= view.findViewById(R.id.choose);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     //   Log.i(TAG,String.valueOf(position));
                        Slot slot=listSlot.get(position);
//                        int num=slot.getSlotNum();
//                         Log.i(TAG,String.valueOf(slot.getSlotNum()));
//                         listUsers listuser=new listUsers();
//                         List<User> list=listuser.getList();
//                        SharedPreferences sharedPreferences=mainActivity.getSharedPreferences("myPref", Context.MODE_PRIVATE);
//                        String email=sharedPreferences.getString("email",null);
//                         for(int i=0;i<list.size();i++) {
//                             if (email.equals(list.get(i).getEmail())) {
//                                 list.get(i).setSlotNum(num);
//                                 Log.i(TAG,"succes1");
//
//                             }
//                         }
//                        Query query= FirebaseDatabase.getInstance().getReference("Users").orderByChild("Email");
//                         String uid=null;
//                        query.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
////                                    if(email.equals(adSnapshot.getValue(User.class).getEmail()))
////                                      //  uid=adSnapshot.getValue(User.class)
////
////                                }
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//                            }
//                        });
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSlot.size();
    }

    class SlotViewHolder extends RecyclerView.ViewHolder{

         TextView IsDisableTPr,IsIndoorTPr,IsFreeTPr, ParkingNumTPr;
         View v;
        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            IsDisableTPr=itemView.findViewById(R.id.IsDisableTPrRecycle);
            IsIndoorTPr=itemView.findViewById(R.id.IsIndoorTPrRecycle);
            IsFreeTPr=itemView.findViewById(R.id.IsFreeTPrRecycle);
            ParkingNumTPr=itemView.findViewById(R.id.ParkingNumTPrRecycle);
            v=itemView;
        }
    }
}
