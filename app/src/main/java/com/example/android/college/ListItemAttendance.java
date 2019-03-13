package com.example.android.college;

public class ListItemAttendance {
    private String Subject;
    private String Attendance;


    public ListItemAttendance(String subject, String attendance) {
        Subject = subject;
        Attendance = attendance;
    }

    public String getSubject() {
        return Subject;
    }

    public String getAttendance() {
        return Attendance;
    }
}
