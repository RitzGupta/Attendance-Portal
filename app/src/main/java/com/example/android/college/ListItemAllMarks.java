package com.example.android.college;

public class ListItemAllMarks {
    private String obtainMarks;
    private String totalMarks;
    private String subjectName;

    public ListItemAllMarks(String obtainMarks, String totalMarks, String subjectName) {
        this.obtainMarks = obtainMarks;
        this.totalMarks = totalMarks;
        this.subjectName = subjectName;
    }

    public String getObtainMarks() {
        return obtainMarks;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
