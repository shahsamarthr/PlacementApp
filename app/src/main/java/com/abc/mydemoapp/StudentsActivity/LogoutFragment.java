package com.abc.mydemoapp.StudentsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abc.mydemoapp.MainActivity;
import com.abc.mydemoapp.R;

public class LogoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Intent intent = new Intent(this.getActivity(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//This line and below line are used to prevent user from going
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);// back to profileactivity by pressing back button.If the user is logged out of the app.
        startActivity(intent);

        return inflater.inflate(R.layout.fragment_logout,container,false);
    }
}
