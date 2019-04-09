package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.mydemoapp.R;
import com.abc.mydemoapp.SelectRoleActivity;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentUpdateActivity extends AppCompatActivity {

    private Button btnupdate;
    private EditText pass1,Studentname,branchname;
    DatabaseReference databasestudent;
    DatabaseReference databasestudent1;
    String key;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);

        pass1= (EditText)findViewById(R.id.pass1);
        Studentname= (EditText)findViewById(R.id.Studentname);
        branchname= (EditText)findViewById(R.id.branchname);
        btnupdate = (Button)findViewById(R.id.btnupdate);

        //Here we are retreiving the key
        Intent intent = getIntent();
        key = intent.getStringExtra(ListViewAdapter.EXTRA_TEXT);

        Log.w(key,"Key is ...");

        databasestudent = FirebaseDatabase.getInstance().getReference("Student");



        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot ds= dataSnapshot.child(key);
                Log.w(key,"Update key");
                 student = ds.getValue(Student.class);

                pass1.setText(String.valueOf(student.getCpi()));
                Studentname.setText(student.getName());
                branchname.setText(student.getBranchname());

                Log.w(student.getName(),"Student name is ...");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 databasestudent1 = FirebaseDatabase.getInstance().getReference("Student").child(key);

                  student.setCpi(Float.parseFloat(pass1.getText().toString()));
                  student.setName(Studentname.getText().toString());
                  student.setBranchname(branchname.getText().toString());

                databasestudent1.setValue(student);
                Toast.makeText(StudentUpdateActivity.this,"Data Updated successfully...",Toast.LENGTH_LONG).show();
                Intent i = new Intent(StudentUpdateActivity.this,TPOProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
