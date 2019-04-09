package com.abc.mydemoapp.CompanyActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abc.mydemoapp.R;

public class FeedbackFragment extends Fragment {
    EditText mRecipientEt,mSubjectEt,mMessageEt;
    Button msendEmailbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companyfeedback, container, false);

        mRecipientEt = (EditText) view.findViewById(R.id.recipientEt);
        mSubjectEt = (EditText) view.findViewById(R.id.subjectEt);
        mMessageEt = (EditText) view.findViewById(R.id.messageEt);
        msendEmailbtn =(Button)view.findViewById(R.id.sendEmailbtn);

        mRecipientEt.setText("harshsrivastva18@gmail.com");
        //mRecipientEt.setText(getIntent().getStringExtra("companyemail").trim());
        //mMessageEt.setText(getIntent().getStringExtra("tannumber").trim());


        msendEmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String recipient = mRecipientEt.getText().toString().trim();//trim will remove space before and after space.
                String subject = mSubjectEt.getText().toString().trim();
                String message = mMessageEt.getText().toString().trim();
                sendEmail(recipient, subject, message);
            }
        });


        return view;
    }
    private void sendEmail (String recipient, String subject, String message)
    {
        Intent mEmailIntent = new Intent(Intent.ACTION_SEND);
        mEmailIntent.setData(Uri.parse("mailto:"));
        mEmailIntent.setType("text/plain");
        //put recipient email in intent
        mEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        mEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mEmailIntent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(mEmailIntent, "Choose an Email Client"));
        } catch (Exception e) {
            Toast.makeText(getActivity(),  "", Toast.LENGTH_SHORT).show();
        }

    }


}

