package com.example.xubii.finalprojectfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Aziz on 4/23/2018.
 */
public class mapFragment extends Fragment{

    View view;
    MapView mMapView;
    private GoogleMap googleMap;
    mainGroundView context;
String langLat;
    public  mapFragment(Context c)
    {
        context=(mainGroundView)c;
    }
    public mapFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.getArguments();
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        if (getArguments() != null)
        {
            langLat = getArguments().getString("langLat");
        }

        SupportMapFragment sMapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
     //  MapView sMapFragment = (MapView) rootView.findViewById(R.id.mapView);
        sMapFragment.getMapAsync(context);


        return rootView;
    }

}