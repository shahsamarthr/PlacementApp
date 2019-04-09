package com.abc.mydemoapp.TPOActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.CompanyActivity.Job;
import com.abc.mydemoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;




public class ListViewCompanyAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> sname = new ArrayList<String>();
    private Context context;
    private String key,compid;
    private String email,password;
    private ArrayList<String> jobidlist = new ArrayList<>();
    DatabaseReference databasejob = FirebaseDatabase.getInstance().getReference("Job");
    DatabaseReference databasecomp = FirebaseDatabase.getInstance().getReference("Company");
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    public static  final String EXTRA_TEXT = "com.abc.mydemoapp.TPOActivity.EXTRA_TEXT";

    ListViewCompanyAdapter(ArrayList<String> sname,Context context,String key,String email,String password)
    {
        this.sname=sname;
        this.context=context;
        this.key=key;
        this.email=email;
        this.password=password;
    }

    @Override
    public int getCount() {
        return sname.size();
    }

    @Override
    public Object getItem(int position) {
        return sname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customcompanylistview, null);
        }

        TextView txtcompanyname= (TextView)view.findViewById(R.id.txtcompanyname);
        txtcompanyname.setText(sname.get(position));




        Button btnupdate = (Button)view.findViewById(R.id.btnupdate);
        Button btndelete = (Button)view.findViewById(R.id.btndelete);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                databasecomp.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for(DataSnapshot ds : dataSnapshot.getChildren()){
//                        Company c1 = ds.getValue(Company.class);
//
//                        if(c1.getId().equals(key)){
//                            compid = c1.getEmailaddress();
//                        }
//
//                    }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });


                databasejob.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            Job j = ds.getValue(Job.class);

                            if(j.getCompemail().equals(email)){
                                jobidlist.add(j.getId());
                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Do you want to delete this account?");

                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    //Here due to this listener we can react on button click inside the dialog
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthCredential authCredential = EmailAuthProvider.getCredential(email, password);
                        deletecompany(key);
                        //firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        // @Override
                        //  public void onComplete(@NonNull Task<Void> task) {
//                                firebaseUser.delete().addOnCompleteListener( new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            //Toast.makeText(context,"Company deleted sucessfully...",Toast.LENGTH_LONG).show();
//
//                                        }
//                                        else
//                                        {
//                                            Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                });
                        //}
                        //});
                    }
                });

                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                //Now we are going to create a dialog box
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UpdateCompanyActivity.class);
                i.putExtra(EXTRA_TEXT, key);
                context.startActivity(i);
            }
        });
        return view;
    }
    private void deletecompany(String key)
    {
        DatabaseReference dbcompany = FirebaseDatabase.getInstance().getReference("Company").child(key);
        DatabaseReference dbjob = FirebaseDatabase.getInstance().getReference("Job");
        for(int i=0;i<jobidlist.size();i++){
            dbjob.child(jobidlist.get(i)).removeValue();

        }

        dbcompany.removeValue();
        Toast.makeText(context,"Company deleted successfully",Toast.LENGTH_LONG).show();
        return;
    }
}
