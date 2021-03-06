package com.example.xubii.finalprojectfinal;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class mainGroundView extends AppCompatActivity{

    private mainGroundView context;

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;
     ground temp;
    String nname;
    MapView mMapView;
    String langLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groundmain);

        Intent intent=getIntent();
          nname=intent.getStringExtra("gName");
          temp=new ground();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();


        // Read from the database
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                            temp.setGroundName(ds.child("Grounds").child(nname).child("groundName").getValue(String.class));
                            temp.setDetails(ds.child("Grounds").child(nname).child("details").getValue(String.class));
                            temp.setEmail(ds.child("Grounds").child(nname).child("email").getValue(String.class));
                            temp.setImage(ds.child("Grounds").child(nname).child("image").getValue(String.class));
                            temp.setLocation(ds.child("Grounds").child(nname).child("location").getValue(String.class));
                            temp.setOwnerName(ds.child("Grounds").child(nname).child("ownerName").getValue(String.class));
                            temp.setPhone(ds.child("Grounds").child(nname).child("phone").getValue(String.class));
                            temp.setRatting(ds.child("Grounds").child(nname).child("ratting").getValue(Integer.class));
                            temp.setVotes(ds.child("Grounds").child(nname).child("votes").getValue(Integer.class));
                            temp.setMapCods(ds.child("Grounds").child(nname).child("mapCods").getValue(String.class));

                Toast.makeText(mainGroundView.this, "Data reading successful", Toast.LENGTH_SHORT).show();


                langLat=temp.getMapCods();
                tabLayout = (TabLayout) findViewById(R.id.tablayout);
                //    appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
                viewPager = (ViewPager) findViewById(R.id.view_pager);

                ImageView ig=(ImageView) findViewById(R.id.imageView);
                StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Images/"+temp.getGroundName()+" "+temp.getLocation()+".jpg");
                Glide.with(mainGroundView.this)
                        .using(new FirebaseImageLoader())
                        .load(mStorage)
                        .into(ig);
                TextView tv = (TextView) findViewById(R.id.textView2);
                tv.setText(temp.getGroundName());

                Fragment map =new mapFragment();
                Bundle args=new Bundle();
                args.putString("langLat",temp.getMapCods());
                map.setArguments(args);


                Fragment det=new detailFragment();
                Bundle detArgs=new Bundle();
                detArgs.putString("det",temp.getDetails());
                det.setArguments(detArgs);

                groundViewPagerAdapter adapter = new groundViewPagerAdapter(getSupportFragmentManager());
                adapter.addFragment(new bookingFragment(),"Booking");
                adapter.addFragment(det,"Detail");
                adapter.addFragment(map,"Map");
                adapter.addFragment(new reviewFragment(),"Review");
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

               // mMapView=(MapView) findViewById(R.id.mapView);
               // mMapView.getMapAsync(mainGroundView.this);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(mainGroundView.this, "Data reading failed", Toast.LENGTH_SHORT).show();
            }
        });



    }

}