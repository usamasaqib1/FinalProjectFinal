package com.example.xubii.finalprojectfinal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Aziz on 4/23/2018.
 */
public class detailFragment extends Fragment {

    View view;
    String details;

    public detailFragment() {


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_fragment,container,false);

        if (getArguments() != null)
        {
            details = getArguments().getString("det");
        }

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(details);

        return view;
    }
}