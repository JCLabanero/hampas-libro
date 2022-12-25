package com.example.slambookapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.slambookapp.R;
import com.example.slambookapp.classes.ContentUsers;
import com.example.slambookapp.database.SQLiteDBHelper;
import com.example.slambookapp.viewholders.RecyclerViewAdapterForUser;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterForUser recyclerAdapter;
    Context context;
    ArrayList<ContentUsers>  contentUsersList = new ArrayList<>();
    SQLiteDBHelper database;
    int questionID;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();
    }
    public void init() {
        context = this;
        database = new SQLiteDBHelper(context);
        Intent intent = getIntent();
        userID = intent.getIntExtra("id", 0);
        retrieveUsers();
        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.hasFixedSize();
        layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerViewAdapterForUser(context,R.layout.row_users,contentUsersList);
        recyclerView.setAdapter(recyclerAdapter);
    }
    private void retrieveUsers(){
        Cursor result = database.selectUserAndDisregard(String.valueOf(userID));
        if(result.getCount()==0) Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
        else{while (result.moveToNext()) contentUsersList.add(new ContentUsers(R.drawable.ic_launcher_background,result.getString(1),result.getString(0),result.getString(2)));}
    }
    private void startIntent(String uno, String dos, String tres){
        Intent intent = new Intent(context, AnswersActivity.class);
        intent.putExtra("ID",Integer.parseInt(uno));
        intent.putExtra("question",dos);
        intent.putExtra("user_id", Integer.parseInt(tres));
        startActivity(intent);
    }
}