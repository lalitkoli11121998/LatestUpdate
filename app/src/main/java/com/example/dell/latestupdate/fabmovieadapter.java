package com.example.dell.latestupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 4/12/2018.
 */

public class fabmovieadapter extends RecyclerView.Adapter<fabmovieadapter.UserViewHolder> {

    boolean st = true;
    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<favourite> users=new ArrayList<>();
    Context context;
    fabmovieadapter.OnItemClickListener listener;

    public fabmovieadapter(Context context, ArrayList<favourite> users, OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public fabmovieadapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user,parent,false);
        fabmovieadapter.UserViewHolder holder = new fabmovieadapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final fabmovieadapter.UserViewHolder holder, int position) {
        final favourite movie= users.get(position);
        holder.title.setText(movie.title);
        String s =movie.poster_path;
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+s).into(holder.avatar);
        holder.iconimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{


        ImageView iconimage2;
        TextView title;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            iconimage2 = itemView.findViewById(R.id.iconimage);
            title = itemView.findViewById(R.id.textView);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}


