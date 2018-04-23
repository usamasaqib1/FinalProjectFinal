package com.example.xubii.finalprojectfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class userOffline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_offline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        //    appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"Tab1");
        adapter.addFragment(new Tab2Fragment(),"Tab2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
