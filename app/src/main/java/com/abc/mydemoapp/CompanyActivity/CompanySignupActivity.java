package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.SelectRoleActivity;
import com.abc.mydemoapp.StudentsActivity.SignUpActivity;
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompanySignupActivity extends AppCompatActivity {

    String role;
    EditText pass,email,companyname,companyaddress,TAN;
    Button signupbtn,loginbtn;
    DatabaseReference databasecompany;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_signup);

        pass = (EditText)findViewById(R.id.pass);
        pass.setText(getIntent().getStringExtra("cpass"));
        pass.setEnabled(false);
        email = (EditText)findViewById(R.id.email);
        email.setText(getIntent().getStringExtra("cemail"));
        email.setEnabled(false);
        companyname = (EditText)findViewById(R.id.companyname);
        companyname.setText(getIntent().getStringExtra("cname"));
        companyname.setEnabled(false);



        companyaddress = (EditText)findViewById(R.id.companyaddress);
        companyaddress.setText(getIntent().getStringExtra("caddress"));
        companyaddress.setEnabled(false);
        TAN = (EditText)findViewById(R.id.TAN);
        TAN.setText(getIntent().getStringExtra("tan"));
        TAN.setEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        loginbtn =(Button)findViewById(R.id.loginbtn);
        signupbtn = (Button)findViewById(R.id.signupbtn);

        //Intent intent = getIntent();
        //role = intent.getStringExtra(SelectRoleActivity.EXTRA_TEXT);
        role="Company";
        //Here we are retrieving the user selection of the activity.
        databasecompany = FirebaseDatabase.getInstance().getReference(role);

        loginbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanySignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking password and confirm password match
                //if(pass.getText().toString().equals(pass1.getText().toString()))
                //{
                if(!TextUtils.isEmpty(companyname.getText().toString())&&!TextUtils.isEmpty(companyaddress.getText().toString())&&!TextUtils.isEmpty(TAN.getText().toString()))
                {
                    //Toast.makeText(SignUpActivity.this,"Calling firebase methods to register users...",Toast.LENGTH_LONG).show();


                    //Verification mail code
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            //if user has verified the email then register the user

                                            Toast.makeText(CompanySignupActivity.this, "User registered successfully please check your email for verification...",
                                                    Toast.LENGTH_LONG).show();
                                            email.setText("");
                                            pass.setText("");
                                            companyaddress.setText("");
                                            companyname.setText("");
                                            TAN.setText("");
                                        }
                                        else
                                        {
                                            Toast.makeText(CompanySignupActivity.this,task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });


                                String id = databasecompany.push().getKey();
                                Company company = new Company(id,companyname.getText().toString(),companyaddress.getText().toString(),TAN.getText().toString(),email.getText().toString(),pass.getText().toString(),role);
                                databasecompany.child(id).setValue(company);
                            }
                            else {
                                Toast.makeText(CompanySignupActivity.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                //if(pass.getText().toString()!=pass1.getText().toString())
                else
                {
                    Toast.makeText(CompanySignupActivity.this,"Fill all the fields ...",Toast.LENGTH_LONG).show();
                    return;
                }
               /* else
                {
                    Toast.makeText(SignUpActivity.this,"Password and ConfirmPassword Donot match...",Toast.LENGTH_LONG).show();
                    return;
                }*/

            }
        });
    }
}
