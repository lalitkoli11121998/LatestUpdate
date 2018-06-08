package com.example.dell.latestupdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dell on 3/26/2018.
 */

public class overviewAdapter extends RecyclerView.Adapter<overviewAdapter.UserViewHolder> {

interface OnItemClickListener {

    void onItemClick(int position);
}

    ArrayList<String> users=new ArrayList<>();
    Context context;
    overviewAdapter.OnItemClickListener listener;

    public overviewAdapter(Context context, ArrayList<String> users, overviewAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public overviewAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user2,parent,false);
        overviewAdapter.UserViewHolder holder = new overviewAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final overviewAdapter.UserViewHolder holder, int position) {
        String S = users.get(position);
        holder.title.setText(S);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return  users.size();
    }

class UserViewHolder extends RecyclerView.ViewHolder{

    TextView title;
    View itemView;

    public UserViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        title = itemView.findViewById(R.id.textView5);
    }
}
}
