package com.abc.mydemoapp.TPOActivity;

public class Tan {
    String companyname,tan,companyemailadress,companyaddress,pass;

    public Tan()
    {

    }

    public Tan(String companyname, String tan, String companyemailadress, String companyaddress, String pass) {
        this.companyname = companyname;
        this.tan = tan;
        this.companyemailadress = companyemailadress;
        this.companyaddress = companyaddress;
        this.pass = pass;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getTan() {
        return tan;
    }

    public void setTan(String tan) {
        this.tan = tan;
    }

    public String getCompanyemailadress() {
        return companyemailadress;
    }

    public void setCompanyemailadress(String companyemailadress) {
        this.companyemailadress = companyemailadress;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
