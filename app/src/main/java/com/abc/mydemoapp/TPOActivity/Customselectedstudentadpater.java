package com.abc.mydemoapp.TPOActivity;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customselectedstudentadpater extends BaseAdapter implements ListAdapter {

    //DatabaseReference databasestudent = FirebaseDatabase.getInstance().getReference("SelectedStudents");
    //public static  final String EXTRA_TEXT = "com.abc.mydemoapp.TPOActivity.EXTRA_TEXT";
    private ArrayList<String> studentemail = new ArrayList<String>();
    private ArrayList<String> studentid = new ArrayList<String>();
    private ArrayList<String> jobid = new ArrayList<String>();
    private ArrayList<String> jobtitle = new ArrayList<String>();
    private ArrayList<String> compemail = new ArrayList<String>();
    private ArrayList<String> compname = new ArrayList<String>();
    private Context context;

    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser firebaseUser ;
    FirebaseAuth mauth = FirebaseAuth.getInstance();


    Customselectedstudentadpater(ArrayList<String> studentemail,Context context,ArrayList<String> studentid,ArrayList<String> jobid,ArrayList<String> jobtitle,ArrayList<String> compemail,ArrayList<String> compname)
    {
        this.studentemail=studentemail;
        this.context=context;
        this.compemail=compemail;
        this.compname=compname;
        this.studentid=studentid;
        this.jobid=jobid;
        this.jobtitle=jobtitle;
    }

    @Override
    public int getCount() {
        return studentemail.size();
    }

    @Override
    public Object getItem(int position) {
        return studentemail.get(position);
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
            view = inflater.inflate(R.layout.customselectedstudentlistview, null);
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

        TextView txtstudentemail = (TextView)view.findViewById(R.id.txtstudentname);
        txtstudentemail.setText(studentemail.get(position));

        Button btnshowdetails = (Button)view.findViewById(R.id.btnshowdetails);

        btnshowdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SelectedStudentDetailsActivity.class);
                intent.putExtra("studentemail",studentemail.get(position));
                intent.putExtra("studentid",studentid.get(position));
                intent.putExtra("jobid",jobid.get(position));
                intent.putExtra("jobtitle",jobtitle.get(position));
                intent.putExtra("compemail",compemail.get(position));
                intent.putExtra("compname",compname.get(position));
                context.startActivity(intent);
            }
        });
        return view;
    }
}
