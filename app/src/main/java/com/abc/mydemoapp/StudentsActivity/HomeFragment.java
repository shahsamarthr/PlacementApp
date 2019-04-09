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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<String> jobid = new ArrayList<String>();
    private ArrayList<String> jobtitle = new ArrayList<String>() ;
    private ArrayList<String> jobdate  = new ArrayList<String>();
    private ArrayList<Float> jobsalary  = new ArrayList<Float>();
    ListView listviewhome;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference dbstudentjob,dbjob;
    String studentid;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_home,container,false);

        listviewhome = (ListView)view.findViewById(R.id.listviewhome);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        dbstudentjob = firebaseDatabase.getReference("StudentJob");

        dbjob = firebaseDatabase.getReference("Job");

        studentid = ((Home)getActivity().getApplication()).getEmailaddress();

        dbstudentjob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    StudentJob sj = ds.getValue(StudentJob.class);

                    if(sj.getStudentemail().equals(studentid)){
                        Log.w(sj.getJobid(),"job id .....");
                        jobid.add(sj.getJobid());
                        //listviewhome.setAdapter(new CustomHomeViewAdapter( getActivity(),jobid));
                    }

                }
                Log.w(" "+jobid.size(),"Size of jobid ...");

                for(int i=0;i<jobid.size();i++){
                    jobdate.add(" ");
                    jobsalary.add(0.0f);
                    jobtitle.add(" ");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        dbjob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Job j = ds.getValue(Job.class);

                    if(jobid.contains(j.getId())){
                        int pos = jobid.indexOf(j.getId());

                        jobsalary.set(pos,j.getSalaryRange());

                        jobdate.set(pos,j.getDate());

                        jobtitle.set(pos,j.getJobtitle());

                        listviewhome.setAdapter(new CustomHomeViewAdapter(getActivity(),jobid,jobsalary,jobdate,jobtitle));

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

}
