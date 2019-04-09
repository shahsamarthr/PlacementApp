package com.abc.mydemoapp.StudentsActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.abc.mydemoapp.CompanyActivity.Job;
import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.TPOActivity.ListViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewjobsFragment extends Fragment {

    private ArrayList<String> jobid = new ArrayList<String>();
    private ArrayList<Float> jobsalary = new ArrayList<Float>();
    private ArrayList<String> jobtitle = new ArrayList<String>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String emailaddress;
    ListView listviewjob;
    DatabaseReference databasejob,databasestudent,databasestudentjob;
    public ArrayList<String> joblist = new ArrayList<String>();
    float studentcpi=0.0f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewjobs, container, false);

        listviewjob = (ListView) view.findViewById(R.id.listviewjob);

        emailaddress = ((Home)getActivity().getApplication()).getEmailaddress();

        //Log.w(emailaddress,"Student emailaddress...");




        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasejob = firebaseDatabase.getReference("Job");
        databasestudent = firebaseDatabase.getReference("Student");
        databasestudentjob = firebaseDatabase.getReference("StudentJob");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        databasestudentjob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    StudentJob sj = ds.getValue(StudentJob.class);

                    if(sj.getStudentemail().equals(emailaddress)){
                        jobtitle.add(sj.getJobtitle());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                findstudentid(emailaddress,dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databasejob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                joblist.clear();
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Job job = ds.getValue(Job.class);

            Log.w(""+job,"Job obj");
           // Log.w("","");

            if(studentcpi>=job.getCpi() && !jobtitle.contains(job.getJobtitle().toString())) {
                 joblist.add(job.getJobtitle());

                 jobsalary.add(job.getSalaryRange());
                 Log.w(""+job.getSalaryRange(),"Job salary...");
                 Log.w(job.getId(),"Job id is...");
                 jobid.add(job.getId());

                //jobmap.put(job.getId(),job.getSalaryRange());
                listviewjob.setAdapter(new Customviewjobadapter(joblist, this.getActivity(),jobid,jobsalary));
               // Log.w(job.getId(),"Job id...");
            }
        }
    }

    private void findstudentid(String emailaddress,DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Student s  = ds.getValue(Student.class);
            if(s.getEmailaddress().equals(emailaddress)) {
                studentcpi = s.getCpi();
                break;
            }
        }
        Log.w(""+studentcpi,"Student cpi...");
        return;
    }
}