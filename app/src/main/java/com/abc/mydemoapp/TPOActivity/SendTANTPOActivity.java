package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.mydemoapp.R;

public class SendTANTPOActivity extends AppCompatActivity {

    EditText mRecipientEt,mSubjectEt,mMessageEt;
    Button msendEmailbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tantpo);

        mRecipientEt=findViewById(R.id.recipientEt);
        mSubjectEt=findViewById(R.id.subjectEt);
        mMessageEt=findViewById(R.id.messageEt);
        msendEmailbtn=findViewById(R.id.sendEmailbtn);

        mRecipientEt.setText(getIntent().getStringExtra("companyemail").trim());
        mMessageEt.setText(getIntent().getStringExtra("tannumber").trim());


        msendEmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recipient=mRecipientEt.getText().toString().trim();//trim will remove space before and after space.
                String subject=mSubjectEt.getText().toString().trim();
                String message=mMessageEt.getText().toString().trim();
                sendEmail(recipient, subject,message);
            }
        });
    }
    private void sendEmail(String recipient, String subject, String message) {
        Intent mEmailIntent=new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        //put recipient email in intent
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT,message);
        try
        {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));
        }
        catch (Exception e)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }

    }
}
