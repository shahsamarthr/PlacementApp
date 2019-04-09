package com.abc.mydemoapp.CompanyActivity;

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
import com.abc.mydemoapp.TPOActivity.Tan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestTANActivity extends AppCompatActivity {

    private Button btnverifytan,btnrequesttan;
    private EditText txttan,txtcmpname,txtcmpaddress,txtcmppass,txtcompemail;

    private DatabaseReference dbtan = FirebaseDatabase.getInstance().getReference("Tan");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tan);

        btnrequesttan = (Button)findViewById(R.id.btnrequesttan);
        btnverifytan = (Button)findViewById(R.id.btnverifytan);

        txtcmpaddress = (EditText)findViewById(R.id.txtcmpaddress);
        txtcmppass = (EditText)findViewById(R.id.txtcmppass);
        txtcmpname = (EditText)findViewById(R.id.txtcmpname);
        txtcompemail = (EditText)findViewById(R.id.txtcompemail);
        txttan = (EditText)findViewById(R.id.txttan);

        //Write Verification code here and requesttan

        btnrequesttan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestTANActivity.this,TanmailActivity.class);
                intent.putExtra("tan",txttan.getText().toString());
                intent.putExtra("cname",txtcmpname.getText().toString());
                intent.putExtra("cemail",txtcompemail.getText().toString());
                intent.putExtra("cpass",txtcmppass.getText().toString());
                intent.putExtra("caddress",txtcmpaddress.getText().toString());
                startActivity(intent);
            }
        });

        btnverifytan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbtan.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            Tan t = ds.getValue(Tan.class);
                            Log.w("Tan",t.getTan());
                            Log.w("Tan1",t.getCompanyaddress());
                            Log.w("Tan2",t.getCompanyemailadress());
                            Log.w("Tan3",t.getCompanyname());
                            Log.w("Tan4",t.getPass());
                            if(t.getCompanyname().equals(txtcmpname.getText().toString())&&t.getTan().equals(txttan.getText().toString()) &&t.getCompanyaddress().equals(txtcmpaddress.getText().toString())&&t.getPass().equals(txtcmppass.getText().toString())&&t.getCompanyemailadress().equals(txtcompemail.getText().toString()) )
                            {
                                Intent intent = new Intent(RequestTANActivity.this,CompanySignupActivity.class);
                                intent.putExtra("tan",txttan.getText().toString());
                                intent.putExtra("cname",txtcmpname.getText().toString());
                                intent.putExtra("cemail",txtcompemail.getText().toString());
                                intent.putExtra("cpass",txtcmppass.getText().toString());
                                intent.putExtra("caddress",txtcmpaddress.getText().toString());

                                startActivity(intent);
                                return;
                            }
                        }
                        Toast.makeText(RequestTANActivity.this,"Invalid credentials...",Toast.LENGTH_LONG).show();
                        return;
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
