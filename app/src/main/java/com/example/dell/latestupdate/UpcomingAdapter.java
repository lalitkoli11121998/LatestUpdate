package com.example.dell.latestupdate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.provider.ContactsContract;
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
 * Created by dell on 3/24/2018.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UserViewHolder> {

    boolean st = true;
interface OnItemClickListener {

    void onItemClick(int position);
}

    ArrayList<movie> users=new ArrayList<>();
    Context context;
    UpcomingAdapter.OnItemClickListener listener;

    public UpcomingAdapter(Context context, ArrayList<movie> users, UpcomingAdapter.OnItemClickListener listener){
        this.users = users;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UpcomingAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_user,parent,false);
        UpcomingAdapter.UserViewHolder holder = new UpcomingAdapter.UserViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UpcomingAdapter.UserViewHolder holder, int position) {
        final movie movie = users.get(position);
        holder.title.setText(movie.title);
        String s =movie.backdrop_path;
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

                st = false;
                holder.iconimage2.setColorFilter(R.color.colorAccent);
                ViewOpenHelper openHelper = new ViewOpenHelper(context);
                SQLiteDatabase database = openHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.Expenses.TITLE,movie.title);
                contentValues.put(Contract.Expenses.poster_path,movie.poster_path);
                contentValues.put(Contract.Expenses.ID,movie.id);
                contentValues.put(Contract.Expenses.overview , movie.overview);
                contentValues.put(Contract.Expenses.vote_avarage , movie.vote_average);
                long id = database.insert(Contract.Expenses.TABLE_NAME,null,contentValues);
                String t = movie.title;
                Toast.makeText(context,t +"add to favourite" , Toast.LENGTH_SHORT).show();
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
