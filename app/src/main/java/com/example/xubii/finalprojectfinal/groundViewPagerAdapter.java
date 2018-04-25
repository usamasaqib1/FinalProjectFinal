package com.example.xubii.finalprojectfinal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Aziz on 4/23/2018.
 */


public class groundViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentList = new ArrayList<>();
    private final ArrayList<String> fragmentListNames = new ArrayList<>();



    public groundViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentListNames.get(position);
    }


    public void addFragment(Fragment fragment, String string)
    {
        fragmentList.add(fragment);
        fragmentListNames.add(string);
    }






}