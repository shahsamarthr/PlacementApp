package com.abc.mydemoapp.StudentsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.CompanyActivity.SelectedStudents;
import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.TPOActivity.StudentUpdateActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public  class CustomPendingAdapter extends BaseAdapter implements android.widget.ListAdapter {

    private ArrayList<SelectedStudents> sslist = new ArrayList<SelectedStudents>();
    private ArrayList<String> jobtitle = new ArrayList<String>();
    private ArrayList<String> cnamelist = new ArrayList<String>();
    private ArrayList<Student> studentlist = new ArrayList<Student>();
    private Student stud;
    DatabaseReference databasestudent;
    private Context context;
    Button btnshowdetails,btnaccept;
    int count=0,cs=0;
    CustomPendingAdapter(ArrayList<SelectedStudents> sslist,ArrayList<String> jobtitle,Context context,ArrayList<String> cnamelist,ArrayList<Student> studentlist){
        this.jobtitle = jobtitle;
        this.sslist = sslist;
        this.context = context;
        this.cnamelist = cnamelist;
        this.studentlist = studentlist;
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


        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_pendingjob, null);
        }

        TextView txtjobtitle = (TextView)view.findViewById(R.id.txtjobtitle);
        txtjobtitle.setText(jobtitle.get(position));

        TextView txtcname = (TextView)view.findViewById(R.id.txtcname);
        txtcname.setText(cnamelist.get(count));



        count++;

        if(count == cnamelist.size()){
            count=0;
        }

        cs++;

        if(cs == studentlist.size()){
            cs=0;
        }

        btnshowdetails = (Button)view.findViewById(R.id.btnshowdetails);
        btnshowdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedStudents ss= sslist.get(cs);

                Intent i = new Intent(context, ShowPendingJobDetails.class);
                i.putExtra("job title",jobtitle.get(position));
                i.putExtra("Cname",cnamelist.get(count));
                i.putExtra("SelectedStudentid",ss.getSelectedstudentsid());

                context.startActivity(i);


            }
        });
        btnaccept = (Button)view.findViewById(R.id.btnaccept);
        btnaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stud = studentlist.get(cs);
                stud.setSelected(true);
                databasestudent = FirebaseDatabase.getInstance().getReference("Student").child(stud.getId());
                databasestudent.setValue(stud);
                Toast.makeText(v.getContext(),"Congratulations ,You are  placed...",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//This line and below line are used to prevent user from going
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// back to profileactivity by pressing back button.If the user is logged out of the app.
                context.startActivity(intent);
            }
        });




        return view;
    }
}



