package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.database.SQLiteDBHelper;

public class RegisterActivity extends AppCompatActivity {
    Context context;
    EditText fullname;
    EditText email;
    EditText password;
    EditText passwordConfirm;
    Button signUp;
    TextView login;

    SQLiteDBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        database = new SQLiteDBHelper(context);
        signUp.setOnClickListener(clickHandler);
        login.setOnClickListener(clickHandler);
    }

    public void init(){
        context = this;
        fullname = findViewById(R.id.editTextFullName);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        passwordConfirm = findViewById(R.id.editTextConfirmPassword);

        signUp = findViewById(R.id.buttonSignup);
        login = findViewById(R.id.textViewLogin);
    }

    View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonSignup:
                    boolean check = password.getText().toString().equals(passwordConfirm.getText().toString());
                    if(!check) {Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT).show();return;}
                    if(database.insertIntoTable(fullname.getText().toString(),email.getText().toString(),password.getText().toString())){
                        Toast.makeText(context, "Sign up account successful ", Toast.LENGTH_SHORT).show();
                        finish();
                    } else Toast.makeText(context, "Sign up account failed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textViewLogin:
                    finish();
                    break;
            }
        }
    };

}