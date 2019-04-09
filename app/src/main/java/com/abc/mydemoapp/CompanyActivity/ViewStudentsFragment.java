package com.abc.mydemoapp.CompanyActivity;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.StudentsActivity.StudentJob;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ViewStudentsFragment extends Fragment {

    DatabaseReference databasejob,databasestudent;
    private ArrayList<String> jobtitle = new ArrayList<>();
    private ArrayList<String> studentemail= new ArrayList<>();
    private ArrayList<StudentJob> sjob = new ArrayList<StudentJob>();
    private ArrayList<Student> studentlist = new ArrayList<Student>();
    private ArrayList<Job> jobs = new ArrayList<Job>();

    String email;
    ListView listviewselectstudent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewstudents, container, false);
        listviewselectstudent = (ListView)view.findViewById(R.id.listviewselectstudent);
        databasejob = FirebaseDatabase.getInstance().getReference("Job");
        databasestudent = FirebaseDatabase.getInstance().getReference("StudentJob");
        email = ((Home)getActivity().getApplication()).getEmailaddress();

        databasejob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds : dataSnapshot.getChildren()){
                   Job j1 = ds.getValue(Job.class);

                   if(j1.getCompemail().equals(email)){
                       jobs.add(j1);
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
                studentemail.clear();
                addstudentname(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }



//    private void addjobname(DataSnapshot dataSnapshot)
//    {
//        for(DataSnapshot ds : dataSnapshot.getChildren())
//        {
//            Job j = ds.getValue(Job.class);
//            if(j.getCompemail().equals(email))
//            {
//                jobtitle.add(j.getJobtitle());
//            }
//        }
//    }

    private void addstudentname(DataSnapshot dataSnapshot)
    {
        int count=0;
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            StudentJob sj = ds.getValue(StudentJob.class);
            if(sj.getCompanyemail().equals(email))
            {
                studentemail.add(sj.getStudentemail());
                jobtitle.add(sj.getJobtitle());
                sjob.add(sj);
                Log.w(studentemail.get(count),"Value");
                count++;
                listviewselectstudent.setAdapter(new CustomAdapterforviewstudents(jobtitle,studentemail,sjob,jobs,this.getActivity()));
            }
        }
    }
}
