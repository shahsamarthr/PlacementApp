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
import android.widget.TextView;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;
import com.abc.mydemoapp.StudentsActivity.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    TextView textview1,textview2,textview3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        textview1 = (TextView) view.findViewById(R.id.textView1);
//        textview1=(TextView)view.findViewById(R.id.textView1)
        textview2 = (TextView)view.findViewById(R.id.textView2);
        textview3 = (TextView)view.findViewById(R.id.textView3);
        textview1.setText("Harsh Srivastava");

        textview2.setText("harsrivastva18@gmail.com");
        textview3.setText("123456");

        Button signoutbutton = (Button)view.findViewById(R.id.signoutbutton);
        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//This line and below line are used to prevent user from going
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// back to profileactivity by pressing back button.If the user is logged out of the app.
                startActivity(intent);
            }
        });

        return view;
    }
}
