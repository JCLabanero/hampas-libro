package com.example.slambookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager; //layout linear, grid etc... also control layout
    RecyclerViewAdapter recyclerAdapter; //adapter that holds our design
    Button buttonAdd;
    Context context = this;
    ArrayList<Content> contentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void init() {
        contentList.add(new Content(R.drawable.ic_launcher_foreground,"Joanna Laine Pueyo","- John Carlo Labanero"));
        contentList.add(new Content(R.drawable.ic_launcher_foreground,"John Carlo Labanero","- John Carlo Labanero"));
        contentList.add(new Content(R.drawable.ic_launcher_foreground,"Joanna Laine Pueyo","- John Carlo Labanero"));
        contentList.add(new Content(null,null,null));
        recyclerView1 = findViewById(R.id.recyclerView);
        recyclerView1.hasFixedSize();
        layoutManager = new LinearLayoutManager(context);
//        layoutManager = new GridLayoutManager(context,/*Column*/2);
//        layoutManager = new StaggeredGridLayoutManager(/*Column*/2,LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerViewAdapter(context, R.layout.row_slam,contentList);
        recyclerView1.setAdapter(recyclerAdapter);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentList.add(new Content(R.drawable.ic_launcher_foreground,"new","new"));
                recyclerAdapter.notifyItemInserted(0);
                layoutManager.scrollToPosition(0);
                Toast.makeText(context, "new slam added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}