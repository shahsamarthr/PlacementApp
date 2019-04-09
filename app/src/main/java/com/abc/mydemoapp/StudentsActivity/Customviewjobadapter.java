package com.abc.mydemoapp.StudentsActivity;

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

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.TPOActivity.StudentUpdateActivity;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class Customviewjobadapter extends BaseAdapter implements ListAdapter {

    DatabaseReference databasestudent = FirebaseDatabase.getInstance().getReference("Student");
    //public static  final String EXTRA_TEXT = "com.abc.mydemoapp.TPOActivity.EXTRA_TEXT";
    private ArrayList<String> joblist = new ArrayList<String>();

    private Context context;
    private String id;
    private ArrayList<String> jobidlist;
    private ArrayList<Float> jobsalary;
    //private Map<String,Float> jobmap;
    private static  boolean ascending=true;
    private static  boolean descending=false;
    private String tempjobtitle,tempjobid;
    private Float temp;

    Customviewjobadapter(ArrayList<String> joblist,Context context,ArrayList<String> jobidlist,ArrayList<Float> jobsalary)
    {
        this.joblist=joblist;
        this.context=context;

        this.jobidlist = jobidlist;
        this.jobsalary = jobsalary;

    }

    @Override
    public int getCount() {
        return joblist.size();
    }

    @Override
    public Object getItem(int position) {
        return joblist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

    //Sorting the jobs according to the salary in descending order.

      for(int i=0;i<jobsalary.size()-1;i++)
      {
          for(int j=0;j<jobsalary.size()-i-1;j++)
          {
              if(jobsalary.get(j)<=jobsalary.get(j+1))
              {
                  temp = jobsalary.get(j);
                  jobsalary.set(j,jobsalary.get(j+1));
                  jobsalary.set(j+1,temp);

                  tempjobid = jobidlist.get(j);
                  jobidlist.set(j,jobidlist.get(j+1));
                  jobidlist.set(j+1,tempjobid);

                  tempjobtitle = joblist.get(j);
                  joblist.set(j,joblist.get(j+1));
                  joblist.set(j+1,tempjobtitle);

              }
          }
      }
        //id=((Home)getA)

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlistviewstudentforjob, null);
        }


        TextView txtjobtitle = (TextView)view.findViewById(R.id.txtjobtitle);
        txtjobtitle.setText(joblist.get(position));

        Button btnshowalldetails = (Button)view.findViewById(R.id.btnshowalldetails);

        btnshowalldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, showjobdetailsActivity.class);
                i.putExtra("JOBID",jobidlist.get(position));
                i.putExtra("Salary",jobsalary.get(position));
               // i.putExtra("",);
                //i.putExtra(EXTRA_TEXT, keyList.get(position));
                //Log.w(keyList.get(position),"Key in listviewadapter");
                context.startActivity(i);
            }
        });
        return view;
    }
}
