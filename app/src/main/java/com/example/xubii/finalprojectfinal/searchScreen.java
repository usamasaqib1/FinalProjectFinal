package com.example.xubii.finalprojectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class searchScreen extends AppCompatActivity {
    private static final int VOICE_RECOGNITION_REQUEST = 1234;
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
