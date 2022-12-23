package com.example.slambookapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.viewholders.RecyclerViewAdapterForAnswers;
import com.example.slambookapp.classes.ContentAnswers;

import java.util.ArrayList;

public class AnswersActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager; //layout linear, grid etc... also control layout
    RecyclerViewAdapterForAnswers recyclerAdapter; //adapter that holds our design
    TextView question;
    Button buttonAdd;
    Context context = this;
    ArrayList<ContentAnswers> contentAnswersList = new ArrayList<>();
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        init();
        Intent intent = getIntent();
        userID = intent.getIntExtra("Key",0);
        question.setText(intent.getStringExtra("question"));
    }

    public void init() {
        question = findViewById(R.id.textViewQuestion);
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"Joanna Laine Pueyo","- John Carlo Labanero"));
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"Angel Jane Labanero","- John Carlo Labanero"));
        contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_foreground,"Laine Pueyo","- John Carlo Labanero"));
        recyclerView1 = findViewById(R.id.recyclerView);
        recyclerView1.hasFixedSize();
        layoutManager = new LinearLayoutManager(context);
//        layoutManager = new GridLayoutManager(context,/*Column*/2);
//        layoutManager = new StaggeredGridLayoutManager(/*Column*/2,LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerViewAdapterForAnswers(context, R.layout.row_slam_answers, contentAnswersList);
        recyclerView1.setAdapter(recyclerAdapter);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentAnswersList.add(0, new ContentAnswers(R.drawable.ic_launcher_foreground,"new","new"));
                recyclerAdapter.notifyItemInserted(0);
                layoutManager.scrollToPosition(0);
                Toast.makeText(context, "new slam added", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerAdapter.setCustomOnItemClickListener(new RecyclerViewAdapterForAnswers.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Warning!")
                        .setMessage("Confirm delete?")
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
                            public void onClick(DialogInterface dialogInterface, int i) {/*No action*/}
                        });
                AlertDialog warning = alertBuilder.create();
                warning.show();
            }
        });
    }
}