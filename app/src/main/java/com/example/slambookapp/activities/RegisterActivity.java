package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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
        ConstraintLayout resigterAct = findViewById(R.id.registerAct);
        AnimationDrawable anim = (AnimationDrawable) resigterAct.getBackground();
        anim.setEnterFadeDuration(1000);
        anim.setExitFadeDuration(5000);
        anim.start();

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
                    /*if(fullname.getText() == null ||email.getText().equals("")||password.getText().equals("")||password.getText().equals("")){
                        Toast.makeText(context, "fill up all information first", Toast.LENGTH_SHORT).show();
                        return;
                    }*/
                    String name = String.valueOf(fullname.getText()),
                            username = String.valueOf(email.getText()),
                                    pass = String.valueOf(password.getText()),
                                            passConfirm = String.valueOf(passwordConfirm.getText());
                    if(name.matches("")||username.matches("")||pass.matches("")||passConfirm.matches("")){
                        Toast.makeText(context, "fill up all information first", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(!check) {Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT).show();return;}
                    if(database.insertIntoUserTable(fullname.getText().toString(),email.getText().toString(),password.getText().toString())){
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