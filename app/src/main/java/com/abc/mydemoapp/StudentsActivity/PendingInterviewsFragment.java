package com.abc.mydemoapp.StudentsActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.abc.mydemoapp.CompanyActivity.SelectedStudents;
import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingInterviewsFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ListView listviewss;
    DatabaseReference databaseselectedstudents,databasejob,databasestudent;
    private String emailaddress;
    private ArrayList<SelectedStudents> sslist = new ArrayList<SelectedStudents>();
    private ArrayList<String> jobtitle = new ArrayList<String>();
    private ArrayList<String> cnamelist = new ArrayList<String>();
    private ArrayList<Student> studentlist = new ArrayList<Student>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pendinginterviews,container,false);

        listviewss = (ListView) view.findViewById(R.id.listviewss);

        emailaddress = ((Home)getActivity().getApplication()).getEmailaddress();

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databasejob = firebaseDatabase.getReference("Job");
        databasestudent = firebaseDatabase.getReference("Student");
        databaseselectedstudents = firebaseDatabase.getReference("SelectedStudents");

        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Student s = ds.getValue(Student.class);

                    if(s.getEmailaddress().equals(emailaddress)){
                        studentlist.add(s);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseselectedstudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    SelectedStudents ss = ds.getValue(SelectedStudents.class);

                    if(ss.getStudentemail().equals(emailaddress)){
                        sslist.add(ss);
                        jobtitle.add(ss.getJobtitle());
                        cnamelist.add(ss.getCompname());
                        listviewss.setAdapter(new CustomPendingAdapter(sslist,jobtitle,getActivity(),cnamelist,studentlist));
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
