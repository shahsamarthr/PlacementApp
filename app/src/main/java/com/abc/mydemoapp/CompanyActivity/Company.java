package com.abc.mydemoapp.CompanyActivity;

public class Company {
    public String id,companyname,address,tan,emailaddress,password,role;

    public Company()
    {

    }

    public Company(String id,String companyname, String address, String tan, String emailaddress, String password,String role) {
        this.companyname = companyname;
        this.address = address;
        this.tan = tan;
        this.emailaddress = emailaddress;
        this.password = password;
        this.role=role;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getAddress() {
        return address;
    }

    public String getTan() {
        return tan;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getEmailaddresscompany() {
        return emailaddress;
    }

    public String getPassword() {
        return password;
    }
}
