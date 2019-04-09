package com.abc.mydemoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.abc.mydemoapp.CompanyActivity.Company;
import com.abc.mydemoapp.CompanyActivity.CompanySignupActivity;
import com.abc.mydemoapp.CompanyActivity.RequestTANActivity;
import com.abc.mydemoapp.StudentsActivity.SignUpActivity;

public class SelectRoleActivity extends AppCompatActivity {

    public static  final String EXTRA_TEXT = "com.abc.mydemoapp.EXTRA_TEXT";

    Spinner spinner;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        spinner = (Spinner)findViewById(R.id.spinner1);
        register = (Button)findViewById(R.id.signupbtn);



        ArrayAdapter<String> myadapter = new ArrayAdapter<String>
                (SelectRoleActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        //Basically adpater is the container that will hold the values which we will read through spinner.
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = spinner.getSelectedItem().toString();
                if(role.equals("Student")) {
                    Intent intent = new Intent(SelectRoleActivity.this, SignUpActivity.class);
                    intent.putExtra(EXTRA_TEXT, role);
                    //Here EXTRA_TEXT is the key and role is the value.
                    //By using above line we are passing the user selected role from this activity to SignUpActivity.
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(SelectRoleActivity.this, RequestTANActivity.class);
                    intent.putExtra(EXTRA_TEXT, role);
                    //Here EXTRA_TEXT is the key and role is the value.
                    //By using above line we are passing the user selected role from this activity to CompanySignupActivity.
                    startActivity(intent);
                }
            }
        });
    }
}
