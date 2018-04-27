package com.example.xubii.finalprojectfinal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SignUpMob extends AppCompatActivity {

    ProgressDialog pd;
    private FirebaseAuth mAuth;
    FirebaseDatabase fbDB;
    DatabaseReference mRef;
    StorageReference mStorage;
    String tname, tpass, tcpass;
    Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mob);
    }
    public void submit(View v)
    {
        EditText a = (EditText) findViewById(R.id.editText2);
        tname=a.getText().toString();

        a=findViewById(R.id.editText3);
        tpass=a.getText().toString();

        a=findViewById(R.id.editText4);
        tcpass=a.getText().toString();

        if(tname.isEmpty() || tpass.isEmpty()||tcpass.isEmpty()||selectedImage==null)
        {
            Toast.makeText(this, "Error: Some field(s) missing", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (tpass.equals(tcpass) == false)
            {
                a.setError("Password does not match");
            }
            else
            {
                pd= new ProgressDialog(this);
                pd.setMessage("Creating Ground");
                pd.show();

                mAuth=FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                OnlineUserInformation temp = new OnlineUserInformation();
                temp.setUid(user.getUid());
                temp.setName(tname);
                temp.setPassword(tpass);
                temp.setType("mobile");
                temp.setContact(user.getProviderId());
                fbDB= FirebaseDatabase.getInstance() ;
                mRef= fbDB.getReference();
                mStorage= FirebaseStorage.getInstance().getReference();
                mRef.child("Users").child(user.getUid()).setValue(temp);

                StorageReference mSRef = mStorage.child("Images/" + user.getUid() + ".jpg");
                mSRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(SignUpMob.this, "image upload fail", Toast.LENGTH_SHORT).show();
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });

                pd.dismiss();
                Intent intent = new Intent(this,searchScreen.class);
                startActivity(intent);
                finish();
            }
        }



    }

    public void  uploadPic(View v)
    {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 1);

    }
    public void cameraUpload (View v)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1111);
        }
    }
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
        else  if (requestCode == 1111 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Uri path = saveToInternalStorage(imageBitmap);
            //  Uri imgUri = Uri.parse(path);

            selectedImage=path;
            ImageView myImageView = (ImageView) findViewById(R.id.imageView);
            //myImageView.setImageBitmap(imageBitmap);
            myImageView.setImageURI(selectedImage);
        }

    }

    private Uri saveToInternalStorage(Bitmap bitmap){
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(file, "UniqueFileName"+".jpg");
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI;
    }
}
