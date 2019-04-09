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
import com.abc.mydemoapp.StudentsActivity.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter implements ListAdapter
{
    DatabaseReference databasestudent = FirebaseDatabase.getInstance().getReference("Student");
    public static  final String EXTRA_TEXT = "com.abc.mydemoapp.TPOActivity.EXTRA_TEXT";
    private ArrayList<String> sname = new ArrayList<String>();
    private Context context;
    private String key;
    private String email;
    private String password;
    private ArrayList<String> keyList = new ArrayList<>();
    int count=0;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser firebaseUser ;
    FirebaseAuth mauth = FirebaseAuth.getInstance();


    ListViewAdapter(ArrayList<String> sname,ArrayList<String> keyList,Context context,String key,String email,String password)
    {
        this.sname=sname;
        this.keyList = keyList;
        this.context=context;
        this.key=key;
        this.email=email;
        this.password=password;
    }

    @Override
    public int getCount() {
        return sname.size();
    }

    @Override
    public Object getItem(int position) {
        return sname.get(position);
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
            view = inflater.inflate(R.layout.customstudentlistview, null);
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

        TextView txtstudentname = (TextView)view.findViewById(R.id.txtstudentname);
        txtstudentname.setText(sname.get(position));

        Button btnupdate = (Button)view.findViewById(R.id.btnupdate);
        Button btndelete = (Button)view.findViewById(R.id.btndelete);
        btndelete.setTag(position);

        for(int i=0;i<keyList.size();i++)
            Log.w(keyList.get(i),"Key of "+i);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // count  = (Integer)v.getTag();
                //count=position;
                Log.w(String.valueOf(position),"Hello");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Do you want to delete this account?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    //Here due to this listener we can react on button click inside the dialog
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthCredential authCredential = EmailAuthProvider.getCredential("harshsrivastva18@gmail.com", "123456");
                        //Log.w(firebaseUser.getUid(),"UID");

                        deletestudent(keyList.get(position));

//                        firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//                            public void onComplete(@NonNull Task<Void> task) {
//                                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            //Log.w(key,"Key for delete is .....");
//                                            //Toast.makeText(context,"Student deleted sucessfully...",Toast.LENGTH_LONG).show();
//                                        }
//                                        else
//                                        {
//                                            Toast.makeText(context,task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                });
//                            }
//                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //Now we are going to create a dialog box
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StudentUpdateActivity.class);
                i.putExtra(EXTRA_TEXT, keyList.get(position));
                Log.w(keyList.get(position),"Key in listviewadapter");
                context.startActivity(i);
            }
        });
        return view;
    }
    private void deletestudent(String key1)
    {
        DatabaseReference dbstudent = FirebaseDatabase.getInstance().getReference("Student").child(key1);
        dbstudent.removeValue();
        Toast.makeText(context,"Student deleted successfully",Toast.LENGTH_LONG).show();
        return;
    }
}
