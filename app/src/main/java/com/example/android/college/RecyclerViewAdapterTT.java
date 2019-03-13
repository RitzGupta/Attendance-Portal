package com.example.android.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterTT extends RecyclerView.Adapter<RecyclerViewAdapterTT.MyViewHolder> {
    Context mContext;
    List<DaysContentTT> mData;

    public RecyclerViewAdapterTT(Context mContext, List<DaysContentTT> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterTT.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item_day_tt, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterTT.MyViewHolder holder, int position) {
        holder.textViewTeacherName.setText(mData.get(position).getTeacherName());
        holder.textViewSubjectName.setText(mData.get(position).getSubjectName());
        holder.textViewSubjectCode.setText(mData.get(position).getSubjectCode());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSubjectName;
        public TextView textViewSubjectCode;
        public TextView textViewTeacherName;
        public LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubjectName = (TextView)itemView.findViewById(R.id.textViewSubjectName);
            textViewSubjectCode = (TextView)itemView.findViewById(R.id.textViewSubjectCode);
            textViewTeacherName = (TextView)itemView.findViewById(R.id.textViewTeacher_Name);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);

        }
    }
}
