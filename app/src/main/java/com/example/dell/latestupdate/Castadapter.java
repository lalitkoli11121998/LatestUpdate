package com.example.dell.latestupdate;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 4/4/2018.
 */

public class Castadapter extends RecyclerView.Adapter<Castadapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<movie> users=new ArrayList<>();
    Context context;
    Castadapter.OnItemClickListener listener;

    public Castadapter(Context context, ArrayList<movie> users, Castadapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Castadapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.cast_user,parent,false);
        Castadapter.UserViewHolder holder = new Castadapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Castadapter.UserViewHolder holder, int position) {
        movie movie = users.get(position);
        ArrayList<String> n = new ArrayList<>();
        n.add(movie.title);
        Bioadapter bioadapter = new Bioadapter(context, n, new Bioadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        holder.r.setAdapter(bioadapter);
        bioadapter.notifyDataSetChanged();
        holder.r.setLayoutManager( new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false));
        holder.character.setText(movie.character);
        String s =movie.backdrop_path;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+s).resize(350, 500).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        RecyclerView r;
        TextView character;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            r = itemView.findViewById(R.id.recycle1);
            character =itemView.findViewById(R.id.textView2);
            avatar = itemView.findViewById(R.id.avatar2);
        }
    }
}
