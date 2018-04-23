package com.example.xubii.finalprojectfinal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Uri selectedImage;
    FirebaseDatabase fbDB;
    DatabaseReference mRef;
    StorageReference mStorage;
    ProgressDialog pd;
    FirebaseAuth mAuth;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();

        mAuth=FirebaseAuth.getInstance();
        fbDB= FirebaseDatabase.getInstance() ;
        mRef= fbDB.getReference();
        mStorage= FirebaseStorage.getInstance().getReference();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent i = new Intent(this,rvActivity.class);
           // startActivity(i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void  uploadPic(View v)
    {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==1 && resultCode == Activity.RESULT_OK) {
        selectedImage=data.getData();
            //    selectedImage=Uri.fromFile(new File(data.getData().getPath()));
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView ig = findViewById(R.id.imageView);
                ig.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
   public void onClickDone(View v)
    {
         pd= new ProgressDialog(this);
                pd.setMessage("Creating User");
                pd.show();


 String groundName="", ownerName="",phone="", email="", location="", details="", ratting="0",votes="0" ;
 image="imageURL";
        EditText a = findViewById(R.id.editText2);
        if(a.length()!=0)
            groundName= a.getText().toString();

        a=findViewById(R.id.editText);
        if(a.length()!=0)
          ownerName=a.getText().toString();

        a=findViewById(R.id.editText3);
        if(a.length()!=0)
            phone=a.getText().toString();
        a=findViewById(R.id.editText4);
        if(a.length()!=0)
            email=a.getText().toString();
        a=findViewById(R.id.editText5);
        if(a.length()!=0)
            location=a.getText().toString();

        a=findViewById(R.id.editText8);
        if(a.length()!=0)
            details=a.getText().toString();

        StorageReference mSRef=mStorage.child("Images/"+groundName+".jpg");
        mSRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                image=downloadUrl.toString();


            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

        image=mSRef.child("Images").child(groundName+".jpg").toString();
        image= mStorage.child("Images").child(groundName+".jpg").toString();
        Task<Uri> u = mStorage.getDownloadUrl();
        image=u.toString();
        ground g = new ground(groundName,ownerName,location,phone,email,image,details);
        //mRef.child("Grounds").setValue(g);
        mRef.child("Grounds").child(groundName+" "+location).setValue(g);
        //mRef.child("Grounds").child(groundName).setValue(g);
    /*    mRef.child("grounds").child(groundName).child("ownerName").child(ownerName).setValue("true");
        mRef.child("grounds").child(groundName).child("location").child(location).setValue("true");
        mRef.child("grounds").child(groundName).child("phone").child(phone).setValue("true");
        mRef.child("grounds").child(groundName).child("email").child(email).setValue("true");
        mRef.child("grounds").child(groundName).child("image").child(image).setValue("true");
        mRef.child("grounds").child(groundName).child("details").child(details).setValue("true");
        mRef.child("grounds").child(groundName).child("ratting").child(ratting).setValue("true");
        mRef.child("grounds").child(groundName).child("votes").child(votes).setValue("true");
        pd.dismiss();
*/
        pd.dismiss();
        Intent i = new Intent(this,rvActivity.class);
      //  startActivity(i);
    }
}
