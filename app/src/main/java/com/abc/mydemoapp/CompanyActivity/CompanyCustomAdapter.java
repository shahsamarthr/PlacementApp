//package com.abc.mydemoapp.CompanyActivity;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ListAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.abc.mydemoapp.R;
//import com.abc.mydemoapp.CompanyActivity.JobUpdateActivity;
//import com.abc.mydemoapp.StudentsActivity.StudentJob;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.EmailAuthProvider;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class CompanyCustomAdapter extends BaseAdapter implements ListAdapter {
//
//    DatabaseReference stujob = FirebaseDatabase.getInstance().getReference("StudentJob");
//    DatabaseReference databasejob = FirebaseDatabase.getInstance().getReference("Job");
//    public static  final String EXTRA_TEXT = "com.abc.mydemoapp.CompanyActivity.EXTRA_TEXT";
//    private ArrayList<String> jname = new ArrayList<String>();
//    private Context context;
//    private String key;
//    private String jobtitle;
//    private String id;
//    String studentjobid;
//    private ArrayList<String> keyList = new ArrayList<>();
//    int count=0;
//    FirebaseAuth.AuthStateListener mAuthListener;
//    FirebaseUser firebaseUser ;
//    FirebaseAuth mauth = FirebaseAuth.getInstance();
//
//
//    CompanyCustomAdapter(ArrayList<String> jname,ArrayList<String> keyList,Context context,String id,String jobtitle)
//    {
//        this.jname=jname;
//        this.keyList = keyList;
//        this.context=context;
//
//        this.id=id;
//        this.jobtitle=jobtitle;
//    }
//
//    @Override
//    public int getCount() {
//        return jname.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return jname.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        View view = convertView;
//        if (view == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.customlistviewforjobs, null);
//        }
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
//
//                if(firebaseUser!=null)
//                {
//                    Log.w("Congo","Your code is executing perfctly...");
//                }
//            }
//        };
//
//        TextView txtjobname = (TextView)view.findViewById(R.id.txtjobname);
//        Log.w(jname.get(position),"Position....");
//        txtjobname.setText(jname.get(position));
//
//        stujob.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren())
//                {
//                    StudentJob studentJob = ds.getValue(StudentJob.class);
//                    if(studentJob.getJobid().equals(keyList.get(position)))
//                    {
//                        studentjobid=studentJob.getStudentjobid();
//                    }
//                }
//
//
//            }
//
//
//
//        Button btnupdate = (Button)view.findViewById(R.id.btnupdate);
//        Button btndelete = (Button)view.findViewById(R.id.btndelete);
//        btndelete.setTag(position);
//
//
//        btndelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.w(String.valueOf(position),"Hello");
//                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//                dialog.setTitle("Are you sure?");
//                dialog.setMessage("Do you want to delete this account?");
//                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    //Here due to this listener we can react on button click inside the dialog
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                        deletestudent(keyList.get(position),studentjobid);
//
//                    }
//                });
//                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                //Now we are going to create a dialog box
//                AlertDialog alertDialog = dialog.create();
//                alertDialog.show();
//            }
//        });
//        btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, JobUpdateActivity.class);
//                i.putExtra(EXTRA_TEXT, keyList.get(position));
//                Log.w(keyList.get(position),"Key in companydapter");
//                context.startActivity(i);
//            }
//        });
//        return view;
//    }
//
//    private void deletestudent(String key1)
//    {
//        DatabaseReference dbjob = FirebaseDatabase.getInstance().getReference("").child(key1);
//        dbjob.removeValue();
//        Toast.makeText(context,"Job deleted successfully",Toast.LENGTH_LONG).show();
//        return;
//    }
//}
package com.abc.mydemoapp.CompanyActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.CompanyActivity.JobUpdateActivity;
import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.StudentsActivity.StudentJob;
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

public class CompanyCustomAdapter extends BaseAdapter implements ListAdapter {

    DatabaseReference databasejob = FirebaseDatabase.getInstance().getReference("Job");
    DatabaseReference stujob = FirebaseDatabase.getInstance().getReference("StudentJob");
    public static  final String EXTRA_TEXT = "com.abc.mydemoapp.CompanyActivity.EXTRA_TEXT";
    private ArrayList<String> jname = new ArrayList<String>();
    private Context context;
    private String key;
    private String jobtitle;
    private String id;
    private ArrayList<String> keyList = new ArrayList<>();
    String studentjobid;
    int count=0;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser firebaseUser ;
    FirebaseAuth mauth = FirebaseAuth.getInstance();


    CompanyCustomAdapter(ArrayList<String> jname,ArrayList<String> keyList,Context context,String id,String jobtitle)
    {
        this.jname=jname;
        this.keyList = keyList;
        this.context=context;

        this.id=id;
        this.jobtitle=jobtitle;
    }

    @Override
    public int getCount() {
        return jname.size();
    }

    @Override
    public Object getItem(int position) {
        return jname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlistviewforjobs, null);
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

                if(firebaseUser!=null)
                {
                    Log.w("Congo","Your code is executing perfctly...");
                }
            }
        };
        stujob.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    StudentJob studentJob = ds.getValue(StudentJob.class);
                    if(studentJob.getJobid().equals(keyList.get(position)))
                    {
                        studentjobid=studentJob.getStudentjobid();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        TextView txtjobname = (TextView)view.findViewById(R.id.txtjobname);
        Log.w(jname.get(position),"Position....");
        txtjobname.setText(jname.get(position));

        Button btnupdate = (Button)view.findViewById(R.id.btnupdate);
        Button btndelete = (Button)view.findViewById(R.id.btndelete);
        btndelete.setTag(position);



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.w(String.valueOf(position),"Hello");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Do you want to delete this account?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    //Here due to this listener we can react on button click inside the dialog
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(studentjobid!=null) {
                            deletestudent(keyList.get(position), studentjobid);
                        }
                        else
                        {
                            DatabaseReference dbjob = FirebaseDatabase.getInstance().getReference("Job").child(keyList.get(position));
                            dbjob.removeValue();
                            Toast.makeText(context,"Job deleted successfully",Toast.LENGTH_LONG).show();
                            //return;
                            Intent i = new Intent(context, ProfileCompanyActivity.class);
                            context.startActivity(i);
                        }
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
                Intent i = new Intent(context, JobUpdateActivity.class);
                i.putExtra(EXTRA_TEXT, keyList.get(position));
                Log.w(keyList.get(position),"Key in companydapter");
                context.startActivity(i);
            }
        });
        return view;
    }

    private void deletestudent(String key1,String studentjobid)
    {
        DatabaseReference dbjob = FirebaseDatabase.getInstance().getReference("Job").child(key1);
        DatabaseReference dbstujob = FirebaseDatabase.getInstance().getReference("StudentJob").child(studentjobid);
        //DatabaseReference dbstujob = FirebaseDatabase.getInstance().getReference("StudentJob");
        dbstujob.removeValue();
        //dbstujob.removeValue();
        dbjob.removeValue();
        Toast.makeText(context,"Job deleted successfully",Toast.LENGTH_LONG).show();
        //return;
        Intent i = new Intent(context, ProfileActivity.class);
        context.startActivity(i);
    }
}
