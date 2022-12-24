package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.classes.ContentQuestions;
import com.example.slambookapp.database.SQLiteDBHelper;
import com.example.slambookapp.viewholders.RecyclerViewAdapterForQuestions;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterForQuestions recyclerAdapter;
    Button buttonAddRandomQuestion;
    Context context;
    ArrayList<ContentQuestions>  contentQuestionsList = new ArrayList<>();
    String[] questions = {"Who is your secret crush?",
            "You do want me to put this as my status?",
            "Words about me",
            "Worst thing that ever happened to you?",
            "Your dream?",
            "Your favorite artist and why?",
            "My name on your phone?",
            "What would be your reaction if i die?",
            "Relation between you and me?",
            "A song you want to dedicate me?",
            "Something you like in me?",
            "Something you like in me?"};
    SQLiteDBHelper database;
    int questionID;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();
    }
    public void init() {
        context = this;
        database = new SQLiteDBHelper(context);
        Intent intent = getIntent();
        userID = intent.getIntExtra("Key", 0);
        retrieveQuestion();
        recyclerView = findViewById(R.id.recyclerViewQuestions);
        recyclerView.hasFixedSize();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerViewAdapterForQuestions(context, R.layout.row_slam_questions, contentQuestionsList);
        recyclerView.setAdapter(recyclerAdapter);

        buttonAddRandomQuestion = findViewById(R.id.buttonAddQuestion);
        buttonAddRandomQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int rand = (int) (Math.random() * questions.length);
                insertNewQuestion(rand);
            }
        });
        recyclerAdapter.setCustomOnItemClickListener(new RecyclerViewAdapterForQuestions.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openQuestion(String.valueOf((position+1)));
//                openQuestion(contentQuestionsList.get(position).getQuestion());
            }
        });
    }

    private void insertNewQuestion(int rand) {
        if(database.insertQuestion(questions[rand], userID)){
            contentQuestionsList.add(0, new ContentQuestions(R.drawable.ic_launcher_foreground,questions[rand]));
            recyclerAdapter.notifyItemInserted(0);
            layoutManager.scrollToPosition(0);
            Toast.makeText(context, "new question added", Toast.LENGTH_SHORT).show();
        }else Toast.makeText(context, "question adding failed", Toast.LENGTH_SHORT).show();
    }

    private void retrieveQuestion(){
        Cursor result = database.selectQuestionByUserID(String.valueOf(userID));
        if(result.getCount()==0) Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
        else{while (result.moveToNext()) contentQuestionsList.add(new ContentQuestions(R.drawable.ic_launcher_background,result.getString(1)));}
    }
    private void openQuestion(String input){
        Cursor result = database.selectQuestionByID(input);
        if(result.getCount()==0){
            Toast.makeText(context, "question doesn't exist", Toast.LENGTH_SHORT).show();
        } else {
            while (result.moveToNext()){
                startIntent(result.getString(0),result.getString(1), String.valueOf(userID));
            }
        }
    }
    private void startIntent(String uno, String dos, String tres){
        Intent intent = new Intent(context, AnswersActivity.class);
        intent.putExtra("ID",Integer.parseInt(uno));
        intent.putExtra("question",dos);
        intent.putExtra("user_id", Integer.parseInt(tres));
        startActivity(intent);
    }
}