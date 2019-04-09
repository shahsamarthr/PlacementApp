package com.abc.mydemoapp.TPOActivity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendTANFragment extends Fragment {

    private Button btnsendtan;
    private EditText txtcmpname,txttan,txtcmpemail,txtcmpaddress,txtcmppass;
    private DatabaseReference dbtan;
    private String companyname;
    private String tannumber;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_send_tan,container,false);
        btnsendtan = (Button)view.findViewById(R.id.btnsendtan);
        txtcmpname = (EditText)view.findViewById(R.id.txtcmpname);
        txttan = (EditText)view.findViewById(R.id.txttan);
        txtcmpemail = (EditText)view.findViewById(R.id.txtcmpemail);
        txtcmpaddress = (EditText)view.findViewById(R.id.txtcmpaddress);
        txtcmppass = (EditText)view.findViewById(R.id.txtcmppass);
        dbtan = FirebaseDatabase.getInstance().getReference("Tan");
        btnsendtan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String companyname, String tan, String companyemailadress, String companyaddress, String pass
                    companyname = txtcmpname.getText().toString();
                    tannumber = txttan.getText().toString();
                    String id = dbtan.push().getKey();
                    Tan tan = new Tan(companyname,tannumber,txtcmpemail.getText().toString(),txtcmpaddress.getText().toString(),txtcmppass.getText().toString());

                    dbtan.child(id).setValue(tan);
                   // Toast.makeText(getContext(), "Data inserted successfully...",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),SendTANTPOActivity.class);
                intent.putExtra("companyname",companyname);
                intent.putExtra("tannumber",tannumber);
                intent.putExtra("companyemail",txtcmpemail.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}
