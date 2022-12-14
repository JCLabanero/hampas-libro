package com.example.slambookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        View layout = findViewById(R.id.linearLayout3);
        EditText username = findViewById(R.id.editTextEmailAddress);
        EditText password = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView buttonSignUp = findViewById(R.id.textViewSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });
        /*        buttonLogin.setOnClickListener(tapListener);*/
    }
    /*    View.OnClickListener tapListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    Intent i = new Intent(context, HomeActivity.class);
    startActivity(i);
    }
    };*/
}