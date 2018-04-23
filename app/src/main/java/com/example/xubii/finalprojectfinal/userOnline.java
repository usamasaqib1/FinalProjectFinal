package com.example.xubii.finalprojectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class userOnline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_online);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
public void logOut(View v)
{
    Intent i= new Intent(this, login.class);
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    mAuth.signOut();
    LoginManager.getInstance().logOut();
    startActivity(i);
    finish();
}

}
