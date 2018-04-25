package com.example.xubii.finalprojectfinal;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class mainGroundView extends AppCompatActivity {

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groundmain);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        //    appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        groundViewPagerAdapter adapter = new groundViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new bookingFragment(),"Booking");
        adapter.addFragment(new detailFragment(),"Detail");
        adapter.addFragment(new mapFragment(),"Map");
        adapter.addFragment(new reviewFragment(),"Review");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }



}