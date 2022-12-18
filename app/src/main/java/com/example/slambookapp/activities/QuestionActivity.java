package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.example.slambookapp.R;
import com.example.slambookapp.classes.ContentQuestions;
import com.example.slambookapp.viewholders.RecyclerViewAdapterForQuestions;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterForQuestions recyclerAdapter;
    Button buttonAddRandomQuestion;
    Context context = this;
    ArrayList<ContentQuestions>  contentQuestionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        init();
    }
    public void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerViewAdapterForQuestions(context,R.layout.row_slam_questions,contentQuestionsList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}