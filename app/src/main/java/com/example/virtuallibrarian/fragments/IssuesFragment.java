package com.example.virtuallibrarian.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.virtuallibrarian.R;

/**
 * Created by rohitramaswamy on 25/02/17.
 */

public class IssuesFragment extends Fragment {
    public IssuesFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        Log.v("t","i");
        return view;
    }
}
