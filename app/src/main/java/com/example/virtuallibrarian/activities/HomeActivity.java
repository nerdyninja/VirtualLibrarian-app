package com.example.virtuallibrarian.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.virtuallibrarian.R;
import com.example.virtuallibrarian.fragments.BooksFragment;
import com.example.virtuallibrarian.fragments.IssuesFragment;
import com.example.virtuallibrarian.fragments.ProfileFragment;
import com.example.virtuallibrarian.fragments.UpdatesFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.v("t","t");
        BottomBar bottomBar = (BottomBar)findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                if(tabId==R.id.fourth)
                {
                    fragment = new ProfileFragment();
                }
                else if(tabId==R.id.second)
                {
                    fragment = new BooksFragment();
                }
                else if(tabId==R.id.third)
                {
                    fragment = new IssuesFragment();
                }
                else if(tabId==R.id.first)
                {
                    fragment = new UpdatesFragment(HomeActivity.this);
                }

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, fragment);
                    ft.commit();
                }
            }
        });
    }
}
