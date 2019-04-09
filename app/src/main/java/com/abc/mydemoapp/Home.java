package com.abc.mydemoapp;

import android.app.Application;
import android.content.Intent;

import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//This class is used to check whether the user is logged in or not
//If user is logged in then after clearing the app also user will not
//have to login again.User will be directed back to the ProfileActivity.

public class Home extends Application {

    private String emailaddress;

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = firebaseAuth.getCurrentUser();

        if(currentuser!=null&&currentuser.isEmailVerified())//To check whether the user is logged in or not and whether user has verified email or not
        {
            startActivity(new Intent(Home.this,ProfileActivity.class));
        }
    }
}
