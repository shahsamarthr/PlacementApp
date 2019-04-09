package com.abc.mydemoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.CompanyActivity.ProfileCompanyActivity;
import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.abc.mydemoapp.TPOActivity.TPOProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Spinner spinner2;
    DatabaseReference dbref;
    FirebaseAuth userAuth;
    EditText pass,email;
    Button forgotpasswordbtn,loginbtn,signupbtn;
    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Code for splashing in the app(i.e. For rainbow that appears at the starting and then goes away... )
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner2 = (Spinner)findViewById(R.id.spinner2);
        pass = (EditText)findViewById(R.id.pass);
        email = (EditText)findViewById(R.id.email);
        forgotpasswordbtn = (Button)findViewById(R.id.forgotpasswordbtn);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        signupbtn = (Button)findViewById(R.id.signinbtn);
        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        userAuth = FirebaseAuth.getInstance();
        handler.postDelayed(runnable,2000);//2000 is the timeout for splash

        //Initializing dropdown list
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                (MainActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        //Basically adpater is the container that will hold the values which we will read through spinner.
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myadapter);

       signupbtn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,SelectRoleActivity.class);
               startActivity(intent);
           }
       });

       loginbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            //User Authentication code for realtime database
            final String roleselection = spinner2.getSelectedItem().toString();

            //Now we are taking the email address of the currently logged in user globally.
                ((Home)getApplication()).setEmailaddress(email.getText().toString());
//               Home h = new Home();
//               h.setEmailaddress(email.getText().toString());
               //Log.w(((Home)getApplication()).getEmailaddress(),"Email Address...");

            if(email.getText().toString().equals("harshsrivastva18@gmail.com")&&pass.getText().toString().equals("123456"))
            {
                Intent intent = new Intent(MainActivity.this,TPOProfileActivity.class);
                startActivity(intent);
                return;
            }



                //User Authentication code for Authentication in firebase.
               userAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful())
                               {
                                   //checking if the user has verified the email or not
                                   if(userAuth.getCurrentUser().isEmailVerified())
                                   {
                                       //Query for ordering all the node accoding to email address
                                       Query query = FirebaseDatabase.getInstance().getReference(roleselection).
                                               orderByChild("emailaddress").equalTo(email.getText().toString());


                                       if(roleselection.equals("Student")) {
                                           query.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                   //Checking whether the User exists or not.
                                                   if (dataSnapshot.exists()) {
                                                       for (DataSnapshot user : dataSnapshot.getChildren()) {
                                                           Student student  = user.getValue(Student.class);

                                                           //Verifying the selected field

                                                           if(student.isSelected()){
                                                               Toast.makeText(MainActivity.this,"You are already placed...",Toast.LENGTH_LONG).show();
                                                               return;

                                                           }



                                                           //Verifying the password and role of the user.


                                                           if(student.getRole().equals(roleselection))
                                                           {
                                                               //If correct then go to next activity.
                                                               Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                                                               startActivity(intent);
                                                           }
                                                           else
                                                           {
                                                               //If wrong then display the following message.
                                                               Toast.makeText(MainActivity.this,"Invalid credentials...",Toast.LENGTH_LONG).show();
                                                               return;
                                                           }
                                                       }

                                                   }
                                                   //If user does not exists.
                                                   else
                                                   {
                                                       Toast.makeText(MainActivity.this,"No such user exists...",Toast.LENGTH_LONG).show();
                                                       return;
                                                   }
                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }
                                           });
                                       }
                                       if(roleselection.equals("Company")) {
                                           query.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                   if (dataSnapshot.exists()) {
                                                       for (DataSnapshot user : dataSnapshot.getChildren()) {
                                                           Company company = user.getValue(Company.class);
                                                           if(company.role.equals(roleselection))
                                                           {
                                                               Intent intent = new Intent(MainActivity.this,ProfileCompanyActivity.class);
                                                               startActivity(intent);
                                                           }
                                                           else
                                                           {
                                                               Toast.makeText(MainActivity.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                                                               return;
                                                           }
                                                       }
                                                   }
                                                   else
                                                   {
                                                       Toast.makeText(MainActivity.this,"No such company exists...",Toast.LENGTH_LONG).show();
                                                       return;
                                                   }
                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }
                                           });
                                       }

                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this,"Please verify your email address...",Toast.LENGTH_LONG).show();
                                   }

                               }
                               else
                               {
                                   Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                               }
                           }
                       });
           }
       });
       forgotpasswordbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,ForgotPasswordActivity.class);
               startActivity(intent);
           }
       });
    }
}
