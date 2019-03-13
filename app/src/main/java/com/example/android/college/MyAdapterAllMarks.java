package com.example.android.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapterAllMarks extends RecyclerView.Adapter<MyAdapterAllMarks.ViewHolder> {
    private List<ListItemAllMarks>listItemAllMarks;
    private Context context;

    public MyAdapterAllMarks(List<ListItemAllMarks> listItemAllMarks, Context context) {
        this.listItemAllMarks = listItemAllMarks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_marks,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItemAllMarks listItemAllMark = listItemAllMarks.get(position);
        holder.textViewObtainMarks.setText(listItemAllMark.getObtainMarks());
        holder.textViewTotalMarks.setText(listItemAllMark.getTotalMarks());
        holder.textViewSubjectName.setText(listItemAllMark.getSubjectName());


    }

    @Override
    public int getItemCount() {
        return listItemAllMarks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewObtainMarks,textViewTotalMarks,textViewSubjectName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewObtainMarks = (TextView)itemView.findViewById(R.id.tvObtainMarks);
            textViewTotalMarks=(TextView)itemView.findViewById(R.id.tvTotalMarks);
            textViewSubjectName = (TextView)itemView.findViewById(R.id.textViewSubjectName);

        }
    }
}
