package com.abc.mydemoapp.CompanyActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.mydemoapp.Home;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddJobActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DatabaseReference databasejob,databasecompany;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText txtjobdesc,jobtitle,jobreq,cpi,Salary;
    private DataSnapshot ds;
    private Button btnaddjob,btnselectdate;
    private float cpis=0.0f,sr;
    private  String compemail,cid;
    private   String cname;
    private String id;
    private String currentDateString;
    //private Date d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        txtjobdesc = (EditText)findViewById(R.id.txtjobdesc);
        jobtitle = (EditText)findViewById(R.id.jobtitle);
        jobreq = (EditText)findViewById(R.id.jobreq);

        cpi = (EditText)findViewById(R.id.cpi);
        Salary = (EditText)findViewById(R.id.Salary);
        btnaddjob = (Button) findViewById(R.id.btnaddjob);
        btnselectdate = (Button)findViewById(R.id.btnselectdate);

        btnselectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(),"Date picker");

            }
        });


        compemail = ((Home) getApplication()).getEmailaddress();


        databasecompany = FirebaseDatabase.getInstance().getReference("Company");
        Log.w(""+databasecompany,"Company database...");
        databasejob = FirebaseDatabase.getInstance().getReference("Job");
        //dbcompany = FirebaseDatabase.getInstance().getReference("Company");
        btnaddjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(cpi.getText().toString()) || TextUtils.isEmpty(txtjobdesc.getText().toString()) || TextUtils.isEmpty(jobtitle.getText().toString()) || TextUtils.isEmpty(jobreq.getText().toString()) || TextUtils.isEmpty(Salary.getText().toString())) {
                    Toast.makeText(AddJobActivity.this, "Fill all fields...", Toast.LENGTH_LONG).show();
                    return;
                }
                cpis = Float.parseFloat(cpi.getText().toString());
                 sr = Float.parseFloat(Salary.getText().toString());
                 id = databasejob.push().getKey();


            //Remove this comment when app misbehaves.

//                     cid = databasecompany.push().getKey();
//                    Company c = new Company(cid,"ab","cd","ef","gh","ij","company");
//                    databasecompany.child(cid).setValue(c);
//                    databasecompany.child(cid).removeValue();

                databasecompany.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Company comp = ds.getValue(Company.class);
                                if (comp.getEmailaddress().equals(compemail)) {

                                    cname = comp.getCompanyname();
                                    Log.w(cname, "company name ... ");
                                    Job j = new Job(jobtitle.getText().toString(), txtjobdesc.getText().toString(), jobreq.getText().toString(), sr, cpis, id, cname, currentDateString, compemail);
                                    databasejob.child(id).setValue(j);
                                    Toast.makeText(AddJobActivity.this,"Job added successfully...",Toast.LENGTH_LONG).show();

                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





                Intent intent=  new Intent(AddJobActivity.this,ProfileCompanyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//        d.setYear(year);
//        d.setMonth(month);
//        d.setDate(dayOfMonth);
        month=month+1;
        //String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        if(month>=10) {
            currentDateString = dayOfMonth + "/" + month + "/" + year;
            TextView selecteddate = (TextView) findViewById(R.id.selecteddate);
            selecteddate.setText(currentDateString);
        }
        else
        {
            currentDateString = dayOfMonth + "/0" + month + "/" + year;
            TextView selecteddate = (TextView) findViewById(R.id.selecteddate);
            selecteddate.setText(currentDateString);
        }
    }
}

