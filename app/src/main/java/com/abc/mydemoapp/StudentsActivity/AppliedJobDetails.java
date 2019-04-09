package com.abc.mydemoapp.StudentsActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.abc.mydemoapp.CompanyActivity.Job;
import com.abc.mydemoapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppliedJobDetails extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
     TextView txtjobtitle,txtcname,txtjobsalary,txtjobreq,txtjobdate;
    String jobid;
    DatabaseReference databasejob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_job_details);
        jobid=getIntent().getStringExtra("JOBID");


        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasejob = firebaseDatabase.getReference("Job");
        txtjobtitle = findViewById(R.id.txtjobtitle);
        txtcname = findViewById(R.id.txtcname);
        txtjobsalary = findViewById(R.id.txtjobsalary);
        txtjobreq = findViewById(R.id.txtjobreq);
        txtjobdate = findViewById(R.id.txtjobdate);

        databasejob.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //String key = databasestudent.push().getKey();
                    //Log.w(key,"New key...");
                    Job job = ds.getValue(Job.class);
                    Log.w(job.getId(), "Job id ");
                    //String key = student.getId();
                    //Log.w("" + job.getSalaryRange(), "New Salary...");
                    if (job.getId().equals(jobid)) {
                       // Log.w(jobid, "Job id now....");
                        txtjobtitle.setText(job.getJobtitle());
                        txtcname.setText(job.getCname());
                        txtjobsalary.setText(Float.toString(job.getSalaryRange()));
                        txtjobreq.setText(job.getJobRequirements());
                        txtjobdate.setText(job.getDate());


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }));


    }
}
