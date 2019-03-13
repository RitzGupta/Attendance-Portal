package com.example.android.college;

public class DaysContentTT {

    private String subjectName;
    private String subjectCode;
    private String teacherName;
    public DaysContentTT(){}

    public DaysContentTT(String subjectName, String subjectCode, String teacherName) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
