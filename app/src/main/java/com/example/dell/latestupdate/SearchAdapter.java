package com.example.dell.latestupdate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 4/7/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.UserViewHolder> {

    interface OnItemClickListener {

        void onItemClick(int position);
    }

    ArrayList<Similiar> users = new ArrayList<>();
    Context context;
    SearchAdapter.OnItemClickListener listener;

    public SearchAdapter(Context context, ArrayList<Similiar> users, SearchAdapter.OnItemClickListener listener) {
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public SearchAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.searchlayout, parent, false);
        SearchAdapter.UserViewHolder holder = new SearchAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SearchAdapter.UserViewHolder holder, int position) {
        Similiar similiar = users.get(position);

        holder.t1.setText(similiar.original_title);
        holder.t2.setText("Movie");
        holder.t3.setText(similiar.overview);
        String s = similiar.backdrop_path;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + s).fit().into(holder.avatar);

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView t1, t2,t3;
        ImageView avatar;
        View itemView;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            t1 = itemView.findViewById(R.id.textView15);
            t2 = itemView.findViewById(R.id.textView16);
            t3 = itemView.findViewById(R.id.textView17);
            avatar = itemView.findViewById(R.id.imageView18);
        }
    }
}
