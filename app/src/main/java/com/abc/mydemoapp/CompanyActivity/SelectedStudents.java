package com.abc.mydemoapp.CompanyActivity;

public class SelectedStudents {

    String studentid,jobid,jobtitle;//add studentrollno
    String compemail,compname,studentjobid,selectedstudentsid,studentemail;

    public SelectedStudents(){

    }

    public SelectedStudents(String selectedstudentsid ,String studentid, String jobid, String jobtitle, String compemail, String compname, String studentjobid,String studentemail) {
        this.selectedstudentsid = selectedstudentsid;
        this.studentid = studentid;
        this.jobid = jobid;
        this.jobtitle = jobtitle;
        //this.studentrollno = studentrollno;
        this.compemail = compemail;
        this.compname = compname;
        this.studentjobid = studentjobid;
        this.studentemail = studentemail;
    }


    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getSelectedstudentsid() {
        return selectedstudentsid;
    }

    public void setSelectedstudentsid(String selectedstudentsid) {
        this.selectedstudentsid = selectedstudentsid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }



    public String getCompemail() {
        return compemail;
    }

    public void setCompemail(String compemail) {
        this.compemail = compemail;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getStudentjobid() {
        return studentjobid;
    }

    public void setStudentjobid(String studentjobid) {
        this.studentjobid = studentjobid;
    }
}
