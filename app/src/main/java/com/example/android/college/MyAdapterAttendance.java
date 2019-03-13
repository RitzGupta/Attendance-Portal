package com.example.android.college;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapterAttendance extends RecyclerView.Adapter<MyAdapterAttendance.ViewHolder> {

    private List<ListItemAttendance> listItemAttendances;
    private Context context;

    public MyAdapterAttendance(List<ListItemAttendance> listItemAttendances, Context context) {
        this.listItemAttendances = listItemAttendances;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_attendance, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItemAttendance listItemAttendance = listItemAttendances.get(position);

        holder.Attendance.setText(listItemAttendance.getAttendance());
        holder.SubjectName.setText(listItemAttendance.getSubject());

    }

    @Override
    public int getItemCount() {
        return listItemAttendances.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView SubjectName;
        public TextView Attendance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            SubjectName = (TextView)itemView.findViewById(R.id.tvSubjectName);
            Attendance = (TextView)itemView.findViewById(R.id.tvAttendance);
        }
    }
}
