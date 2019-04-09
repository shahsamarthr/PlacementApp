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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentListFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    ListView listviewstudent;
    DatabaseReference databasestudent;
    public ArrayList<String> studentname = new ArrayList<String>();
    public  ArrayList<String> keyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_studentlist, container, false);

        listviewstudent = (ListView) view.findViewById(R.id.listviewstudent);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasestudent = firebaseDatabase.getReference("Student");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentname.clear();
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
            //String key = databasestudent.push().getKey();
            //Log.w(key,"New key...");
            Student student = ds.getValue(Student.class);
            String key = student.getId();
            Log.w(key, "New key...");
            //student.setName(ds.child(key).getValue(Student.class).getName());//set the name
            //student.setBranchname(ds.child(key).getValue(Student.class).getBranchname());
            //student.setCpi(ds.child(key).getValue(Student.class).getCpi());
            //student.setEmailaddress(ds.child(key).getValue(Student.class).getEmailaddress());
            //student.setId(ds.child(key).getValue(Student.class).getId());
            //student.setPassword(ds.child(key).getValue(Student.class).getPassword());
            //student.setRole(ds.child(key).getValue(Student.class).getRole());

            Log.w(student.getName(), "Student name...");

            //Do this for roll number also.
            keyList.add(student.getId());

            studentname.add(student.getName());
            listviewstudent.setAdapter(new ListViewAdapter(studentname,keyList, this.getActivity(),key,student.getEmailaddress(),student.getPassword()));
        }
    }
}