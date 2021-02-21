package com.example.tsparking.classes;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.tsparking.R;

        import java.util.List;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder>{

    private Context mCtx;
    private List<Slot> listSlot;
    private ObserverToReport newR1;
    private static final String TAG = "MyActivity";


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
         TextView IsDisableTPr,IsIndoorTPr,IsFreeTPr, ParkingNumTPr;
         View v;
        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            IsDisableTPr=itemView.findViewById(R.id.IsDisableTPrRecycle);
            IsIndoorTPr=itemView.findViewById(R.id.IsIndoorTPrRecycle);
            IsFreeTPr=itemView.findViewById(R.id.IsFreeTPrRecycle);
            ParkingNumTPr=itemView.findViewById(R.id.ParkingNumTPrRecycle);
            v = itemView;
        }
    }
}
