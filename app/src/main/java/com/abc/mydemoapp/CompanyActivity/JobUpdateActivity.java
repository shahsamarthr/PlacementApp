package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.TPOActivity.ListViewAdapter;
import com.abc.mydemoapp.TPOActivity.StudentUpdateActivity;
import com.abc.mydemoapp.TPOActivity.TPOProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobUpdateActivity extends AppCompatActivity {
    private Button btnupdate;
    private EditText pass1,jobtitle,jobdescription,jobrequirement,date,salary;
    DatabaseReference databasestudent;
    DatabaseReference databasestudent1;
    DatabaseReference databasejob;
    String key;
    Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_update);
        pass1= (EditText)findViewById(R.id.pass1);
        jobtitle= (EditText)findViewById(R.id.jobtitle1);
        jobdescription= (EditText)findViewById(R.id.jobdescription1);
        jobrequirement= (EditText)findViewById(R.id.jobreq1);
        date= (EditText)findViewById(R.id.date);
        salary=(EditText)findViewById(R.id.Salary1);

        btnupdate = (Button)findViewById(R.id.btnupdate);
        Intent intent = getIntent();
        key = intent.getStringExtra(CompanyCustomAdapter.EXTRA_TEXT);
        Log.w(key,"Key in jobupdateactivity...");
        databasejob= FirebaseDatabase.getInstance().getReference("Job");
        databasejob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot ds= dataSnapshot.child(key);
                job = ds.getValue(Job.class);

                pass1.setText(String.valueOf(job.getCpi()));
                jobtitle.setText(job.getJobtitle());
                jobdescription.setText(job.getJobdescription());
                jobrequirement.setText(job.getJobRequirements());
                date.setText(job.getDate());
                salary.setText(String.valueOf(job.getSalaryRange()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasestudent1 = FirebaseDatabase.getInstance().getReference("Job").child(key);

                job.setCpi(Float.parseFloat(pass1.getText().toString()));
                job.setJobtitle(jobtitle.getText().toString());
                job.setJobdescription(jobdescription.getText().toString());
                job.setJobRequirements(jobrequirement.getText().toString());
                job.setDate(date.getText().toString());
                job.setSalaryRange(Float.parseFloat(salary.getText().toString()));

                databasestudent1.setValue(job);
                Toast.makeText(JobUpdateActivity.this,"Data Updated successfully...",Toast.LENGTH_LONG).show();
                Intent i = new Intent(JobUpdateActivity.this,ProfileCompanyActivity.class);
                startActivity(i);
            }
        });

    }
}
