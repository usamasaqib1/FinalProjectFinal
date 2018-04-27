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
import android.widget.Toast;

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
public class mapFragment extends Fragment implements OnMapReadyCallback{

    View view;
    MapView mMapView;
    private GoogleMap googleMap;
    mainGroundView context;
    private GoogleMap mMap;
String langLat;
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
        sMapFragment.getMapAsync(this);


        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getActivity(), "FOLLLLLLLLL", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        //   googleMap.setMyLocationEnabled(true);
        // For dropping a marker at a point on the Map
        langLat=langLat.substring(10,langLat.length()-1);
        String[]langLat2=langLat.split(",");
        double latitude = Double.parseDouble(langLat2[0]);
        double longitude = Double.parseDouble(langLat2[1]);
        LatLng curLoc = new LatLng(latitude,longitude);
        googleMap.addMarker(new MarkerOptions().position(curLoc).title("Marker Title"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(curLoc).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}