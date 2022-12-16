package com.example.slambookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AnswerSlamActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager; //layout linear, grid etc... also control layout
    RecyclerViewAdapter recyclerAdapter; //adapter that holds our design
    Button buttonAdd;
    Context context = this;
    ArrayList<ContentAnswers> contentAnswersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void init() {
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"Joanna Laine Pueyo","- John Carlo Labanero"));
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"John Carlo Labanero","- John Carlo Labanero"));
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"Joanna Laine Pueyo","- John Carlo Labanero"));
        recyclerView1 = findViewById(R.id.recyclerView);
        recyclerView1.hasFixedSize();
        layoutManager = new LinearLayoutManager(context);
//        layoutManager = new GridLayoutManager(context,/*Column*/2);
//        layoutManager = new StaggeredGridLayoutManager(/*Column*/2,LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerViewAdapter(context, R.layout.row_slam, contentAnswersList);
        recyclerView1.setAdapter(recyclerAdapter);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("This should be the question")
                            .setMessage("Enter your answer")
                            .setCancelable(true)
                            .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"new","new"));//Your image
                                    recyclerAdapter.notifyItemInserted(0);
                                    layoutManager.scrollToPosition(0);
                                    Toast.makeText(context, "new answer added", Toast.LENGTH_SHORT).show();
                                }
                            });
                AlertDialog answer = alertBuilder.create();
                answer.show();
            }
        });

        recyclerAdapter.setCustomOnItemClickListener(new RecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {/*
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Warning!")
                        .setMessage("Confirm delete "+ contentAnswersList.get(position).getName()+"s' answer")
                        .setCancelable(true)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                contentAnswersList.remove(position);
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
                warning.show();*/
            }
        });
    }
}