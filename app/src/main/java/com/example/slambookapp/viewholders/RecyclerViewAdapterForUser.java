package com.example.slambookapp.viewholders;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slambookapp.R;
import com.example.slambookapp.activities.QuestionActivity;
import com.example.slambookapp.classes.ContentUsers;

import java.util.ArrayList;

public class RecyclerViewAdapterForUser extends RecyclerView.Adapter<RecyclerViewAdapterForUser.ViewHolder> {
    Context context;
    int layout, userID;
    ArrayList<ContentUsers> contentUsersList;

    public RecyclerViewAdapterForUser(Context context, int layout, ArrayList<ContentUsers> contentUsersList,int userID) {
        this.context = context;
        this.layout = layout;
        this.contentUsersList = contentUsersList;
        this.userID = userID;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(layout,parent,false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForUser.ViewHolder holder, int position) {
        ContentUsers oneLine = contentUsersList.get(position);
        holder.image.setImageResource(oneLine.getImage());
        holder.username.setText(oneLine.getUsername());
        holder.id.setText(oneLine.getId());
        holder.email.setText(oneLine.getEmail());
        holder.buttonAnswerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.buttonAnswerUser.getContext(), QuestionActivity.class);
                intent.putExtra("Key",Integer.parseInt(oneLine.getId()));
                intent.putExtra("KeyOrigUser",userID);
                holder.buttonAnswerUser.getContext().startActivity(intent);
            }
        });
    }

    public interface ClickListener{void onClick(int position);}

    @Override
    public int getItemCount() {return contentUsersList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView username,id,email;
        Button buttonAnswerUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.row_users_profile);
            this.username = itemView.findViewById(R.id.row_users_username);
            this.id = itemView.findViewById(R.id.row_users_id);
            this.email = itemView.findViewById(R.id.row_users_email);
            this.buttonAnswerUser = itemView.findViewById(R.id.row_users_button);
        }
    }
}
