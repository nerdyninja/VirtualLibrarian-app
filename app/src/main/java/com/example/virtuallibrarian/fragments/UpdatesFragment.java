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

public class UpdatesFragment extends Fragment {

    public UpdatesFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_updates, container, false);
        Log.v("t","u");
        return view;
    }
}
