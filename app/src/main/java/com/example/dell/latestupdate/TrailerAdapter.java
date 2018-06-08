package com.example.dell.latestupdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 3/26/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.UserViewHolder> {


    interface OnItemClickListener {

        void onItemClick(int position);
    }

    TrailerAdapter.OnItemClickListener listener;
    ArrayList<video> users=new ArrayList<>();
    Context context;


    public TrailerAdapter(Context context, ArrayList<video> users, TrailerAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public TrailerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user3,parent,false);
        TrailerAdapter.UserViewHolder holder = new TrailerAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TrailerAdapter.UserViewHolder holder, final int position) {
        video Video = users.get(position);
        holder.title.setText(Video.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        Log.d("key1" , Video.key);
        Picasso.get().load("https://img.youtube.com/vi/"+Video.key+"/mqdefault.jpg").into(holder.avatar);
    }
//https://img.youtube.com/vi/GpAuCG6iUcA/mqdefault.jpg
    //https://img.youtube.com/vi/GpAuCG6iUcA/mqdefault.jpg
    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.trailertext);
            avatar = itemView.findViewById(R.id.trailerimage);
        }
    }
}
