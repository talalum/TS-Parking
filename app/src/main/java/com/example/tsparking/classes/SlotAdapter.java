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

    private Context mCtx;
    private List<Slot> listSlot;
    private ObserverToReport newR1;


    public SlotAdapter(Context mCtx, List<Slot> listSlot, ObserverToReport newR) {
        this.mCtx = mCtx;
        this.listSlot = listSlot;
        this.newR1=newR;
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
        MainActivity mainActivity=new MainActivity();
        Parking parking=mainActivity.getParkingByNum(slot.getParkingNum());
        if (parking.isPaved() == true)
            holder.IspavedTPr.setText("V");
        else
            holder.IspavedTPr.setText("X");

        holder.adressTPr.setText(String.valueOf(parking.getAddress()));
        holder.priceTPr.setText(String.valueOf(parking.getPrice()));
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Slot slot=listSlot.get(position);
                String numSlotChoose=String.valueOf(slot.getSlotNum());
                newR1.show(numSlotChoose);
              


            }
        });
    }

    @Override
    public int getItemCount() {
        return listSlot.size();
    }

    class SlotViewHolder extends RecyclerView.ViewHolder{
         TextView IspavedTPr,priceTPr, adressTPr;
         View v;
        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            IspavedTPr=itemView.findViewById(R.id.pavedTPrRecycle);
            priceTPr=itemView.findViewById(R.id.priceTPrRecycle);
            adressTPr=itemView.findViewById(R.id.adressTPrRecycle);
            v=itemView;
        }
    }
}
