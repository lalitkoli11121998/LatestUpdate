package com.example.dell.latestupdate;

import android.content.Context;
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
 * Created by dell on 4/1/2018.
 */

public class circleAdapter extends RecyclerView.Adapter<circleAdapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<CastingM> users=new ArrayList<>();
    Context context;
    circleAdapter.OnItemClickListener listener;

    public circleAdapter(Context context, ArrayList<CastingM> users, circleAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public circleAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.circle_user,parent,false);
        circleAdapter.UserViewHolder holder = new circleAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final circleAdapter.UserViewHolder holder, int position) {
        CastingM castingM = users.get(position);
        holder.title.setText(castingM.character);
        holder.name.setText(castingM.name);
        String s =castingM.profile_path;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+s).into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView name;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.circle_text);
            name = itemView.findViewById(R.id.name_text);
            avatar = itemView.findViewById(R.id.CimageView);
        }
    }
}
