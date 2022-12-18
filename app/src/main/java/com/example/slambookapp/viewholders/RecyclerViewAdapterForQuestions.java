package com.example.slambookapp.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slambookapp.R;
import com.example.slambookapp.classes.ContentQuestions;

import java.util.ArrayList;

public class RecyclerViewAdapterForQuestions extends RecyclerView.Adapter<RecyclerViewAdapterForQuestions.ViewHolder> {
    Context context;
    int layout;
    ArrayList<ContentQuestions> contentQuestionsList;

    public RecyclerViewAdapterForQuestions(Context context, int layout, ArrayList<ContentQuestions> contentQuestionsList) {
        this.context = context;
        this.layout = layout;
        this.contentQuestionsList = contentQuestionsList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForQuestions.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContentQuestions oneLine = contentQuestionsList.get(position);
        holder.image.setImageResource(oneLine.getImage());
        holder.question.setText(oneLine.getQuestion());
    }

    @Override
    public int getItemCount() {return contentQuestionsList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView question;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.row_slam_image);
            this.question = itemView.findViewById(R.id.row_slam_question);
        }
    }
}
