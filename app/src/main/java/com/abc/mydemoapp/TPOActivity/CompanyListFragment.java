package com.abc.mydemoapp.TPOActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.CompanyActivity.Company;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CompanyListFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    ListView listviewcompany;
    DatabaseReference databasecompany;
    public ArrayList<String> keyList = new ArrayList<String>();
    public ArrayList<String> companyname = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companylist,container,false);
        //RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.fragment_profile,container,false);


        listviewcompany = (ListView) view.findViewById(R.id.listviewcompany);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databasecompany = firebaseDatabase.getReference("Company");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        databasecompany.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                companyname.clear();
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
    private  void showData(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            //String key = databasecompany.push().getKey();
            //Log.w(key,"New key...");
            Company company = ds.getValue(Company.class);


            String key = company.getId();
            Log.w(key,"New key...");
            //company.setName(ds.child(key).getValue(Company.class).getName());//set the name
            //company.setBranchname(ds.child(key).getValue(Company.class).getBranchname());
            //company.setCpi(ds.child(key).getValue(Company.class).getCpi());
            //company.setEmailaddress(ds.child(key).getValue(Company.class).getEmailaddress());
            //company.setId(ds.child(key).getValue(Company.class).getId());
            //company.setPassword(ds.child(key).getValue(Company.class).getPassword());
            //company.setRole(ds.child(key).getValue(Company.class).getRole());

            Log.w(company.getCompanyname(),"Company name...");

            //Do this for roll number also.

            companyname.add(company.getCompanyname());
            listviewcompany.setAdapter(new ListViewCompanyAdapter(companyname,this.getActivity(),key,company.getEmailaddresscompany(),company.getPassword()));
        }        
    }
}
