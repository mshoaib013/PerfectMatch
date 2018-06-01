package com.example.darkshadow.perfectmatch;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Welcome extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private String email="shoaib.mehedi@gmail.com",password="mshoaib1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        googleSignInButton=(ImageView) findViewById(R.id.googleSignInButton);
//        googleSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("ClickController", "google working");
//            }
//        });
//        facebookSignInButton=(ImageView) findViewById(R.id.facebookSignInButton);
//        facebookSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("ClickController", "facebook working");
//            }
//        });
        findViewById(R.id.googleSignInButton).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.facebookSignInButton).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.loginButton).setOnClickListener((View.OnClickListener) this);
        mAuth = FirebaseAuth.getInstance();


        //test
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.setValue("Hello, World!!!!!");

        DatabaseReference myReff = database.getReference("haha");

        myReff.setValue("ohh fuck, World!");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("CheckIfSignedIn", String.valueOf(mAuth.getCurrentUser()));
        if (mAuth.getCurrentUser()==null)
            Toast.makeText(Welcome.this, "Not Logged In!!!!",Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.googleSignInButton) {
//            signupNewUser();
            getUserInfo();
            Log.d("ClickController", String.valueOf(i));
        }
        else if (i == R.id.facebookSignInButton){
            startActivity(new Intent(this,SignUpManually.class));

            Log.d("ClickController", String.valueOf(i));
        }
        else if (i == R.id.loginButton){
            startActivity(new Intent(this,signInManually.class));
            Log.d("ClickController", String.valueOf(i));
        }
    }

    public void signinExistinguser(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Welcome.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void signupNewUser(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Welcome.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void getUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();

            Log.d("photourl", user.getDisplayName());
            Log.d("photourl", user.getEmail());
            Log.d("photourl", String.valueOf(user.getPhotoUrl()));
            Log.d("photourl", user.getPhoneNumber());
            Log.d("photourl", user.getProviderId());
            Log.d("photourl", user.getUid());
        }
    }
}