package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.TPOActivity.ListViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FloatingActionButton btnaddjob;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    ListView listviewjob;
    DatabaseReference databasejob;
    private String comemail;
    public ArrayList<String> jobtitle = new ArrayList<String>();
    public  ArrayList<String> keyList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companyhome, container, false);

        listviewjob = (ListView) view.findViewById(R.id.joblist);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databasejob = firebaseDatabase.getReference("Job");
        comemail = ((Home)getActivity().getApplication()).getEmailaddress();
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged( FirebaseAuth firebaseAuth) {


                if(firebaseAuth.getCurrentUser()!=null){

                    String cid = firebaseAuth.getCurrentUser().getUid();
                    Log.w(cid,"Company id in HomeF......");
                }

            }
        };

        databasejob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobtitle.clear();
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnaddjob = (FloatingActionButton)view.findViewById(R.id.btnaddjob);
        btnaddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddJobActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void showData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            //String key = databasestudent.push().getKey();
            //Log.w(key,"New key...");


            Job j = ds.getValue(Job.class);

            if(j.getCompemail().equals(comemail)) {
                String key = j.getId();
                Log.w(key, "New key...");
                //student.setName(ds.child(key).getValue(Student.class).getName());//set the name
                //student.setBranchname(ds.child(key).getValue(Student.class).getBranchname());
                //student.setCpi(ds.child(key).getValue(Student.class).getCpi());
                //student.setEmailaddress(ds.child(key).getValue(Student.class).getEmailaddress());
                //student.setId(ds.child(key).getValue(Student.class).getId());
                //student.setPassword(ds.child(key).getValue(Student.class).getPassword());
                //student.setRole(ds.child(key).getValue(Student.class).getRole());

                Log.w(j.getJobtitle(), "job title ...");
                //  if() {
                //Do this for roll number also.
                keyList.add(j.getId());

                jobtitle.add(j.getJobtitle());
                listviewjob.setAdapter(new CompanyCustomAdapter(jobtitle, keyList, getContext(), j.getId(), j.getJobtitle()));
            }
           // }
        }
    }
}
