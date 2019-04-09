package com.abc.mydemoapp.StudentsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.abc.mydemoapp.CompanyActivity.Job;
import com.abc.mydemoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomHomeViewAdapter extends BaseAdapter implements ListAdapter {



    DatabaseReference databasejob = FirebaseDatabase.getInstance().getReference("Job");

    private Context context;
    private String id;
    private ArrayList<String> jobidlist,jobdate,jobtitle ;
    private ArrayList<Float> jobsalary;

    private String tempjobtitle,tempjobid,tempdate;
    private Float temp;




    CustomHomeViewAdapter(Context context,ArrayList<String> jobidlist,ArrayList<Float> jobsalary,ArrayList<String
            > jobdate,ArrayList<String > jobtitle)
    {

        this.context=context;
        this.jobidlist = jobidlist;
        this.jobsalary = jobsalary;
        this.jobdate = jobdate;
        this.jobtitle = jobtitle;
    }

    @Override
    public int getCount() {
        return jobidlist.size();
    }

    @Override
    public Object getItem(int position) {
        return jobidlist.get(position);
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

                    tempdate = jobdate.get(j);
                    jobdate.set(j,jobdate.get(j+1));
                    jobdate.set(j+1,tempdate);

                    tempjobtitle = jobtitle.get(j);
                    jobtitle.set(j,jobtitle.get(j+1));
                    jobtitle.set(j+1,tempjobtitle);

                }
            }
        }

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_listview_home_students, null);
        }

        TextView txtjobtitle = (TextView) view.findViewById(R.id.txtjobtitle);
        txtjobtitle.setText(jobtitle.get(position));

        TextView txtjobdate = (TextView)view.findViewById(R.id.txtjobdate);
        txtjobdate.setText(jobdate.get(position));

        Button btnshowdetails = (Button)view.findViewById(R.id.btnshowdetails);

        btnshowdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AppliedJobDetails.class);
                i.putExtra("JOBID",jobidlist.get(position));
               // i.putExtra("Salary",jobsalary.get(position));
                //i.putExtra(EXTRA_TEXT, keyList.get(position));
                //Log.w(keyList.get(position),"Key in listviewadapter");
                context.startActivity(i);
            }
        });

        return view;
    }
}
