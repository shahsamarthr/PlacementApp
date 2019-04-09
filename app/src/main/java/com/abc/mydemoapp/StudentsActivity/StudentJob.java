package com.abc.mydemoapp.StudentsActivity;

public class StudentJob {
    String jobid,studentid,studentjobid;
    //String rollno;
    String studentemail,companyemail;
    String date,jobtitle;

    public StudentJob(){

    }

    public StudentJob(String jobid, String studentid, String studentemail, String companyemail, String date, String jobtitle,String studentjobid) {
        this.jobid = jobid;
        this.studentid = studentid;
        this.studentemail = studentemail;
        this.companyemail = companyemail;
        this.date = date;
        this.jobtitle = jobtitle;
        this.studentjobid = studentjobid;
    }

    public String getStudentjobid() {
        return studentjobid;
    }

    public void setStudentjobid(String studentjobid) {
        this.studentjobid = studentjobid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }
//    public String getRollno() {
//        return rollno;
//    }
//
//    public void setRollno(String rollno) {
//        this.rollno = rollno;
//    }

}
