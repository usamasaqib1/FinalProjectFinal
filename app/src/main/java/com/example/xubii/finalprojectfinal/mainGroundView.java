package com.example.xubii.finalprojectfinal;


import android.content.Intent;
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

        Intent intent=getIntent();
        ground tempG=(ground)intent.getSerializableExtra("gName");
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        //    appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        TextView tv = (TextView) findViewById(R.id.textView2);
        Fragment m =new mapFragment();
        Bundle args=new Bundle();
        args.putString("langLat",tempG.getMapCods());
        m.setArguments(args);
        groundViewPagerAdapter adapter = new groundViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new bookingFragment(),"Booking");
        adapter.addFragment(new detailFragment(),"Detail");
        adapter.addFragment(m,"Map");
        adapter.addFragment(new reviewFragment(),"Review");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }



}