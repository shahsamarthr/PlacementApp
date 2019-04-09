package com.abc.mydemoapp.CompanyActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.StudentsActivity.StudentJob;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomAdapterforviewstudents extends BaseAdapter implements ListAdapter {


    private ArrayList<String> jobtitle = new ArrayList<String>();
    private DatabaseReference databaseselectedstudents,databasejob,databasestudentjob,databasestudent;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private  Job j;
    private StudentJob sj;
    private Student s;

    private Context context;
    //private String key;
   // private String jobtitle;
    private String id,compemail;
    private ArrayList<String> studentemail = new ArrayList<>();
    private ArrayList<StudentJob> sjob = new ArrayList<StudentJob>();
    private ArrayList<Job> jobs = new ArrayList<Job>();
    int count=0,c1=0,c2=0;


    CustomAdapterforviewstudents(ArrayList<String> jobtitle,ArrayList<String> studentemail,ArrayList<StudentJob> sjob,ArrayList<Job> jobs,Context context)
    {
        this.jobtitle=jobtitle;
        this.studentemail = studentemail;
        this.context=context;
        this.sjob = sjob;
        this.jobs = jobs;
    }

    @Override
    public int getCount() {
        return jobtitle.size();
    }

    @Override
    public Object getItem(int position) {
        return jobtitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //count=studentemail.size()-1;
        Log.w(""+count,"Count");
        Log.w(""+position,"Position");
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlistviewcompanyviewstudents, null);
        }
     //   databasestudent = FirebaseDatabase.getInstance().getReference("Student");
        databaseselectedstudents = FirebaseDatabase.getInstance().getReference("SelectedStudents");
       // databasejob = FirebaseDatabase.getInstance().getReference("Job");
       databasestudentjob = FirebaseDatabase.getInstance().getReference("StudentJob");
      //  compemail = ((Home) context.getApplicationContext()).getEmailaddress();

        TextView txtjobtitle = (TextView)view.findViewById(R.id.txtjobtitle);
        Log.w(jobtitle.get(position),"Position....");
        txtjobtitle.setText(jobtitle.get(position));


        TextView txtstudentname = (TextView)view.findViewById(R.id.txtstudentname);
        txtstudentname.setText(studentemail.get(count));
         sj = sjob.get(c1);
        j = jobs.get(c2);
        Log.w(sj.getJobid(),"Sjobid is");


//            Job j1 = new Job("ab","ab","ab",0.0f,0.0f,"ab","ab","ab","ab");
//            id = databasejob.push().getKey();
//            databasejob.child(id).setValue(j1);
//
//        databasejob.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    j = ds.getValue(Job.class);
//                    if(jobtitle.get(position).equals(j.getJobtitle())){
//                        Log.w(j.getJobtitle(),"Job title before for");
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        databasejob.child(id).removeValue();
//
//        Log.w(j.getJobtitle(),"Job title after for");
//
//        databasejob.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    j = ds.getValue(Job.class);
//                    if(jobtitle.get(position).equals(j.getJobtitle())){
//                        Log.w(j.getJobtitle(),"Job title before for");
//                        break;
//                    }
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        databasestudentjob.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    sj = ds.getValue(StudentJob.class);
//                    if(jobtitle.get(position).equals(sj.getJobtitle())){
//                        Log.w(sj.getJobtitle(),"Job title before for in student job");
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//        Log.w(sj.getJobtitle(),"Job title after for in student job");
//
//        databasestudent.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    s = ds.getValue(Student.class);
//                    if(studentemail.get(position).equals(s.getEmailaddress())){
//                        Log.w(s.getEmailaddress(),"Student email before for ");
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        Log.w(s.getEmailaddress(),"Student email after for ");

        count++;
        if(count==studentemail.size())
            count=0;

        c1++;
        if(c1==sjob.size())
            c1=0;

        c2++;
        if(c2==jobs.size())
            c2=0;


        Button btnselect = (Button)view.findViewById(R.id.btnselect);
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                String id1 = databaseselectedstudents.push().getKey();
                SelectedStudents ss = new SelectedStudents(id1,sj.getStudentid(),sj.getJobid(),sj.getJobtitle(),j.getCompemail(),j.getCname(),sj.getStudentjobid(),sj.getStudentemail());
                databaseselectedstudents.child(id1).setValue(ss);
                databasestudentjob.child(sj.getStudentjobid()).removeValue();
                Toast.makeText(v.getContext(),"Student  added successfully...",Toast.LENGTH_LONG).show();
                Intent i = new Intent(context,ProfileCompanyActivity.class);
                context.startActivity(i);


            }
        });

        return view;
    }
}
