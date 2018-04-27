package com.example.xubii.finalprojectfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class mobileSignup extends AppCompatActivity {
EditText number, code;
Button send, verify, resend;
PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
FirebaseAuth mAuth;
Boolean mVarificationInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_signup);

        mAuth=FirebaseAuth.getInstance();
        number=(EditText)findViewById(R.id.editText);
        code=(EditText)findViewById(R.id.editText2);
        send=(Button) findViewById(R.id.button13);
        verify=(Button) findViewById(R.id.button14);
        resend=(Button) findViewById(R.id.button15);
        mVarificationInProgress=false;


       mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
          //      Log.d(TAG, "onVerificationCompleted:" + credential);

//                code.setText(credential.getSmsCode());
                mVarificationInProgress=false;
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
              //  Log.w(TAG, "onVerificationFailed", e);
                mVarificationInProgress=false;


                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    number.setError("Invalid Phone Number");
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Toast.makeText(mobileSignup.this, "Quota exceeded", Toast.LENGTH_SHORT).show();
                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
             //   Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                        //    Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();


                            Intent i=new Intent(mobileSignup.this, SignUpMob.class);
                            startActivity(i);
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                          //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                code.setError("Invalid code");
                            }
                        }
                    }
                });
    }

    public void send(View v)
    {
        if(!validatePhoneNumber())
        {
            return;
        }
        else
        {
            startPhoneNumberVerification(number.getText().toString());
        }
    }

    private boolean validatePhoneNumber() {
        String num=number.getText().toString();
        if(TextUtils.isEmpty(num))
        {
            number.setError("Invalid phone number");
            return false;
        }
        return true;
    }

    private void startPhoneNumberVerification(String s)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                s,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        mVarificationInProgress=true;
    }

    public void verify(View v)
    {
        String cc=code.getText().toString();
        if(TextUtils.isEmpty(cc))
        {
            code.setError("Can not be empty");
            return;
        }
        else
            verifyPhoneNumberWithCode(mVerificationId,cc);
    }

    private void verifyPhoneNumberWithCode(String mVerificationId, String cc) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, cc);
            signInWithPhoneAuthCredential(credential);
        }
        catch (IllegalArgumentException e)
        {
            Toast.makeText(this, "Wrong code entered", Toast.LENGTH_SHORT).show();
        }
    }

    public void resend(View v)
    {
        resendVerificationCode(number.getText().toString(),mResendToken);
    }

    private void resendVerificationCode(String s, PhoneAuthProvider.ForceResendingToken mResendToken) {
       try {
           PhoneAuthProvider.getInstance().verifyPhoneNumber(
                   s,        // Phone number to verify
                   60,                 // Timeout duration
                   TimeUnit.SECONDS,   // Unit of timeout
                   this,               // Activity (for callback binding)
                   mCallbacks, mResendToken);        // OnVerificationStateChangedCallbacks
       }
       catch (IllegalArgumentException e)
       {
           Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
       }
    }

}
