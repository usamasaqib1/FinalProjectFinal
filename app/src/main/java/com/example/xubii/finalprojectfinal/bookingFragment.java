package com.example.xubii.finalprojectfinal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aziz on 4/23/2018.
 */

public class bookingFragment extends Fragment {

    View view;

    public bookingFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.booking_fragment,container,false);
        return view;
    }
}