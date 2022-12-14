package com.example.slambookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    Button buttonAdd;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }



    public void init() {
        recyclerView1 = findViewById(R.id.recycler);
        buttonAdd = findViewById(R.id.buttonAdd);
    }
}