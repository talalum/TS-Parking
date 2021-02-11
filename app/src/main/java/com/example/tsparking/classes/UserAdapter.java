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

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private Context mCtx;
    private List<User> listUser;

    public UserAdapter(Context mCtx, List<User> listUser) {
        this.mCtx = mCtx;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_user_layout,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=listUser.get(position);
        holder.FirstNameTPr.setText(user.getFirstName());
        holder.LastNameTPr.setText(user.getLastName());
        holder.EmailTPr.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView FirstNameTPr,LastNameTPr,EmailTPr;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            FirstNameTPr=itemView.findViewById(R.id.FirstNameTPrRecycle);
            LastNameTPr=itemView.findViewById(R.id.LastNameTPrRecycle);
            EmailTPr=itemView.findViewById(R.id.EmailTPrRecycle);

        }
    }
}
