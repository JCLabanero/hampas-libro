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
    OnItemClickListener customListener;

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
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForQuestions.ViewHolder holder, int position) {
        ContentQuestions oneLine = contentQuestionsList.get(position);
        holder.image.setImageResource(oneLine.getImage());
        holder.question.setText(oneLine.getQuestion());
    }

    @Override
    public int getItemCount() {return contentQuestionsList.size();}

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setCustomOnItemClickListener(OnItemClickListener listenerFromActivity){
        customListener = listenerFromActivity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView question;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.row_slam_image);
            this.question = itemView.findViewById(R.id.row_slam_question);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(customListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            customListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
