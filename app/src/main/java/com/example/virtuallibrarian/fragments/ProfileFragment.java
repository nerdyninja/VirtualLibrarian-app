package com.example.virtuallibrarian.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.virtuallibrarian.R;
import com.example.virtuallibrarian.utils.SessionManager;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class ProfileFragment extends Fragment {
    SessionManager session;
    Context context;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(Context ctx){
        session=new SessionManager(ctx);
        context=ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Profile");
        Log.v("t","p");
        TextView name=(TextView)view.findViewById(R.id.profileName);
        TextView prn=(TextView)view.findViewById(R.id.profilePRN);
        TextView email=(TextView)view.findViewById(R.id.profileEmail);

        name.setText(session.getName());
        prn.setText(session.getKeyPrn());
        email.setText(session.getKeyEmail());
        Button btn = (Button)view.findViewById(R.id.logoutButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser(context);
                getActivity().finish();
            }
        });
        return view;
    }
}
