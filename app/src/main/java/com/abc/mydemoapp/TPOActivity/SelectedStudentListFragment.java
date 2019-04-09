package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.abc.mydemoapp.CompanyActivity.SelectedStudents;
import com.abc.mydemoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedStudentListFragment extends Fragment {

    private ListView selectedstudentlistview;
    private DatabaseReference dbselectedstudent = FirebaseDatabase.getInstance().getReference("SelectedStudents");
    private ArrayList<String> studentemail = new ArrayList<String>();
    private ArrayList<String> studentid = new ArrayList<String>();
    private ArrayList<String> jobid = new ArrayList<String>();
    private ArrayList<String> jobtitle = new ArrayList<String>();
    private ArrayList<String> compemail = new ArrayList<String>();
    private ArrayList<String> compname = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selectedstudentlist,container,false);

        selectedstudentlistview = (ListView)view.findViewById(R.id.selectedstudentlistview);


        dbselectedstudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    SelectedStudents ss = ds.getValue(SelectedStudents.class);
                    //ss.getCompemail();
                    studentemail.add(ss.getStudentemail());
                    Log.w("Email is...",ss.getStudentemail());
                    studentid.add(ss.getStudentid());
                    jobid.add(ss.getJobid());
                    jobtitle.add(ss.getJobtitle());
                    compemail.add(ss.getCompemail());
                    compname.add(ss.getCompname());
                    selectedstudentlistview.setAdapter(new Customselectedstudentadpater(studentemail,getActivity(),studentid,jobid,jobtitle,compemail,compname));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
