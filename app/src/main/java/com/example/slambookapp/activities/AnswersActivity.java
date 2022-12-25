package com.example.slambookapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.database.SQLiteDBHelper;
import com.example.slambookapp.viewholders.RecyclerViewAdapterForAnswers;
import com.example.slambookapp.classes.ContentAnswers;

import java.util.ArrayList;

public class AnswersActivity extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager; //layout linear, grid etc... also control layout
    RecyclerViewAdapterForAnswers recyclerAdapter; //adapter that holds our design
    TextView question;
    Button buttonAdd;
    Context context;
    ArrayList<ContentAnswers> contentAnswersList = new ArrayList<>();
    int userID,questionID,userToAnswer;
    SQLiteDBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        init();
        retrieveAnswer();
    }

    public void init() {
        context = this;
        database = new SQLiteDBHelper(context);
        question = findViewById(R.id.textViewQuestion);

        Intent intent = getIntent();
        userID = intent.getIntExtra("user_id_owner",0);
        questionID = intent.getIntExtra("ID",0);
        userToAnswer = intent.getIntExtra("user_id_to_answer",0);
        question.setText(intent.getStringExtra("question"));

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
                insertNewAnswer();
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
//                                deleteAnswer(position,contentAnswersList.get(position).getAnswer());
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

    private void insertNewAnswer() {
        String ans = "My " + Math.random();
        if(database.insertForeignAnswer(ans,questionID,userID,userToAnswer)){
            contentAnswersList.add(0, new ContentAnswers(R.drawable.ic_launcher_background,ans,returnUsername(String.valueOf(userToAnswer))));
            recyclerAdapter.notifyItemInserted(0);
            layoutManager.scrollToPosition(0);
            Toast.makeText(context, "new answer added", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(context, "answer adding failed", Toast.LENGTH_SHORT).show();
    }
    private void retrieveAnswer(){
        Cursor result = database.selectAnswerByQuestionIDAndUserID(String.valueOf(questionID),String.valueOf(userID));
        if(result.getCount()==0) Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
        else {
            while(result.moveToNext())
                contentAnswersList.add(new ContentAnswers(R.drawable.ic_launcher_background,result.getString(1), returnUsername(result.getString(3))));
        }
    }
    private String returnUsername(String id){
        Cursor result = database.selectUserByID(id);
        if(result.getCount()!=0){
            result.moveToNext();
            return result.getString(1);
        }
        return id;
    }
}