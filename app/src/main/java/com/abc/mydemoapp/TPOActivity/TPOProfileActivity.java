package com.abc.mydemoapp.TPOActivity;

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
import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;


public class TPOProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    //private Button signoutbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpoprofile);
        //signoutbutton = (Button)findViewById(R.id.signoutbutton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //signoutbutton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  FirebaseAuth.getInstance().signOut();
                //Intent intent = new Intent(TPOProfileActivity.this,MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//This line and below line are used to prevent user from going
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// back to profileactivity by pressing back button.If the user is logged out of the app.
                //startActivity(intent);
            //}
        //});
        drawer = findViewById(R.id.drawer_tpo_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();//So that the 3lines can rotate.

        //Due to the if statement StudentListActivity will be shown only when we actually want it.
        //i.e. if we rotate the device the savedInstanceState will be not null so StudentListActivity
        // won't be loaded twice.
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new StudentListFragment()).commit();
            //To check the student list view item in the navigation drawer.
            navigationView.setCheckedItem(R.id.nav_studentlist);
        }
    }

    //Due to this return true item will remain selected.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId())
        {
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SendTANFragment()).commit();
                break;
            case R.id.nav_Companylist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CompanyListFragment()).commit();
                break;
            case R.id.nav_studentlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentListFragment()).commit();
                break;
            case R.id.nav_selectedstudentlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SelectedStudentListFragment()).commit();
                break;
            case R.id.nav_myprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_messagestudent:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SendStudentTANFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
