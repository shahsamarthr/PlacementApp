package com.abc.mydemoapp.CompanyActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Job {
    String jobtitle,jobdescription,jobRequirements,id,cname,compemail;
    float cpi,salaryRange;
    String date;




    public Job()

    {

    }

    public Job(String jobtitle, String jobdescription, String jobRequirements, float salaryRange, float cpi,String id,String cname,String date,String compemail) {
        this.jobtitle = jobtitle;
        this.jobdescription = jobdescription;
        this.jobRequirements = jobRequirements;
        this.date = date;
        this.salaryRange = salaryRange;
        this.cpi = cpi;
        this.cname = cname;
        this.id = id;
        this.compemail = compemail;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCompemail() {
        return compemail;
    }

    public void setCompemail(String compemail) {
        this.compemail = compemail;
    }

    public float getCpi() {
        return cpi;
    }

    public void setCpi(float cpi) {
        this.cpi = cpi;
    }

    public float getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(float salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
