package com.example.slambookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
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

        recyclerAdapter.setCustomOnItemClickListener(new RecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
//                        AlertDialog.Builder(context);
                alertBuilder.setTitle("Warning!")
                        .setMessage("Confirm delete?")
                        .setCancelable(true)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                contentList.remove(position);
                                recyclerAdapter.notifyItemRemoved(position);
                                layoutManager.scrollToPosition(position);
                                Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
                AlertDialog warning = alertBuilder.create();
                warning.show();
            }
        });
    }
}