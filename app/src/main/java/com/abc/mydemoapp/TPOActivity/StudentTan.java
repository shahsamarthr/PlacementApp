package com.abc.mydemoapp.TPOActivity;

public class StudentTan {
    String studentrollno,studentemail,studentname,branchname,pass;
    float studentcpi;

    public StudentTan()
    {

    }

    public StudentTan(String studentrollno, String studentemail, String studentname, String branchname, String pass, float studentcpi) {
        this.studentrollno = studentrollno;
        this.studentemail = studentemail;
        this.studentname = studentname;
        this.branchname = branchname;
        this.pass = pass;
        this.studentcpi = studentcpi;
    }

    public String getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(String studentrollno) {
        this.studentrollno = studentrollno;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public float getStudentcpi() {
        return studentcpi;
    }

    public void setStudentcpi(float studentcpi) {
        this.studentcpi = studentcpi;
    }
}
