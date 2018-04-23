package com.example.xubii.finalprojectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class searchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void search(View v)
    {
        Intent i = new Intent(this,rvActivity.class);
        EditText editText=(EditText) findViewById(R.id.searchBar);
        String string=editText.getText().toString();
        i.putExtra("keyword",string);
        startActivity(i);
    }

    public void userProfile(View v)
    {
        Intent i = new Intent(this, userOnline.class);
        startActivity(i);

    }
}
