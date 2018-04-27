package com.example.xubii.finalprojectfinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartScreen extends AppCompatActivity {
    private static final String TAG ="StartScreen";
    Intent offlineUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        offlineUser   = new Intent(this, userOffline.class);



        if(isNetworkAvailable())
        {

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser!=null)
            {
                Intent i = new Intent(this,searchScreen.class);
                startActivity(i);
                finish();
            }
            else
            {
                Intent i = new Intent(this,signUp.class);
                startActivity(i);
                finish();
            }
        }
        else
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("No Internet Connection");
            dlgAlert.setTitle("Error Message");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.w(TAG, "APP BAND KR ");

                    startActivity(offlineUser);
                    finish();
                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
