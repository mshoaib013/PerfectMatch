package com.example.darkshadow.perfectmatch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class signInManually extends AppCompatActivity {
    EditText signInPasswordEditText,signInEmailEditText;
    ImageView signInSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_manually);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        signInPasswordEditText = (EditText) findViewById(R.id.signInPasswordEditText);
        signInEmailEditText = (EditText) findViewById(R.id.signInEmailEditText);
        signInSubmitButton = (ImageView) findViewById(R.id.signInSubmitButton);
        signInPasswordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (signInEmailEditText.getText().toString().length()>0){
                    signInSubmitButton.setImageResource(R.drawable.signinfinal);
                    Log.d("test",signInEmailEditText.getText().toString());
                }
                return false;

            }
        });
    }
}
