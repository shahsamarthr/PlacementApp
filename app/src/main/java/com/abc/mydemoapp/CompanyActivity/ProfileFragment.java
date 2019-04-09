package com.abc.mydemoapp.CompanyActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference databasejob,dbstujob,databasestudent,databasecompany;
    TextView textview1,textview2,textview3,textview4,textview5,textview6;
    private String comemail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companyprofile, container, false);
        comemail = ((Home)getActivity().getApplication()).getEmailaddress();
        textview1 = (TextView) view.findViewById(R.id.textView1);
//        textview1=(TextView)view.findViewById(R.id.textView1)
        textview2 = (TextView)view.findViewById(R.id.textView2);
        textview3 = (TextView)view.findViewById(R.id.textView3);
        textview4 =  (TextView)view.findViewById(R.id.textView4);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasejob = firebaseDatabase.getReference("Job");
        dbstujob = firebaseDatabase.getReference("StudentJob");
        databasestudent = firebaseDatabase.getReference("Student");
        databasecompany=firebaseDatabase.getReference("Company");


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


            }
        };
        databasecompany.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Company s = ds.getValue(Company.class);
                    if(s.getEmailaddress().equals(comemail))
                    {
                        Log.w(s.getId(),"company id ");
                        Log.w(s.getCompanyname(),"company name.... ");
                        //textview1.setText(s.getCompanyname());
                        textview1.setText(s.getCompanyname());

                        textview2.setText(s.getEmailaddress());
                        textview3.setText(s.getAddress());
                        textview4.setText(s.getPassword());



//                        textview2.setText(s.getEmailaddress());
//                        textview3.setText(s.getAddress());
//                        textview4.setText(s.getPassword());
                    }
                }
                //demo(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;

    }
//    private  void demo(DataSnapshot dataSnapshot)
//    {
//        for(DataSnapshot ds : dataSnapshot.getChildren())
//        {
//            Company s = ds.getValue(Company.class);
//            if(s.getEmailaddress().equals(comemail))
//            {
//                Log.w(s.getId(),"company id ");
//                textview1.setText(s.getCompanyname());
//                textview2.setText(s.getEmailaddress());
//                textview3.setText(s.getAddress());
//                textview4.setText(s.getPassword());
//            }
//        }
//    }
}
