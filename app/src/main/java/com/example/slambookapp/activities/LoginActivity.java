package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.database.SQLiteDBHelper;

public class LoginActivity extends AppCompatActivity {
    Context context;
    private SQLiteDBHelper db;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        text = findViewById(R.id.welcome);
        View layout = findViewById(R.id.linearLayout3);
        EditText username = findViewById(R.id.editTextEmailAddress);
        EditText password = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView buttonSignUp = findViewById(R.id.textViewSignUp);

        db = new SQLiteDBHelper(context);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectAndDisplayAllUser();


                Intent intent = new Intent(context, QuestionActivity.class);
                startActivity(intent);
            }
        });


    }

    private void selectAndDisplayAllUser() {
        text.setText("");
        Cursor result = db.selectAllUser();
        if(result.getCount()==0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer userList = new StringBuffer();
            while (result.moveToNext()){
                userList.append("ID: "+result.getString(0)+
                        "/Name: "+result.getString(1)+
                        "/Email: "+result.getString(2)+"" +
                        "/Password: "+result.getString(3));
            }
            text.setText(userList);
        }
    }
}