package com.example.xubii.finalprojectfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class rvActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    private static final String TAG ="rvActivity";
    private static final int VOICE_RECOGNITION_REQUEST = 1234;
    FirebaseDatabase database;
    DatabaseReference mRef;
    FirebaseAuth mAuth;
    ArrayList<ground>groundList;
    GestureDetector gestureDetector;
    RecyclerView rv;
    String[]key;
    MyAdapter adapter;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pd= new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        Intent in=getIntent();
        String nkey =in.getStringExtra("keyword");
        EditText search = (EditText) findViewById(R.id.searchBar);
        search.setText(nkey);

        nkey=nkey.toLowerCase();
       key=nkey.split(" ");
        groundList=new ArrayList<>();

        mAuth=FirebaseAuth.getInstance();
         database = FirebaseDatabase.getInstance();
         mRef = database.getReference("Grounds");

        // Read from the database
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    for (int i = 0; i < key.length; i++) {
                        if (ds.getKey().toLowerCase().contains(key[i])) {
                            String s=ds.getKey();
                            ground temp = new ground();

                        //    temp.setGroundName(ds.child(key[i]).getValue(ground.class).getGroundName());
                         //   temp.setGroundName((String)ds.child(s).child("groundName").getValue());
                            temp.setGroundName(ds.child("groundName").getValue(String.class));
                            temp.setDetails(ds.child("details").getValue(String.class));
                            temp.setEmail(ds.child("email").getValue(String.class));
                            temp.setImage(ds.child("image").getValue(String.class));
                            temp.setLocation(ds.child("location").getValue(String.class));
                            temp.setOwnerName(ds.child("ownerName").getValue(String.class));
                            temp.setPhone(ds.child("phone").getValue(String.class));
                            temp.setRatting(ds.child("ratting").getValue(Integer.class));
                            temp.setVotes(ds.child("votes").getValue(Integer.class));
                            temp.setMapCods(ds.child("mapCods").getValue(String.class));
                            groundList.add(temp);
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }

                }
                pd.dismiss();
                if(groundList.isEmpty())
                {
                    rv.setVisibility(View.INVISIBLE);
                    TextView textView=(TextView) findViewById(R.id.textView4);
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        rv = (RecyclerView) findViewById(R.id.rcv);
       adapter = new MyAdapter(groundList, R.layout.recyclerviewlayout,this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnItemTouchListener(this);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null) {
                    //if tap was performed on some recyclerview row item
                    Integer i = rv.getChildAdapterPosition(child); //index of item which was clicked
                   String n= groundList.get(i).getGroundName()+" "+groundList.get(i).getLocation();
                   ground tempG=groundList.get(i);
                    Intent ii =  new Intent(rvActivity.this,mainGroundView.class);
                    ii.putExtra("gName",tempG.getGroundName()+" "+tempG.getLocation());
                    startActivity(ii);

                }
                return true;
            }

        });
    }

    public void searchAgain(View v)
    {
        EditText searchbar= (EditText) findViewById(R.id.searchBar);

        Intent i= new Intent(this, rvActivity.class);
        i.putExtra("keyword",searchbar.getText().toString());
        startActivity(i);
        finish();
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());


        if (childView != null && gestureDetector.onTouchEvent(e)) {

            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public void speechToText(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Please speak slowly and enunciate clearly.");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST && resultCode == RESULT_OK) {
            ArrayList matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            EditText textView = (EditText) findViewById(R.id.searchBar);
            String firstMatch = String.valueOf(matches.get(0));
            textView.setText(firstMatch);
        }
    }
}
//kjgkgkj