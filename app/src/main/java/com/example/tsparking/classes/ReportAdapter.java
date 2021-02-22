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

public class ReportAdapter  extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{

    private Context mCtx;
    private List<Report> listReport;

    public ReportAdapter(Context mCtx, List<Report> listReport) {
        this.mCtx = mCtx;
        this.listReport = listReport;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_report_layout,null);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report =listReport.get(position);
        holder.TheReport.setText(report.getTheReport());
        holder.WriteBy.setText(report.getWriteBy());
        holder.DateOfCreate.setText(report.getDateOfCreate());

    }

    @Override
    public int getItemCount() {
        return listReport.size();
    }

    class ReportViewHolder extends RecyclerView.ViewHolder{

        TextView TheReport, WriteBy, DateOfCreate;
        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            TheReport=itemView.findViewById(R.id.ReportTPrRecycle);
            WriteBy=itemView.findViewById(R.id.UserEmailTPrRecycle);
            DateOfCreate=itemView.findViewById(R.id.DateOfCreateTPrRecycle);
        }
    }
}