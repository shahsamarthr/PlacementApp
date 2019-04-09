package com.abc.mydemoapp.StudentsActivity;

public class Student {
    String id,emailaddress,branchname,password,name,role,rollno;
    float cpi;
    boolean selected;
//This is Student class empty constructor
    public  Student()
    {

    }

    public Student(String id,String emailaddress, String branchname, String password, String name, float cpi,String role,String rollno) {
        this.emailaddress = emailaddress;
        this.branchname = branchname;
        this.password = password;
        this.name = name;
        this.cpi = cpi;
        this.role = role;
        this.id = id;
        this.rollno=rollno;
        selected = false;

    }

    public String getRollno() {
        return rollno;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCpi(float cpi) {
        this.cpi = cpi;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getBranchname() {
        return branchname;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }

    public float getCpi() {
        return cpi;
    }
}
