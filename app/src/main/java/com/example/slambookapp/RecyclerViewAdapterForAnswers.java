package com.example.slambookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterForAnswers extends RecyclerView.Adapter<RecyclerViewAdapterForAnswers.ViewHolder> {
    Context context;
    int layout;
    ArrayList<ContentAnswers> contentAnswersList;
    OnItemLongClickListener customListener;

    public RecyclerViewAdapterForAnswers(Context context, int layout, ArrayList<ContentAnswers> contentAnswersList) {
        this.context = context;
        this.layout = layout;
        this.contentAnswersList = contentAnswersList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForAnswers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ContentAnswers oneLine = contentAnswersList.get(position);
        viewHolder.image.setImageResource(oneLine.getImage());
        viewHolder.answer.setText(oneLine.getAnswer());
        viewHolder.name.setText(oneLine.getName());
    }

    @Override
    public int getItemCount() {
        return contentAnswersList.size();
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setCustomOnItemClickListener(OnItemLongClickListener listenerFromActivity) {
        customListener = listenerFromActivity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView answer;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            this.image = convertView.findViewById(R.id.row_slam_image);
            this.name = convertView.findViewById(R.id.row_slam_name);
            this.answer = convertView.findViewById(R.id.row_slam_answer);

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(customListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            customListener.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });

        }
    }
}
