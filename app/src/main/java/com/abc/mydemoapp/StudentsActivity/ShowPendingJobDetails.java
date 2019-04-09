package com.abc.mydemoapp.StudentsActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.abc.mydemoapp.CompanyActivity.Job;
import com.abc.mydemoapp.CompanyActivity.SelectedStudents;
import com.abc.mydemoapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowPendingJobDetails extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference databasejob,dbselectedstudents;
    String jobtitle,ssid,cname;
    TextView txtjobtitle,txtjobsalary,txtcompname,txtjobreq,txtcompemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pending_job_details);

        jobtitle = getIntent().getStringExtra("job title");
        cname = getIntent().getStringExtra("Cname");
        ssid = getIntent().getStringExtra("SelectedStudentid");
        txtjobtitle = findViewById(R.id.txtjobtitle);
        txtjobsalary = findViewById(R.id.txtjobsalary);
        txtcompname = findViewById(R.id.txtcompname);
        txtjobreq = findViewById(R.id.txtjobreq);
        txtcompemail = findViewById(R.id.txtcompemail);



        dbselectedstudents = FirebaseDatabase.getInstance().getReference("SelectedStudents");
        databasejob = FirebaseDatabase.getInstance().getReference("Job");


        databasejob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showdata(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbselectedstudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showstudents(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showdata(DataSnapshot dataSnapshot){


        for(DataSnapshot ds : dataSnapshot.getChildren()){

            Job j = ds.getValue(Job.class);

            if(jobtitle.equals(j.getJobtitle())){

                txtjobtitle.setText(j.getJobtitle());
                Log.w(j.getJobtitle(),"Job title");
                txtjobsalary.setText(Float.toString(j.getSalaryRange()));
                Log.w(" " + j.getSalaryRange(),"Job salary");
                txtjobreq.setText(j.getJobRequirements());
                Log.w(j.getJobRequirements(),"Job title");


            }
        }


    }

    private void showstudents(DataSnapshot dataSnapshot){

        for (DataSnapshot ds : dataSnapshot.getChildren()) {


            SelectedStudents ss = ds.getValue(SelectedStudents.class);

            if (ssid.equals(ss.getSelectedstudentsid())) {

                txtcompname.setText(ss.getCompname());
                Log.w(ss.getCompname(), "comp name");
                txtcompemail.setText(ss.getCompemail());
                Log.w(ss.getCompemail(), "comp email");

            }

        }

    }



}
