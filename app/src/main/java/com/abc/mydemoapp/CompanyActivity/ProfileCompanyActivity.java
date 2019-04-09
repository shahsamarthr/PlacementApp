package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.HomeFragment;
import com.abc.mydemoapp.StudentsActivity.LogoutFragment;
import com.abc.mydemoapp.StudentsActivity.PendingInterviewsFragment;
import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.abc.mydemoapp.StudentsActivity.ProfileFragment;
import com.abc.mydemoapp.StudentsActivity.SendQueryFragment;
import com.abc.mydemoapp.StudentsActivity.ViewjobsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileCompanyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_company);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_company_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open
                , R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.abc.mydemoapp.CompanyActivity.HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.abc.mydemoapp.CompanyActivity.HomeFragment()).commit();
                break;

            case R.id.nav_myprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.abc.mydemoapp.CompanyActivity.ProfileFragment()).commit();
                break;

            case R.id.nav_studentlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ViewStudentsFragment()).commit();
                break;

            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FeedbackFragment()).commit();
                break;

            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.abc.mydemoapp.CompanyActivity.LogoutFragment()).commit();
                break;
            case R.id.nav_selectedstudentlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.abc.mydemoapp.CompanyActivity.SelectedStudentsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
